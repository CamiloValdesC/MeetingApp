using Newtonsoft.Json;
using System.Collections.Generic;

namespace MeetingApp.Models
{
    public class Usuario
    {
        public string _id { get; set; }

        [JsonProperty(PropertyName = "username")]
        public string Username { get; set; }

        [JsonProperty(PropertyName = "password")]
        public string Password { get; set; }

        [JsonProperty(PropertyName = "persona")]
        public Persona Persona { get; set; }

        [JsonProperty(PropertyName = "perfil")]
        public Perfil Perfil { get; set; }

        [JsonProperty(PropertyName = "meeting")]
        public List<Meeting> Meeting { get; set; }
    }
}
