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

// The User Control item template is documented at http://go.microsoft.com/fwlink/?LinkId=234236

namespace NullPointerSegurosApp.Controls
{
    public sealed partial class DetallSinistre : UserControl
    {
        public event EventHandler onClickEntradesInforme;
        public event EventHandler onClickTornar;
        public DetallSinistre()
        {
            this.InitializeComponent();
        }




        public Sinistre Sinistre
        {
            get { return (Sinistre)GetValue(SinistreProperty); }
            set { SetValue(SinistreProperty, value);

                DataContext = value;
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
    }
}
