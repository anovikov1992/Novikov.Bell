CREATE TABLE IF NOT EXISTS organization (
    id          INTEGER  PRIMARY KEY AUTO_INCREMENT,
    version     INTEGER NOT NULL,
    name        VARCHAR(50) NOT NULL,
    full_Name   VARCHAR(50) NOT NULL,
    inn         INTEGER NOT NULL,
    kpp         INTEGER NOT NULL,
    ur_Address  VARCHAR(50) NOT NULL,
    phone       INTEGER ,
    is_Active   BOOLEAN
);

CREATE TABLE IF NOT EXISTS office (
    id                  INTEGER  PRIMARY KEY AUTO_INCREMENT,
    version             INTEGER NOT NULL,
    name                VARCHAR(50) NOT NULL,
    Address             VARCHAR(50) NOT NULL,
    is_Active           BOOLEAN NOT NULL,
    organization_id     INTEGER ,
    CONSTRAINT office_FKEY   FOREIGN KEY (organization_id) REFERENCES organization(id)
);

CREATE TABLE IF NOT EXISTS doc (
   id                  INTEGER  PRIMARY KEY AUTO_INCREMENT,
   version              INTEGER NOT NULL,
   doc_name             VARCHAR(50) NOT NULL,
   doc_code             VARCHAR(50)  NOT NULL
);

CREATE TABLE IF NOT EXISTS country (
   id                   INTEGER  PRIMARY KEY AUTO_INCREMENT,
   version              INTEGER NOT NULL,
   country_name         VARCHAR(50) NOT NULL,
   country_code         INTEGER(50)  NOT NULL,
   citizenship_code     VARCHAR(250)  NOT NULL
);

CREATE TABLE IF NOT EXISTS user (
    id                 INTEGER      PRIMARY KEY AUTO_INCREMENT,
   version             INTEGER      NOT NULL,
   first_name          VARCHAR(50)  NOT NULL,
   middle_name         VARCHAR(50)  NOT NULL,
   second_name         VARCHAR(50)  NOT NULL,
   position            VARCHAR(50)  NOT NULL,
   phone               INTEGER      NOT NULL,
   doc_date            DATE         NOT NULL,
   is_Identified       BOOLEAN     NOT NULL,
   office_id           INTEGER  ,
   doc_id              INTEGER  ,
   country_id              INTEGER  ,
   CONSTRAINT user_FKEY_doc   FOREIGN KEY (doc_id) REFERENCES doc(id),
   CONSTRAINT user_FKEY_office   FOREIGN KEY (office_id) REFERENCES office(id),
   CONSTRAINT user_FKEY_country   FOREIGN KEY (country_id) REFERENCES country(id)
);
