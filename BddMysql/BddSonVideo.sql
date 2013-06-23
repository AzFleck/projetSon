CREATE  TABLE IF NOT EXISTS 'File' (
  'idFile' INT NOT NULL ,
  'Title' VARCHAR(45) NULL ,
  'ReleaseDate' DATE NULL ,
  'Length' VARCHAR(255) NULL ,
  'Path' VARCHAR(255) NULL ,
  'Find' TINYINT(1) NULL ,
  PRIMARY KEY ('idFile') );

CREATE  TABLE IF NOT EXISTS 'Movie' (
  'idMovie' INT NOT NULL ,
  'Synopsis' VARCHAR(255) NULL ,
  PRIMARY KEY ('idMovie') ,
  INDEX 'fk_Movie_File1_idx' ('idMovie' ASC) ,
  CONSTRAINT 'fk_Movie_File1'
    FOREIGN KEY ('idMovie' )
    REFERENCES 'File' ('idFile' ) );

CREATE  TABLE IF NOT EXISTS 'Person' (
  'idPerson' INT NOT NULL ,
  'LastName' VARCHAR(45) NOT NULL ,
  'FirstName' VARCHAR(45) NOT NULL ,
  'Nationality' VARCHAR(45) NULL ,
  PRIMARY KEY ('idPerson') );

CREATE  TABLE IF NOT EXISTS 'Type' (
  'idType' INT NOT NULL ,
  'Libelle' VARCHAR(45) NOT NULL ,
  PRIMARY KEY ('idType') );

CREATE  TABLE IF NOT EXISTS 'PersonType' (
  'idPerson' INT NOT NULL ,
  'idType' INT NOT NULL ,
  PRIMARY KEY ('idPerson', 'idType') ,
  INDEX 'fk_Person_has_Type_Type1_idx' ('idType' ASC) ,
  INDEX 'fk_Person_has_Type_Person1_idx' ('idPerson' ASC) ,
  CONSTRAINT 'fk_Person_has_Type_Person1'
    FOREIGN KEY ('idPerson' )
    REFERENCES 'Person' ('idPerson' ),
  CONSTRAINT 'fk_Person_has_Type_Type1'
    FOREIGN KEY ('idType' )
    REFERENCES 'Type' ('idType' ) );

CREATE  TABLE IF NOT EXISTS 'Sort' (
  'idSort' INT NOT NULL ,
  'Libelle' VARCHAR(45) NOT NULL ,
  PRIMARY KEY ('idSort') );

CREATE  TABLE IF NOT EXISTS 'PersonFile' (
  'idPerson' INT NOT NULL ,
  'idFile' INT NOT NULL ,
  PRIMARY KEY ('idPerson', 'idFile') ,
  INDEX 'fk_Person_has_File_File1_idx' ('idFile' ASC) ,
  INDEX 'fk_Person_has_File_Person1_idx' ('idPerson' ASC) ,
  CONSTRAINT 'fk_Person_has_File_Person1'
    FOREIGN KEY ('idPerson' )
    REFERENCES 'Person' ('idPerson' ),
  CONSTRAINT 'fk_Person_has_File_File1'
    FOREIGN KEY ('idFile' )
    REFERENCES 'File' ('idFile' ) );

CREATE  TABLE IF NOT EXISTS 'FileSort' (
  'idFile' INT NOT NULL ,
  'idSort' INT NOT NULL ,
  PRIMARY KEY ('idFile', 'idSort') ,
  INDEX 'fk_File_has_Sort_Sort1_idx' ('idSort' ASC) ,
  INDEX 'fk_File_has_Sort_File1_idx' ('idFile' ASC) ,
  CONSTRAINT 'fk_File_has_Sort_File1'
    FOREIGN KEY ('idFile' )
    REFERENCES 'File' ('idFile' ),
  CONSTRAINT 'fk_File_has_Sort_Sort1'
    FOREIGN KEY ('idSort' )
    REFERENCES 'Sort' ('idSort' ) );

CREATE  TABLE IF NOT EXISTS 'Album' (
  'idAlbum' INT NOT NULL ,
  'Libelle' VARCHAR(45) NULL ,
  'ReleaseDate' DATE NULL ,
  PRIMARY KEY ('idAlbum') );

CREATE  TABLE IF NOT EXISTS 'Music' (
  'idMusic' INT NOT NULL ,
  'idAlbum' INT NOT NULL ,
  PRIMARY KEY ('idMusic', 'idAlbum') ,
  INDEX 'fk_Music_File1_idx' ('idMusic' ASC) ,
  INDEX 'fk_Music_Album1_idx' ('idAlbum' ASC) ,
  CONSTRAINT 'fk_Music_File1'
    FOREIGN KEY ('idMusic' )
    REFERENCES 'File' ('idFile' ),
  CONSTRAINT 'fk_Music_Album1'
    FOREIGN KEY ('idAlbum' )
    REFERENCES 'Album' ('idAlbum' ) );
