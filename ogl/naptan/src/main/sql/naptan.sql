-- ======================================================================
-- NaPTAN
-- ======================================================================

CREATE SCHEMA reference;
SET search_path = reference;

-- Rail References
DROP TABLE naptan_rail;
CREATE TABLE naptan_rail (
       id               SERIAL NOT NULL,
       atcoCode         VARCHAR(16),
       tiplocCode       VARCHAR(12),
       crsCode          CHAR(3),
       stationName      VARCHAR(64),
       stationNameLang  VARCHAR(64),
       gridType         CHAR(1),
       easting          INTEGER,
       northing         INTEGER,
       creationDT       TIMESTAMP WITHOUT TIME ZONE,
       modificationDT   TIMESTAMP WITHOUT TIME ZONE,
       revisionNumber   INTEGER,
       modification     VARCHAR(64),
       PRIMARY KEY (ID)
);
CREATE UNIQUE INDEX naptan_rail_a ON naptan_rail(atcoCode);
CREATE UNIQUE INDEX naptan_rail_t ON naptan_rail(tiplocCode);
CREATE INDEX naptan_rail_c ON naptan_rail(crsCode);
CREATE INDEX naptan_rail_s ON naptan_rail(stationName);
CREATE INDEX naptan_rail_g ON naptan_rail(easting,northing);
ALTER TABLE naptan_rail OWNER TO rail;

DROP TABLE naptan_air;
CREATE TABLE naptan_air (
    id          SERIAL NOT NULL,
    AtcoCode    VARCHAR(16),
    IataCode    VARCHAR(16),
    Name        NAME,
    NameLang    NAME,
    CreationDT  TIMESTAMP WITHOUT TIME ZONE,
    ModificationDT  TIMESTAMP WITHOUT TIME ZONE,
    RevisionNumber  INTEGER,
    Modification    VARCHAR(64),
    PRIMARY KEY (ID)
);
CREATE INDEX naptan_air_a ON naptan_air(AtcoCode);
CREATE INDEX naptan_air_i ON naptan_air(IataCode);
CREATE INDEX naptan_air_n ON naptan_air(Name);
ALTER TABLE naptan_air OWNER TO rail;