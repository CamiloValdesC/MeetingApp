namespace MeetingApp.Helpers
{
    using Plugin.Settings;
    using Plugin.Settings.Abstractions;

    public static class Settings
    {
        static ISettings AppSettings
        {
            get
            {
                return CrossSettings.Current;
            }
        }

        const string userToken = "userToken";
        static readonly string stringDefault = string.Empty;

        public static string UserToken
        {
            get
            {
                return AppSettings.GetValueOrDefault(userToken, stringDefault);
            }
            set
            {
                AppSettings.AddOrUpdateValue(userToken, value);
            }
        }

    }
}
