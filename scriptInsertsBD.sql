insert into perit values(null,"12345678F","Nil","Centellas","Calaf",date'1996-07-25',"nilcente","4567527766403b33df1717882f8c2d3c");
insert into perit values(null,"11223344A","Mayte","Hinojosa","Arenas",date'1979-04-12',"mayte","0e29be28f22f45dc8fbb973e81645dd7");
insert into perit values(null,"55667788B","Oriol","Bernaus","Rovira",date'1992-01-23',"bernaus","d07f55040ac2236bec1df6b8a7da51b3");
insert into perit values(null,"22334455C","Carles","Mas","Jene",date'1974-11-06',"cmas","fcb1cb75483e6bc683fa92f7c4dd9611");
insert into perit values(null,"99887766H","Cristian","Lopez",null,date'1974-11-06',"clopez","3cd5d210acb9a1b81c04150ca305d662");

insert into comptadors values("perit",6);

insert into client values(null,"56478632L","Uriel","Costa","Bonet",date'1982-02-03');
insert into client values(null,"98542365K","Ester","Mas",null,date'1992-07-15');
insert into client values(null,"47111543J","Guillem","Estany","Bosch",date'1994-10-22');
insert into client values(null,"77233568S","Eduard","Lorente",null,date'1991-01-01');
insert into client values(null,"11323456Y","Pau","Ter","Caberizo",date'1995-11-11');

insert into comptadors values("client",6);


insert into polissa values(null,date'2016-12-12','2017-12-12',1147.54,850,297.54,4,"Igualada","Av. Gaudí nº 55, B7","PIS");
insert into polissa values(null,date'2016-12-12','2017-12-12',433.00,0,433.00,4,"Igualada","Av. Gaudí","PARKING");

insert into polissa values(null,date'2017-03-15','2018-03-15',2453.22,1400,1053.22,1,"Castell de Cabrera","C. Major nº 103","CASA_ADOSADA");
insert into polissa values(null,date'2017-03-19','2018-03-19',320,0,320,1,"Igualada","C. Trasters nº 21","TRASTER");
insert into polissa values(null,date'2015-03-19','2021-03-19',120,0,120,1,"Igualada","C. Parkings nº 1","PARKING");

insert into polissa values(null,date'2016-06-12','2019-06-12',2125,1740,385,2,"Piera","Plaça de la Independència nº 4","CASA_INDIV");
insert into polissa values(null,date'2017-02-21','2019-02-21',450.75,200,250.75,2,"Igualada","C. Bellprat nº 13","LOCAL");


insert into polissa values(null,date'2014-11-21','2017-11-21',480.20,0,480.20,3,"Cervera","C. Fals nº 99","PARKING");

insert into polissa values(null,date'2017-05-18','2020-05-18',4500.21,3850,650.21,5,"Castellolí","C. Angel Guimerà nº 4","CASA_ADOSADA");



insert into comptadors values("polissa",10);



insert into cobertura values(1,null,"Cobertura Bàsica, cobreix fins a 850€ en relació a (Desperfectes Estructurals,Robatori,Incendi) i 279.54€ en relació (Objectes robats, desperfectes materials)");
insert into cobertura values(1,null,"Cobertura extesa a llunes del vehicle, Pels anys de fidelitat cobreix el trancament 'Parcial o Total' de qualsevol de les llunes del cotxe '9215 JBB'");

-- La polissa amb numero '2' no te cap Cobertura;

insert into cobertura values(3,null,"Cobertura completa: cobreix fins a 1400€ en relació a (Desperfectes Estructurals,Incendi, Desastres natural: 'Tsunami, Tornado només') i 1053.22€ en relació (Objectes robats, desperfectes materials, dañs i perjudicis) ");
insert into cobertura values(3,null,"Cobertura Automòbil: cobreix fins a 3000€ en cas de robatori del vehicle, 800€ per dañs materials i reparació de llunes");

insert into cobertura values(4,null,"Cobertura Standard: Cobreix fins a 450€ del contingut del traster en cas de: Robatori o Inundació");


insert into cobertura values(5,null,"Cobertura Parking amb extres: Cobreix fins a 800€ per dañs materials (en el vehicle) i reparació de llunes");
insert into cobertura values(5,null,"Cobertura Anti-Humitats: Cobreix totalment possibles humitats dins del PARKING");


insert into cobertura values(6,null,"Cobertura Integral: Cobreix fins a 1740€ en relacio a qualsevol desperfecte estructural i 385€ en relacio a bens materials en cas únic de robatori");
insert into cobertura values(6,null,"Cobertura Electrodomestic: Cobreix fins a 300€ en la reparació o substitució de qualsevol electrodomèstic d'Alt consum (frigorific, nevera, televisió, ordinador inclosos)");

insert into sinistre values(null,date'2017-04-21',date'2017-04-20',null,
				"Robatori de bens i inmobles valorats en (8000€)",1,1,"ROBATORI","ASSIGNAT",0);

insert into sinistre values(null,date'2017-01-26',date'2017-01-25',date'2017-04-27',
				"Humitats que han causat desperfectes a la paret i a la pintura del cotxe",5,4,"HUMITAT","TANCAT",0);

insert into sinistre values(null,null,date'2017-05-19',null,
				"Fuita d'aigua durant la nit que ha inundat un traster",4,null,"FUITA_AIGUA","NOU",0);
                
                
insert into sinistre values(null,date'2017-03-24',date'2017-03-19',null,
				"Explosió de gas que ha esfondrat parcialment un local",7,2,"GAS","ASSIGNAT",0);   
     
     
insert into comptadors values("sinistre",5);

-- perits 1,2,4
insert into cita values(null,1,date'2017-05-10',1,1);
insert into cita values(null,1,date'2017-05-19',1,2);
insert into cita values(null,1,date'2017-05-25',1,3);

insert into cita values(null,4,date'2017-04-20',2,2);

insert into cita values(null,2,date'2017-04-10',4,3);
insert into cita values(null,2,date'2017-04-21',4,1);
insert into cita values(null,2,date'2017-05-12',4,1);

insert into trucada values(null,1,date'2017-04-20',"Robatori de bens i inmobles, casa particular","Eduard Lorente");
insert into trucada values(null,1,date'2017-05-12',"Encara està esperant resposta","Eduard Lorente");

insert into trucada values(null,2,date'2017-01-25',"Humitats que han causat desperfectes a la paret i a la pintura del cotxe","Uriel Costa");

insert into trucada values(null,4,date'2017-03-19',"Explosió de gas que ha esfondrat parcialment un local","Ester mas");
insert into trucada values(null,4,date'2017-03-25',"No esta conforme amb el resultat del primer peritatge","Ester mas");
insert into trucada values(null,4,date'2017-04-10',"Ha trobat evidències que la explosió ha estat provocada","Ester mas");


insert into informe_pericial values(1,date'2017-05-10',0.00,"Han entrat a robar al pis, hem detectat que el client disposava d'alarma però la tenia desactivada..",1,"TANCAT","SENSE_COBERTURA");

insert into informe_pericial values(2,date'2017-01-27',0.00,"Humitats que han causat desperfectes a la paret i a la pintura del cotxe",1,"TANCAT","SENSE_COBERTURA");


insert into entrada_informe values(1,null,date'2017-05-10',"Hi ha hagut un robatori mentre el client estava de cap de setmana",0,null);
insert into entrada_informe values(1,null,date'2017-04-21',"He comprovat la alarma per determinar perqué no va saltar, i no estava activada el dia del robatori",0,null);
insert into entrada_informe values(1,null,date'2017-05-25',"Al tenir la alarma operativa, però sense activar, no ens fem càrrec dels danys causats, ho sentim.",0,null);

insert into entrada_informe values(2,null,date'2017-01-25',"El veí de dalt ha deixat la aixeta oberta i això ha provocat humitats al parking, desperfetes al cotxe.",0,null);



/*
select * from perit;
select * from client;
select * from polissa;
select * from cobertura;
select * from sinistre;
select * from comptadors;
*/

