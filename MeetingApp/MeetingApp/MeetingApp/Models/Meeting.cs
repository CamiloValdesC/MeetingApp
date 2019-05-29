using Newtonsoft.Json;
using System;
using System.Collections.Generic;

namespace MeetingApp.Models
{
    public class Meeting
    {

        [JsonProperty(PropertyName = "idMeeting")]
        public string IdMeeting { get; set; }

        [JsonProperty(PropertyName = "descripcion")]
        public string Descripcion { get; set; }

        [JsonProperty(PropertyName = "ubicacion")]
        public string Ubicacion { get; set; }

        [JsonProperty(PropertyName = "fechaHoraIni")]
        public DateTime? FechaHoraIni { get; set; }

        [JsonProperty(PropertyName = "fechaHoraFin")]
        public DateTime? FechaHoraFin { get; set; }

        [JsonProperty(PropertyName = "participante")]
        public List<Participante> Participante { get; set; }
    }
}
