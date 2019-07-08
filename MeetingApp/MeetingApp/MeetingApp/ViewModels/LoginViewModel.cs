namespace MeetingApp.ViewModels
{
    using GalaSoft.MvvmLight.Command;
    using Helpers;
    using Services;
    using Models;
    using System.Windows.Input;
    using Views;
    using ViewsModels;
    using Xamarin.Forms;

    public class LoginViewModel : BaseViewModel
    {

        #region Services
        private ApiService apiService;
        #endregion

        /********************************************************************************************************************/

        #region Attributes
        //Solo propiedades que se cambian en tiempo de ejecución
        private string username;
        private string password;
        private bool isRunning;
        private bool isRemembered;
        private bool isEnabled;
        #endregion

        /********************************************************************************************************************/

        #region Properties
        public string Username
        {
            get { return this.username; }
            set { this.SetValue(ref this.username, value); }
        }
        public string Password
        {
            get { return this.password;  }
            set { this.SetValue(ref this.password, value); }
        }
        public bool IsRunning
        {
            get { return this.isRunning; }
            set { this.SetValue(ref this.isRunning, value); }
        }
        public bool IsRemembered
        {
            get { return this.isRemembered; }
            set { this.SetValue(ref this.isRemembered, value); }
        }
        public bool IsEnabled
        {
            get { return this.isEnabled; }
            set { this.SetValue(ref this.isEnabled, value); }
        }
        #endregion

        /********************************************************************************************************************/

        #region Constructors
        public LoginViewModel()
        {
            this.apiService = new ApiService();

            this.IsRemembered = true;
            this.IsRunning = false;
            this.IsEnabled = true;
        }
        #endregion

        /********************************************************************************************************************/

        #region Commands
        public ICommand IngresarCommand
        {
            get
            {
                return new RelayCommand(LoginUser);
            }
        }
        public ICommand RegisterCommand
        {
            get
            {
                return new RelayCommand(RegisterUser);
            }
        }
        public ICommand DebugCommand
        {
            get
            {
                return new RelayCommand(DebugMethod);
            }
        }
        #endregion

        /********************************************************************************************************************/

        #region Methods
        private async void LoginUser()
        {
            if (string.IsNullOrEmpty(this.Username))
            {
                await Application.Current.MainPage.DisplayAlert(
                    "Error",
                    "Debe ingresar un Usuario",
                    "Aceptar"
                    );
                return;
            }

            if (string.IsNullOrEmpty(this.Password))
            {
                await Application.Current.MainPage.DisplayAlert(
                        "Error",
                        "Debe ingresar una Contraseña",
                        "Aceptar"
                        );
                return;
            }


            this.IsRunning = true;
            this.IsEnabled = false;

            /*********************************************************************************************/

            this.LoadLogin(this.Username, this.Password);

            /*********************************************************************************************/
        }

        private void CleanVarVisuals()
        {
            this.IsRunning = false;
            this.IsEnabled = true;
            //Limpiar Formulario Login
            this.Username = string.Empty;
            this.Password = string.Empty;
        }

        public void LoginOK()
        {
            var mainViewModel = MainViewModel.GetInstance();
            
            /** GUARDA USUARIO EN MEMORIA  */
            mainViewModel.UserToken = this.Username;

            /** GUARDAR EN PERSISTENCIA VALUE USERNAME */
            if (this.IsRemembered)
            {
                Settings.UserToken = this.Username;
            }
            
            /** CARGA ELEMENTOS BOARD_PAGE */
            mainViewModel.Board = new BoardViewModel();

            //await Application.Current.MainPage.Navigation.PushAsync(new NavigationPage(new BoardPage()));
            //await Application.Current.MainPage.Navigation.PushAsync(new MasterPage());
            Application.Current.MainPage = new MasterPage();
        }

        private void DebugMethod()
        {
            this.Username = "Antonia";
            this.Password = "@nt0n1@";
        }
       
        private async void RegisterUser()
        {
            MainViewModel.GetInstance().Register = new RegisterViewModel();
            await Application.Current.MainPage.Navigation.PushAsync(new RegisterPage());
        }
        #endregion

        /*********************************************************************************************/

        #region ApiServicesLogin
        public async void LoadLogin(string username, string password)
        {            
            var connection = await apiService.CheckConnection();

            //VALIDAR CONEXION Y SALIDA A INTERNET
            if (!connection.IsSuccess)
            {
                this.IsRunning = false;
                this.IsEnabled = true;
                await Application.Current.MainPage.DisplayAlert (
                                                                    "Error Connection",
                                                                    connection.Message,
                                                                    "OK"
                                                                );
                //Volver atrás (PopAsync)
                //await Application.Current.MainPage.Navigation.PopAsync();
                //Application.Current.MainPage = new NavigationPage(new LoginPage());
                return;
            }

            //CONSUMIR REST
            var response = await apiService.Get<Persona>(
                                                        "http://186.64.122.131:8080",
                                                        "/MeetingServices",
                                                        "/usuario/" + username + "/" + password);
            //VALIDAR STATUS REST
            if (!response.IsSuccess)
            {
                this.IsRunning = false;
                this.IsEnabled = true;
                await Application.Current.MainPage.DisplayAlert (
                                                                    "Error Server",
                                                                    response.Message,
                                                                    "OK"
                                                                );
                //await Application.Current.MainPage.Navigation.PopAsync();
                //Application.Current.MainPage = new NavigationPage(new LoginPage());
                return;
            }
            
            //CARGO RESULTADO EN UN LIST TIPO USUARIO
            Persona persona = (Persona) response.Result;

            if (persona.Response == 0)
            {
                await Application.Current.MainPage.DisplayAlert (
                                                                    "Error",
                                                                    "Datos ingresados son incorrectos...",
                                                                    "Aceptar"
                                                                );
                //await Application.Current.MainPage.Navigation.PopAsync();
                //Application.Current.MainPage = new NavigationPage(new LoginPage());
                this.Password = string.Empty;
                this.IsRunning = false;
                this.IsEnabled = true;
                return;
            }

            LoginOK();
            CleanVarVisuals();

        }
        #endregion

    }
}
