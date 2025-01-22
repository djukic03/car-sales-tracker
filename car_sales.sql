/*
SQLyog Community v13.3.0 (64 bit)
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
  `first_reg` date NOT NULL,
  `mileage` int(11) NOT NULL,
  `category` varchar(20) NOT NULL,
  `fuel` varchar(20) NOT NULL,
  `engine_capacity` double NOT NULL,
  `engine_power` double NOT NULL,
  `gearbox` varchar(20) NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

/*Data for the table `car` */

insert  into `car`(`id`,`brand`,`model`,`first_reg`,`mileage`,`category`,`fuel`,`engine_capacity`,`engine_power`,`gearbox`,`price`) values 
(1,'Audi','R8','2015-01-01',0,'','',0,0,'',50000),
(2,'Nissan','R34 GTR','2015-03-14',0,'','',0,0,'',100000),
(3,'Seat','Ibiza','2011-01-01',420000,'Hatchback','Diesel',1.2,65,'Manual',5000),
(4,'Audi','A4','2003-01-01',0,'','',0,0,'',10000),
(5,'Ford','Mustang','2016-01-01',0,'','',0,0,'',25000),
(6,'Volkswagen','Golf 5','2008-01-01',0,'','',0,0,'',3000),
(11,'BMW','M4','2011-01-01',0,'','',0,0,'',20000),
(12,'Škoda','Superb','2021-01-01',150000,'Limousine/Salon','Petrol',1.5,90,'Automatic',10000),
(13,'Nissan','Qashqai','2010-01-01',0,'','',0,0,'',12000),
(14,'Volkswagen','Golf 8','2019-01-01',230000,'Hatchback','Petrol',1.4,85,'Automatic',15000),
(15,'BMW','M3','2018-01-01',0,'','',0,0,'',50000),
(16,'Audi','A3','2013-01-01',0,'','',0,0,'',6000),
(17,'Volkswagen','Passat','2020-01-01',220885,'Estate/Wagon','Petrol',2,150,'Automatic',16999),
(18,'Opel','Astra','2006-01-01',420000,'Estate/Wagon','Diesel',1.6,120,'Manual',2000),
(19,'Mercedes Benz','E 200','2009-01-01',235800,'Limousine/Salon','Petrol',2.2,136,'Manual',11000),
(23,'Volkswagen','Polo','2012-01-01',460000,'Hatchback','Diesel',1.2,65,'Manual',4000);

/*Table structure for table `customer` */

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

/*Data for the table `customer` */

insert  into `customer`(`id`,`name`,`phone`,`email`) values 
(1,'Nemanja Đukić','0637412291','djukic.nemanja003@gmail.com'),
(3,'Fakultet organizacionih nauka','011123456','fon@fon.bg.ac.rs'),
(4,'Centro Agrar','069661036','centro.agrar@gmail.com'),
(5,'Teodora Đukić','0631748008','teka.djukic@gmail.com'),
(6,'Nebojsa Đukić','069661036','nebojsa@gmail.com'),
(7,'Ana Djukic','0656153878','ana@gmail.com'),
(8,'OTP Banka','011456789','banka@otp.com'),
(9,'Vrtic \"Neven\"','0143423456','neven@gmail.com'),
(12,'Mileta Đukić','064845612','mileta@gmail.com'),
(13,'Metalac Valjevo','014111111','metalac.to.je.tim.iz.valjeva@valjevo.com'),
(15,'KK Crvena Zvezda','011456123','kkczv@beograd.rs'),
(16,'Studenjak','011111111','stud@stud.stud'),
(20,'Djukic Prevoz','014568565','prevoz@prevoz.djukic');

/*Table structure for table `invoice` */

DROP TABLE IF EXISTS `invoice`;

CREATE TABLE `invoice` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `invoice_num` bigint(20) unsigned NOT NULL,
  `date_of_issue` date NOT NULL,
  `total_amount` double NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `customer_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_fk` (`user_id`),
  KEY `costumer_fk` (`customer_id`),
  CONSTRAINT `costumer_fk` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

/*Data for the table `invoice` */

insert  into `invoice`(`id`,`invoice_num`,`date_of_issue`,`total_amount`,`user_id`,`customer_id`) values 
(8,1,'2024-12-21',50000,3,1),
(9,2,'2024-12-21',5080000,3,3),
(10,3,'2024-12-21',20000,2,5),
(11,4,'2024-12-21',100000,1,4),
(12,5,'2024-12-21',325000,3,8),
(13,6,'2024-12-23',1000000,3,7),
(14,7,'2024-12-24',100000,3,1),
(15,8,'2024-12-25',20000,3,7),
(16,9,'2024-12-26',330000,1,5),
(17,10,'2024-12-26',24000,1,13),
(18,11,'2025-01-19',15000,3,13),
(19,12,'2025-01-19',85000,2,3),
(20,13,'2025-01-20',30000,3,20),
(21,14,'2025-01-22',95995,3,3);

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
(12,2,5,25000,125000,5),
(13,1,1,1000000,1000000,6),
(14,1,1,100000,100000,2),
(15,1,1,8000,8000,3),
(15,2,1,12000,12000,13),
(16,1,4,20000,80000,11),
(16,2,5,50000,250000,15),
(17,1,12,2000,24000,18),
(18,1,1,15000,15000,14),
(19,1,10,6000,60000,16),
(19,2,5,5000,25000,3),
(20,1,3,10000,30000,12),
(21,1,1,11000,11000,19),
(21,2,5,16999,84995,17);

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
