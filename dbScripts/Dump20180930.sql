CREATE DATABASE  IF NOT EXISTS `POSv2` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `POSv2`;
-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: POSv2
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `account` (
  `acct_id` int(11) NOT NULL AUTO_INCREMENT,
  `acct_attdt_id` int(11) DEFAULT NULL,
  `acct_username` varchar(45) DEFAULT NULL,
  `acct_password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`acct_id`),
  KEY `acct_attndt_fk_idx` (`acct_attdt_id`),
  CONSTRAINT `acct_attndt_fk` FOREIGN KEY (`acct_attdt_id`) REFERENCES `attendant` (`attdt_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `attendance` (
  `attdnc_id` int(11) NOT NULL AUTO_INCREMENT,
  `attdnc_attdnt_id` int(11) NOT NULL,
  `attdnc_signin_time` varchar(45) DEFAULT NULL,
  `attdnc_signout_time` varchar(45) DEFAULT NULL,
  `attdnc_date` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`attdnc_id`),
  KEY `attndce_attdnt_fk_idx` (`attdnc_attdnt_id`),
  CONSTRAINT `attndce_attdnt_fk` FOREIGN KEY (`attdnc_attdnt_id`) REFERENCES `attendant` (`attdt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `attendant`
--

DROP TABLE IF EXISTS `attendant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `attendant` (
  `attdt_id` int(11) NOT NULL AUTO_INCREMENT,
  `attdt_first_name` varchar(45) DEFAULT NULL,
  `attdt_mid_name` varchar(45) DEFAULT NULL,
  `attdt_surname` varchar(45) DEFAULT NULL,
  `attdt_gender` varchar(7) DEFAULT NULL,
  `attdt_dob` date DEFAULT NULL,
  `attdt_address` varchar(45) DEFAULT NULL,
  `attdt_phone_no` varchar(45) DEFAULT NULL,
  `attdt_email` varchar(45) DEFAULT NULL,
  `attdt_dt_emp` date DEFAULT NULL,
  `attdt_position` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`attdt_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `branch`
--

DROP TABLE IF EXISTS `branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `branch` (
  `branchID` int(11) NOT NULL AUTO_INCREMENT,
  `banchName` varchar(50) DEFAULT NULL,
  `branchAddress` varchar(60) DEFAULT NULL,
  `branchState` varchar(50) DEFAULT NULL,
  `branchLGA` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`branchID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `inventory` (
  `inv_id` int(11) NOT NULL AUTO_INCREMENT,
  `inv_pro_id` int(11) NOT NULL,
  `inv_desc` varchar(45) NOT NULL,
  `inv_no_of_units` int(11) NOT NULL,
  `inv_qty_per_unit` int(11) NOT NULL,
  `inv_total_qty` int(11) NOT NULL,
  `inv_total_ordered` int(11) NOT NULL,
  `inv_reorder_lvl` int(11) NOT NULL,
  PRIMARY KEY (`inv_id`),
  KEY `inv_pro_id_fk_idx` (`inv_pro_id`),
  CONSTRAINT `inv_pro_id_fk` FOREIGN KEY (`inv_pro_id`) REFERENCES `product` (`pro_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_date` date NOT NULL,
  `order_time` time NOT NULL,
  `order_no` varchar(45) NOT NULL,
  `order_price` int(11) NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `organization`
--

DROP TABLE IF EXISTS `organization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `organization` (
  `org_id` int(11) NOT NULL AUTO_INCREMENT,
  `org_name` varchar(45) NOT NULL,
  `org_addrs` varchar(45) NOT NULL,
  `org_phone` varchar(45) NOT NULL,
  `org_email` varchar(45) NOT NULL,
  `org_logo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`org_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `payment` (
  `pay_id` int(11) NOT NULL AUTO_INCREMENT,
  `pay_attdt_id` int(11) NOT NULL,
  `pay_order_id` int(11) NOT NULL,
  `pay_price` int(11) NOT NULL,
  `pay_amt_paid` int(11) NOT NULL,
  `pay_balance` int(11) NOT NULL,
  `pay_type` varchar(45) NOT NULL,
  `pay_date` date NOT NULL,
  `pay_time` time NOT NULL,
  PRIMARY KEY (`pay_id`),
  KEY `pay_attdt_id_fk_idx` (`pay_attdt_id`),
  KEY `pay_order_id_fk_idx` (`pay_order_id`),
  CONSTRAINT `pay_attdt_id_fk` FOREIGN KEY (`pay_attdt_id`) REFERENCES `attendant` (`attdt_id`),
  CONSTRAINT `pay_order_id_fk` FOREIGN KEY (`pay_order_id`) REFERENCES `order` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `product` (
  `pro_id` int(11) NOT NULL AUTO_INCREMENT,
  `pro_name` varchar(45) NOT NULL,
  `pro_cat_id` int(11) NOT NULL,
  `pro_desc` varchar(45) NOT NULL,
  `pro_cost` int(11) NOT NULL,
  `pro_price` varchar(45) NOT NULL,
  PRIMARY KEY (`pro_id`),
  KEY `pro_cat_id_fk_idx` (`pro_cat_id`),
  CONSTRAINT `pro_cat_id_fk` FOREIGN KEY (`pro_cat_id`) REFERENCES `product_category` (`cat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product_category`
--

DROP TABLE IF EXISTS `product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `product_category` (
  `cat_id` int(11) NOT NULL AUTO_INCREMENT,
  `cat_name` varchar(45) NOT NULL,
  `cat_desc` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `purchases`
--

DROP TABLE IF EXISTS `purchases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `purchases` (
  `pur_id` int(11) NOT NULL,
  `pur_order_id` int(11) NOT NULL,
  `pur_pro_id` int(11) NOT NULL,
  `pur_price` int(11) NOT NULL,
  PRIMARY KEY (`pur_id`),
  KEY `pur_item_fk_idx` (`pur_pro_id`),
  KEY `pur_order_id_idx` (`pur_order_id`),
  CONSTRAINT `pur_item_fk` FOREIGN KEY (`pur_pro_id`) REFERENCES `product` (`pro_id`),
  CONSTRAINT `pur_order_id` FOREIGN KEY (`pur_order_id`) REFERENCES `order` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `shop`
--

DROP TABLE IF EXISTS `shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `shop` (
  `shop_id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_name` varchar(45) NOT NULL,
  `shop_location` varchar(45) DEFAULT NULL,
  `shop_branch` varchar(45) DEFAULT NULL,
  `shop_rc_no` varchar(15) DEFAULT NULL,
  `shop_owner` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-30 14:35:12
