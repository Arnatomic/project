using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DemoImagesInsert
{
    public class BDConnector
    {
        #region props
        public MySqlConnection Connection { get; set; }

        public string ConnectionString { get; set; }

        #endregion props

        public BDConnector(string connectionString)
        {
            this.ConnectionString = connectionString;
        }

        

        public void insertDemoImages()
        {


            byte[] robatori1 = File.ReadAllBytes("F:\\DAM 2.1\\M12Project\\NullPointerSeguros\\project\\DemoImagesInsert\\bin\\x86\\Debug\\AppX\\Assets\\robatori1.jpg");
            byte[] robatori2 = File.ReadAllBytes("F:\\DAM 2.1\\M12Project\\NullPointerSeguros\\project\\DemoImagesInsert\\bin\\x86\\Debug\\AppX\\Assets\\robatori2.jpg");
            byte[] robatori3 = File.ReadAllBytes("F:\\DAM 2.1\\M12Project\\NullPointerSeguros\\project\\DemoImagesInsert\\bin\\x86\\Debug\\AppX\\Assets\\robatori3.jpg");

            byte[] humitats = File.ReadAllBytes("F:\\DAM 2.1\\M12Project\\NullPointerSeguros\\project\\DemoImagesInsert\\bin\\x86\\Debug\\AppX\\Assets\\humitats.jpg");

            insertFotoInEntradaInforme(robatori1, 1, 1);
            insertFotoInEntradaInforme(robatori2, 1, 2);
            insertFotoInEntradaInforme(robatori3, 1, 3);

            insertFotoInEntradaInforme(humitats, 2, 1);


        }

        private void insertFotoInEntradaInforme(byte[] rawData, int idSinistre, int numEntrada)
        {

            try
            {
                EncodingProvider provider = CodePagesEncodingProvider.Instance;
                Encoding.RegisterProvider(provider);


                using (Connection = new MySqlConnection(ConnectionString))
                {

                    Connection.Open();
                    MySqlCommand command = Connection.CreateCommand();
                    command.CommandText = "update entrada_informe set foto = @foto where numero = @idSinistre and ordre = @numEntrada";
                    command.Parameters.Add(new MySqlParameter("idSinistre", idSinistre));
                    command.Parameters.Add(new MySqlParameter("numEntrada", numEntrada));
                    command.Parameters.Add(new MySqlParameter("foto", rawData));

                    command.ExecuteNonQuery();

                }

            }
            catch (MySqlException e)
            {
                Debug.WriteLine("ERROR BD insertFotoInEntradaInforme:  " + e.Message);
            }





        }


       
      
        

       

     
    }



}
