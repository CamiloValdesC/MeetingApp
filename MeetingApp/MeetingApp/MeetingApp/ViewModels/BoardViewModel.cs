namespace MeetingApp.ViewModels
{
    using GalaSoft.MvvmLight.Command;
    using Helpers;
    using Views;
    using Models;
    using Services;
    using System.Collections.Generic;
    using System.Collections.ObjectModel;
    using System.Linq;
    using System.Windows.Input;
    using ViewsModels;
    using Xamarin.Forms;

    public class BoardViewModel : BaseViewModel
	{
        #region Services
        private ApiService apiService;
        #endregion

        /********************************************************************************************************************/

        #region Attributes
        private ObservableCollection<MeetingItemViewModel> meetings;
        private bool isRefreshing;
        private string filter;
        #endregion

        /********************************************************************************************************************/

        #region Properties
        public ObservableCollection<MeetingItemViewModel> Meetings
        {
            get { return this.meetings; }
            set { this.SetValue(ref this.meetings, value); }
        }
        public bool IsRefreshing
        {
            get { return this.isRefreshing; }
            set { this.SetValue(ref this.isRefreshing, value); }
        }
        public string Filter
        {
            get { return this.filter; }
            set
            {
                this.SetValue(ref this.filter, value);
                this.Search();
            }
        }
        public string Username { get; set; }
        #endregion

        /********************************************************************************************************************/

        #region Constructors
        public BoardViewModel()
        {
            this.Username = MainViewModel.GetInstance().UserToken;
            this.apiService = new ApiService();
            this.LoadMeetings();
        }
        #endregion

        /********************************************************************************************************************/

        #region Commands
        public ICommand RefreshCommand
        {
            get
            {
                return new RelayCommand(LoadMeetings);
            }
        }
        public ICommand SearchCommand
        {
            get
            {
                return new RelayCommand(Search);
            }
        }
        #endregion

        /********************************************************************************************************************/

        #region Methods
        private async void LoadMeetings()
        {
            this.IsRefreshing = true;
            var connection = await this.apiService.CheckConnection();

            if (!connection.IsSuccess)
            {
                this.IsRefreshing = false;
                await Application.Current.MainPage.DisplayAlert (
                                                                    "Error",
                                                                    connection.Message,
                                                                    "OK"
                                                                );
                //Limpia Persistencia - Retorna a LoginPage
                Settings.UserToken = string.Empty;
                var mainViewModel = MainViewModel.GetInstance();
                mainViewModel.UserToken = string.Empty;
                Application.Current.MainPage = new NavigationPage(new LoginPage());
                return;
            }

            var response = await this.apiService.GetList<Meeting>   (
                                                                        "http://186.64.122.131:8080",
                                                                        "/MeetingServices",
                                                                        "/usuario/meeting/" + this.Username
                                                                    );

            if (!response.IsSuccess)
            {
                this.IsRefreshing = false;
                await Application.Current.MainPage.DisplayAlert (
                                                                    "Error",
                                                                    response.Message,
                                                                    "OK"
                                                                );
                return;
            }

            if (!string.IsNullOrEmpty(this.Filter))
            {
                this.Filter = "";
            }

            MainViewModel.GetInstance().MeetingsList = (List<Meeting>) response.Result;
            this.Meetings = new ObservableCollection<MeetingItemViewModel>(this.ToMeetingItemViewModel());
            this.IsRefreshing = false;

        }

        private IEnumerable<MeetingItemViewModel> ToMeetingItemViewModel()
        {
           return MainViewModel.GetInstance().MeetingsList.Select(m => new MeetingItemViewModel
           {
                IdMeeting       = m.IdMeeting,
                Descripcion     = m.Descripcion,
                Ubicacion       = m.Ubicacion,
                FechaHoraIni    = m.FechaHoraIni,
                FechaHoraFin    = m.FechaHoraFin,
                Participante    = m.Participante
           });
        }

        private void Search()
        {
            if (string.IsNullOrEmpty(this.Filter))
            {
                this.Meetings = new ObservableCollection<MeetingItemViewModel>(this.ToMeetingItemViewModel());
            }
            else
            {
                this.Meetings = new ObservableCollection<MeetingItemViewModel>(
                    this.ToMeetingItemViewModel().Where(
                        m => m.Descripcion.ToLower().Contains(this.Filter.ToLower()) ||
                             m.Ubicacion.ToLower().Contains(this.Filter.ToLower())
                    ));
            }
        }
        #endregion

    }
}