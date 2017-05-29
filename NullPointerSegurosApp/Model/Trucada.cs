using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NullPointerSegurosApp.Model
{
    public class Trucada
    {
      

        public Trucada(int numero, DateTime dataHora, string descripcio, string personaContacte)
        {
            Numero = numero;
            DataHora = dataHora;
            Descripcio = descripcio;
            PersonaContacte = personaContacte;
        }

        public int Numero { get; set; }

        public DateTime DataHora { get; set; }

        public string Descripcio { get; set; }

        public string PersonaContacte { get; set; }

    }
}
