namespace MeetingApp.ViewModels
{
    using GalaSoft.MvvmLight.Command;
    using Helpers;
    using System.Windows.Input;
    using Views;
    using Xamarin.Forms;

    public class MenuItemViewModel
    {
        #region Properties
        public string Icon { get; set; }
        public string Title { get; set; }
        public string PageName { get; set; }
        #endregion

        #region Commands
        public ICommand NavigateCommand
        {
            get
            {
                return new RelayCommand(Navigate);
            }
        }

        private void Navigate()
        {
            if (this.PageName == "LoginPage")
            {
                Settings.UserToken = string.Empty;
                var mainViewModel = MainViewModel.GetInstance();
                mainViewModel.UserToken = string.Empty;
                Application.Current.MainPage = new NavigationPage(new LoginPage());
            }
        }
        #endregion
    }
}
