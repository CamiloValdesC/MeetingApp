namespace MeetingApp.ViewModels
{
    using GalaSoft.MvvmLight.Command;
    using Models;
    using System.Windows.Input;
    using Views;

    public class MeetingItemViewModel : Meeting
    {
        #region Commands
        public ICommand SelectMeetingCommand
        {
            get
            {
                return new RelayCommand(SelectMeeting);
            }
        }

        private async void SelectMeeting()
        {
            MainViewModel.GetInstance().Meeting = new MeetingViewModel(this);
            //await Application.Current.MainPage.Navigation.PushAsync(new MeetingTabbedPage());
            await App.Navigator.PushAsync(new MeetingTabbedPage());
        }
        #endregion

    }
}
