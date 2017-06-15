using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using System.Text;
using System.Threading.Tasks;
using Windows.Storage.Streams;
using Windows.UI.Xaml.Media.Imaging;

namespace NullPointerSegurosApp.Model
{
    public class EntradaInforme
    {
       

        public EntradaInforme(int numero, DateTime dataInforme, string descripcio, bool postReparacio, byte[] imatge)
        {
            Numero = numero;
            Data = dataInforme;
            Descripcio = descripcio;
            PostReparacio = postReparacio;
            Foto = imatge;
        }

        public int Numero { get; set; }

        public DateTime Data { get; set; }

        public string Descripcio { get; set; }

        private byte[] mFoto;

        public byte[] Foto
        {
            get { return mFoto; }
            set { mFoto = value;
                setImage();
            }
        }


        public bool PostReparacio { get; set; }

        public BitmapImage BitmapFoto { get; set; }


        private async void setImage()
        {
            BitmapFoto = await ImageFromBytes(Foto);
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
