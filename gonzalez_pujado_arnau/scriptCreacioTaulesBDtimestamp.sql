
create table comptadors(
	taula varchar(30) not null,
    comptador bigint not null,
    
    primary key(taula),
    CHECK(length(taula)>0),
    CHECK(comptador >=0)

);

create table perit(
  numero int not null,
  nif varchar(9) not null unique,
  nom varchar(30) not null,
  cognom1 varchar(30) not null,
  cognom2 varchar(30),
  data_naix timestamp not null,
  login varchar(30) not null unique,
  password varchar(32) not null,
  
  
  primary key(numero),
  
  CHECK(numero >0),
  CHECK(length(nif)=9),
  CHECK(length(nom)>0),
  CHECK(length(cognom1)>0) ,
  CHECK(length(login)>0),
  CHECK(length(password)>0)

);
/*
create table user_log(
	connection_code bigint not null,
    user_id int,
    user_name varchar(30),
    connection_time date not null,
    
    primary key (connection_code),
    
    CHECK(connection_code >0)
);
*/

create table user_online(
	connection_code bigint not null,
    user_id int unique,
    user_name varchar(30),
    connection_time timestamp not null,
    
    primary key (connection_code),
    
    CHECK(connection_code >0)
);





create table client(
  numero int not null,
  nif varchar(9) not null unique,
  nom varchar(30) not null,
  cognom1 varchar(30) not null,
  cognom2 varchar(30),
  data_naix timestamp not null,
  
  primary key(numero),
  
  CHECK(numero >0),
  CHECK(length(nif)=9),
  CHECK(length(nom)>0),
  CHECK(length(cognom1)>0)     
);



create table polissa(
  numero int not null,
  data_fi timestamp not null,
  data_inici timestamp not null,
  import_polissa decimal(9,2) not null,
  import_continent decimal(9,2) not null,
  import_contingut decimal(9,2) not null,
  num_client int not null,
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
  
  FOREIGN KEY (num_client) REFERENCES client(numero)
  

);


create table cobertura(
  num_polissa int not null,
  codi int not null auto_increment,
  descripcio varchar(255) not null,
  
  primary key(codi),
  CHECK(num_polissa>0),
  CHECK (codi>0),
  CHECK(length(descripcio)>0),
  
  FOREIGN KEY (num_polissa) REFERENCES polissa(numero)
  

);


create table sinistre(
  numero int not null,
  data_assignacio timestamp,
  data_obertura timestamp not null,
  data_tancament timestamp,
  descripcio varchar(100) not null,
  num_polissa int not null,
  num_perit int,
  tipus_sinistre varchar(20) not null,
  estat_sinistre varchar(20) not null,
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


create table cita(
  id int not null,
  num_perit int not null,
  dia_hora timestamp not null,
  num_sinistre int not null,
  duracio int not null,
  
  primary key(id,num_perit),
  
  CHECK(length(num_perit)>0),
  CHECK(duracio >0),
  CHECK(num_sinistre>0),
  
  
  FOREIGN KEY (num_perit) REFERENCES perit(numero),
  FOREIGN KEY (num_sinistre) REFERENCES sinistre(numero)
  
);


create table trucada(
  ordre int not null,
  num_sinistre int not null,
  data_hora timestamp not null,
  descripcio varchar(100) not null,
  persona_contacte varchar(30) not null,
  
  primary key(ordre,num_sinistre),
  
  CHECK(num_sinistre>0),
  CHECK(length(descripcio)>0),
  CHECK(length(persona_contacte)>0),
  
  FOREIGN KEY (num_sinistre) REFERENCES sinistre(numero)
  

);


create table informe_pericial(
  num_sinistre int not null,
  data_emisio timestamp not null,
  import_cobert decimal(8,2) not null,
  informe varchar(100),
  num_perit int not null,  
  estat_informe varchar(30),
  resultat_peritatge varchar(30),
  
  primary key(num_sinistre),
  
  CHECK(num_sinistre>0),
  CHECK(import_cobert >0),
  CHECK(num_perit>0),
  CHECK(length(estat_informe)>0),
  CHECK(length(resultat_peritatge)>0),
  
  FOREIGN KEY (num_sinistre) REFERENCES sinistre(numero),
  FOREIGN KEY(num_perit) REFERENCES perit(numero)
  
);


create table entrada_informe(
  numero int not null,
  ordre int not null,
  
  data_informe timestamp not null,
  descripcio varchar(255) not null,
  despres_reparacio bit not null,
  foto longblob,
  
  primary key(ordre,numero),
  
  CHECK(numero>0),
  CHECK(length(descripcio)>0),
  
  FOREIGN KEY (numero) REFERENCES informe_pericial(num_sinistre)

);



