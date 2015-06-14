-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema workflow
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema workflow
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `workflow` DEFAULT CHARACTER SET latin1 ;
USE `workflow` ;

-- -----------------------------------------------------
-- Table `workflow`.`authorities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `workflow`.`authorities` ;

CREATE TABLE IF NOT EXISTS `workflow`.`authorities` (
  `Username` VARCHAR(60) NOT NULL,
  `Authority` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`Username`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `workflow`.`customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `workflow`.`customer` ;

CREATE TABLE IF NOT EXISTS `workflow`.`customer` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `FirstName` VARCHAR(45) NOT NULL,
  `LastName` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(1000) NOT NULL,
  `Admin` TINYINT(1) NOT NULL,
  `Enabled` TINYINT(1) NOT NULL,
  `HasFacebook` TINYINT(1) NOT NULL,
  `HasInstagram` TINYINT(1) NOT NULL,
  `HasTwitter` TINYINT(1) NOT NULL,
  `HasGoogle` TINYINT(1) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `Email_UNIQUE` (`Email` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `workflow`.`logging`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `workflow`.`logging` ;

CREATE TABLE IF NOT EXISTS `workflow`.`logging` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `TextPosted` VARCHAR(500) NOT NULL,
  `PicturePosted` TINYINT(1) NULL DEFAULT NULL,
  `PostedAt` DATETIME NOT NULL,
  `State` ENUM('Pending','Sucessful','Unsucessful') NULL DEFAULT NULL,
  `MediaName` VARCHAR(500) NOT NULL,
  `Customer_Id` INT(11) NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_logging_customer1_idx` (`Customer_Id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `workflow`.`user_media_channels_parameters`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `workflow`.`user_media_channels_parameters` ;

CREATE TABLE IF NOT EXISTS `workflow`.`user_media_channels_parameters` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `Media` VARCHAR(45) NOT NULL,
  `AccessToken` VARCHAR(100) NOT NULL,
  `AccessTokenSecret` VARCHAR(100) NOT NULL,
  `Customer_Id` INT(11) NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_user_media_channels_parameters_customer_idx` (`Customer_Id` ASC),
  CONSTRAINT `fk_user_media_channels_parameters_customer`
    FOREIGN KEY (`Customer_Id`)
    REFERENCES `workflow`.`customer` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
