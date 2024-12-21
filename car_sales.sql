/*
SQLyog Community v13.2.1 (64 bit)
MySQL - 10.4.32-MariaDB : Database - car_sales
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`car_sales` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci */;

USE `car_sales`;

/*Table structure for table `car` */

DROP TABLE IF EXISTS `car`;

CREATE TABLE `car` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `brand` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

/*Data for the table `car` */

insert  into `car`(`id`,`brand`,`model`,`price`) values 
(1,'Audi','R8',50000),
(2,'Nissan','R34 GTR',100000),
(3,'Seat','Ibiza',5000),
(4,'Audi','A4',10000),
(5,'Ford','Mustang',25000),
(6,'Volkswagen','Golf 5',1000000),
(10,'Audi','A3',6000),
(11,'BMW','M4',20000),
(12,'Škoda','Octavia',10000);

/*Table structure for table `customer` */

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `phone` int(11) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

/*Data for the table `customer` */

insert  into `customer`(`id`,`name`,`phone`,`email`) values 
(1,'Nemanja Đukić',637412291,'djukic.nemanja003@gmail.com'),
(3,'Fakultet organizacionih nauka',11123456,'fon@fon.bg.ac.rs'),
(4,'Centro agrar',69661036,'centro.agrar@gmail.com'),
(5,'Teodora Đukić',631748008,'teka.djukic@gmail.com'),
(6,'Nebojsa Djukic',69661036,'nebojsa@gmail.com'),
(7,'Ana Djukic',656153878,'ana@gmail.com'),
(8,'OTP Banka',11456789,'banka@otp.com');

/*Table structure for table `invoice` */

DROP TABLE IF EXISTS `invoice`;

CREATE TABLE `invoice` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `date_of_issue` date NOT NULL,
  `total_amount` double NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `customer_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_fk` (`user_id`),
  KEY `costumer_fk` (`customer_id`),
  CONSTRAINT `costumer_fk` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

/*Data for the table `invoice` */

insert  into `invoice`(`id`,`date_of_issue`,`total_amount`,`user_id`,`customer_id`) values 
(8,'2024-12-21',50000,3,1),
(9,'2024-12-21',5080000,3,3),
(10,'2024-12-21',20000,2,5),
(11,'2024-12-21',100000,1,4),
(12,'2024-12-21',325000,3,8);

/*Table structure for table `invoice_item` */

DROP TABLE IF EXISTS `invoice_item`;

CREATE TABLE `invoice_item` (
  `invoice_id` bigint(20) unsigned NOT NULL,
  `rb` int(10) unsigned NOT NULL,
  `quantity` int(10) unsigned NOT NULL,
  `price_of_one` double unsigned NOT NULL,
  `sum` double unsigned NOT NULL COMMENT 'quantity*price_of_one',
  `car_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`invoice_id`,`rb`),
  KEY `rb` (`rb`),
  KEY `car_fk` (`car_id`),
  CONSTRAINT `car_fk` FOREIGN KEY (`car_id`) REFERENCES `car` (`id`),
  CONSTRAINT `invoice_fk` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

/*Data for the table `invoice_item` */

insert  into `invoice_item`(`invoice_id`,`rb`,`quantity`,`price_of_one`,`sum`,`car_id`) values 
(8,1,1,50000,50000,1),
(9,1,3,10000,30000,4),
(9,3,10,5000,50000,3),
(9,4,5,1000000,5000000,6),
(10,1,1,20000,20000,11),
(11,1,4,25000,100000,5),
(12,1,20,10000,200000,12),
(12,2,5,25000,125000,5);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`first_name`,`last_name`) values 
(1,'admin','admin','Admin','Adminić'),
(2,'nemanja','nemanja','Nemanja','Đukić'),
(3,'a','a','A','A');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
