using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NullPointerSegurosApp.Model
{
    public class Cita
    {
        

        public Cita(int id, int numPerit, DateTime diaHora, int numSinistre, int duracio)
        {
            Id = id;
            NumPerit = numPerit;
            DiaHora = diaHora;
            NumSinistre = numSinistre;
            Duracio = duracio;
        }

        public Cita(int numPerit, DateTime diaHora, int numSinistre, int duracio)
        {
            NumPerit = numPerit;
            DiaHora = diaHora;
            NumSinistre = numSinistre;
            Duracio = duracio;
        }

        public int Id { get; set; }

        public int NumPerit { get; set; }

        public int NumSinistre { get; set; }

        public int Duracio { get; set; }

        public DateTime DiaHora { get; set; }



    }
}
