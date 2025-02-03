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
CREATE TABLE personnel(
  idPersonnel SERIAL PRIMARY KEY ,
  matricule VARCHAR UNIQUE ,
  nom VARCHAR,
  prenom VARCHAR,
  CIN VARCHAR(12),
  CIN_du date,
  indice VARCHAR,
  CatOR VARCHAR,
  codeGrade VARCHAR,
  email VARCHAR UNIQUE ,
  tel VARCHAR(10) UNIQUE
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
    cles VARCHAR UNIQUE
);
CREATE TABLE utilisateur(
  idUtilisateur SERIAL PRIMARY KEY ,
  dates date,
  idPersonnel INT REFERENCES personnel(idPersonnel),
  idRole INT REFERENCES role(idRole),
  pwd VARCHAR
);
