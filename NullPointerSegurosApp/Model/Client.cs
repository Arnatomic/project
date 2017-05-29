using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NullPointerSegurosApp.Model
{
    public class Client
    {
        public Client(int numero, string nif, string nom, string cognom1, string cognom2, DateTime dataNaix)
        {
            Numero = numero;
            Nif = nif;
            Nom = nom;
            Cognom1 = cognom1;
            Cognom2 = cognom2;
            DataNaix = dataNaix;
        }

        public int Numero { get; set; }
        
        public string Nif { get; set; }

        public string Nom { get; set; }

        public string Cognom1 { get; set; }
        public string Cognom2 { get; set; }

        public DateTime DataNaix { get; set; }

    }
}
