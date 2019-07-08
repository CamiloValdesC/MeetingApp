using Newtonsoft.Json;

namespace MeetingApp.Models
{
    public class Perfil
    {
        [JsonProperty(PropertyName = "descripcion")]
        public string Descripcion { get; set; }
    }
}
