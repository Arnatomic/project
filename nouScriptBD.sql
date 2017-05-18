drop table if exists perit;
create table perit(
  numero int not null auto_increment,
  nif varchar(9) not null,
  nom varchar(30) not null,
  cognom1 varchar(30) not null,
  cognom2 varchar(30),
  data_naix date,
  login varchar(30),
  password varchar(50),
  
  primary key(numero),
  
  CHECK(numero >0),
  CHECK(length(nif)=9),
  CHECK(length(nom)>0),
  CHECK(length(cognom1)>0) ,
  CHECK(length(login)>0),
  CHECK(length(password)>0)

);


drop table if exists client;
create table client(
  numero int not null,
  nif varchar(9) not null,
  nom varchar(30) not null,
  cognom1 varchar(30) not null,
  cognom2 varchar(30),
  data_naix date,
  
  primary key(numero),
  
  CHECK(numero >0),
  CHECK(length(nif)=9),
  CHECK(length(nom)>0),
  CHECK(length(cognom1)>0)     
);


drop table if exists polissa;
create table polissa(
  numero int not null auto_increment,
  data_fi date not null,
  data_inici date not null,
  import_polissa double(9,2) not null,
  import_continent double(9,2) not null,
  import_contingut double(9,2) not null,
  numero_client int not null,
  poblacio varchar(40) not null,
  linia_adreca varchar(80) not null,
  tipus_habitatge varchar(20) not null,
  
  primary key(numero),
  
  CHECK(numero >0),
  CHECK(data_fi>data_inici),
  CHECK(import_polissa >0),
  CHECK(import_continent >0),
  CHECK(import_contingut >0),
  CHECK(length(poblacio)>2),
  CHECK(length(linia_adreca)>3),
  
  FOREIGN KEY (numero_client) REFERENCES client(numero)
  

);

drop table if exists cobertura;
create table cobertura(
  num_polissa int not null,
  codi int not null,
  descripcio varchar(100) not null,
  
  primary key(codi),
  CHECK(num_polissa>0),
  CHECK (codi>0),
  CHECK(length(descripcio)>0),
  
  FOREIGN KEY (num_polissa) REFERENCES polissa(numero)
  

);

drop table if exists sinistre;
create table sinistre(
  numero int not null auto_increment,
  data_assignacio date not null,
  data_obertura date,
  data_tancament date,
  descripcio varchar(100) not null,
  num_polissa int not null,
  num_perit int,
  tipus_sinistre varchar(20),
  estat_sinistre varchar(20),
  arxivat int,
  
  
  primary key(numero),
  
  CHECK (numero>0),
  CHECK(data_obertura >= data_assignacio),
  CHECK(data_tancament >= data_obertura),
  CHECK(length(descripcio)>0),
  CHECK(num_polissa>0),
  CHECK(length(tipus_sinistre)>0),
  CHECK(length(estat_sinistre)>0),
  CHECK(arxivat = 0 or arxivat = 1),
  
  FOREIGN KEY (num_polissa) REFERENCES polissa(numero),
  FOREIGN KEY (num_perit) REFERENCES perit(numero)
);



drop table if exists cita;
create table cita(
  num_perit int not null,
  dia_hora date not null,
  num_sinistre int not null,
  duracio int not null,
  
  primary key(num_perit,dia_hora),
  
  CHECK(length(num_perit)>0),
  CHECK(duracio >0),
  CHECK(num_sinistre>0),
  
  
  FOREIGN KEY (num_perit) REFERENCES perit(numero),
  FOREIGN KEY (num_sinistre) REFERENCES sinistre(numero)
  
);






drop table if exists trucada;
create table trucada(
  num_sinistre int not null,
  data_hora date not null,
  descripcio varchar(100) not null,
  persona_contacte varchar(30) not null,
  
  primary key(num_sinistre,data_hora),
  
  CHECK(num_sinistre>0),
  CHECK(length(descripcio)>0),
  CHECK(length(persona_contacte)>0),
  
  FOREIGN KEY (num_sinistre) REFERENCES sinistre(numero)
  

);

drop table if exists informe_pericial;
create table informe_pericial(
  num_sinistre int not null,
  data_emisio date not null,
  import_cobert double(8,2) not null,
  informe varchar(100),
  num_perit int not null,
  
  primary key(num_sinistre),
  
  CHECK(num_sinistre>0),
  CHECK(import_cobert >0),
  CHECK(num_perit>0),
  
  FOREIGN KEY (num_sinistre) REFERENCES sinistre(numero),
  FOREIGN KEY(num_perit) REFERENCES perit(numero)
  
);

drop table if exists entrada_informe;
create table entrada_informe(
  num_sinistre int not null,
  
  numero int not null auto_increment, 
  data_informe date not null,
  descripcio varchar(100) not null,
  despres_reparacio int(1) not null,
  foto blob,
  
  primary key(numero),
  
  CHECK(num_informe>0),
  CHECK(numero>0),
  CHECK(length(descripcio)>0),
  
  FOREIGN KEY (num_sinistre) REFERENCES informe_pericial(num_sinistre)

);



