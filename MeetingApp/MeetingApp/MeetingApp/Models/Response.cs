namespace MeetingApp.Models
{

    /// <summary>
    ///     CLASE PARA CONTROLAR RESPUESTAS DEL CONSUMO DE UNA API
    /// </summary>
    public class Response
    {

        /// <summary>
        ///     ESTADO DEL CONSUMO
        /// </summary>
        public bool IsSuccess { get; set; }

        /// <summary>
        ///     MENSAJE DE RETORNO EN CASO DE ERROR
        /// </summary>
        public string Message { get; set; }

        /// <summary>
        ///     RETORNO DEL OBJETO CONSUMIDO
        /// </summary>
        public object Result { get; set; }

    }
}
