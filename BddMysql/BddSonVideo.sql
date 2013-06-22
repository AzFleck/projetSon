SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `BddSonVideo` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `BddSonVideo` ;

-- -----------------------------------------------------
-- Table `BddSonVideo`.`Movie`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `BddSonVideo`.`Movie` (
  `idMovie` INT NOT NULL ,
  `Title` VARCHAR(45) NOT NULL ,
  `Sort` VARCHAR(45) NULL ,
  `ReleaseDate` VARCHAR(45) NULL ,
  `Length` VARCHAR(45) NULL ,
  `Synopsis` VARCHAR(45) NULL ,
  PRIMARY KEY (`idMovie`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BddSonVideo`.`Person`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `BddSonVideo`.`Person` (
  `idPerson` INT NOT NULL ,
  `LastName` VARCHAR(45) NOT NULL ,
  `FirstName` VARCHAR(45) NOT NULL ,
  `Nationality` VARCHAR(45) NULL ,
  PRIMARY KEY (`idPerson`) )
ENGINE = InnoDB;

USE `BddSonVideo` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
