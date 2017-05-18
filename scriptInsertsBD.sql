insert into perit values(null,"12345678F","Nil","Centellas","Calaf",date'1996-07-25',"nilcente","4567527766403b33df1717882f8c2d3c");
insert into perit values(null,"11223344A","Mayte","Hinojosa","Arenas",date'1979-04-12',"mayte","0e29be28f22f45dc8fbb973e81645dd7");
insert into perit values(null,"55667788B","Oriol","Bernaus","Rovira",date'1992-01-23',"bernaus","d07f55040ac2236bec1df6b8a7da51b3");
insert into perit values(null,"22334455C","Carles","Mas","Jene",date'1974-11-06',"cmas","fcb1cb75483e6bc683fa92f7c4dd9611");
insert into perit values(null,"99887766H","Cristian","Lopez",null,date'1974-11-06',"clopez","3cd5d210acb9a1b81c04150ca305d662");

insert into client values(null,"56478632L","Uriel","Costa","Bonet",date'1982-02-03');
insert into client values(null,"98542365K","Ester","Mas",null,date'1992-07-15');
insert into client values(null,"47111543J","Guillem","Estany","Bosch",date'1994-10-22');
insert into client values(null,"77233568S","Eduard","Lorente",null,date'1991-01-01');
insert into client values(null,"11323456Y","Pau","Ter","Caberizo",date'1995-11-11');


insert into polissa values(null,date'2016-12-12','2017-12-12',1147.54,850,297.54,4,"Igualada","Av. Gaudí nº 55, B7","PIS");
insert into polissa values(null,date'2016-12-12','2017-12-12',433.00,0,433.00,4,"Igualada","Av. Gaudí","PARKING");

insert into polissa values(null,date'2017-03-15','2018-03-15',2453.22,1400,153.22,1,"Castell de Cabrera","C. Major nº 103","CASA_ADOSADA");
insert into polissa values(null,date'2017-03-19','2018-03-19',320,0,320,1,"Igualada","C. Trasters nº 21","TRASTER");
insert into polissa values(null,date'2015-03-19','2021-03-19',120,0,120,1,"Igualada","C. Parkings nº 1","PARKING");

insert into polissa values(null,date'2016-06-12','2019-06-12',2125,1740,385,2,"Piera","Plaça de la Independència nº 4","CASA_INDIV");
insert into polissa values(null,date'2017-02-21','2019-02-21',450.75,200,250.75,2,"Igualada","C. Bellprat nº 13","LOCAL");


insert into polissa values(null,date'2014-11-21','2017-11-21',480.20,0,480.20,3,"Cervera","C. Fals nº 99","PARKING");

insert into polissa values(null,date'2017-05-18','2020-05-18',4500.21,3850,650.21,5,"Castellolí","C. Angel Guimerà nº 4","CASA_ADOSADA");

insert into cobertura values(1,null,"No cubre na");
insert into cobertura values(1,null,"No cubre na");
insert into cobertura values(2,null,"No cubre na");


select * from perit;
select * from client;
select * from polissa;
select * from cobertura;

delete from cobertura where num_polissa =1;
