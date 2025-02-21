CREATE TABLE direction(
     idDirection SERIAL PRIMARY KEY ,
     nom VARCHAR
);
 CREATE TABLE service(
      idService SERIAL PRIMARY KEY ,
      nom VARCHAR,
      idDirection INT REFERENCES direction(idDirection)
 );
 CREATE TABLE fonction(
   idFonction SERIAL PRIMARY KEY ,
   nom VARCHAR
 );
CREATE TABLE CatOR(
    IdCatOr SERIAL PRIMARY KEY ,
    nom VARCHAR,
    codeGrade VARCHAR,
    indice VARCHAR
);
CREATE TABLE personnel(
    idPersonnel SERIAL PRIMARY KEY ,
    matricule VARCHAR UNIQUE ,
    nom VARCHAR,
    prenom VARCHAR,
    CIN VARCHAR(12),
    CIN_du date,
    email VARCHAR UNIQUE ,
    tel VARCHAR(10) UNIQUE
);
CREATE TABLE CatOr_personne(
    IdCatOr_personne SERIAL PRIMARY KEY ,
    dates date,
    IdCatOr INT REFERENCES CatOR(IdCatOr),
    idPersonnel INT REFERENCES personnel(idPersonnel)
);
CREATE TABLE fonction_personnel(
    idfonction_personnel SERIAL PRIMARY KEY ,
    dates date,
    idFonction INT REFERENCES fonction(idFonction),
    idPersonnel INT REFERENCES personnel(idPersonnel)
);
CREATE TABLE service_personnel(
    idService_Personnel SERIAL PRIMARY KEY ,
    dates date,
    idPersonnel INT REFERENCES personnel(idPersonnel),
    idService INT REFERENCES service(idService)
);
CREATE TABLE role(
    idRole Serial PRIMARY KEY ,
    nom VARCHAR
);
CREATE TABLE inscription(
    idInscription SERIAL PRIMARY KEY ,
    matricule VARCHAR,
    nom VARCHAR,
    prenom VARCHAR,
    CIN VARCHAR(12),
    CIN_du date,
    indice VARCHAR,
    CatOR VARCHAR,
    codeGrade VARCHAR,
    email VARCHAR ,
    tel VARCHAR(10),
    cles VARCHAR UNIQUE,
    pwd VARCHAR,
    idFonction INT REFERENCES fonction(idFonction),
    idService INT REFERENCES service(idService),
    IdCatOr INT REFERENCES CatOR(IdCatOr)
);
CREATE TABLE utilisateur(
    idUtilisateur SERIAL PRIMARY KEY ,
    dates date,
    idPersonnel INT REFERENCES personnel(idPersonnel),
    idRole INT REFERENCES role(idRole),
    pwd VARCHAR
);
CREATE TABLE region(
    idRegion SERIAL PRIMARY KEY ,
    nom VARCHAR
);
CREATE TABLE transport(
  idtransport SERIAL PRIMARY KEY ,
  nom VARCHAR
);
CREATE TABLE depart(
    idDepart SERIAL PRIMARY KEY ,
    dates DATE,
    IM_mission VARCHAR,
    IdUtilisateur INT REFERENCES utilisateur(idUtilisateur),
    IdPersonne INT REFERENCES personnel(idPersonnel),
    numero_OR VARCHAR,
    date_depart TIMESTAMP,
    date_arriver date,
    code_Visa varchar,
    avance VARCHAR,
);
CREATE TABLE itineraire(
    idItineraire SERIAL PRIMARY KEY ,
    idDepart INT REFERENCES depart(idDepart),
    numero INT,
    idRegion_depart INT REFERENCES region(idRegion),
    idRegion_arriver INT REFERENCES region(idRegion),
    idTransport INT REFERENCES transport(idtransport)
);
CREATE TABLE depart_valider(
  idDepart_valider SERIAL PRIMARY KEY ,
  dates DATE,
  idDepart INT REFERENCES depart(idDepart),
  idUtilisateur INT REFERENCES utilisateur(idUtilisateur),
  nb VARCHAR
);
CREATE TABLE depart_refuser(
    idDepart_refuser SERIAL PRIMARY KEY ,
    dates DATE,
    idDepart INT REFERENCES depart(idDepart),
    idUtilisateur INT REFERENCES utilisateur(idUtilisateur),
    nb VARCHAR
);