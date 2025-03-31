

CREATE TABLE direction(
     idDirection SERIAL PRIMARY KEY ,
     nom VARCHAR UNIQUE
);
 CREATE TABLE service(
      idService SERIAL PRIMARY KEY ,
      nom VARCHAR UNIQUE ,
      idDirection INT REFERENCES direction(idDirection)
 );

 CREATE TABLE fonction(
   idFonction SERIAL PRIMARY KEY ,
   nom VARCHAR UNIQUE
 );

CREATE TABLE CatOR(
    IdCatOr SERIAL PRIMARY KEY ,
    nom VARCHAR UNIQUE ,
    codeGrade VARCHAR UNIQUE ,
    indice VARCHAR UNIQUE
);

CREATE TABLE personnel(
    idPersonnel SERIAL PRIMARY KEY ,
    dates date,
    matricule VARCHAR UNIQUE ,
    nom VARCHAR,
    prenom VARCHAR,
    CIN VARCHAR(12) UNIQUE ,
    CIN_du date,
    email VARCHAR UNIQUE ,
    tel VARCHAR(10) UNIQUE,
    status boolean
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
    email VARCHAR ,
    tel VARCHAR(10),
    pwd VARCHAR,
    cles VARCHAR
);
CREATE TABLE utilisateur(
    idUtilisateur SERIAL PRIMARY KEY ,
    dates date,
    idPersonnel INT REFERENCES personnel(idPersonnel) UNIQUE ,
    idRole INT REFERENCES role(idRole),
    pwd VARCHAR
);

CREATE TABLE import_personnel(
     idimport_personnel SERIAL PRIMARY KEY ,
     IM VARCHAR,
     nom VARCHAR,
     prenom VARCHAR,
     CIN VARCHAR(12),
     date_CIN DATE,
     indice VARCHAR,
     catOr VARCHAR,
     code_grade VARCHAR,
     fonction VARCHAR,
     tel VARCHAR,
     direction VARCHAR,
     service VARCHAR,
     idUtilisateur INT REFERENCES utilisateur(idUtilisateur)
);

CREATE TABLE region(
    idRegion SERIAL PRIMARY KEY ,
    nom VARCHAR
);
CREATE TABLE region_personne(
   idregion_personne SERIAL PRIMARY KEY ,
   dates date,
   idRegion INT REFERENCES region(idRegion),
   idPersonnel INT REFERENCES personnel(idPersonnel)
);
CREATE TABLE transport(
  idtransport SERIAL PRIMARY KEY ,
  nom VARCHAR
);
CREATE TABLE depart(
    iddepart SERIAL PRIMARY KEY ,
    dates DATE,
    numero_OR VARCHAR UNIQUE,
    Idpersonnel INT REFERENCES personnel(idPersonnel),
    Objet_mission VARCHAR,
    date_depart date,
    date_arriver date,
    code_Visa_depart varchar UNIQUE ,
    code_avance varchar UNIQUE ,
    engagement DATE,
    bordereau VARCHAR UNIQUE,
    soa VARCHAR,
    IdUtilisateur INT REFERENCES utilisateur(idUtilisateur)
);
CREATE TABLE itineraire(
    idItineraire SERIAL PRIMARY KEY ,
    iddepart INT REFERENCES depart(iddepart),
    numero INT,
    idRegion_depart INT REFERENCES region(idRegion),
    idRegion_arriver INT REFERENCES region(idRegion),
    idTransport INT REFERENCES transport(idtransport)
);

CREATE TABLE passage(
   idpassage SERIAL PRIMARY KEY ,
   dates DATE,
   numero_OR VARCHAR,
   Idpersonnel INT REFERENCES personnel(idPersonnel),
   date_passage date,
   objet_mission VARCHAR,
   code_visa_passage VARCHAR UNIQUE,
   idItineraire INT REFERENCES itineraire(idItineraire),
   IdUtilisateur INT REFERENCES utilisateur(idUtilisateur)
);
CREATE TABLE destination(
    iddestination SERIAL PRIMARY KEY ,
    dates date,
    numero_Or VARCHAR UNIQUE,
    Idpersonnel INT REFERENCES personnel(idPersonnel),
    iditineraire INT REFERENCES itineraire(idItineraire),
    objet_mission VARCHAR,
    date_arriver_destination DATE,
    code_visa_destination VARCHAR UNIQUE ,
    IdUtilisateur INT REFERENCES utilisateur(idUtilisateur)
);
CREATE TABLE retour(
  idretour SERIAL PRIMARY KEY ,
   dates date,
  numero_OR VARCHAR,
  Idpersonnel INT REFERENCES personnel(idPersonnel),
  objet_mission VARCHAR,
  date_visa_fin date,
  code_visa_fin VARCHAR,
  IdUtilisateur INT REFERENCES utilisateur(idUtilisateur)
);
CREATE TABLE paiement(
  idpaiement SERIAL PRIMARY KEY ,
  dates date,
  Iddepart INT REFERENCES depart(iddepart),
  date_suivit DATE,
  date_sortie_bon_de_caisse DATE
);

CREATE TABLE paiement_situation(
  idpaiement_situation SERIAL PRIMARY KEY ,
  dates date,
  situation VARCHAR,
  Idpaiement INT REFERENCES paiement(idpaiement),
  IdUtilisateur INT REFERENCES utilisateur(idUtilisateur)
);
CREATE TABLE modif_paiment(
    idmodif_paiment SERIAL PRIMARY KEY ,
    dates date,
    modif VARCHAR,
    Idpaiement INT REFERENCES paiement(idpaiement),
    IdUtilisateur INT REFERENCES utilisateur(idUtilisateur)
);
