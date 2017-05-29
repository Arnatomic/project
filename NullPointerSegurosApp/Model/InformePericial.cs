using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NullPointerSegurosApp.Model
{
    public class InformePericial
    {
        public int Numero { get; set; }

        public DateTime DataEmisio { get; set; }

        public Decimal ImportCobert { get; set; }

        public string Informe { get; set; }

        public Perit Perit { get; set; }

        public Enums.RESULTAT_PERITATGE ResultatPeritatge { get; set; }

        public Enums.ESTAT_INFORME EstatInforme { get; set; }

        private Sinistre mSinistre;

        public Sinistre Sinistre
        {
            get { return mSinistre; }
            set { if (value != null) {
                    mSinistre = value;
                    if (!mSinistre.Informe.Equals(this)) {
                        mSinistre.Informe = this;
                    }
                }
                }
        }


        public ObservableCollection<EntradaInforme> Entrades { get; set; } = new ObservableCollection<EntradaInforme>();


        public InformePericial() { }

        public InformePericial(int numero, DateTime dataEmisio, decimal importCobert, string informe, Perit perit, Enums.ESTAT_INFORME estatInforme, Enums.RESULTAT_PERITATGE resultatPeritatge)
        {
            Numero = numero;
            DataEmisio = dataEmisio;
            ImportCobert = importCobert;
            Informe = informe;
            Perit = perit;
            EstatInforme = estatInforme;
            ResultatPeritatge = resultatPeritatge;
        }
    }
}
