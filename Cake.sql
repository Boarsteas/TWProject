SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Lager`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Lager` (
  `Lagerbest` INT NULL ,
  `Lagerminbest` INT NULL ,
  `LID` INT NOT NULL ,
  PRIMARY KEY (`LID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Ware`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Ware` (
  `WID` INT NOT NULL ,
  `WName` VARCHAR(45) NULL ,
  `WPreis` VARCHAR(45) NULL ,
  `LID` INT NULL ,
  PRIMARY KEY (`WID`) ,
  INDEX `LID_idx` (`LID` ASC) ,
  CONSTRAINT `LID`
    FOREIGN KEY (`LID` )
    REFERENCES `mydb`.`Lager` (`LID` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`KAdresse`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`KAdresse` (
  `KAID` INT NOT NULL ,
  `KOrt` VARCHAR(45) NULL ,
  `KPLZ` VARCHAR(45) NULL ,
  `KStrasse` VARCHAR(45) NULL ,
  PRIMARY KEY (`KAID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Kunde`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Kunde` (
  `KID` INT NOT NULL ,
  `KVorname` VARCHAR(45) NULL ,
  `KName` VARCHAR(45) NULL ,
  `KAID` INT NULL ,
  PRIMARY KEY (`KID`) ,
  INDEX `KAID_idx` (`KAID` ASC) ,
  CONSTRAINT `KAID`
    FOREIGN KEY (`KAID` )
    REFERENCES `mydb`.`KAdresse` (`KAID` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Warenkorb`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Warenkorb` (
  `KorbID` INT NOT NULL ,
  `WID` INT NULL ,
  `WMenge` INT NULL ,
  `KID` INT NULL ,
  PRIMARY KEY (`KorbID`) ,
  INDEX `WID_idx` (`WID` ASC) ,
  INDEX `KID_idx` (`KID` ASC) ,
  CONSTRAINT `WID`
    FOREIGN KEY (`WID` )
    REFERENCES `mydb`.`Ware` (`WID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `KID`
    FOREIGN KEY (`KID` )
    REFERENCES `mydb`.`Kunde` (`KID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Kasse`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Kasse` (
  `KNr` INT NOT NULL ,
  `KAktBest` INT NULL ,
  `KAAnfBest` INT NULL ,
  `KorbID` INT NULL ,
  PRIMARY KEY (`KNr`) ,
  INDEX `KorbID_idx` (`KorbID` ASC) ,
  CONSTRAINT `KorbID`
    FOREIGN KEY (`KorbID` )
    REFERENCES `mydb`.`Warenkorb` (`KorbID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
