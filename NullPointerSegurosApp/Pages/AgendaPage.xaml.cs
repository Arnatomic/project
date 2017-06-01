using NullPointerSegurosApp.BD;
using NullPointerSegurosApp.Controls;
using NullPointerSegurosApp.Dialogs;
using NullPointerSegurosApp.Model;
using System;
using System.Threading;
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
using static NullPointerSegurosApp.Controls.ControlAgenda;

// La plantilla de elemento Página en blanco está documentada en http://go.microsoft.com/fwlink/?LinkId=234238

namespace NullPointerSegurosApp.Pages
{
    /// <summary>
    /// Una página vacía que se puede usar de forma independiente o a la que se puede navegar dentro de un objeto Frame.
    /// </summary>
    public sealed partial class AgendaPage : Page
    {
        public ObservableCollection<Perit> Perits { get; set; } = new ObservableCollection<Perit>();
        public ObservableCollection<Client> Clients { get; set; } = new ObservableCollection<Client>();

        public ObservableCollection<Sinistre> Sinistres { get; set; } = new ObservableCollection<Sinistre>();

        private List<string> horesDia = new List<string>() { "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00" };

        public List<Enums.ESTAT_SINISTRE> EstatsSinistre { get; set; } = new List<Enums.ESTAT_SINISTRE>();
        private BDConnector con;
        public AgendaPage()
        {
            this.InitializeComponent();
            Loaded += AgendaPage_Loaded;

        }

        private void AgendaPage_Loaded(object sender, RoutedEventArgs e)
        {


            con = new BDConnector("server=127.0.0.1;uid=root;pwd=root;database=projecte;SslMode=None;");
            // con = new BDConnector("server=92.222.27.83;uid=m2-agonzalez;pwd=47111651S;database=m2_agonzalez;SslMode=None;");

            Perits = con.getLlistaPerits();
            ObservableCollection<object> peritsObject = new ObservableCollection<object>(Perits);

            prepararLlistaPerits();
            prepararLlistaSinistres(lvSinistres);

            lvPerits.ItemsSource = peritsObject;
            lvSinistres.onDouble_tap += LvSinistres_onDouble_tap;


            Clients = con.getLlistaClients();

            cboxClient.ItemsSource = Clients;
            cboxClient.DisplayMemberPath = "FullName";

            getEstatsSinistre();
            cboxEstatSinistre.ItemsSource = EstatsSinistre;


            Sinistres = con.getLlistaSinistres();
            ObservableCollection<object> sinistresObject = new ObservableCollection<object>(Sinistres);
            lvSinistres.ItemsSource = sinistresObject;


            lvSinistresSenseCita.onSelected_changed += LvSinistresSenseCita_onSelected_changed;

            cboxHoresDia.ItemsSource = horesDia;
            cboxHoresDia.SelectedIndex = 0;
        }

        private void LvSinistresSenseCita_onSelected_changed(object sender, EventArgs e)
        {
            btnAssignaCita.IsEnabled = true;

        }

        private void getEstatsSinistre()
        {
            EstatsSinistre.Add(Enums.ESTAT_SINISTRE.NOTHING);
            EstatsSinistre.Add(Enums.ESTAT_SINISTRE.NOU);
            EstatsSinistre.Add(Enums.ESTAT_SINISTRE.ASSIGNAT);
            EstatsSinistre.Add(Enums.ESTAT_SINISTRE.TANCAT);

        }

        protected override void OnNavigatedTo(NavigationEventArgs e)
        {
            base.OnNavigatedTo(e);

            if (e.Parameter is string)
            {

            }
            else
            {
                con = new BDConnector("server=127.0.0.1;uid=root;pwd=root;database=projecte;SslMode=None;");
                string missatgeError;
                //  Sinistre param = ((Sinistre)e.Parameter);
                con.ActualitzarEstatSinistre(((Sinistre)e.Parameter).Numero, ((Sinistre)e.Parameter).EstatSininistre.ToString());
                con.ActualitzarPeritSinistre(((Sinistre)e.Parameter).Numero, ((Sinistre)e.Parameter).Perit.Numero, out missatgeError);

                int i = Sinistres.IndexOf((Sinistre)e.Parameter);
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

        private void prepararLlistaSinistres(Control_ListViewDBCreator lvSinistres)
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
            Control_ListViewDBCreator lvPerits = (Control_ListViewDBCreator)sender;
            Perit p = (Perit)lvPerits.ObjecteSeleccionat;
            if (p != null)
            {
                lvSinistres.ItemsSource = new ObservableCollection<object>(con.getSinistresFiltrats(null, Enums.ESTAT_SINISTRE.NOTHING, 0, p.Numero));
                btnFiltreOn.IsEnabled = lvSinistres.ItemsSource.Count > 0;


                lvSinistresSenseCita.ItemsSource = getSinistresSenseCita(p.Numero);

                btnEditar.IsEnabled = true;
                btnEsborrar.IsEnabled = true;

                controlAgenda.CitesPerit = p.Cites;

            }





        }

        private ObservableCollection<object> getSinistresSenseCita(int numero)
        {
            prepararLlistaSinistres(lvSinistresSenseCita);
            ObservableCollection<object> sinistres = new ObservableCollection<object>();
            foreach (object s in lvSinistres.ItemsSource)
            {
                bool trobat = false;
                if (((Sinistre)s).Perit.Cites.Count != 0)
                {
                    foreach (Cita c in ((Sinistre)s).Perit.Cites)
                    {
                        if (c.NumSinistre == ((Sinistre)s).Numero)
                        {
                            trobat = true;
                            break;
                        }
                    }
                    if (!trobat)
                    {
                        sinistres.Add(s);
                    }
                }
            }

            return sinistres;
        }

        private void LvSinistres_onDouble_tap(object sender, EventArgs e)
        {
            Control_ListViewDBCreator lv = (Control_ListViewDBCreator)sender;
            Sinistre s = (Sinistre)lv.ObjecteSeleccionat;
            SinistreDetall sd = new SinistreDetall();

            sd.Sinistre = (Sinistre)lv.ObjecteSeleccionat;
            sd.Perits = Perits;

            this.Frame.Navigate(typeof(SinistreDetallPage), sd);

        }

        private void ControlCalendari_DateChanged(object sender, EventArgs e)
        {
            ControlCalendar cc = (ControlCalendar)sender;
            Debug.WriteLine(cc.MCurrentDate.Date);
        }



        private async void btnNou_editar_Click(object sender, RoutedEventArgs e)
        {
            Button btnPulsat = (Button)sender;

            if (btnPulsat.Tag.ToString().Equals("Editar"))
            {
                DialogNouPerit dialog = new DialogNouPerit(con, false, (Perit)lvPerits.ObjecteSeleccionat);
                ContentDialogResult res = await dialog.ShowAsync();
                lvPerits.ItemsSource = new ObservableCollection<object>(lvPerits.ItemsSource);
            }
            else
            {
                Perit p = new Perit();
                DialogNouPerit dialog = new DialogNouPerit(con, true, p);
                ContentDialogResult res = await dialog.ShowAsync();
                if (dialog.BotoClicat == Enums.BOTO.DESAR)
                {
                    lvPerits.ItemsSource.Add(dialog.PeritActual);
                }
            }
        }

        private void btnEsborrar_Click(object sender, RoutedEventArgs e)
        {
            string missatgeError = "";
            con.deletePerit(((Perit)lvPerits.ObjecteSeleccionat).Numero, out missatgeError);
            tbMissatgeError.Text = missatgeError;
            Debug.WriteLine("MISSATGE ? :" + missatgeError);
            if (missatgeError.Equals(""))
            {
                lvPerits.ItemsSource.Remove((Perit)lvPerits.ObjecteSeleccionat);
            }
            espera();

        }

        private async void espera()
        {
            await System.Threading.Tasks.Task.Delay(2000);
            tbMissatgeError.Text = "";
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {

        }

        private void btnFiltreOn_Click(object sender, RoutedEventArgs e)
        {
            var date = dpDataSinistre.Date;
            DateTime? dataSinistre = null;
            if (date != null)
            {
                dataSinistre = date.Value.DateTime;
            }


            Enums.ESTAT_SINISTRE estatSinistre = (Enums.ESTAT_SINISTRE)cboxEstatSinistre.SelectedItem;
            Client client = (Client)cboxClient.SelectedItem;

            lvSinistres.ItemsSource = new ObservableCollection<object>(con.getSinistresFiltrats(dataSinistre, estatSinistre,
                                        client == null ? 0 : client.Numero, ((Perit)lvPerits.ObjecteSeleccionat).Numero));

        }

        private void btnFiltreOff_Click(object sender, RoutedEventArgs e)
        {
            desactivarFiltre();

        }

        private void desactivarFiltre()
        {
            dpDataSinistre.Date = null;
            cboxEstatSinistre.SelectedIndex = 0;
            cboxClient.SelectedIndex = -1;

            lvSinistres.ItemsSource = new ObservableCollection<object>(con.getLlistaSinistres());
            lvSinistresSenseCita.ItemsSource = new ObservableCollection<object>();

            lvSinistres.IndexItemSeleccionat = -1;
        }

        private void btnAssignaCita_Click(object sender, RoutedEventArgs e)
        {
            if (lvSinistresSenseCita.IndexItemSeleccionat != -1 && cboxHoresDia.SelectedIndex != -1)
            {

                Sinistre s = (Sinistre)lvSinistresSenseCita.ObjecteSeleccionat;

                string avui = DateTime.Today.Date.ToString();
                avui = avui.Substring(0, avui.Length - 7) + cboxHoresDia.SelectedItem.ToString();

                Cita c = new Cita(s.Perit.Numero, Convert.ToDateTime(avui), s.Numero, Int16.Parse(tbDuracio.Text));

                string missatgeError = "";

                controlAgenda.comprovaAgenda(c, out missatgeError);

                tbErrorCita.Text = missatgeError;
                if (missatgeError.Equals(""))
                {
                    if (s.Perit.Cites != null)
                    {
                        c.Id = s.Perit.Cites.Count+1;
                    }
                    else
                    {
                        c.Id = 1;
                    }
                    con.insertCita(c.Id, c.NumPerit, c.DiaHora, c.NumSinistre, c.Duracio);
                    lvSinistresSenseCita.ItemsSource.Remove(s);
                }
                canviTextBlock();


            }
        }

        private async void canviTextBlock()
        {
            await System.Threading.Tasks.Task.Delay(2000);
            tbErrorCita.Text = "";
        }


    }
}

