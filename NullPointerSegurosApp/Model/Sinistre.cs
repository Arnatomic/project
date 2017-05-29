 using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NullPointerSegurosApp.Model
{
    public class Sinistre
    {
        #region Props

        public int Numero { get; set; }
        public DateTime? DataAssignacio { get; set; }

        public DateTime DataObertura { get; set; }

        public DateTime? DataTancament { get; set; }

        public string Descripcio { get; set; }

        public int IdPerit { get; set; }

        public Perit Perit { get; set; }

        public Enums.ESTAT_SINISTRE EstatSininistre { get; set; }

        public Enums.TIPUS_SINISTRE TipusSinistre { get; set; }

        private InformePericial mInforme;

        public InformePericial Informe
        {
            get { return mInforme; }
            set
            {
                mInforme = value;
                value.Sinistre = this;
            }
        }

        public int NumPolissa { get; set; }

        private Polissa mPolissa;


        public Sinistre(int numero, DateTime? dataAssignacio, DateTime dObertura, DateTime? dataTancament, string descripcio, int numPolissa, int idPerit, Enums.TIPUS_SINISTRE tipusSinistre, Enums.ESTAT_SINISTRE estatSinistre)
        {
            Numero = numero;
            DataAssignacio = dataAssignacio;
            DataObertura = dObertura;
            DataTancament = dataTancament;
            Descripcio = descripcio;
            NumPolissa = numPolissa;
            IdPerit = idPerit;
            TipusSinistre = tipusSinistre;
            EstatSininistre = estatSinistre;
        }

        public Polissa Polissa
        {
            get { return mPolissa; }
            set
            {
                mPolissa = value;
                if (!value.Sinistres.Contains(this))
                {
                    value.addSinistre(this);
                }
            }
                   
        }

        public ObservableCollection<Trucada> Trucades { get; set; } = new ObservableCollection<Trucada>();

        #endregion Props


        
        
    }
}
