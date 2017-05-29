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
using Windows.Storage.Streams;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Controls.Primitives;
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Input;
using Windows.UI.Xaml.Media;
using Windows.UI.Xaml.Media.Imaging;
using Windows.UI.Xaml.Navigation;

// La plantilla de elemento Página en blanco está documentada en http://go.microsoft.com/fwlink/?LinkId=234238

namespace NullPointerSegurosApp.Pages
{
    /// <summary>
    /// Una página vacía que se puede usar de forma independiente o a la que se puede navegar dentro de un objeto Frame.
    /// </summary>
    public sealed partial class EntradaInformePage : Page
    {
        public EntradaInformePage()
        {
            this.InitializeComponent();
        }

        private ObservableCollection<EntradaInforme> entrades;

        protected override void OnNavigatedTo(NavigationEventArgs e)
        {
            base.OnNavigatedTo(e);

            if (e.Parameter is string)
            {

            }
            else
            {
                entrades = new ObservableCollection<EntradaInforme>();
                entrades = (ObservableCollection <EntradaInforme>) e.Parameter;
                if (entrades != null)
                {
                    foreach (EntradaInforme ei in entrades)
                    {
                        Debug.WriteLine("ENTRADA: " + ei.Data + ei.Descripcio + ei.Numero + ei.PostReparacio + ei.Foto);

                    }
                }

                setImagge();

            }
        }

        private async void setImagge()
        {
            imgFoto.Source = await ImageFromBytes(entrades.ElementAt(0).Foto);
        }

        public async static Task<BitmapImage> ImageFromBytes(byte[] bytes)
        {
            BitmapImage image = new BitmapImage();
            using (InMemoryRandomAccessStream stream = new InMemoryRandomAccessStream())
            {
                await stream.WriteAsync(bytes.AsBuffer());
                stream.Seek(0);
                await image.SetSourceAsync(stream);
            }
            return image;
        }
    }



   
}
