using NullPointerSegurosApp.Model;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using Windows.Foundation;
using Windows.Foundation.Collections;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Controls.Primitives;
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Input;
using Windows.UI.Xaml.Media;
using Windows.UI.Xaml.Navigation;

// The User Control item template is documented at http://go.microsoft.com/fwlink/?LinkId=234236

namespace NullPointerSegurosApp.Controls
{
    public sealed partial class DetallSinistre : UserControl
    {
        public event EventHandler onClickEntradesInforme;
        public event EventHandler onClickTornar;
        public event EventHandler onClickUpdatePerit;
  
        public DetallSinistre()
        {
            this.InitializeComponent();
            Loaded += DetallSinistre_Loaded;

            lvEntradesInforme.onSelected_changed += LvEntradesInforme_onSelected_changed;
        }

        private void LvEntradesInforme_onSelected_changed(object sender, EventArgs e)
        {
            imgEntradaInforme.Source = ((EntradaInforme)lvEntradesInforme.ObjecteSeleccionat).BitmapFoto;
        }

        private void DetallSinistre_Loaded(object sender, RoutedEventArgs e)
        {
            preparaLlistaEntrades();
            preparaLlistaTrucades();
            ObservableCollection<object> entradesObject;
            ObservableCollection<object> trucadesObject;
            if (Sinistre.Informe != null)
            {
                entradesObject = new ObservableCollection<object>(Sinistre.Informe.Entrades);                
            }
            else {
                entradesObject = new ObservableCollection<object>();
                trucadesObject = new ObservableCollection<object>();
            }
            if (Sinistre.Trucades != null)
            {
                trucadesObject = new ObservableCollection<object>(Sinistre.Trucades);
            }
            else {
                trucadesObject = new ObservableCollection<object>();
            }

            
            lvEntradesInforme.ItemsSource = entradesObject;

            lvTrucades.ItemsSource = trucadesObject;

            cboxPerits.SelectedIndex = getIndexPeritSinistre();

            checkTancat.IsEnabled = Sinistre.EstatSininistre != Enums.ESTAT_SINISTRE.TANCAT;
            cboxPerits.IsEnabled = Sinistre.EstatSininistre != Enums.ESTAT_SINISTRE.TANCAT;
            btnCanviarPerit.IsEnabled = Sinistre.EstatSininistre != Enums.ESTAT_SINISTRE.TANCAT;

          //  checkTancat.IsChecked = Sinistre.EstatSininistre == Enums.ESTAT_SINISTRE.TANCAT;
        }

        private int getIndexPeritSinistre()
        {
            foreach (Perit p in Perits) {
                if (Sinistre.Perit != null && p.Numero == Sinistre.Perit.Numero) {
                    return Perits.IndexOf(p);
                }
            }
            return -1;
        }

        public Sinistre Sinistre
        {
            get { return (Sinistre)GetValue(SinistreProperty); }
            set { SetValue(SinistreProperty, value);

                DataContext = value;
            }

            
        }

        private ObservableCollection<Perit> mPerits;

        public ObservableCollection<Perit> Perits
        {
            get { return mPerits; }
            set { mPerits = value;
                cboxPerits.ItemsSource = value;
                cboxPerits.DisplayMemberPath = "FullName";
                
            }
        }

 

        // Using a DependencyProperty as the backing store for Sinistre.  This enables animation, styling, binding, etc...
        public static readonly DependencyProperty SinistreProperty =
            DependencyProperty.Register("Sinistre", typeof(Sinistre), typeof(DetallSinistre), new PropertyMetadata(null));
   

        private void btnInformes_Click(object sender, RoutedEventArgs e)
        {
            onClickEntradesInforme(this, null);
        }

        private void btnEnrere_Click(object sender, RoutedEventArgs e)
        {
            onClickTornar(this, null);
        }

        private void preparaLlistaEntrades()
        {
            Dictionary<string, string> headerCamps = new Dictionary<string, string>();
            Dictionary<string, int> ordreCamps = new Dictionary<string, int>();
            Dictionary<string, bool> campsVisibles = new Dictionary<string, bool>();
           // double[] midesColumnes = { 1, 1, 2 };
            double[] midesColumnes = {  1, 2 };

     
            campsVisibles["Data"] = true;
            campsVisibles["Descripcio"] = true;
          


            headerCamps["Data"] = "Data Creació";
            headerCamps["Descripcio"] = "Descripció";
      

       
            ordreCamps["Data"] = 0;
            ordreCamps["Descripcio"] = 1;
      


            lvEntradesInforme.CampsVisibles = campsVisibles;
            lvEntradesInforme.Capsaleres = headerCamps;
            lvEntradesInforme.OrdreCamps = ordreCamps;
            lvEntradesInforme.MidaColumnes = midesColumnes;
        }

        private void preparaLlistaTrucades()
        {
            Dictionary<string, string> headerCamps = new Dictionary<string, string>();
            Dictionary<string, int> ordreCamps = new Dictionary<string, int>();
            Dictionary<string, bool> campsVisibles = new Dictionary<string, bool>();
            double[] midesColumnes = { 1, 1, 2 };
      
       

            campsVisibles["PersonaContacte"] = true;
            campsVisibles["DataHora"] = true;
            campsVisibles["Descripcio"] = true;
            


            headerCamps["PersonaContacte"] = "Persona de Contacte";
            headerCamps["DataHora"] = "Dia i Hora";
            headerCamps["Descripcio"] = "Descripció";



            ordreCamps["PersonaContacte"] = 0;
            ordreCamps["DataHora"] = 1;
            ordreCamps["Descripcio"] = 2;



            lvTrucades.CampsVisibles = campsVisibles;
            lvTrucades.Capsaleres = headerCamps;
            lvTrucades.OrdreCamps = ordreCamps;
            lvTrucades.MidaColumnes = midesColumnes;
        }

        private void btnCanviarPerit_Click(object sender, RoutedEventArgs e)
        {            
            setPeritSinistre();
            onClickUpdatePerit(this, null);
        }

        private void setPeritSinistre()
        {

            if (cboxPerits.SelectedIndex != -1 && getIndexPeritSinistre() != Perits.IndexOf((Perit)cboxPerits.SelectedItem))
            {
                Sinistre.Perit = (Perit)cboxPerits.SelectedItem;
                if (checkTancat.IsChecked == true && checkTancat.IsEnabled)
                {
                    Sinistre.EstatSininistre = Enums.ESTAT_SINISTRE.TANCAT;
                }
                else
                {
                    Sinistre.EstatSininistre = Enums.ESTAT_SINISTRE.ASSIGNAT;
                }
            }
            else {
                if (checkTancat.IsChecked == true && checkTancat.IsEnabled)
                {
                    Sinistre.EstatSininistre = Enums.ESTAT_SINISTRE.TANCAT;
                }
            }
        }

       
    }
}
