using NullPointerSegurosApp.BD;
using NullPointerSegurosApp.Model;
using System;
using System.Collections.Generic;
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
    public sealed partial class ControlEdicioCreacioPerit : UserControl
    {

        public event EventHandler onClickDesar;
        public event EventHandler onClickCancelar;
    
        public ControlEdicioCreacioPerit()
        {
            this.InitializeComponent();
            
        }


        public BDConnector Con { get; set; }

        public bool NouPerit { get; set; }


        public Perit Perit
        {
            get { return (Perit)GetValue(PeritProperty); }
            set { SetValue(PeritProperty, value);

                DataContext = value;
                if (!NouPerit)
                {
                    dpDNaix.Date = value.DataNaix;
                    tbPassword.IsEnabled = false;
                }
                else {
                    btnDesa.IsEnabled = false;                    
                }

            }
        }

        // Using a DependencyProperty as the backing store for Perit.  This enables animation, styling, binding, etc...
        public static readonly DependencyProperty PeritProperty =
            DependencyProperty.Register("Perit", typeof(Perit), typeof(ControlEdicioCreacioPerit), new PropertyMetadata(null));

        

  

        private bool validaTot()
        {
            
            if (tbNif.Text.Length != 9)
            {
                return false;            
            }
            if (tbNom.Text.Equals("") || tbNom.Text.Length <= 1)
            {
                return false;
            }
            if (tbCognom1.Text.Equals("") || tbCognom1.Text.Length <= 2)
            {
                return false;
            }
            if (tbLogin.Text.Equals("") || tbLogin.Text.Length <= 2)
            {
                return false;
            }
            if (tbPassword.Text.Equals("") || tbPassword.Text.Length <= 4)
            {
                return false;
            }

            if (dpDNaix.Date == null || dpDNaix.Date.ToString().Equals("")) {
                dpDNaix.Background = new SolidColorBrush(Colors.PaleVioletRed);
                return false;
            }

            return true;
        }

        private bool validaCamp(TextBox tbGeneric)
        {
            bool campOk = true;
            switch (tbGeneric.Tag.ToString()) {

                case "Nif":
                    if (tbGeneric.Text.Length != 9)
                    {
                        campOk = false;
                    }
                    break;
                case "Nom":
                    if ( tbGeneric.Text.Equals("") || tbGeneric.Text.Length <= 1)                  
                        campOk = false;
                    break;                   

                case "Cognom1":
                    if (tbGeneric.Text.Equals("") || tbGeneric.Text.Length <= 2)
                    {
                        campOk = false;                
                    }
                    break;
                case "Login":
                    if (tbGeneric.Text.Equals("") || tbGeneric.Text.Length <= 2)
                    {
                        campOk = false;          
                    }
                    break;
                case "Password":
                    if (tbGeneric.Text.Equals("") || tbGeneric.Text.Length <= 4)
                    {
                        campOk = false;
         
                    }
                    break;

            }

            setBackground(tbGeneric, campOk);

            return campOk;
        }

        private void setBackground(TextBox tb, bool campOk)
        {
            if (campOk)
            {
                tb.Background = new SolidColorBrush(Colors.PaleGreen);
            }
            else {
                tb.Background = new SolidColorBrush(Colors.PaleVioletRed);
            }
        }

       

        private void btnDesa_Click(object sender, RoutedEventArgs e)
        {
            onClickDesar(this, null);
        }

        private void btnCancel_Click(object sender, RoutedEventArgs e)
        {
            onClickCancelar(this, null);
        }

        private void tbGeneric_TextChanged(object sender, TextChangedEventArgs e)
        {
            validaCamp((TextBox)sender);
            btnDesa.IsEnabled = validaTot();
        }

        private void dpDNaix_DateChanged(CalendarDatePicker sender, CalendarDatePickerDateChangedEventArgs args)
        {
            dpDNaix.Background = new SolidColorBrush(Colors.PaleGreen);
            btnDesa.IsEnabled = validaTot();
            var date = dpDNaix.Date;
            Perit.DataNaix = date.Value.DateTime;
        }
    }
}
