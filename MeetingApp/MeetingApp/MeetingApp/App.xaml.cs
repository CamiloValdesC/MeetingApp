using MeetingApp.Helpers;
using MeetingApp.ViewModels;
using MeetingApp.Views;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

[assembly: XamlCompilation(XamlCompilationOptions.Compile)]
namespace MeetingApp
{
    public partial class App : Application
    {
        #region Properties
        public static NavigationPage Navigator
        {
            get;
            internal set;
        }
        #endregion

        #region Constructors
        public App()
        {
            InitializeComponent();

            if (string.IsNullOrEmpty(Settings.UserToken))
            {
                this.MainPage = new NavigationPage(new LoginPage());
            }
            else
            {
                var mainViewModel = MainViewModel.GetInstance();
                mainViewModel.UserToken = Settings.UserToken;
                mainViewModel.Board = new BoardViewModel();
                this.MainPage = new MasterPage();
            }
            
        }
        #endregion

        #region Methods
        protected override void OnStart()
        {
            // Handle when your app starts
        }

        protected override void OnSleep()
        {
            // Handle when your app sleeps
        }

        protected override void OnResume()
        {
            // Handle when your app resumes
        } 
        #endregion
    }
}
