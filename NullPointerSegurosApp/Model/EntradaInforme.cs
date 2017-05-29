using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NullPointerSegurosApp.Model
{
    public class EntradaInforme
    {
       

        public EntradaInforme(int numero, DateTime dataInforme, string descripcio, bool postReparacio, byte[] imatge)
        {
            Numero = numero;
            Data = dataInforme;
            Descripcio = descripcio;
            PostReparacio = postReparacio;
            Foto = imatge;
        }

        public int Numero { get; set; }

        public DateTime Data { get; set; }

        public string Descripcio { get; set; }

        public Byte[] Foto { get; set; }

        public bool PostReparacio { get; set; }


    }
}
