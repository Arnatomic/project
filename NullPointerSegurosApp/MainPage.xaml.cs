using NullPointerSegurosApp.BD;
using NullPointerSegurosApp.Controls;
using NullPointerSegurosApp.Model;
using NullPointerSegurosApp.Pages;
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
using Windows.Graphics.Display;
using Windows.UI.ViewManagement;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Controls.Primitives;
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Input;
using Windows.UI.Xaml.Media;
using Windows.UI.Xaml.Navigation;

// La plantilla de elemento Página en blanco está documentada en http://go.microsoft.com/fwlink/?LinkId=402352&clcid=0x409

namespace NullPointerSegurosApp
{
    /// <summary>
    /// Página vacía que se puede usar de forma independiente o a la que se puede navegar dentro de un objeto Frame.
    /// </summary>
    public sealed partial class MainPage : Page
    {

        public ObservableCollection<Perit> Perits { get; set; } = new ObservableCollection<Perit>();
        private BDConnector con;

        public MainPage()
        {
            this.InitializeComponent();
            Loaded += MainPage_Loaded;

            //var bounds = ApplicationView.GetForCurrentView().VisibleBounds;
            //var scaleFactor = DisplayInformation.GetForCurrentView().RawPixelsPerViewPixel;
            //Size size = new Size(bounds.Width * scaleFactor, bounds.Height * scaleFactor);

            //ApplicationView.PreferredLaunchViewSize = size;
            //ApplicationView.PreferredLaunchWindowingMode = ApplicationViewWindowingMode.PreferredLaunchViewSize;
        }

        private void MainPage_Loaded(object sender, RoutedEventArgs e)
        {
            frameSinistresDetall.Navigate(typeof(AgendaPage),"");

            //con = new BDConnector("server=127.0.0.1;uid=root;pwd=root;database=projecte;SslMode=None;");


            //Perits = con.getLlistaPerits();
            //ObservableCollection<object> peritsObject = new ObservableCollection<object>(Perits);

            //prepararLlistaPerits();
            //prepararLlistaSinistres();

            //lvPerits.ItemsSource = peritsObject;
            //lvSinistres.onDouble_tap += LvSinistres_onDouble_tap;



            //foreach (Perit p in con.getLlistaPerits()) {
            //    Debug.WriteLine("Perit: " + p.Nom);
            //    foreach (Cita c in p.Cites) {
            //        Debug.WriteLine("Cita: " + c.Id);
            //    }
            //}

            //con.getLlistaSinistres(4);

            //Debug.WriteLine("Filtre x Client");
            //con.getSinistresFiltrats(null, Enums.ESTAT_SINISTRE.NOTHING, 1, 0);

            //Debug.WriteLine("Filtre x Estat");
            //con.getSinistresFiltrats(null, Enums.ESTAT_SINISTRE.ASSIGNAT, 0, 0);

            //Debug.WriteLine("Filtre x Perit");
            //con.getSinistresFiltrats(null, Enums.ESTAT_SINISTRE.NOTHING, 0, 1);


            //DateTime dt = new DateTime(2017,4,20);

            //Debug.WriteLine("Filtre x Data");
            //con.getSinistresFiltrats(dt, Enums.ESTAT_SINISTRE.NOTHING, 0, 0);

            //dt = new DateTime(2017, 01, 25);

            //Debug.WriteLine("Filtre Tots");


            //foreach (Sinistre s in con.getSinistresFiltrats(dt, Enums.ESTAT_SINISTRE.TANCAT, 1, 4)) {
            //    Debug.WriteLine("Sinistre: " + s.Numero + " Polissa: " + s.Polissa.Numero + " Perit: " + s.Perit.Numero);
            //    foreach (Trucada t in s.Trucades) {
            //        Debug.WriteLine("Trucada: " + t.Numero + " - " + t.Descripcio);
            //    }

            //}



            //Debug.WriteLine("Insert Imatges");
            //Task.Run(() => con.insertDemoImages());

            //string missatgeError  ="";
            //bool res = con.ActualitzarPeritSinistre(3, 3, out missatgeError);
            //Debug.WriteLine((res ? "1" : "0" )+ " Sinistres actualitzats");


            //con.ActualitzarEstatSinistre(3, Enums.ESTAT_SINISTRE.TANCAT.ToString());

        }

     

       

     
    }
}
