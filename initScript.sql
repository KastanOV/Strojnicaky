INSERT INTO `users`
(`login`,`fname`,`lname`,`oec`,`phone`,`email`,`password`,`role`,`pas`,`rota`,`ceta`)
VALUES('kas0110','admin','admin','123456','123456',null,'80758069d5b77a6aa33a8622db79ddb9','admin',null,10000,10000);
INSERT INTO `users`
(`login`,`fname`,`lname`,`oec`,`phone`,`email`,`password`,`role`,`pas`,`rota`,`ceta`)
VALUES('jan000','Petr','Jandat','742024','777123456',null,'80758069d5b77a6aa33a8622db79ddb9','vc',null,1,2);
INSERT INTO `users`
(`login`,`fname`,`lname`,`oec`,`phone`,`email`,`password`,`role`,`pas`,`rota`,`ceta`)
VALUES('mor000','Premysl','Moravec','724333','123456',null,'80758069d5b77a6aa33a8622db79ddb9','vd',null,1,2);

INSERT INTO `seznamopravneni`(`shortname`,`name`)
VALUES('MŘP','Motorové řětězové pily');

INSERT INTO `seznamopravneni`(`shortname`,`name`)
VALUES('JŘB','Jeřáby');

INSERT INTO `opravneni`(`datumexpirace`,`users_login`,`seznamopravneni_shortname`)
VALUES('2016-03-02','jan000','MŘP');

INSERT INTO `opravneni`(`datumexpirace`,`users_login`,`seznamopravneni_shortname`)
VALUES('2017-03-02','jan000','JŘB');

INSERT INTO `opravneni`(`datumexpirace`,`users_login`,`seznamopravneni_shortname`)
VALUES('2015-03-02','mor000','MŘP');

INSERT INTO `opravneni`(`datumexpirace`,`users_login`,`seznamopravneni_shortname`)
VALUES('2014-03-02','mor000','JŘB');

select * from opravneni;


