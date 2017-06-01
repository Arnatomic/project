using NullPointerSegurosApp.Model;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Diagnostics;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using Windows.Foundation;
using Windows.Foundation.Collections;
using Windows.UI;
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
    public sealed partial class ControlAgenda : UserControl
    {
        private DateTime diaAgenda;

        private ObservableCollection<Cita> mCitesPerit;

        public ObjecteCita[] citesTractades = new ObjecteCita[12];

        private List<string> horesOcupades = new List<string>();

    

        public ObservableCollection<Cita> CitesPerit
        {
            get { return mCitesPerit; }
            set { mCitesPerit = value;
                netejaTextBoxes();
                citesTractades = new ObjecteCita[12];
                ompleAgenda();

            }
        }

       

        public ControlAgenda()
        {
            this.InitializeComponent();           
            Loaded += ControlAgenda_Loaded;
        }

        private void ControlAgenda_Loaded(object sender, RoutedEventArgs e)
        {
            diaAgenda = DateTime.Now;
            actualitzaTextBoxData();
        }

        private void netejaTextBoxes()
        {
            foreach (TextBlock tb in gridCites.Children)
            {
                tb.Text = "";
            }
        }

        private void ompleAgenda()
        {
            foreach (Cita c in CitesPerit) {
                if (c.DiaHora.Date.ToString("dddd dd MMMM yyyy", CultureInfo.CurrentCulture).Equals(diaAgenda.Date.ToString("dddd dd MMMM yyyy", CultureInfo.CurrentCulture)))
                {
                    int index = getFilaCita(c.DiaHora);
                    citesTractades[index] = new ObjecteCita(index, "Cita del sinistre: " + c.NumSinistre);
                    for (int i = 1; i < c.Duracio; i++)
                    {
                        citesTractades[index+i] = new ObjecteCita(index+i, "Cita del sinistre: " + c.NumSinistre);
                    }
                }
                    
            }
            ompleTextBlocks();
        }

        private void ompleTextBlocks()
        {
            foreach (TextBlock tb in gridCites.Children) {

                foreach (ObjecteCita oc in citesTractades) {
                    if (oc != null)
                    {
                        if (tb.Name.Equals("tb" + oc.Index))
                        {
                            tb.Text = oc.NomCita;
                        }
                    }
                }

            }
        }

        

        private void situaCita(Cita c, ObjecteCita objecteCita, out string missatgeError)
        {
            bool mesDunaHora = false;
            missatgeError = "";
           
                foreach (TextBlock t in gridCites.Children) {
                    if (t.Name.Equals(objecteCita.Id)) {
                        if (!horesOcupades.Contains(c.DiaHora.TimeOfDay.ToString()))
                        {
                        t.Text = "Cita del sinistre: " + c.NumSinistre;
                        horesOcupades.Add(c.DiaHora.TimeOfDay.ToString());
                        mesDunaHora = c.Duracio > 1;
                    }
                    else {
                        if(mesDunaHora)
                            missatgeError = "Error ja hi han cites assignades en aquest interval";
                        }           
                    }
                }
          
        }

        private int getFilaCita(DateTime diaHora)
        {

            Debug.WriteLine("COmparacio: " + diaHora.TimeOfDay.ToString());

            switch (diaHora.TimeOfDay.ToString()) {

                case "08:00:00":                
                    return 0;
                case "09:00:00":
                    return 1;
                case "10:00:00":
                    return 2;
                case "11:00:00":
                    return 3;
                case "12:00:00":
                    return 4;
                case "13:00:00":
                    return 5;
                case "14:00:00":
                    return 6;
                case "15:00:00":
                    return 7;
                case "16:00:00":
                    return 8;
                case "17:00:00":
                    return 9;
                case "18:00:00":
                    return 10;
                case "19:00:00":
                    return 11;
                default: return -1;

            }
        }

        public void actualitzaTextBoxData() {

            tbData.Text = diaAgenda.ToString("dddd dd MMMM yyyy", CultureInfo.CurrentCulture);
        }

        private void btnDiaSeguent_Click(object sender, RoutedEventArgs e)
        {
           diaAgenda =  diaAgenda.AddDays(1);
            actualitzaTextBoxData();
            netejaTextBoxes();
            citesTractades = new ObjecteCita[12];
            ompleAgenda();
        }

        private void btnDiaAnterior_Click(object sender, RoutedEventArgs e)
        {
           diaAgenda =  diaAgenda.AddDays(-1);
            actualitzaTextBoxData();
            netejaTextBoxes();
            citesTractades = new ObjecteCita[12];
            ompleAgenda();
        }

        public class ObjecteCita {

            public int Index { get; set; }

            public int Duracio { get; set; }

            public string Id { get; set; }

            public string NomCita { get; set; }

            public ObjecteCita(int index, string nomCita) {
                Index = index;
                NomCita = nomCita;

                Id = "tb" + index.ToString();
            }

        }

        public void comprovaAgenda(Cita c, out string missatgeError)
        {
            missatgeError = "";
            int index = getFilaCita(c.DiaHora);

            if (citesTractades[index] != null)
            {

                missatgeError = "Agenda ocupada durant aquest interval";
            }
            else
            {
                for (int i = 1; i < c.Duracio; i++) {

                    if (citesTractades[index + i] != null) {
                        missatgeError = "Agenda ocupada durant aquest interval";
                    }

                }

            }


            if (missatgeError.Equals("")) {
           
              

                CitesPerit.Add(c);
                ompleAgenda();
            }

        }
    }
}
