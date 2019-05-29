namespace MeetingApp.ViewModels
{
    using Models;
    using System.Collections.ObjectModel;
    using ViewsModels;

    public class MeetingViewModel : BaseViewModel
    {
        #region Attributes
        private ObservableCollection<Participante> participantes;
        #endregion

        #region Properties
        public Meeting Meeting { get; set; }

        public ObservableCollection<Participante> Participantes
        {
            get { return this.participantes; }
            set { this.SetValue(ref this.participantes, value);  }
        }
        #endregion

        #region Constructors
        public MeetingViewModel(Meeting meeting)
        {
            this.Meeting = meeting;
            this.Participantes = new ObservableCollection<Participante>(this.Meeting.Participante);
        }
        #endregion

        #region Methods
        
        #endregion
    }
}
