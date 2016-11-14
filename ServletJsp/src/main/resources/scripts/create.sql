create table IF NOT EXISTS PAISES(
	idPais int  PRIMARY KEY,
	nombrePais varchar(50),
	idIdioma int
);
create table IF NOT EXISTS IDIOMAS(
	idIdioma int  PRIMARY KEY,
	nombreIdioma varchar(25),
);