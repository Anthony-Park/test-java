DROP TABLE vet_specialties IF EXISTS;
DROP TABLE vets IF EXISTS;
DROP TABLE specialties IF EXISTS;
DROP TABLE visits IF EXISTS;
DROP TABLE pets IF EXISTS;
DROP TABLE types IF EXISTS;
DROP TABLE owners IF EXISTS;

-- kpop.calendar definition
DROP TABLE calendar IF EXISTS;
DROP TABLE artist IF EXISTS;
DROP TABLE agency IF EXISTS;
-- NOT NULL is not allowed
CREATE TABLE calendar (
    id          INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    artist_fk   INTEGER,
    name        VARCHAR(255),
    start       DATETIME,
    end1       DATETIME, -- end is keyword, MySQL uses backtick '
    link        VARCHAR(255), -- DEFAULT NULL
    meta        VARCHAR(255)
);

CREATE TABLE artist (
    artist_id   INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    agency_id    INT,
    artist_name     VARCHAR(100),
    artist_content   VARCHAR(100),
    artist_img      VARCHAR(255),
    artist_Count   INT
/*  artist_id   INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    agency_id   INT,
    artist_name VARCHAR(1),
    artist_cd   VARCHAR(100),
    comment     VARCHAR(500),

    gender      CHAR(1),
    unit_yn     CHAR(1),
    del_yn      CHAR(1),
    reg_id      VARCHAR(100),
    reg_dt      DATETIME,

    mod_id      VARCHAR(100),
    mod_dt      DATETIME
*/
);

CREATE TABLE agency (
    agencyId INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    agencyName varchar(255)
);

ALTER TABLE calendar ADD CONSTRAINT fk_calendar_artist FOREIGN KEY (artist_fk) REFERENCES artist (artist_id);
ALTER TABLE artist ADD CONSTRAINT fk_artist_agency FOREIGN KEY (agency_id) REFERENCES agency (agencyId);
-- endof kpop.calendar definition

CREATE TABLE vets (
  id         INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  first_name VARCHAR(30),
  last_name  VARCHAR(30)
);
CREATE INDEX vets_last_name ON vets (last_name);

CREATE TABLE specialties (
  id   INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  name VARCHAR(80)
);
CREATE INDEX specialties_name ON specialties (name);

CREATE TABLE vet_specialties (
  vet_id       INTEGER NOT NULL,
  specialty_id INTEGER NOT NULL
);
ALTER TABLE vet_specialties ADD CONSTRAINT fk_vet_specialties_vets FOREIGN KEY (vet_id) REFERENCES vets (id);
ALTER TABLE vet_specialties ADD CONSTRAINT fk_vet_specialties_specialties FOREIGN KEY (specialty_id) REFERENCES specialties (id);

CREATE TABLE types (
  id   INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  name VARCHAR(80)
);
CREATE INDEX types_name ON types (name);

CREATE TABLE owners (
  id         INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  first_name VARCHAR(30),
  last_name  VARCHAR_IGNORECASE(30),
  address    VARCHAR(255),
  city       VARCHAR(80),
  telephone  VARCHAR(20)
);
CREATE INDEX owners_last_name ON owners (last_name);

CREATE TABLE pets (
  id         INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  name       VARCHAR(30),
  birth_date DATE,
  type_id    INTEGER NOT NULL,
  owner_id   INTEGER
);
ALTER TABLE pets ADD CONSTRAINT fk_pets_owners FOREIGN KEY (owner_id) REFERENCES owners (id);
ALTER TABLE pets ADD CONSTRAINT fk_pets_types FOREIGN KEY (type_id) REFERENCES types (id);
CREATE INDEX pets_name ON pets (name);

CREATE TABLE visits (
  id          INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  pet_id      INTEGER,
  visit_date  DATE,
  description VARCHAR(255)
);
ALTER TABLE visits ADD CONSTRAINT fk_visits_pets FOREIGN KEY (pet_id) REFERENCES pets (id);
CREATE INDEX visits_pet_id ON visits (pet_id);
