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

        public ObservableCollection<Client> getLlistaClients()
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
                            DateTime dataNaix = Convert.ToDateTime(reader["data_naix"]);
                            

                            clients.Add(new Client(numero, nif, nom, cognom1, cognom2, dataNaix));

                        }

                    }

                    

                }

            }
            catch (MySqlException e)
            {
                Debug.WriteLine("ERROR BD GetLlistaClients:  " + e.Message);
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
                            DateTime dataNaix = Convert.ToDateTime(reader["data_naix"]);
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
                Debug.WriteLine("ERROR BD GetLlistaPerits:  " + e.Message);
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
                            DateTime diaHora = Convert.ToDateTime(reader["dia_hora"]);
                            int numSinistre = reader.GetInt16("num_sinistre");
                            int duracio = reader.GetInt16("duracio");
                           

                            cites.Add(new Cita(id,numPerit,diaHora,numSinistre,duracio));

                        }
                    }
                }               
            }
            catch (MySqlException e)
            {
                Debug.WriteLine("ERROR BD getCitesByPeritId:  " + e.Message);
            }



            return cites;
        }

        public ObservableCollection<Sinistre> getLlistaSinistresSenseCita(int numPerit) {
            ObservableCollection<Sinistre> sinistres = new ObservableCollection<Sinistre>();

            try
            {
                EncodingProvider provider = CodePagesEncodingProvider.Instance;
                Encoding.RegisterProvider(provider);


                using (Connection = new MySqlConnection(ConnectionString))
                {

                    Connection.Open();
                    MySqlCommand command = Connection.CreateCommand();
                    command.CommandText = "SELECT * FROM sinistre where num_perit = @numPerit and numero not in(select num_sinistre from cita)";
                    command.Parameters.Add(new MySqlParameter("numPerit", numPerit));

                    using (MySqlDataReader reader = command.ExecuteReader())
                    {

                        while (reader.Read())
                        {

                            int numero = reader.GetInt16("numero");
                            object dataAssig = reader["data_assignacio"];
                            object dataTanc = reader["data_tancament"];
                            DateTime? dataAssignacio = (dataAssig == null) ? (DateTime?)null : Convert.ToDateTime(dataAssig);
                            DateTime dataObertura = Convert.ToDateTime(reader["data_obertura"]);
                            DateTime? dataTancament = (dataTanc == null) ? (DateTime?)null : Convert.ToDateTime(dataTanc);
                            string descripcio = reader.GetString("descripcio");
                            int numPolissa = reader.GetInt16("num_polissa");
                            int idPerit;
                            if (reader.IsDBNull(reader.GetOrdinal("num_perit"))) idPerit = 0;
                            else idPerit = reader.GetInt16(reader.GetOrdinal("num_perit"));

                            Enums.TIPUS_SINISTRE tipusSinistre = Enums.getTipusSinistreFromString(reader.GetString("tipus_sinistre"));
                            Enums.ESTAT_SINISTRE estatSinistre = Enums.getEstatSinistreFromString(reader.GetString("estat_sinistre"));


                            Debug.WriteLine("Sinistre: " + numero);

                            sinistres.Add(new Sinistre(numero, dataAssignacio, dataObertura, dataTancament, descripcio, numPolissa, idPerit, tipusSinistre, estatSinistre));

                        }

                    }

                }

            }
            catch (MySqlException e)
            {
                Debug.WriteLine("ERROR BD:  " + e.Message);
            }
            getPolicesPerId(sinistres);
            getPeritsPerId(sinistres);
            getTrucadesPerIdSinistre(sinistres);
            getInformesPericialsPerIdSinistre(sinistres);

            return sinistres;


        }

        public ObservableCollection<Sinistre> getLlistaSinistres()
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
                    command.CommandText = "SELECT * FROM sinistre";            

                    using (MySqlDataReader reader = command.ExecuteReader())
                    {

                        while (reader.Read())
                        {

                            int numero = reader.GetInt16("numero");
                            object dataAssig = reader["data_assignacio"];
                            object dataTanc = reader["data_tancament"];
                            DateTime? dataAssignacio = (dataAssig == null) ? (DateTime?)null : Convert.ToDateTime(dataAssig);
                            DateTime dataObertura = Convert.ToDateTime(reader["data_obertura"]);
                            DateTime? dataTancament = (dataTanc == null) ? (DateTime?)null : Convert.ToDateTime(dataTanc);
                            string descripcio = reader.GetString("descripcio");
                            int numPolissa = reader.GetInt16("num_polissa");
                            int idPerit;
                            if (reader.IsDBNull(reader.GetOrdinal("num_perit"))) idPerit = 0;
                            else idPerit = reader.GetInt16(reader.GetOrdinal("num_perit"));

                            Enums.TIPUS_SINISTRE tipusSinistre = Enums.getTipusSinistreFromString(reader.GetString("tipus_sinistre"));
                            Enums.ESTAT_SINISTRE estatSinistre = Enums.getEstatSinistreFromString(reader.GetString("estat_sinistre"));
                           

                            Debug.WriteLine("Sinistre: " + numero);

                            sinistres.Add(new Sinistre(numero, dataAssignacio, dataObertura, dataTancament, descripcio, numPolissa, idPerit, tipusSinistre, estatSinistre));

                        }

                    }

                }

            }
            catch (MySqlException e)
            {
                Debug.WriteLine("ERROR BD:  " + e.Message);
            }
            getPolicesPerId(sinistres);
            getPeritsPerId(sinistres);
            getTrucadesPerIdSinistre(sinistres);
            getInformesPericialsPerIdSinistre(sinistres);

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
                            //object dataOb = reader["data_obertura"];
                            DateTime? dataAssignacio = (dataAssig == null) ? (DateTime?)null : Convert.ToDateTime(dataAssig);
                            DateTime dObertura = Convert.ToDateTime(reader["data_obertura"]);
                            DateTime? dataTancament = (dataTanc == null) ? (DateTime?)null : Convert.ToDateTime(dataTanc);
                            string descripcio = reader.GetString("descripcio");
                            int numPolissa = reader.GetInt16("num_polissa");
                            int idPerit;                            
                            if (reader.IsDBNull(reader.GetOrdinal("num_perit"))) idPerit = 0;
                            else idPerit = reader.GetInt16(reader.GetOrdinal("num_perit"));


                            Enums.TIPUS_SINISTRE tipusSinistre = Enums.getTipusSinistreFromString(reader.GetString("tipus_sinistre"));
                            Enums.ESTAT_SINISTRE eSinistre = Enums.getEstatSinistreFromString(reader.GetString("estat_sinistre"));


                            Debug.WriteLine("SINISTRE DATA: " + dObertura + " ASS: " + dataAssignacio);

                            sinistresFiltrats.Add(new Sinistre(numero,dataAssignacio,dObertura,dataTancament,descripcio,numPolissa,idPerit,tipusSinistre,eSinistre));

                        }

                    }
                }

               

            }catch (MySqlException e)
            {
                Debug.WriteLine("ERROR BD GetSinistresFiltrats:  " + e.Message);
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
                                DateTime dataInici = Convert.ToDateTime(reader["data_inici"]);
                                DateTime dataFi = Convert.ToDateTime(reader["data_fi"]);
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
                    Debug.WriteLine("ERROR BD getPolicesPerId:  " + e.Message);
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
                                DateTime dataNaix = Convert.ToDateTime(reader["data_naix"]);
                                string loggin = reader.GetString("login");
                                string password = reader.GetString("password");

                                s.Perit = new Perit(numero, nif, nom, cognom1, cognom2, dataNaix, loggin, password);

                            }

                        }

                    }

                }
                catch (MySqlException e)
                {
                    Debug.WriteLine("ERROR BD getPeritsPerId:  " + e.Message);
                }


                //TODO fer el get cites per num Sinistre.
                if (s.Perit != null)
                {
                    s.Perit.Cites = getCitesByPeritId(s.Perit.Numero);
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
                                DateTime dataHora = Convert.ToDateTime(reader["data_hora"]);
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
                    Debug.WriteLine("ERROR BD getTrucadesPerIdSinistre:  " + e.Message);
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
                                DateTime dataEmisio = Convert.ToDateTime(reader["data_emisio"]);
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
                    Debug.WriteLine("ERROR BD GetInformesPericialsPerIdSinistre:  " + e.Message);
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
                            DateTime dataInforme = Convert.ToDateTime(reader["data_informe"]);
                            string descripcio = reader.GetString("descripcio");
                            bool postReparacio = reader.GetInt16("despres_reparacio") == 1;
                            Byte[] imatge = (byte[])reader["foto"];
                            Debug.WriteLine("DATA ENTRADA = " + dataInforme);



                            entrades.Add(new EntradaInforme(numero, dataInforme, descripcio, postReparacio, imatge));                            
                        }

                      

                    }

                }

            }
            catch (MySqlException e)
            {
                Debug.WriteLine("ERROR BD getEntradesById:  " + e.Message);
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
                Debug.WriteLine("ERROR BD getMidaFotos:  " + e.Message);
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
                Debug.WriteLine("ERROR BD countInformeFromSinistre:  " + e.Message);
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

                    
                    //using (Connection = new MySqlConnection(ConnectionString))
                    //{

                    //    Connection.Open();
                    //    MySqlCommand command = Connection.CreateCommand();
                    //    command.CommandText = "Update sinistre set estat_sinistre = 'ASSIGNAT' where numero = @idSinistre";                  
                    //    command.Parameters.Add(new MySqlParameter("idSinistre", idSinistre));

                    //    command.ExecuteNonQuery();
                    //    return true;
                    //}

                }
                catch (MySqlException e)
                {
                    Debug.WriteLine("ERROR BD ActualitzarPeritSinistre:  " + e.Message);
                }



            }
            missatgeError = "El Perit ja ha començat a treballar en aquest sinistre, no es pot realitzar el canvi.";
            return false;

        }

        public bool ActualitzarEstatSinistre(int idSinistre, string estatSinistre) {

            string comanda = "";
            DateTime ara = DateTime.Now;

            try
            {
                EncodingProvider provider = CodePagesEncodingProvider.Instance;
                Encoding.RegisterProvider(provider);


                using (Connection = new MySqlConnection(ConnectionString))
                {

                    Connection.Open();
                    MySqlCommand command = Connection.CreateCommand();

                    if (estatSinistre.Equals("TANCAT"))
                    {
                        comanda = "Update sinistre set estat_sinistre = @estatSinistre, data_tancament = @ara where numero = @idSinistre";
                        command.Parameters.Add(new MySqlParameter("ara", ara));
                    }
                    else if (estatSinistre.Equals("ASSIGNAT")) {
                        comanda = "Update sinistre set estat_sinistre = @estatSinistre, data_assignacio = @ara where numero = @idSinistre";
                        command.Parameters.Add(new MySqlParameter("ara", ara));
                    }

                    command.CommandText = comanda;
                    command.Parameters.Add(new MySqlParameter("estatSinistre", estatSinistre));
                    command.Parameters.Add(new MySqlParameter("idSinistre", idSinistre));

                    command.ExecuteNonQuery();
                    Connection.Close();
                    return true;
                }

            }
            catch (MySqlException e)
            {
                Debug.WriteLine("ERROR BD actualitzarEstatSinistre:  " + e.Message);
            }
            return false;

        }

        
        public int insertNouPerit(string nif, string nom, string cognom1, string cognom2, DateTime dataNaix, string loggin, string password)
        {
            int numPerit = getInsertIdPerit();            
            try
            {
                EncodingProvider provider = CodePagesEncodingProvider.Instance;
                Encoding.RegisterProvider(provider);

                Debug.WriteLine("P: " + numPerit + "," + nom + "," +  nif + "," + cognom1 + "," + cognom2 + "," + dataNaix + "," + loggin + "," + password);

                using (Connection = new MySqlConnection(ConnectionString))
                {

                    Connection.Open();
                    MySqlCommand command = Connection.CreateCommand();
                    command.CommandText = "Insert into Perit values(@numPerit,@nif,@nom, @cognom1,@cognom2,@dataNaix,@loggin,@password)";
                    command.Parameters.Add(new MySqlParameter("numPerit", numPerit));
                    command.Parameters.Add(new MySqlParameter("nif", nif));
                    command.Parameters.Add(new MySqlParameter("nom", nom));
                    command.Parameters.Add(new MySqlParameter("cognom1", cognom1));
                    command.Parameters.Add(new MySqlParameter("cognom2", cognom2));
                    command.Parameters.Add(new MySqlParameter("dataNaix", dataNaix));
                    command.Parameters.Add(new MySqlParameter("loggin", loggin));
                    command.Parameters.Add(new MySqlParameter("password", password));

                    command.ExecuteNonQuery();

                }

            }
            catch (MySqlException e)
            {
                Debug.WriteLine("ERROR BD INSERT PERIT:  " + e.Message);
            }
            return numPerit;
        }

        public void actualitzarPerit(int numPerit,string nif,string nom, string cognom1, string cognom2, DateTime dataNaix, string loggin, string password)
        {
            
            try
            {
                EncodingProvider provider = CodePagesEncodingProvider.Instance;
                Encoding.RegisterProvider(provider);


                using (Connection = new MySqlConnection(ConnectionString))
                {

                    Connection.Open();
                    MySqlCommand command = Connection.CreateCommand();
                    command.CommandText = "Update perit set nif = @nif,nom = @nom, cognom1 = @cognom1, cognom2 = @cognom2, data_naix = @dataNaix, login = @loggin, password = @password where numero = @numPerit";
                    command.Parameters.Add(new MySqlParameter("numPerit", numPerit));
                    command.Parameters.Add(new MySqlParameter("nif", nif));
                    command.Parameters.Add(new MySqlParameter("nom", nom));
                    command.Parameters.Add(new MySqlParameter("cognom1", cognom1));
                    command.Parameters.Add(new MySqlParameter("cognom2", cognom2));
                    command.Parameters.Add(new MySqlParameter("dataNaix", dataNaix));
                    command.Parameters.Add(new MySqlParameter("loggin", loggin));
                    command.Parameters.Add(new MySqlParameter("password", password));

                    command.ExecuteNonQuery();

                }

            }
            catch (MySqlException e)
            {
                Debug.WriteLine("ERROR Actualitzant Perit BD:  " + e.Message);
            }

        }

        public void deletePerit(int numPerit, out string misstageError)
        {
            misstageError = "";
            try
            {
                EncodingProvider provider = CodePagesEncodingProvider.Instance;
                Encoding.RegisterProvider(provider);


                using (Connection = new MySqlConnection(ConnectionString))
                {

                    Connection.Open();
                    MySqlCommand command = Connection.CreateCommand();
                    command.CommandText = "Delete from perit where numero = @numPerit";
                    command.Parameters.Add(new MySqlParameter("numPerit", numPerit));

                    command.ExecuteNonQuery();

                }

            }
            catch (MySqlException e)
            {
                Debug.WriteLine("ERROR BD deletePerit:  " + e.Message);
                misstageError = "No es possible esborrar aquest perit, ja que ja te sinistres assignats";
            }

        }

        public int insertCita(int id,int numPerit, DateTime dataHora,int numSinistre,int duracio)
        {
            
            try
            {
                EncodingProvider provider = CodePagesEncodingProvider.Instance;
                Encoding.RegisterProvider(provider);

               

                using (Connection = new MySqlConnection(ConnectionString))
                {

                    Connection.Open();
                    MySqlCommand command = Connection.CreateCommand();
                    command.CommandText = "Insert into Cita values(@id,@numPerit,@dataHora, @numSinistre,@duracio)";
                    command.Parameters.Add(new MySqlParameter("id", id));
                    command.Parameters.Add(new MySqlParameter("numPerit", numPerit));
                    command.Parameters.Add(new MySqlParameter("dataHora", dataHora));
                    command.Parameters.Add(new MySqlParameter("numSinistre", numSinistre));
                    command.Parameters.Add(new MySqlParameter("duracio", duracio));          

                    command.ExecuteNonQuery();

                }

            }
            catch (MySqlException e)
            {
                Debug.WriteLine("ERROR BD INSERT CITA:  " + e.Message);
            }
            return numPerit;
        }

        public int getInsertIdPerit() {
            int index = 0;
            try
            {
               
                EncodingProvider provider = CodePagesEncodingProvider.Instance;
                Encoding.RegisterProvider(provider);


                using (Connection = new MySqlConnection(ConnectionString))
                {

                    Connection.Open();
                    MySqlCommand command = Connection.CreateCommand();
                    command.CommandText = "SELECT max(comptador) FROM comptadors where taula = 'perit'";
                    

                    using (MySqlDataReader reader = command.ExecuteReader())
                    {

                        if (reader.Read())
                        {
                            index = reader.GetInt16("max(comptador)") + 1;                            
                        }

                    }

                }

            }
            catch (MySqlException e)
            {
                Debug.WriteLine("ERROR GET ID PERIT SEGUENT BD:  " + e.Message);
            }

            ActualitzarComptadorsPerit(index);

            return index;
            

        }

        private void ActualitzarComptadorsPerit(int index)
        {
            try
            {
                EncodingProvider provider = CodePagesEncodingProvider.Instance;
                Encoding.RegisterProvider(provider);


                using (Connection = new MySqlConnection(ConnectionString))
                {

                    Connection.Open();
                    MySqlCommand command = Connection.CreateCommand();
                    command.CommandText = "Update comptadors set comptador = @index where taula = 'perit'";
                    command.Parameters.Add(new MySqlParameter("index", index));            

                    command.ExecuteNonQuery();

                }

            }
            catch (MySqlException e)
            {
                Debug.WriteLine("ERROR BD actualitzarComptadorsPerit:  " + e.Message);
            }
        }
    }

    

    }

