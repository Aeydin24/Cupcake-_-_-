-- MySQL Workbench Forward Engineering
DROP SCHEMA IF EXISTS cupcake;
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema cupcake
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema cupcake
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cupcake` DEFAULT CHARACTER SET latin1 ;
USE `cupcake` ;

-- -----------------------------------------------------
-- Table `cupcake`.`users`
-- -----------------------------------------------------
CREATE TABLE `users` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `balance` int(11) DEFAULT '50',
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`username`);
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `cupcake`.`orderdetails`
-- -----------------------------------------------------
CREATE TABLE `orderdetails` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bname` varchar(45) DEFAULT NULL,
  `tname` varchar(45) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tname_idx` (`tname`),
  KEY `bname_idx` (`bname`),
  CONSTRAINT `bname` FOREIGN KEY (`bname`) REFERENCES `bottoms` (`name`),
  CONSTRAINT `tname` FOREIGN KEY (`tname`) REFERENCES `toppings` (`name`);
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `cupcake`.`invoices`
-- -----------------------------------------------------

CREATE TABLE `invoices` (
  `id` int(11) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `date` datetime DEFAULT CURRENT_TIMESTAMP,
  KEY `username_idx` (`username`),
  KEY `id_idx` (`id`),
  CONSTRAINT `id` FOREIGN KEY (`id`) REFERENCES `orderdetails` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `username` FOREIGN KEY (`username`) REFERENCES `users` (`username`);
  CONSTRAINT `tname` FOREIGN KEY (`tname`) REFERENCES `toppings` (`name`);
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `cupcake`.`toppings`
-- -----------------------------------------------------
CREATE TABLE `toppings` (
  `name` varchar(45) NOT NULL,
  `price` int(11) DEFAULT NULL,
  PRIMARY KEY (`name`);
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `cupcake`.`bottoms`
-- -----------------------------------------------------
CREATE TABLE `bottoms` (
  `name` varchar(45) NOT NULL,
  `price` int(11) DEFAULT NULL,
  PRIMARY KEY (`name`);
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


INSERT INTO users(username, password, balance, email)
VALUES  ("Jens","jens123","0","jens@gmail.com"),
        ("Hans","hans123","0","hans@gmail.com"),
        ("admin","admin123","0","admin@gmail.com");

INSERT INTO toppings(name, price)
VALUES  ("Chocolate","5"),
        ("Blueberry","5"),
        ("Raspberry","5"),
        ("Crispy","6"),
        ("Strawberry","6"),
        ("Rum/Raisin","7"),
        ("Orange","8"),
        ("Lemon","8"),
        ("Blue cheese","9");

INSERT INTO bottoms(name, price)
VALUES  ("Chocolate","5"),
        ("Vanilla","5"),
        ("Nutmeg","5"),
        ("Pistacio","6"),
        ("Almond","7");

