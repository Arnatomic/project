insert into perit values(1,"12345678F","Nil","Centellas","Calaf",STR_TO_DATE('1996-07-25 18:00', '%Y-%m-%d %H:%i'),"nilcente","4567527766403b33df1717882f8c2d3c");
insert into perit values(2,"11223344A","Mayte","Hinojosa","Arenas",STR_TO_DATE('1979-04-12 18:00', '%Y-%m-%d %H:%i'),"mayte","0e29be28f22f45dc8fbb973e81645dd7");
insert into perit values(3,"55667788B","Oriol","Bernaus","Rovira",STR_TO_DATE('1992-01-23 18:00', '%Y-%m-%d %H:%i'),"bernaus","d07f55040ac2236bec1df6b8a7da51b3");
insert into perit values(4,"22334455C","Carles","Mas","Jene",STR_TO_DATE('1974-11-06 18:00', '%Y-%m-%d %H:%i'),"cmas","fcb1cb75483e6bc683fa92f7c4dd9611");
insert into perit values(5,"99887766H","Cristian","Lopez",null,STR_TO_DATE('1974-11-06 18:00', '%Y-%m-%d %H:%i'),"clopez","3cd5d210acb9a1b81c04150ca305d662");

insert into comptadors values("perit",6);

insert into client values(1,"56478632L","Uriel","Costa","Bonet",STR_TO_DATE('1982-02-03 18:00', '%Y-%m-%d %H:%i'));
insert into client values(2,"98542365K","Ester","Mas",null,STR_TO_DATE('1992-07-15 18:00', '%Y-%m-%d %H:%i'));
insert into client values(3,"47111543J","Guillem","Estany","Bosch",STR_TO_DATE('1994-10-22 18:00', '%Y-%m-%d %H:%i'));
insert into client values(4,"77233568S","Eduard","Lorente",null,STR_TO_DATE('1991-01-01 18:00', '%Y-%m-%d %H:%i'));
insert into client values(5,"11323456Y","Pau","Ter","Caberizo",STR_TO_DATE('1995-11-11 18:00', '%Y-%m-%d %H:%i'));

insert into comptadors values("client",6);


insert into polissa values(1,STR_TO_DATE('2017-12-12 18:00', '%Y-%m-%d %H:%i'),STR_TO_DATE('2016-12-12 18:00', '%Y-%m-%d %H:%i'),1147.54,850,297.54,4,"Igualada","Av. Gaudí nº 55, B7","PIS");
insert into polissa values(2,date'2016-12-12','2017-12-12',433.00,0,433.00,4,"Igualada","Av. Gaudí","PARKING");

insert into polissa values(3,STR_TO_DATE('2018-03-15 18:00', '%Y-%m-%d %H:%i'),STR_TO_DATE('2017-03-15 18:00', '%Y-%m-%d %H:%i'),2453.22,1400,1053.22,1,"Castell de Cabrera","C. Major nº 103","CASA_ADOSADA");
insert into polissa values(4,STR_TO_DATE('2018-03-19 18:00', '%Y-%m-%d %H:%i'),STR_TO_DATE('2017-03-19 18:00', '%Y-%m-%d %H:%i'),320,0,320,1,"Igualada","C. Trasters nº 21","TRASTER");
insert into polissa values(5,STR_TO_DATE('2021-03-19 18:00', '%Y-%m-%d %H:%i'),STR_TO_DATE('2015-03-19 18:00', '%Y-%m-%d %H:%i'),120,0,120,1,"Igualada","C. Parkings nº 1","PARKING");

insert into polissa values(6,STR_TO_DATE('2019-06-12 18:00', '%Y-%m-%d %H:%i'),STR_TO_DATE('2016-06-12 18:00', '%Y-%m-%d %H:%i'),2125,1740,385,2,"Piera","Plaça de la Independència nº 4","CASA_INDIV");
insert into polissa values(7,STR_TO_DATE('2019-02-21 18:00', '%Y-%m-%d %H:%i'),STR_TO_DATE('2017-02-21 18:00', '%Y-%m-%d %H:%i'),450.75,200,250.75,2,"Igualada","C. Bellprat nº 13","LOCAL");


insert into polissa values(8,STR_TO_DATE('2017-11-21 18:00', '%Y-%m-%d %H:%i'),STR_TO_DATE('2014-11-21 18:00', '%Y-%m-%d %H:%i'),480.20,0,480.20,3,"Cervera","C. Fals nº 99","PARKING");

insert into polissa values(9,STR_TO_DATE('2020-05-18 18:00', '%Y-%m-%d %H:%i'),STR_TO_DATE('2017-05-18 18:00', '%Y-%m-%d %H:%i'),4500.21,3850,650.21,5,"Castellolí","C. Angel Guimerà nº 4","CASA_ADOSADA");



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

insert into sinistre values(1,STR_TO_DATE('2017-05-31 18:00', '%Y-%m-%d %H:%i'),STR_TO_DATE('2017-05-30 18:00', '%Y-%m-%d %H:%i'),null,
				"Robatori de bens i inmobles valorats en (8000€)",1,1,"ROBATORI","ASSIGNAT",0);

insert into sinistre values(2,STR_TO_DATE('2017-01-26 18:00', '%Y-%m-%d %H:%i'),STR_TO_DATE('2017-01-25 18:00', '%Y-%m-%d %H:%i'),
			STR_TO_DATE('2017-04-27 18:00', '%Y-%m-%d %H:%i'),"Humitats que han causat desperfectes a la paret i a la pintura del cotxe",5,4,"HUMITAT","TANCAT",0);

insert into sinistre values(3,STR_TO_DATE('2017-05-18 18:00', '%Y-%m-%d %H:%i'),STR_TO_DATE('2017-05-19 18:00', '%Y-%m-%d %H:%i'),null,
				"Fuita d'aigua durant la nit que ha inundat un traster",4,1,"FUITA_AIGUA","ASSIGNAT",0);
                
                
insert into sinistre values(4,STR_TO_DATE('2017-03-24 18:00', '%Y-%m-%d %H:%i'),STR_TO_DATE('2017-03-19 18:00', '%Y-%m-%d %H:%i'),null,
				"Explosió de gas que ha esfondrat parcialment un local",7,2,"GAS","ASSIGNAT",0);   
                
insert into sinistre values(5,null,STR_TO_DATE('2017-05-31 22:00', '%Y-%m-%d %H:%i'),null,
				"Inundació en un Pis",4,null,"FUITA_AIGUA","NOU",0); 
     
     
insert into comptadors values("sinistre",6);
update comptadors set comptador = 6 where taula = "sinistre";
-- perits 1,2,4
insert into cita values(1,1,STR_TO_DATE('2017-05-31 09:00', '%Y-%m-%d %H:%i'),1,1);
insert into cita values(2,1,STR_TO_DATE('2017-06-01 10:00', '%Y-%m-%d %H:%i'),1,2);
insert into cita values(3,1,STR_TO_DATE('2017-06-01 16:00', '%Y-%m-%d %H:%i'),1,3);
-- insert into cita values(4,1,STR_TO_DATE('2017-06-02 16:00', '%Y-%m-%d %H:%i'),1,3);

insert into cita values(1,4,STR_TO_DATE('2017-04-20 18:00', '%Y-%m-%d %H:%i'),2,2);

insert into cita values(1,2,STR_TO_DATE('2017-04-10 18:00', '%Y-%m-%d %H:%i'),4,3);
insert into cita values(2,2,STR_TO_DATE('2017-04-21 18:00', '%Y-%m-%d %H:%i'),4,1);
insert into cita values(3,2,STR_TO_DATE('2017-05-12 18:00', '%Y-%m-%d %H:%i'),4,1);

insert into trucada values(1,1,STR_TO_DATE('2017-04-20 18:00', '%Y-%m-%d %H:%i'),"Robatori de bens i inmobles, casa particular","Eduard Lorente");
insert into trucada values(2,1,STR_TO_DATE('2017-05-12 18:00', '%Y-%m-%d %H:%i'),"Encara està esperant resposta","Eduard Lorente");

insert into trucada values(1,2,STR_TO_DATE('2017-01-25 18:00', '%Y-%m-%d %H:%i'),"Humitats que han causat desperfectes a la paret i a la pintura del cotxe","Uriel Costa");

insert into trucada values(1,4,STR_TO_DATE('2017-03-19 18:00', '%Y-%m-%d %H:%i'),"Explosió de gas que ha esfondrat parcialment un local","Ester mas");
insert into trucada values(2,4,STR_TO_DATE('2017-03-25 18:00', '%Y-%m-%d %H:%i'),"No esta conforme amb el resultat del primer peritatge","Ester mas");
insert into trucada values(3,4,STR_TO_DATE('2017-04-10 18:00', '%Y-%m-%d %H:%i'),"Ha trobat evidències que la explosió ha estat provocada","Ester mas");


insert into informe_pericial values(1,STR_TO_DATE('2017-05-10 18:00', '%Y-%m-%d %H:%i'),0.00,"Han entrat a robar al pis, hem detectat que el client disposava d'alarma però la tenia desactivada..",1,"TANCAT","SENSE_COBERTURA");

insert into informe_pericial values(2,STR_TO_DATE('2017-01-27 18:00', '%Y-%m-%d %H:%i'),0.00,"Humitats que han causat desperfectes a la paret i a la pintura del cotxe",1,"TANCAT","SENSE_COBERTURA");


insert into entrada_informe values(1,1,STR_TO_DATE('2017-05-10 18:00', '%Y-%m-%d %H:%i'),"Hi ha hagut un robatori mentre el client estava de cap de setmana",0,null);
insert into entrada_informe values(1,2,STR_TO_DATE('2017-04-21 18:00', '%Y-%m-%d %H:%i'),"He comprovat la alarma per determinar perqué no va saltar, i no estava activada el dia del robatori",0,null);
insert into entrada_informe values(1,3,STR_TO_DATE('2017-05-25 18:00', '%Y-%m-%d %H:%i'),"Al tenir la alarma operativa, però sense activar, no ens fem càrrec dels danys causats, ho sentim.",0,null);

insert into entrada_informe values(2,1,STR_TO_DATE('2017-01-25 18:00', '%Y-%m-%d %H:%i'),"El veí de dalt ha deixat la aixeta oberta i això ha provocat humitats al parking, desperfetes al cotxe.",0,null);

update entrada_informe set data_informe = STR_TO_DATE('2017-05-10 18:00', '%Y-%m-%d %H:%i') where numero = 1 and ordre = 1;
update entrada_informe set data_informe = STR_TO_DATE('2017-04-21 18:00', '%Y-%m-%d %H:%i') where numero = 1 and ordre = 2;
update entrada_informe set data_informe = STR_TO_DATE('2017-05-25 18:00', '%Y-%m-%d %H:%i') where numero = 1 and ordre = 3;
update entrada_informe set data_informe = STR_TO_DATE('2017-01-25 18:00', '%Y-%m-%d %H:%i') where numero = 2 and ordre = 1;




