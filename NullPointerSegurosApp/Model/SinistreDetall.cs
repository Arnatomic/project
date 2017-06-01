using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NullPointerSegurosApp.Model
{
    class SinistreDetall
    {
        public Sinistre Sinistre { get; set; }

        public ObservableCollection<Perit> Perits { get; set; }

    }
}
