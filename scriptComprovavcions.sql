
select * from user_online;
select * from user_ids;
delete from user_online where user_id = 1;
select * from informe_pericial where num_perit = 1;
desc informe_pericial;
select * from entrada_informe where numero = 1;
select * from cita where num_perit = 1;
select * from perit;
select * from client;
select * from polissa;
select * from cobertura;
select * from sinistre;
select * from comptadors;
select * from trucada;

update perit set password = '0DE24EBFD92565A643A5C83D57D24699' where login = 'nilcente';
update informe_pericial set estat_informe = 'PENDENT' where num_sinistre = 6;
desc cita;
SET SQL_SAFE_UPDATES = 0;
DELETE FROM user_online;
SET SQL_SAFE_UPDATES = 1;