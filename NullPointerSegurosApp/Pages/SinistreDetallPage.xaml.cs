using NullPointerSegurosApp.Model;
using System;
using System.Collections.Generic;
using System.Diagnostics;
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

// La plantilla de elemento Página en blanco está documentada en http://go.microsoft.com/fwlink/?LinkId=234238

namespace NullPointerSegurosApp.Pages
{
    /// <summary>
    /// Una página vacía que se puede usar de forma independiente o a la que se puede navegar dentro de un objeto Frame.
    /// </summary>
    public sealed partial class SinistreDetallPage : Page
    {
        private Sinistre sin;
        public SinistreDetallPage()
        {
            this.InitializeComponent();
        }

        protected override void OnNavigatedTo(NavigationEventArgs e)
        {
            base.OnNavigatedTo(e);

            if (e.Parameter is string)
            {

            }
            else
            {

               sin = (Sinistre)e.Parameter;

                controlSinistre.Sinistre = sin;
                controlSinistre.onClickEntradesInforme += ControlSinistre_onClickEntradesInforme;
                controlSinistre.onClickTornar += ControlSinistre_onClickTornar;

            }
        }

        private void ControlSinistre_onClickTornar(object sender, EventArgs e)
        {
            this.Frame.Navigate(typeof(AgendaPage), "");
        }

        private void ControlSinistre_onClickEntradesInforme(object sender, EventArgs e)
        {
            this.Frame.Navigate(typeof(EntradaInformePage), sin.Informe?.Entrades);
        }



    }
}
