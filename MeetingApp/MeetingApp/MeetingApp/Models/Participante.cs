namespace MeetingApp.Models
{
    using Newtonsoft.Json;
    using System;
    using System.Globalization;

    public class Participante
    {
        [JsonProperty(PropertyName = "run")]
        public int Run { get; set; }

        [JsonProperty(PropertyName = "dv")]
        public string Dv { get; set; }

        [JsonProperty(PropertyName = "nombre")]
        public string Nombre { get; set; }

        [JsonProperty(PropertyName = "apellido")]
        public string Apellido { get; set; }

        [JsonProperty(PropertyName = "correo")]
        public string Correo { get; set; }

        [JsonProperty(PropertyName = "fono")]
        public string Fono { get; set; }

        public string RunCompleto {
            get
            {
                CultureInfo elGR = CultureInfo.CreateSpecificCulture("el-GR");
                return String.Format(elGR, "{0:0,0}", this.Run) + "-" + this.Dv;
            }
        }
    }
}
