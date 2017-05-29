using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NullPointerSegurosApp.Model
{
    public class Perit
    {
        

        public Perit(int numero, string nif, string nom, string cognom1, string cognom2, DateTime dataNaix, string loggin, string password)
        {
            Numero = numero;
            Nif = nif;
            Nom = nom;
            Cognom1 = cognom1;
            Cognom2 = cognom2;
            DataNaix = dataNaix;
            Login = loggin;
            Password = password;
        }


        public int Numero { get; set; }

        public string Login { get; set; }

        public string Password { get; set; }

        public string Nif { get; set; }

        public string Nom { get; set; }

        public string Cognom1 { get; set; }
        public string Cognom2 { get; set; }

        public DateTime DataNaix { get; set; }

        public ObservableCollection<Cita> Cites { get; set; } = new ObservableCollection<Cita>();

        public ObservableCollection<Sinistre> Sinistres { get; set; } = new ObservableCollection<Sinistre>();

        public void addSinistre(Sinistre sinistre)
        {
            if (sinistre != null && !Sinistres.Contains(sinistre))
            {
                Sinistres.Add(sinistre);
                if (!sinistre.Perit.Equals(this))
                {
                    sinistre.Perit = this;
                }

            }
        }

        public void removeSinistre(Sinistre sinistre)
        {
            if (sinistre != null && Sinistres.Contains(sinistre))
            {
                Sinistres.Remove(sinistre);
                Sinistres.ElementAt(Sinistres.IndexOf(sinistre)).Perit = null;
            }
        }
        
    }
}
