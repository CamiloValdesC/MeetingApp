namespace MeetingApp.Infrastructure
{
    using ViewModels;

    public class InstanceLocator
    {
        #region Properties
        public MainViewModel Main { get; set; }
        #endregion

        #region Constructors
        public InstanceLocator()
        {
            // Instancia la propiedad Main con la Clase MainViewModel
            this.Main = new MainViewModel();
        }
        #endregion

    }
}
