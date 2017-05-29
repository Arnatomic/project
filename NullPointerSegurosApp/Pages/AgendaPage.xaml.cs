using NullPointerSegurosApp.BD;
using NullPointerSegurosApp.Controls;
using NullPointerSegurosApp.Model;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using System.Threading.Tasks;
using Windows.Foundation;
using Windows.Foundation.Collections;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Controls.Primitives;
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Input;
using Windows.UI.Xaml.Media;
using Windows.UI.Xaml.Navigation;

// La plantilla de elemento Página en blanco está documentada en http://go.microsoft.com/fwlink/?LinkId=234238

namespace NullPointerSegurosApp.Pages
{
    /// <summary>
    /// Una página vacía que se puede usar de forma independiente o a la que se puede navegar dentro de un objeto Frame.
    /// </summary>
    public sealed partial class AgendaPage : Page
    {
        public ObservableCollection<Perit> Perits { get; set; } = new ObservableCollection<Perit>();
        private BDConnector con;
        public AgendaPage()
        {
            this.InitializeComponent();
            Loaded += AgendaPage_Loaded;
            
        }

        private void AgendaPage_Loaded(object sender, RoutedEventArgs e)
        {
            controlCalendari.MCurrentDate = DateTime.Now;
            controlCalendari.DateChanged += ControlCalendari_DateChanged;

            con = new BDConnector("server=127.0.0.1;uid=root;pwd=root;database=projecte;SslMode=None;");


            Perits = con.getLlistaPerits();
            ObservableCollection<object> peritsObject = new ObservableCollection<object>(Perits);

            prepararLlistaPerits();
            prepararLlistaSinistres();

            lvPerits.ItemsSource = peritsObject;
            lvSinistres.onDouble_tap += LvSinistres_onDouble_tap;


            Debug.WriteLine("Insert Imatges");
            Task.Run(() => con.insertDemoImages());

           
        }

        

        protected override void OnNavigatedTo(NavigationEventArgs e)
        {
            base.OnNavigatedTo(e);

            if (e.Parameter is string)
            {

            }
            else
            {

                Sinistre param = (Sinistre)e.Parameter;
                Debug.WriteLine("S: " + param.Numero + param.Polissa.Numero);
            }
        }

        private void prepararLlistaPerits()
        {
            Dictionary<string, string> headerCamps = new Dictionary<string, string>();
            Dictionary<string, int> ordreCamps = new Dictionary<string, int>();
            Dictionary<string, bool> campsVisibles = new Dictionary<string, bool>();
            double[] midesColumnes = { 1, 1, 1, 1 };

            campsVisibles["Numero"] = true;
            campsVisibles["Nom"] = true;
            campsVisibles["Cognom1"] = true;
            campsVisibles["Nif"] = true;


            headerCamps["Numero"] = "Num Perit";
            headerCamps["Nom"] = "Nom";
            headerCamps["Cognom1"] = "Cognom";
            headerCamps["Nif"] = "NIF";

            ordreCamps["Numero"] = 0;
            ordreCamps["Nom"] = 2;
            ordreCamps["Cognom1"] = 1;
            ordreCamps["Nif"] = 3;


            lvPerits.CampsVisibles = campsVisibles;
            lvPerits.Capsaleres = headerCamps;
            lvPerits.OrdreCamps = ordreCamps;
            lvPerits.MidaColumnes = midesColumnes;

            lvPerits.onSelected_changed += LvPerits_onSelected_changed;

        }

        private void prepararLlistaSinistres()
        {
            Dictionary<string, string> headerCamps = new Dictionary<string, string>();
            Dictionary<string, int> ordreCamps = new Dictionary<string, int>();
            Dictionary<string, bool> campsVisibles = new Dictionary<string, bool>();
            double[] midesColumnes = { 1, 1, 1, 1 };

            campsVisibles["Numero"] = true;
            campsVisibles["TipusSinistre"] = true;
            campsVisibles["IdPerit"] = true;
            campsVisibles["DataAssignacio"] = true;


            headerCamps["Numero"] = "Num Sinistre";
            headerCamps["TipusSinistre"] = "Tipus Sinistre";
            headerCamps["IdPerit"] = "Num Perit";
            headerCamps["DataAssignacio"] = "Data Assignacio";

            ordreCamps["Numero"] = 0;
            ordreCamps["TipusSinistre"] = 2;
            ordreCamps["IdPerit"] = 1;
            ordreCamps["DataAssignacio"] = 3;


            lvSinistres.CampsVisibles = campsVisibles;
            lvSinistres.Capsaleres = headerCamps;
            lvSinistres.OrdreCamps = ordreCamps;
            lvSinistres.MidaColumnes = midesColumnes;



        }

        private void LvPerits_onSelected_changed(object sender, EventArgs e)
        {
            Control_ListViewDBCreator lv = (Control_ListViewDBCreator)sender;
            Perit p = (Perit)lv.ObjecteSeleccionat;
            lvSinistres.ItemsSource = new ObservableCollection<object>(con.getSinistresFiltrats(null, Enums.ESTAT_SINISTRE.NOTHING, 0, p.Numero));
        }

        private void LvSinistres_onDouble_tap(object sender, EventArgs e)
        {
            Control_ListViewDBCreator lv = (Control_ListViewDBCreator)sender;
            Sinistre s = (Sinistre)lv.ObjecteSeleccionat;

            Debug.WriteLine("Sinistre seleccionat: " + s.Numero + s.Polissa.Numero);
            this.Frame.Navigate(typeof(SinistreDetallPage),s);
        }

        private void ControlCalendari_DateChanged(object sender, EventArgs e)
        {
            ControlCalendar cc = (ControlCalendar)sender;
            Debug.WriteLine(cc.MCurrentDate.Date);
        }

    }
}
