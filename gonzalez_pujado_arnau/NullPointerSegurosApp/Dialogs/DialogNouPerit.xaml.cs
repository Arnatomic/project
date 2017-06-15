using NullPointerSegurosApp.BD;
using NullPointerSegurosApp.Converters;
using NullPointerSegurosApp.Model;
using System;
using System.Collections.Generic;
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

// La plantilla de elemento del cuadro de diálogo de contenido está documentada en http://go.microsoft.com/fwlink/?LinkId=234238

namespace NullPointerSegurosApp.Dialogs
{
    public sealed partial class DialogNouPerit : ContentDialog
    {
        private BDConnector con;
        private bool nouPerit;
        public Enums.BOTO BotoClicat { get; set; }
        public Perit PeritActual { get; set; }
        public DialogNouPerit(BDConnector con, bool nouPerit,Perit peritActual)
        {
            this.InitializeComponent();
            this.con = con;
            this.nouPerit = nouPerit;
            PeritActual = peritActual;

            Loaded += DialogNouPerit_Loaded;
        }

        private void DialogNouPerit_Loaded(object sender, RoutedEventArgs e)
        {
            controlPerit.Con = con;
            controlPerit.NouPerit = nouPerit;

            controlPerit.onClickCancelar += ControlPerit_onClickCancelar;
            controlPerit.onClickDesar += ControlPerit_onClickDesar;

            controlPerit.Perit = PeritActual;

        }

        private void ControlPerit_onClickDesar(object sender, EventArgs e)
        {
            Perit p = controlPerit.Perit;
            if (nouPerit)
            {
               int numPerit = con.insertNouPerit(p.Nif,p.Nom, p.Cognom1, p.Cognom2, p.DataNaix, p.Login,MD5Encryptor.ComputeMD5(p.Password));
                controlPerit.Perit.Numero = numPerit;
            }
            else {
                con.actualitzarPerit(p.Numero, p.Nif,p.Nom, p.Cognom1, p.Cognom2, p.DataNaix, p.Login, p.Password);
            }
            BotoClicat = Enums.BOTO.DESAR;
            this.Hide();
        }

        private void ControlPerit_onClickCancelar(object sender, EventArgs e)
        {
            BotoClicat = Enums.BOTO.CANCELAR;
            this.Hide();
        }
    }
}
