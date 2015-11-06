drop database if exists carloan;
create database carloan;

use carloan;

create table fascia(
    id integer primary key auto_increment,
    nome varchar(50),
    tariffaBase double,
    indiceMin double,
    indiceMax double
);

insert into fascia(nome, tariffabase, indicemin, indicemax) values ("Utilitaria", 15.50, 0.00, 100);

create table modello(
    id integer primary key auto_increment,
    marca varchar(50),
    nome varchar(50),
    tipoCarburante integer,
    capacitaBagagliaio integer,
    numeroPosti integer,
    numeroporte integer,
    potenza integer,
    trasmissioneautomatica boolean,
    emissioniCO2 float,
    peso integer,
    fascia integer,
    foreign key (fascia) references fascia(id) on delete cascade on update cascade
);

insert into modello(marca, nome, tipocarburante, capacitabagagliaio, numeroposti, numeroporte, potenza, trasmissioneautomatica, emissionico2, peso, fascia)
values ("Fiat", "Punto Evo", 1, 20, 5, 5, 56, 0, null, 1220, 1);
	
create table agenzia(
	id integer primary key auto_increment,
	indirizzo varchar(50),
	citta varchar(50),
	numTelefono varchar(15)
);

insert into agenzia (indirizzo, citta, numtelefono) values("Via Orabona 4" , "Bari", "3384289855");

create table vettura(
	targa char(7) primary key,
	modello integer,
	agenzialocalizzazione integer, 
	chilometraggio integer,
	stato integer,
	foreign key (modello) references modello (id) on delete cascade on update cascade,
	foreign key (agenzialocalizzazione) references agenzia(id) on delete cascade on update cascade
);

insert into vettura values("ED000BA", 1, 1, 20000, 1);

create table optional(
	id integer primary key auto_increment,
	tipo varchar(50),
	costo float
);

insert into optional(tipo, costo) values("Aria condizionata", 3.0);

create table persona(
	id integer primary key auto_increment,
	nome varchar(50),
	cognome varchar(50),
	datanascita date,
	numTelefono varchar(15),
	email varchar(100)
);

insert into persona(nome, cognome, datanascita, numtelefono, email) values("General", "Admin", "1988-05-28", "356892112", "general@admin.org");
insert into persona(nome, cognome, datanascita, numtelefono, email) values("Mario", "Rossi", "1990-02-25", "3384289855", "mariorossi@gmail.com");

create table cliente(
	id integer,
	codicepatente char(10) primary key,
	foreign key(id) references persona(id) on delete cascade on update cascade
);

insert into cliente values(2, "1234567890");

create table profilo(
	id integer not null,
	username varchar(50) primary key,
	password char(32),
	foreign key(id) references persona(id) on delete cascade on update cascade
);

insert into profilo values(1, "Admin", "5f4dcc3b5aa765d61d8327deb882cf99");

create table operatore(
	username varchar(50) primary key,
	agenzia integer, 
	amministratore boolean,
	foreign key (username) references profilo(username) on delete cascade on update cascade,
	foreign key (agenzia) references agenzia(id) on delete cascade on update cascade
);

insert into operatore values("Admin", 1, 1);

create table contratto(
	id integer primary key auto_increment,
	operatore varchar(50), 
	cliente char(10),
	vettura char(7),
	agenzianoleggio integer,
	agenziaconsegna integer,
	datastipula date,
	datainizionoleggio date,
	dataFineNoleggio date,
	datachiusura date,
	chilometriPercorsi integer,
	chilometraggiolimitato boolean,
	chilometriPrevisti integer,
	rifornimento integer,
	acconto float,
	chiuso boolean,
	costo float,
	assicurazioneavanzata boolean,
	foreign key (vettura) references vettura(targa),
	foreign key (operatore) references operatore(username) on delete cascade on update cascade,
	foreign key (cliente) references cliente(codicepatente) on delete cascade on update cascade,
	foreign key (agenzianoleggio) references agenzia(id) on delete cascade on update cascade,
	foreign key (agenziaconsegna) references agenzia(id) on delete cascade on update cascade
);

create table optional_contratto(
	idContratto integer,
	idOptional integer,
	primary key (idContratto, idOptional),
	foreign key (idContratto) references contratto(id) on delete cascade on update cascade,
	foreign key (idOptional) references optional(id) on delete cascade on update cascade
);

GRANT ALL PRIVILEGES ON carloan.* TO CarloanUser@localhost identified by "carloan15";