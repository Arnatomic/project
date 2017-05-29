using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NullPointerSegurosApp.Model
{
    public class Polissa
    {
        private decimal import;
        private Client c;

        public Polissa(int numero, DateTime dataInici, DateTime dataFi, decimal import, decimal importContinent, decimal importContingut, Client c, string poblacio, string liniaAdreca, Enums.TIPUS_HABITATGE tipusHabitatge)
        {
            Numero = numero;
            DataInici = dataInici;
            DataFi = dataFi;
            ImportPolissa = import;
            ImportContinent = importContinent;
            ImportContingut = importContingut;
            Client = c;
            Poblacio = poblacio;
            LiniaAdreca = liniaAdreca;
            TipusHabitatge = tipusHabitatge;
        }

        public int Numero { get; set; }

        public DateTime DataInici { get; set; }

        public DateTime DataFi { get; set; }

        public Decimal ImportPolissa { get; set; }

        public Decimal ImportContinent { get; set; }

        public Decimal ImportContingut { get; set; }

        public string Poblacio { get; set; }

        public string LiniaAdreca { get; set; }

        public Enums.TIPUS_HABITATGE TipusHabitatge { get; set; }

        public ObservableCollection<Sinistre> Sinistres { get; set; } = new ObservableCollection<Sinistre>();

        public int NumClient { get; set; }

        public Client Client { get; set; }


        public void addSinistre(Sinistre sinistre)
        {
            if (sinistre != null && !Sinistres.Contains(sinistre))
            {
                Sinistres.Add(sinistre);
                if (!sinistre.Polissa.Equals(this))
                {
                    sinistre.Polissa = this;
                }

            }
        }

        public void removeSinistre(Sinistre sinistre)
        {
            if (sinistre != null && Sinistres.Contains(sinistre))
            {
                Sinistres.ElementAt(Sinistres.IndexOf(sinistre)).Polissa = null;
                Sinistres.Remove(sinistre);
            }
        }


    }
}
