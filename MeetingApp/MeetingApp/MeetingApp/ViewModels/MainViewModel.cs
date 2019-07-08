namespace MeetingApp.ViewModels
{
    using Models;
    using System.Collections.Generic;
    using System.Collections.ObjectModel;

    public class MainViewModel
    {
        #region Properties
        public List<Meeting> MeetingsList { get; set; }
        public ObservableCollection<MenuItemViewModel> MenuList { get; set; }
        public string UserToken { get; set; }
        #endregion

        #region ViewModels
        public LoginViewModel Login { get; set; }
        public BoardViewModel Board { get; set; }
        public MeetingViewModel Meeting { get; set; }

        public RegisterViewModel Register { get; set; }
        #endregion

        #region Constructors
        public MainViewModel()
        {
            instance = this;
            this.Login = new LoginViewModel();
            this.LoadMenu();
        }
        #endregion

        #region Singleton
        private static MainViewModel instance;
        public static MainViewModel GetInstance()
        {
            if (instance == null)
            {
                return new MainViewModel();
            }

            return instance;
        }
        #endregion

        #region Methods
        private void LoadMenu()
        {
            this.MenuList = new ObservableCollection<MenuItemViewModel>();

            this.MenuList.Add(new MenuItemViewModel
            {
                Icon = "ic_person",
                Title = "Profile",
                PageName = "PerfilPage"
            });

            this.MenuList.Add(new MenuItemViewModel
            {
                Icon = "ic_playlist_add",
                Title = "New Meeting",
                PageName = "MeetingPage"
            });

            this.MenuList.Add(new MenuItemViewModel
            {
                Icon = "ic_exit_to_app",
                Title = "Logout",
                PageName = "LoginPage"
            });
        }
        #endregion
    }
}
