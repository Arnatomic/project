
drop table tipus_habitatge;
create table tipus_habitatge{
  id int not null,
  nom varchar2(15),

  primary key(id)
};


drop table tipus_sinistre;
create table tipus_sinistre{
  id int not null,
  nom varchar2(15),

  primary key(id)
};

drop table tipus_via;
create table tipus_via{
  id int not null,
  nom varchar2(15),

  primary key(id)
};

drop table estat_sinistre;
create table estat_sinistre{
  id int not null,
  nom varchar2(15),

  primary key(id)
};

drop table resultat_sinistre;
create table resultat_sinistre{
  id int not null,
  nom varchar2(15),

  primary key(id)
};

drop table estat_informe;
create table estat_informe{
  id int not null,
  nom varchar2(15),

  primary key(id)
};

drop table adreca;
create table adreca{  
  nom_via varchar(40)
  num int,
  pis int,
  escala varchar(10),
  bloc varchar(40),
  porta varchar(10),
  
  primary key(nom_via,num,pis)

};

drop table cobertura;
create table cobertura{
  codi int,
  descripcio varchar(100),
  
  primary key(codi)

};

drop table polissa;
create table polissa{
  numero int,
  data_fi date,
  data_inici date,
  import double,
  import_continent double,
  import_contingut double
  
  primary key(numero)

};


drop table client;
create table client{
  numero int,
  nif varchar(9),
  nom varchar(30),
  cognom1 varchar(30),
  cognom2 varchar(30),
  data_naix date,
  
  primary key(numero)

};

drop table cita;
create table cita{
  dia_i_hora date,
  duracio int
}

drop table perit;
create table perit{
  numero int,
  login varchar(30),
  password varchar(50),
  
  primary key(numero)

};

drop table trucada;
create table trucada{
  data_hora date,
  descripcio varchar(100),
  persona_contacte varchar(30)

};

drop table sinistre;
create table sinistre{
  numero int,
  data_assignacio date,
  data_obertura date,
  data_tancament date,
  descripcio varchar(100),
  arxivat int,
  
  primary key(numero)
};

drop table informe_percicial;
create table informe_pericial{
  data_emisio date,
  import_cobert double,
  informe varchar(100)

};

drop table entrada_informe;
create table entrada_informe{
  numero int, 
  data date,
  descripcio varchar(100),
  despres_reparacio int(1),
  blob foto,
  
  primary key(numero)

}






