namespace MeetingApp.ViewModels
{
    using GalaSoft.MvvmLight.Command;
    using Helpers;
    using System.Windows.Input;
    using ViewsModels;
    using Xamarin.Forms;

    public class RegisterViewModel : BaseViewModel
    {

        #region Attributes
        private string newUsername;
        private string newEmail;
        private bool isEnabled;
        private bool isRunning;
        #endregion

        #region Properties
        public string NewUsername
        {
            get { return this.newUsername; }
            set { this.SetValue(ref this.newUsername, value); }
        }
        public string NewEmail
        {
            get { return this.newEmail; }
            set { this.SetValue(ref this.newEmail, value); }
        }
        public bool IsEnabled
        {
            get { return this.isEnabled; }
            set { this.SetValue(ref this.isEnabled, value); }
        }
        public bool IsRunning
        {
            get { return this.isRunning; }
            set { this.SetValue(ref this.isRunning, value); }
        }
        #endregion

        #region Constructors
        public RegisterViewModel()
        {
            this.IsEnabled = true;
            this.IsRunning = false;
        }
        #endregion

        #region Commands
        public ICommand RegisterCommand
        {
            get
            {
                return new RelayCommand(RegisterNewUser);
            }
        }
        #endregion

        #region Methods
        private async void RegisterNewUser()
        {
            if (string.IsNullOrEmpty(this.NewUsername))
            {
                await Application.Current.MainPage.DisplayAlert(
                    "Error",
                    "Debe ingresar un Nombre de Usuario",
                    "Aceptar"
                    );
                return;
            }

            if (string.IsNullOrEmpty(this.NewEmail))
            {
                await Application.Current.MainPage.DisplayAlert(
                    "Error",
                    "Debe ingresar un Correo Electrónico",
                    "Aceptar"
                    );
                return;
            }

            if (RegexUtilities.IsValidEmail(this.NewEmail))
            {
                await Application.Current.MainPage.DisplayAlert(
                    "Error",
                    "El formato del Correo Electrónico no es válido",
                    "Aceptar"
                    );
                return;
            }

            this.IsRunning = true;
        }
        #endregion
    }
}
