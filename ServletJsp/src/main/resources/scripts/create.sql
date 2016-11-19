create table IF NOT EXISTS PAISES(
	idPais int IDENTITY(1,1) PRIMARY KEY ,
	nombrePais varchar(50),
	idIdioma int
);
create table IF NOT EXISTS IDIOMAS(
	idIdioma int IDENTITY(1,1) PRIMARY KEY,
	nombreIdioma varchar(25),
);