using Newtonsoft.Json;

namespace MeetingApp.Models
{
    public class Persona
    {
        [JsonProperty(PropertyName = "run")]
        public int Run { get; set; }

        [JsonProperty(PropertyName = "dv")]
        public string Dv { get; set; }

        [JsonProperty(PropertyName = "nombre")]
        public string Nombre { get; set; }

        [JsonProperty(PropertyName = "paterno")]
        public string Paterno { get; set; }

        [JsonProperty(PropertyName = "materno")]
        public string Materno { get; set; }

        [JsonProperty(PropertyName = "correo")]
        public string Correo { get; set; }

        [JsonProperty(PropertyName = "response")]
        public int Response { get; set; }
    }
}
