using MySql.Data.MySqlClient;
using NullPointerSegurosApp.Model;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Windows.Storage;
using Windows.Storage.FileProperties;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Media.Imaging;

namespace NullPointerSegurosApp.BD
{
    public class BDConnector 
    {
        #region props
        public MySqlConnection Connection { get; set; }

        public string ConnectionString { get; set; }

        private ObservableCollection<Client> clients;

        public ObservableCollection<Client> Clients
        {
            get {if (clients == null) {
                    clients = new ObservableCollection<Client>();
                    clients = getLlistaClients();
                }
                return clients; }
            set { clients = value; }
        }

#endregion props

        public BDConnector(string connectionString) {
            this.ConnectionString = connectionString;
        }

        private ObservableCollection<Client> getLlistaClients()
        {
            ObservableCollection<Client> clients = new ObservableCollection<Client>();

            try
            {
                EncodingProvider provider = CodePagesEncodingProvider.Instance;
                Encoding.RegisterProvider(provider);


                using (Connection = new MySqlConnection(ConnectionString))
                {

                    Connection.Open();
                    MySqlCommand command = Connection.CreateCommand();
                    command.CommandText = "SELECT * FROM client";

                    using (MySqlDataReader reader = command.ExecuteReader())
                    {

                        while (reader.Read())
                        {

                            int numero = reader.GetInt16("numero");
                            string nif = reader.GetString("nif");
                            string nom = reader.GetString("nom");
                            string cognom1 = reader.GetString("cognom1");
                            //checkeja null
                            string cognom2 = reader[5] as string;
                            DateTime dataNaix = reader.GetDateTime("data_naix");
                            

                            clients.Add(new Client(numero, nif, nom, cognom1, cognom2, dataNaix));

                        }

                    }

                }

            }
            catch (MySqlException e)
            {
                Debug.WriteLine("ERROR BD:  " + e.Message);
            }


            return clients;
        }


        public ObservableCollection<Perit> getLlistaPerits() {

            ObservableCollection<Perit> perits = new ObservableCollection<Perit>();

            try
            {
                EncodingProvider provider = CodePagesEncodingProvider.Instance;
                Encoding.RegisterProvider(provider);            


                using (Connection = new MySqlConnection(ConnectionString))
                {

                    Connection.Open();
                    MySqlCommand command = Connection.CreateCommand();
                    command.CommandText = "SELECT * FROM perit";

                    using (MySqlDataReader reader = command.ExecuteReader())
                    {

                        while (reader.Read())
                        {

                            int numero = reader.GetInt16("numero");
                            string nif = reader.GetString("nif");
                            string nom = reader.GetString("nom");
                            string cognom1 = reader.GetString("cognom1");
                            //checkeja null                    
                            string cognom2;
                            if (reader.IsDBNull(reader.GetOrdinal("cognom2"))) cognom2 = null;
                            else cognom2 = reader.GetString(reader.GetOrdinal("cognom2"));                          
                            DateTime dataNaix = reader.GetDateTime("data_naix");
                            string loggin = reader.GetString("login");
                            string password = reader.GetString("password");

                            Debug.WriteLine("Cognom: " + cognom2);

                            perits.Add(new Perit(numero, nif, nom, cognom1, cognom2, dataNaix, loggin, password));

                        }

                    }

                }

                foreach (Perit p in perits) {
                    p.Cites = getCitesByPeritId(p.Numero);


                }

            }

            

            catch (MySqlException e) {
                Debug.WriteLine("ERROR BD:  " + e.Message);
            }



            return perits;

        }

        private ObservableCollection<Cita> getCitesByPeritId(int idPerit)
        {
            ObservableCollection<Cita> cites = new ObservableCollection<Cita>();

            try
            {
                EncodingProvider provider = CodePagesEncodingProvider.Instance;
                Encoding.RegisterProvider(provider);


                using (Connection = new MySqlConnection(ConnectionString))
                {

                    Connection.Open();
                    MySqlCommand command = Connection.CreateCommand();
                    command.CommandText = "SELECT * FROM cita where num_perit = @idPerit";
                    command.Parameters.Add(new MySqlParameter("idPerit", idPerit));

                    using (MySqlDataReader reader = command.ExecuteReader())
                    {

                        while (reader.Read())
                        {

                            int id = reader.GetInt16("id");
                            int numPerit = reader.GetInt16("num_perit");
                            DateTime diaHora = reader.GetDateTime("dia_hora");
                            int numSinistre = reader.GetInt16("num_sinistre");
                            int duracio = reader.GetInt16("duracio");
                           

                            cites.Add(new Cita(id,numPerit,diaHora,numSinistre,duracio));

                        }
                    }
                }               
            }
            catch (MySqlException e)
            {
                Debug.WriteLine("ERROR BD:  " + e.Message);
            }



            return cites;
        }

        public ObservableCollection<Sinistre> getLlistaSinistres(int numPerit)
        {

            ObservableCollection<Sinistre> sinistres = new ObservableCollection<Sinistre>();

            try
            {
                EncodingProvider provider = CodePagesEncodingProvider.Instance;
                Encoding.RegisterProvider(provider);


                using (Connection = new MySqlConnection(ConnectionString))
                {

                    Connection.Open();
                    MySqlCommand command = Connection.CreateCommand();
                    command.CommandText = "SELECT * FROM sinistre where num_perit = @numPerit";
                    command.Parameters.Add(new MySqlParameter("numPerit", numPerit));

                    using (MySqlDataReader reader = command.ExecuteReader())
                    {

                        while (reader.Read())
                        {

                            int numero = reader.GetInt16("numero");
                            object dataAssig = reader["data_assignacio"];
                            object dataTanc = reader["data_tancament"];
                            DateTime? dataAssignacio = (dataAssig == null) ? (DateTime?)null : Convert.ToDateTime(dataAssig);
                            DateTime dataObertura = reader.GetDateTime("data_obertura");
                            DateTime? dataTancament = (dataTanc == null) ? (DateTime?)null : Convert.ToDateTime(dataTanc);
                            string descripcio = reader.GetString("descripcio");
                            int numPolissa = reader.GetInt16("num_polissa");
                            int idPerit;
                            if (reader.IsDBNull(reader.GetOrdinal("num_perit"))) idPerit = 0;
                            else idPerit = reader.GetInt16(reader.GetOrdinal("num_perit"));

                            Enums.TIPUS_SINISTRE tipusSinistre = Enums.getTipusSinistreFromString(reader.GetString("tipus_sinistre"));
                            Enums.ESTAT_SINISTRE estatSinistre = Enums.getEstatSinistreFromString(reader.GetString("estat_sinistre"));
                           

                            Debug.WriteLine("Sinistre: " + numero);

                            sinistres.Add(null);

                        }

                    }

                }

            }
            catch (MySqlException e)
            {
                Debug.WriteLine("ERROR BD:  " + e.Message);
            }

            return sinistres;
        }

        public ObservableCollection<Sinistre> getSinistresFiltrats(DateTime? dataObertura, Enums.ESTAT_SINISTRE estatSinistre, int clientCod, int peritCod) {

            ObservableCollection<Sinistre> sinistresFiltrats = new ObservableCollection<Sinistre>();

            try
            {
                EncodingProvider provider = CodePagesEncodingProvider.Instance;
                Encoding.RegisterProvider(provider);


                using (Connection = new MySqlConnection(ConnectionString))
                {

                    Connection.Open();
                    MySqlCommand command = Connection.CreateCommand();
                    if (clientCod > 0)
                    {
                        command.CommandText = "SELECT * FROM sinistre s join polissa p on(s.num_polissa = p.numero) where 1=1 and p.num_client = @clientCod ";
                        command.Parameters.Add(new MySqlParameter("clientCod", clientCod));
                    }
                    else {
                        command.CommandText = "SELECT * FROM sinistre where 1=1 ";
                    }
                    
                    if (dataObertura != null)
                    {
                        command.CommandText += "and data_obertura = @dataSinistre ";
                        command.Parameters.Add(new MySqlParameter("dataSinistre", dataObertura));
                    }
                    if (estatSinistre != Enums.ESTAT_SINISTRE.NOTHING)
                    {
                        command.CommandText += "and estat_sinistre = @estatSinistre ";
                        command.Parameters.Add(new MySqlParameter("estatSinistre", estatSinistre.ToString()));
                    }
                    if (peritCod != 0)
                    {
                        command.CommandText += "and num_perit = @peritCod";
                        command.Parameters.Add(new MySqlParameter("peritCod", peritCod));
                    }

                    using (MySqlDataReader reader = command.ExecuteReader())
                    {

                        while (reader.Read())
                        {

                            int numero = reader.GetInt16("numero");
                            object dataAssig = reader["data_assignacio"];
                            object dataTanc = reader["data_tancament"];
                            DateTime? dataAssignacio = (dataAssig == null) ? (DateTime?)null : Convert.ToDateTime(dataAssig);
                            DateTime dObertura = reader.GetDateTime("data_obertura");
                            DateTime? dataTancament = (dataTanc == null) ? (DateTime?)null : Convert.ToDateTime(dataTanc);
                            string descripcio = reader.GetString("descripcio");
                            int numPolissa = reader.GetInt16("num_polissa");
                            int idPerit;                            
                            if (reader.IsDBNull(reader.GetOrdinal("num_perit"))) idPerit = 0;
                            else idPerit = reader.GetInt16(reader.GetOrdinal("num_perit"));

                            Enums.TIPUS_SINISTRE tipusSinistre = Enums.getTipusSinistreFromString(reader.GetString("tipus_sinistre"));
                            Enums.ESTAT_SINISTRE eSinistre = Enums.getEstatSinistreFromString(reader.GetString("estat_sinistre"));


                            Debug.WriteLine("Tipussinistre: " + tipusSinistre );

                            sinistresFiltrats.Add(new Sinistre(numero,dataAssignacio,dObertura,dataTancament,descripcio,numPolissa,idPerit,tipusSinistre,eSinistre));

                        }

                    }
                }

               

            }catch (MySqlException e)
            {
                Debug.WriteLine("ERROR BD:  " + e.Message);
            }

            getPolicesPerId(sinistresFiltrats);
            getPeritsPerId(sinistresFiltrats);
            getTrucadesPerIdSinistre(sinistresFiltrats);
            getInformesPericialsPerIdSinistre(sinistresFiltrats);

            return sinistresFiltrats;
        }        

        private void getPolicesPerId(ObservableCollection<Sinistre> sinistresFiltrats)
        {
            foreach (Sinistre s in sinistresFiltrats) {

                int idPolissa = s.NumPolissa;

                try
                {
                    EncodingProvider provider = CodePagesEncodingProvider.Instance;
                    Encoding.RegisterProvider(provider);


                    using (Connection = new MySqlConnection(ConnectionString))
                    {

                        Connection.Open();
                        MySqlCommand command = Connection.CreateCommand();
                        command.CommandText = "SELECT * FROM polissa where numero = @idPolissa";
                        command.Parameters.Add(new MySqlParameter("idPolissa", idPolissa));

                        using (MySqlDataReader reader = command.ExecuteReader())
                        {

                            while (reader.Read())
                            {

                                int numero = reader.GetInt16("numero");
                                DateTime dataInici = reader.GetDateTime("data_inici"); 
                                DateTime dataFi = reader.GetDateTime("data_fi");
                                Decimal import = reader.GetDecimal("import_polissa");
                                Decimal importContinent = reader.GetDecimal("import_continent");
                                Decimal importContingut = reader.GetDecimal("import_contingut");
                                int numClient = reader.GetInt16("num_client");
                                string poblacio = reader.GetString("poblacio");
                                string liniaAdreca = reader.GetString("linia_adreca");
                                Enums.TIPUS_HABITATGE tipusHabitatge = Enums.getTipusHabitatgeFromString(reader.GetString("tipus_habitatge"));

                                Client c = getClientById(numClient);

                                s.Polissa = new Polissa(numero, dataInici, dataFi, import, importContinent, importContingut, c, poblacio, liniaAdreca, tipusHabitatge);

                            }

                        }

                    }

                }
                catch (MySqlException e)
                {
                    Debug.WriteLine("ERROR BD:  " + e.Message);
                }

            }
        }

        private void getPeritsPerId(ObservableCollection<Sinistre> sinistresFiltrats)
        {
            foreach (Sinistre s in sinistresFiltrats) {

                int idPerit = s.IdPerit;

                try
                {
                    EncodingProvider provider = CodePagesEncodingProvider.Instance;
                    Encoding.RegisterProvider(provider);


                    using (Connection = new MySqlConnection(ConnectionString))
                    {

                        Connection.Open();
                        MySqlCommand command = Connection.CreateCommand();
                        command.CommandText = "SELECT * FROM perit where numero = @idPerit";
                        command.Parameters.Add(new MySqlParameter("idPerit", idPerit));
                        using (MySqlDataReader reader = command.ExecuteReader())
                        {

                            while (reader.Read())
                            {

                                int numero = reader.GetInt16("numero");
                                string nif = reader.GetString("nif");
                                string nom = reader.GetString("nom");
                                string cognom1 = reader.GetString("cognom1");
                                //checkeja null
                                string cognom2 = reader[5] as string;
                                DateTime dataNaix = reader.GetDateTime("data_naix");
                                string loggin = reader.GetString("login");
                                string password = reader.GetString("password");

                                s.Perit = new Perit(numero, nif, nom, cognom1, cognom2, dataNaix, loggin, password);

                            }

                        }

                    }

                }
                catch (MySqlException e)
                {
                    Debug.WriteLine("ERROR BD:  " + e.Message);
                }

            }
        }

        private void getTrucadesPerIdSinistre(ObservableCollection<Sinistre> sinistresFiltrats)
        {
            foreach (Sinistre s in sinistresFiltrats) {

                int idSinistre = s.Numero;

                try
                {
                    EncodingProvider provider = CodePagesEncodingProvider.Instance;
                    Encoding.RegisterProvider(provider);


                    using (Connection = new MySqlConnection(ConnectionString))
                    {

                        Connection.Open();
                        MySqlCommand command = Connection.CreateCommand();
                        command.CommandText = "SELECT * FROM trucada where num_sinistre = @idSinistre";
                        command.Parameters.Add(new MySqlParameter("idSinistre", idSinistre));
                        using (MySqlDataReader reader = command.ExecuteReader())
                        {
                            ObservableCollection<Trucada> trucadesSinistre = new ObservableCollection<Trucada>();
                            while (reader.Read())
                            {

                                int numero = reader.GetInt16("num_sinistre");
                                DateTime dataHora = reader.GetDateTime("data_hora");
                                string descripcio = reader.GetString("descripcio");
                                string personaContacte = reader.GetString("persona_contacte");


                                trucadesSinistre.Add(new Trucada(numero, dataHora, descripcio, personaContacte));

                            }

                            s.Trucades = trucadesSinistre;

                        }

                    }

                }
                catch (MySqlException e)
                {
                    Debug.WriteLine("ERROR BD:  " + e.Message);
                }

            }
        }

        private void getInformesPericialsPerIdSinistre(ObservableCollection<Sinistre> sinistresFiltrats)
        {

            foreach (Sinistre s in sinistresFiltrats)
            {

                int idSinistre = s.Numero;

                try
                {
                    EncodingProvider provider = CodePagesEncodingProvider.Instance;
                    Encoding.RegisterProvider(provider);


                    using (Connection = new MySqlConnection(ConnectionString))
                    {

                        Connection.Open();
                        MySqlCommand command = Connection.CreateCommand();
                        command.CommandText = "SELECT * FROM informe_pericial where num_sinistre = @idSinistre";
                        command.Parameters.Add(new MySqlParameter("idSinistre", idSinistre));
                        using (MySqlDataReader reader = command.ExecuteReader())
                        {

                            while (reader.Read())
                            {

                                int numero = reader.GetInt16("num_sinistre");
                                DateTime dataEmisio = reader.GetDateTime("data_emisio");
                                Decimal importCobert = reader.GetDecimal("Import_cobert");
                                string informe = reader.GetString("informe");
                                Enums.ESTAT_INFORME estatInforme = Enums.getEstatInformeFromString(reader.GetString("estat_informe"));
                                Enums.RESULTAT_PERITATGE resultatPeritatge = Enums.getResultatPeritatgeFromString(reader.GetString("resultat_peritatge"));

                                s.Informe = new InformePericial(numero, dataEmisio, importCobert, informe, s.Perit, estatInforme, resultatPeritatge);

                            }
                            if (s.Informe != null)
                            {
                                s.Informe.Entrades = getEntradesById(idSinistre);
                            }

                        }

                    }

                }
                catch (MySqlException e)
                {
                    Debug.WriteLine("ERROR BD:  " + e.Message);
                }
            }
        }

        private ObservableCollection<EntradaInforme> getEntradesById(int idSinistre)
        {
            ObservableCollection<EntradaInforme> entrades = new ObservableCollection<EntradaInforme>();
            
            try
            {
                EncodingProvider provider = CodePagesEncodingProvider.Instance;
                Encoding.RegisterProvider(provider);


                using (Connection = new MySqlConnection(ConnectionString))
                {

                    Connection.Open();
                    MySqlCommand command = Connection.CreateCommand();
                    command.CommandText = "SELECT * FROM entrada_informe where numero = @idSinistre";
                    command.Parameters.Add(new MySqlParameter("idSinistre", idSinistre));
                    using (MySqlDataReader reader = command.ExecuteReader())
                    {

                        while (reader.Read())
                        {

                            int numero = reader.GetInt16("numero");
                            DateTime dataInforme = reader.GetDateTime("data_informe");
                            string descripcio = reader.GetString("descripcio");
                            bool postReparacio = reader.GetInt16("despres_reparacio") == 1;
                            Byte[] imatge = (byte[])reader["foto"];
                            
                            entrades.Add(new EntradaInforme(numero, dataInforme, descripcio, postReparacio, imatge));                            
                        }

                      

                    }

                }

            }
            catch (MySqlException e)
            {
                Debug.WriteLine("ERROR BD:  " + e.Message);
            }

            return entrades;
        }

        private List<Int64> getMidaFotos(int idSinistre)
        {
            List<Int64> midesFotos = new List<Int64>();

            try
            {
                EncodingProvider provider = CodePagesEncodingProvider.Instance;
                Encoding.RegisterProvider(provider);


                using (Connection = new MySqlConnection(ConnectionString))
                {

                    Connection.Open();
                    MySqlCommand command = Connection.CreateCommand();
                    command.CommandText = "SELECT OCTET_LENGTH(foto) FROM entrada_informe where numero = @idSinistre order by ordre";
                    command.Parameters.Add(new MySqlParameter("idSinistre", idSinistre));
                    using (MySqlDataReader reader = command.ExecuteReader())
                    {

                        while (reader.Read())
                        {

                            Int64 midaFoto = reader.GetInt64("OCTET_LENGTH(foto)");
                            midesFotos.Add(midaFoto);
                        }
                        

                    }

                }

            }
            catch (MySqlException e)
            {
                Debug.WriteLine("ERROR BD:  " + e.Message);
            }

            return midesFotos;
        }

        private Client getClientById(int numClient) {

            foreach (Client c in Clients) {
                if (c.Numero == numClient) return c;
            }

            return null;
        }

        private Int64 countInformeFromSinistre(int idSinistre) {

            try
            {
                EncodingProvider provider = CodePagesEncodingProvider.Instance;
                Encoding.RegisterProvider(provider);


                using (Connection = new MySqlConnection(ConnectionString))
                {

                    Connection.Open();
                    MySqlCommand command = Connection.CreateCommand();
                    command.CommandText = "select count(num_sinistre) from informe_pericial where num_sinistre = @idSinistre";
                    command.Parameters.Add(new MySqlParameter("idSinistre", idSinistre));

                  return (Int64)command.ExecuteScalar();

                }

            }
            catch (MySqlException e)
            {
                Debug.WriteLine("ERROR BD:  " + e.Message);
            }

            return 0;


        }

        public bool ActualitzarPeritSinistre(int idSinistre, int idPerit,out string missatgeError) {

            missatgeError = "";
            if (countInformeFromSinistre(idSinistre) == 0)
            {

                try
                {
                    EncodingProvider provider = CodePagesEncodingProvider.Instance;
                    Encoding.RegisterProvider(provider);


                    using (Connection = new MySqlConnection(ConnectionString))
                    {

                        Connection.Open();
                        MySqlCommand command = Connection.CreateCommand();
                        command.CommandText = "Update sinistre set num_perit = @idPerit where numero = @idSinistre";
                        command.Parameters.Add(new MySqlParameter("idPerit", idPerit));
                        command.Parameters.Add(new MySqlParameter("idSinistre", idSinistre));    

                        command.ExecuteNonQuery();
                      
                    }

                    
                    using (Connection = new MySqlConnection(ConnectionString))
                    {

                        Connection.Open();
                        MySqlCommand command = Connection.CreateCommand();
                        command.CommandText = "Update sinistre set estat_sinistre = 'ASSIGNAT' where numero = @idSinistre";                  
                        command.Parameters.Add(new MySqlParameter("idSinistre", idSinistre));

                        command.ExecuteNonQuery();
                        return true;
                    }

                }
                catch (MySqlException e)
                {
                    Debug.WriteLine("ERROR BD:  " + e.Message);
                }



            }
            missatgeError = "El Perit ja ha començat a treballar en aquest sinistre, no es pot realitzar el canvi.";
            return false;

        }

        public bool ActualitzarEstatSinistre(int idSinistre, string estatSinistre) {

            try
            {
                EncodingProvider provider = CodePagesEncodingProvider.Instance;
                Encoding.RegisterProvider(provider);


                using (Connection = new MySqlConnection(ConnectionString))
                {

                    Connection.Open();
                    MySqlCommand command = Connection.CreateCommand();
                    command.CommandText = "Update sinistre set estat_sinistre = @estatSinistre where numero = @idSinistre";
                    command.Parameters.Add(new MySqlParameter("estatSinistre", estatSinistre));
                    command.Parameters.Add(new MySqlParameter("idSinistre", idSinistre));

                    command.ExecuteNonQuery();
                    return true;
                }

            }
            catch (MySqlException e)
            {
                Debug.WriteLine("ERROR BD:  " + e.Message);
            }
            return false;

        }

        public void insertDemoImages() {          


            byte[] robatori1 = File.ReadAllBytes("G:\\DAM 2.1\\M12Project\\NullPointerSeguros\\project\\NullPointerSegurosApp\\bin\\x86\\Debug\\AppX\\Assets\\robatori1.jpg");
            byte[] robatori2 = File.ReadAllBytes("G:\\DAM 2.1\\M12Project\\NullPointerSeguros\\project\\NullPointerSegurosApp\\bin\\x86\\Debug\\AppX\\Assets\\robatori2.jpg");
            byte[] robatori3 = File.ReadAllBytes("G:\\DAM 2.1\\M12Project\\NullPointerSeguros\\project\\NullPointerSegurosApp\\bin\\x86\\Debug\\AppX\\Assets\\robatori3.jpg");

            byte[] humitats = File.ReadAllBytes("G:\\DAM 2.1\\M12Project\\NullPointerSeguros\\project\\NullPointerSegurosApp\\bin\\x86\\Debug\\AppX\\Assets\\humitats.jpg");

            insertFotoInEntradaInforme(robatori1, 1, 1);
            insertFotoInEntradaInforme(robatori2, 1, 2);
            insertFotoInEntradaInforme(robatori3, 1,3);

            insertFotoInEntradaInforme(humitats, 2, 1);


        }

        private void insertFotoInEntradaInforme(byte[] rawData, int idSinistre, int numEntrada) {

            try
            {
                EncodingProvider provider = CodePagesEncodingProvider.Instance;
                Encoding.RegisterProvider(provider);


                using (Connection = new MySqlConnection(ConnectionString))
                {

                    Connection.Open();
                    MySqlCommand command = Connection.CreateCommand();
                    command.CommandText = "Update entrada_informe set foto = @foto where numero = @idSinistre and ordre = @numEntrada";
                    command.Parameters.Add(new MySqlParameter("idSinistre", idSinistre));
                    command.Parameters.Add(new MySqlParameter("numEntrada", numEntrada));
                    command.Parameters.Add(new MySqlParameter("foto", rawData));

                    command.ExecuteNonQuery();

                }

            }
            catch (MySqlException e)
            {
                Debug.WriteLine("ERROR BD:  " + e.Message);
            }





        }


    }

    

    }

