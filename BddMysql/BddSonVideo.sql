CREATE  TABLE 'File' (
  'idFile' INT NOT NULL ,
  'Title' VARCHAR(255)NULL ,
  'ReleaseDate' DATE NULL ,
  'Length' VARCHAR(10)NULL ,
  'Path' VARCHAR(255) NULL ,
  'Find' TINYINT(1) NULL ,
  PRIMARY KEY ('idFile') );

CREATE  TABLE 'Movie' (
  'idMovie' INT NOT NULL ,
  'Synopsis' VARCHAR(255) NULL ,
  PRIMARY KEY ('idMovie') ,
  CONSTRAINT 'fk_Movie_File1'
    FOREIGN KEY ('idMovie' )
    REFERENCES 'File' ('idFile' ) );

CREATE  TABLE 'Person' (
  'idPerson' INT NOT NULL ,
  'LastName' VARCHAR(45) NOT NULL ,
  'FirstName' VARCHAR(45) NOT NULL ,
  'Nationality' VARCHAR(45) NULL ,
  PRIMARY KEY ('idPerson') );

CREATE  TABLE 'Type' (
  'idType' INT NOT NULL ,
  'Libelle' VARCHAR(45) NOT NULL ,
  PRIMARY KEY ('idType') );

CREATE  TABLE 'PersonType' (
  'idPerson' INT NOT NULL ,
  'idType' INT NOT NULL ,
  PRIMARY KEY ('idPerson', 'idType') ,
  CONSTRAINT 'fk_Person_has_Type_Person1'
    FOREIGN KEY ('idPerson' )
    REFERENCES 'Person' ('idPerson' ),
  CONSTRAINT 'fk_Person_has_Type_Type1'
    FOREIGN KEY ('idType' )
    REFERENCES 'Type' ('idType' ) );

CREATE  TABLE 'Sort' (
  'idSort' INT NOT NULL ,
  'Libelle' VARCHAR(45) NOT NULL ,
  'Type' INT NOT NULL ,
  PRIMARY KEY ('idSort') );

CREATE  TABLE 'PersonFile' (
  'idPerson' INT NOT NULL ,
  'idFile' INT NOT NULL ,
  'idType' INT NOT NULL ,
  PRIMARY KEY ('idPerson', 'idFile') ,
  CONSTRAINT 'fk_Person_has_File_Person1'
    FOREIGN KEY ('idPerson' )
    REFERENCES 'Person' ('idPerson' ),
  CONSTRAINT 'fk_Person_has_File_File1'
    FOREIGN KEY ('idFile' )
    REFERENCES 'File' ('idFile' ) );

CREATE  TABLE 'FileSort' (
  'idFile' INT NOT NULL ,
  'idSort' INT NOT NULL ,
  PRIMARY KEY ('idFile', 'idSort') ,
  CONSTRAINT 'fk_File_has_Sort_File1'
    FOREIGN KEY ('idFile' )
    REFERENCES 'File' ('idFile' ),
  CONSTRAINT 'fk_File_has_Sort_Sort1'
    FOREIGN KEY ('idSort' )
    REFERENCES 'Sort' ('idSort' ) );

CREATE  TABLE 'Album' (
  'idAlbum' INT NOT NULL ,
  'Libelle' VARCHAR(45) NULL ,
  'ReleaseDate' DATE NULL ,
  PRIMARY KEY ('idAlbum') );

CREATE  TABLE 'Music' (
  'idMusic' INT NOT NULL ,
  'idAlbum' INT NOT NULL ,
  PRIMARY KEY ('idMusic', 'idAlbum') ,
  CONSTRAINT 'fk_Music_File1'
    FOREIGN KEY ('idMusic' )
    REFERENCES 'File' ('idFile' ),
  CONSTRAINT 'fk_Music_Album1'
    FOREIGN KEY ('idAlbum' )
    REFERENCES 'Album' ('idAlbum' ) );

CREATE  TABLE `Playlist` (
  `idPlaylist` INT NOT NULL ,
  `name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`idPlaylist`) );

CREATE  TABLE `FilePlaylist` (
  `idFile` INT NOT NULL ,
  `idPlaylist` INT NOT NULL ,
  PRIMARY KEY (`idFile`, `idPlaylist`) ,
  CONSTRAINT `fk_File_has_Playlist_File1`
    FOREIGN KEY (`idFile` )
    REFERENCES `File` (`idFile` ),
  CONSTRAINT `fk_File_has_Playlist_Playlist1`
    FOREIGN KEY (`idPlaylist` )
    REFERENCES `Playlist` (`idPlaylist` ) );