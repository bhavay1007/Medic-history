-- MySQL dump 10.13  Distrib 8.3.0, for macos14.2 (arm64)
--
-- Host: localhost    Database: medic_history
-- ------------------------------------------------------
-- Server version	8.3.0
DROP SCHEMA IF EXISTS medic_history;
CREATE SCHEMA medic_history;
-- Use the database
USE medic_history;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `allergy`
--

DROP TABLE IF EXISTS `allergy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `allergy` (
  `allergyID` int NOT NULL AUTO_INCREMENT,
  `allergen_name` varchar(255) NOT NULL,
  PRIMARY KEY (`allergyID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allergy`
--

LOCK TABLES `allergy` WRITE;
/*!40000 ALTER TABLE `allergy` DISABLE KEYS */;
INSERT INTO `allergy` VALUES (13,'Peanuts'),(14,'Shellfish'),(15,'Penicillin'),(16,'Latex'),(17,'Pollen');
/*!40000 ALTER TABLE `allergy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointment` (
  `appointmentID` char(36) NOT NULL,
  `doctorID` char(36) NOT NULL,
  `patientID` char(36) NOT NULL,
  PRIMARY KEY (`appointmentID`),
  KEY `doctorID` (`doctorID`),
  KEY `patientID` (`patientID`),
  CONSTRAINT `appointment_ibfk_1` FOREIGN KEY (`doctorID`) REFERENCES `doctor` (`doctorID`) ON DELETE CASCADE,
  CONSTRAINT `appointment_ibfk_3` FOREIGN KEY (`patientID`) REFERENCES `patient` (`patientID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
INSERT INTO `appointment` VALUES ('b218ad6a-17ce-4ec6-9826-d52b5727605f','621c85c8-dcb1-11ee-87fa-815c4c682378','6d0c416c-dcb1-11ee-87fa-815c4c682378');
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appointment_details`
--

DROP TABLE IF EXISTS `appointment_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointment_details` (
  `appointmentID` char(36) NOT NULL,
  `appointment_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `diagnosis` text,
  `prescription` text,
  `paymentreceived` int DEFAULT NULL,
  `paymentdue` int DEFAULT NULL,
  `pdf_url` varchar(1024) DEFAULT NULL,
  `is_confirmed` tinyint(1) DEFAULT '0',
  `is_completed` tinyint(1) DEFAULT '0',
  `appointmentName` text,
  PRIMARY KEY (`appointmentID`),
  CONSTRAINT `appointment_details_ibfk_2` FOREIGN KEY (`appointmentID`) REFERENCES `appointment` (`appointmentID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment_details`
--

LOCK TABLES `appointment_details` WRITE;
/*!40000 ALTER TABLE `appointment_details` DISABLE KEYS */;
INSERT INTO `appointment_details` VALUES ('b218ad6a-17ce-4ec6-9826-d52b5727605f','2023-12-18 07:47:17','','',0,0,'',0,0,'afadfas');
/*!40000 ALTER TABLE `appointment_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Doctor`
--

DROP TABLE IF EXISTS `Doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Doctor` (
  `doctorID` char(36) NOT NULL,
  `name` varchar(255) NOT NULL,
  `specialization` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`doctorID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Doctor`
--

LOCK TABLES `Doctor` WRITE;
/*!40000 ALTER TABLE `Doctor` DISABLE KEYS */;
INSERT INTO `Doctor` VALUES ('621c5de6-dcb1-11ee-87fa-815c4c682378','Dr. Smith','Senior gynacologist','smith@gmail.com','qwerty'),('621c8398-dcb1-11ee-87fa-815c4c682378','Dr. Johnson','Orthopedics','johnson@gmail.com','qwerty'),('621c851e-dcb1-11ee-87fa-815c4c682378','Dr. Brown','Pediatrics','brown@gmail.com','qwerty'),('621c85c8-dcb1-11ee-87fa-815c4c682378','Dr. Davis','Optometrist','davis@gmail.com','qwerty');
/*!40000 ALTER TABLE `Doctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Hospital`
--

DROP TABLE IF EXISTS `Hospital`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Hospital` (
  `hospitalID` char(36) NOT NULL,
  `name` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  PRIMARY KEY (`hospitalID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Hospital`
--

LOCK TABLES `Hospital` WRITE;
/*!40000 ALTER TABLE `Hospital` DISABLE KEYS */;
INSERT INTO `Hospital` VALUES ('9460a802-dcb1-11ee-87fa-815c4c682378','Mayo Clinic','200 1st St SW, Rochester, MN 55905, USA'),('9460ac9e-dcb1-11ee-87fa-815c4c682378','Cleveland Clinic','9500 Euclid Ave, Cleveland, OH 44195, USA'),('9460add4-dcb1-11ee-87fa-815c4c682378','Johns Hopkins Hospital','1800 Orleans St, Baltimore, MD 21287, USA'),('9460ae74-dcb1-11ee-87fa-815c4c682378','Massachusetts General Hospital','55 Fruit St, Boston, MA 02114, USA'),('9460af1e-dcb1-11ee-87fa-815c4c682378','Mayo Clinic Arizona','5777 E Mayo Blvd, Phoenix, AZ 85054, USA'),('9460afb4-dcb1-11ee-87fa-815c4c682378','Stanford Hospital','300 Pasteur Dr, Stanford, CA 94305, USA'),('9460b04a-dcb1-11ee-87fa-815c4c682378','NewYork-Presbyterian Hospital','525 E 68th St, New York, NY 10065, USA'),('9460b0e0-dcb1-11ee-87fa-815c4c682378','Brigham and Womens Hospital','75 Francis St, Boston, MA 02115, USA'),('9460b176-dcb1-11ee-87fa-815c4c682378','Mayo Clinic Florida','4500 San Pablo Rd S, Jacksonville, FL 32224, USA'),('9460b20c-dcb1-11ee-87fa-815c4c682378','M.D. Anderson Cancer Center','1515 Holcombe Blvd, Houston, TX 77030, USA');
/*!40000 ALTER TABLE `Hospital` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `before_insert_hospital` BEFORE INSERT ON `hospital` FOR EACH ROW SET NEW.hospitalID = UUID() */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `medical_drugs`
--

DROP TABLE IF EXISTS `medical_drugs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medical_drugs` (
  `drug_id` int NOT NULL AUTO_INCREMENT,
  `drug_name` varchar(255) NOT NULL,
  `manufacturer` varchar(255) NOT NULL,
  `dosage_form` varchar(100) DEFAULT NULL,
  `strength` varchar(50) DEFAULT NULL,
  `indication` text,
  `contraindications` text,
  `side_effects` text,
  `dosage_instructions` text,
  `storage_instructions` text,
  `precautions` text,
  `warnings` text,
  PRIMARY KEY (`drug_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medical_drugs`
--

LOCK TABLES `medical_drugs` WRITE;
/*!40000 ALTER TABLE `medical_drugs` DISABLE KEYS */;
INSERT INTO `medical_drugs` VALUES (1,'Xylozen','PharmaCorp','Tablet','500mg','Pain relief','Liver disease, pregnancy','Nausea, drowsiness','Take 1 tablet every 6 hours as needed','Store at room temperature','May cause dizziness','Do not operate heavy machinery'),(2,'Respiromax','BioMed','Inhaler','100mcg/dose','Asthma','Hyperthyroidism','Tremors, headache','Inhale 1 dose every 4-6 hours','Store at room temperature','Monitor heart rate','Seek medical help if breathing difficulties persist'),(3,'Cholecor','Cardiopharma','Capsule','20mg','High cholesterol','Liver disease, pregnancy','Muscle pain, constipation','Take 1 capsule daily with food','Store at room temperature','Regular liver function tests','Report any unexplained muscle pain'),(4,'Zythromycin','AntiBio','Suspension','200mg/5mL','Bacterial infections','Liver disease, myasthenia gravis','Diarrhea, rash','Take 5mL every 12 hours for 10 days','Refrigerate after reconstitution','Avoid sun exposure','Discontinue if severe diarrhea occurs'),(5,'Somnalux','DreamPharma','Tablet','10mg','Insomnia','Sleep apnea, depression','Dizziness, dry mouth','Take 1 tablet 30 minutes before bedtime','Store at room temperature','May cause dependence','Do not combine with alcohol'),(6,'Neurozyn','BrainTech','Capsule','500mg','Alzheimer\'s disease','Severe kidney disease','Nausea, headache','Take 1 capsule twice daily','Store at room temperature','Regular kidney function tests','Report any changes in behavior'),(7,'Arthroflex','RheuMed','Injection','100mg/mL','Rheumatoid arthritis','Active infections, pregnancy','Injection site pain, fever','Inject 1mL subcutaneously every 2 weeks','Refrigerate','Monitor for signs of infection','Avoid live vaccines'),(8,'Glucorest','DiabeCare','Tablet','500mg','Type 2 diabetes','Kidney disease, ketoacidosis','Weight gain, edema','Take 1 tablet twice daily with meals','Store at room temperature','Regular kidney function tests','Monitor blood glucose levels'),(9,'Cardiopril','HeartSafe','Tablet','10mg','Hypertension','Pregnancy, angioedema','Cough, dizziness','Take 1 tablet daily','Store at room temperature','Monitor blood pressure','Report swelling of face/tongue'),(10,'Immunoboost','VitaLife','Capsule','500mg','Immune support','None known','Nausea, diarrhea','Take 1 capsule daily with food','Store at room temperature','Consult doctor if symptoms persist','Discontinue if allergic reaction occurs');
/*!40000 ALTER TABLE `medical_drugs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicaldrug_appointmet`
--

DROP TABLE IF EXISTS `medicaldrug_appointmet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medicaldrug_appointmet` (
  `appointmentID` char(36) NOT NULL,
  `drug_id` int NOT NULL,
  PRIMARY KEY (`appointmentID`,`drug_id`),
  KEY `drug_id` (`drug_id`),
  CONSTRAINT `medicaldrug_appointmet_ibfk_1` FOREIGN KEY (`appointmentID`) REFERENCES `appointment` (`appointmentID`) ON DELETE CASCADE,
  CONSTRAINT `medicaldrug_appointmet_ibfk_2` FOREIGN KEY (`drug_id`) REFERENCES `medical_drugs` (`drug_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicaldrug_appointmet`
--

LOCK TABLES `medicaldrug_appointmet` WRITE;
/*!40000 ALTER TABLE `medicaldrug_appointmet` DISABLE KEYS */;
/*!40000 ALTER TABLE `medicaldrug_appointmet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Patient`
--

DROP TABLE IF EXISTS `Patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Patient` (
  `patientID` char(36) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(100) NOT NULL,
  `name` varchar(255) NOT NULL,
  `gender` enum('Male','Female','Other') DEFAULT NULL,
  `blood_type` enum('A+','A-','B+','B-','O+','O-','AB+','AB-','O') DEFAULT NULL,
  `age` int DEFAULT NULL,
  `weight` decimal(5,2) DEFAULT NULL,
  `height` decimal(5,2) DEFAULT NULL,
  `phonenumber` varchar(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`patientID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Patient`
--

LOCK TABLES `Patient` WRITE;
/*!40000 ALTER TABLE `Patient` DISABLE KEYS */;
INSERT INTO `Patient` VALUES ('6d0c416c-dcb1-11ee-87fa-815c4c682378','john.doe@example.com','password123','John Doe','Male','O+',40,76.50,180.00,'+1234567890','123 Main St, Anytown USA'),('6d0c46da-dcb1-11ee-87fa-815c4c682378','jane.smith@example.com','qwerty','Jane Smith','Female','A+',28,60.25,165.75,'+9876543210','456 Oak Rd, Somecity CA'),('6d0c4842-dcb1-11ee-87fa-815c4c682378','bob.johnson@example.com','letmein','Bob Johnson','Male','B-',42,85.00,175.50,'+5551234567','789 Pine Ave, Otherton TX'),('6d0c4914-dcb1-11ee-87fa-815c4c682378','sarah.williams@example.com','password','Sarah Williams','Female','AB+',31,58.75,162.00,'+8675309021','246 Maple Ln, Anothertown NY'),('6d0c49dc-dcb1-11ee-87fa-815c4c682378','michael.brown@example.com','qwerty123','Michael Brown','Male','O-',49,92.30,188.50,'+1112223333','369 Elm St, Newcity IL'),('6d0c4a9a-dcb1-11ee-87fa-815c4c682378','emily.jones@example.com','letmein123','Emily Jones','Female','A-',24,53.00,160.25,'+4445556666','159 Cedar Rd, Oldtown MA'),('6d0c4b4e-dcb1-11ee-87fa-815c4c682378','david.miller@example.com','password456','David Miller','Male','B+',38,78.20,172.75,'+7778889999','753 Walnut Ave, Anotherville CA'),('6d0c4c0c-dcb1-11ee-87fa-815c4c682378','jennifer.davis@example.com','qwerty456','Jennifer Davis','Female','AB-',30,65.80,168.00,'+1233456789','951 Oak Blvd, Somewhereville TX'),('6d0c4cd4-dcb1-11ee-87fa-815c4c682378','christopher.wilson@example.com','letmein456','Christopher Wilson','Male','O+',45,80.75,182.50,'+9635587412','147 Pine St, Newtown NJ'),('6d0c4d88-dcb1-11ee-87fa-815c4c682378','amanda.moore@example.com','password789','Amanda Moore','Female','A+',27,57.50,163.25,'+8524697531','753 Maple Rd, Anotherplace CA'),('6d0c4e3c-dcb1-11ee-87fa-815c4c682378','matthew.taylor@example.com','qwerty789','Matthew Taylor','Male','B-',33,72.00,175.00,'+1236547890','369 Elm Ave, Somewherecity TX'),('6d0c4ee6-dcb1-11ee-87fa-815c4c682378','jessica.anderson@example.com','letmein789','Jessica Anderson','Female','AB+',40,68.40,170.50,'+7418529630','159 Cedar Ln, Anothertown NY'),('6d0c4f9a-dcb1-11ee-87fa-815c4c682378','joshua.thomas@example.com','password012','Joshua Thomas','Male','O-',29,65.25,168.75,'+3691265748','951 Oak St, Newplace IL');
/*!40000 ALTER TABLE `Patient` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `before_insert_patient` BEFORE INSERT ON `patient` FOR EACH ROW SET NEW.patientID = UUID() */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `PatientAllergy`
--

DROP TABLE IF EXISTS `PatientAllergy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PatientAllergy` (
  `patientID` char(36) NOT NULL,
  `allergyID` int NOT NULL,
  `duration` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`patientID`,`allergyID`),
  KEY `allergyID` (`allergyID`),
  CONSTRAINT `patientallergy_ibfk_1` FOREIGN KEY (`patientID`) REFERENCES `Patient` (`patientID`) ON DELETE CASCADE,
  CONSTRAINT `patientallergy_ibfk_2` FOREIGN KEY (`allergyID`) REFERENCES `Allergy` (`allergyID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PatientAllergy`
--

LOCK TABLES `PatientAllergy` WRITE;
/*!40000 ALTER TABLE `PatientAllergy` DISABLE KEYS */;
INSERT INTO `PatientAllergy` VALUES ('6d0c416c-dcb1-11ee-87fa-815c4c682378',14,'2 years'),('6d0c416c-dcb1-11ee-87fa-815c4c682378',15,'2 years'),('6d0c46da-dcb1-11ee-87fa-815c4c682378',13,'2 years'),('6d0c46da-dcb1-11ee-87fa-815c4c682378',14,'2 years');
/*!40000 ALTER TABLE `PatientAllergy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PatientSurgery`
--

DROP TABLE IF EXISTS `PatientSurgery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PatientSurgery` (
  `patientID` char(36) NOT NULL,
  `SurgeryID` int NOT NULL,
  `duration` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`patientID`,`SurgeryID`),
  KEY `SurgeryID` (`SurgeryID`),
  CONSTRAINT `patientsurgery_ibfk_1` FOREIGN KEY (`patientID`) REFERENCES `Patient` (`patientID`) ON DELETE CASCADE,
  CONSTRAINT `patientsurgery_ibfk_2` FOREIGN KEY (`SurgeryID`) REFERENCES `Surgery` (`SurgeryID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PatientSurgery`
--

LOCK TABLES `PatientSurgery` WRITE;
/*!40000 ALTER TABLE `PatientSurgery` DISABLE KEYS */;
INSERT INTO `PatientSurgery` VALUES ('6d0c416c-dcb1-11ee-87fa-815c4c682378',1,'2 years'),('6d0c46da-dcb1-11ee-87fa-815c4c682378',1,'10 days'),('6d0c46da-dcb1-11ee-87fa-815c4c682378',2,'2 years');
/*!40000 ALTER TABLE `PatientSurgery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Surgery`
--

DROP TABLE IF EXISTS `Surgery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Surgery` (
  `SurgeryID` int NOT NULL AUTO_INCREMENT,
  `Surgery_type` varchar(255) NOT NULL,
  PRIMARY KEY (`SurgeryID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Surgery`
--

LOCK TABLES `Surgery` WRITE;
/*!40000 ALTER TABLE `Surgery` DISABLE KEYS */;
INSERT INTO `Surgery` VALUES (1,'Tooth Removal'),(2,'Appendectomy'),(3,'Tonsillectomy'),(4,'Caesarean Section'),(5,'Hip Replacement'),(6,'Cataract Surgery');
/*!40000 ALTER TABLE `Surgery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Works`
--

DROP TABLE IF EXISTS `Works`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Works` (
  `doctorID` char(36) NOT NULL,
  `hospitalID` char(36) NOT NULL,
  PRIMARY KEY (`doctorID`,`hospitalID`),
  KEY `hospitalID` (`hospitalID`),
  CONSTRAINT `works_ibfk_1` FOREIGN KEY (`doctorID`) REFERENCES `doctor` (`doctorID`) ON DELETE CASCADE,
  CONSTRAINT `works_ibfk_2` FOREIGN KEY (`hospitalID`) REFERENCES `hospital` (`hospitalID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Works`
--

LOCK TABLES `Works` WRITE;
/*!40000 ALTER TABLE `Works` DISABLE KEYS */;
INSERT INTO `Works` VALUES ('621c5de6-dcb1-11ee-87fa-815c4c682378','9460a802-dcb1-11ee-87fa-815c4c682378'),('621c5de6-dcb1-11ee-87fa-815c4c682378','9460ac9e-dcb1-11ee-87fa-815c4c682378'),('621c8398-dcb1-11ee-87fa-815c4c682378','9460add4-dcb1-11ee-87fa-815c4c682378'),('621c8398-dcb1-11ee-87fa-815c4c682378','9460ae74-dcb1-11ee-87fa-815c4c682378'),('621c851e-dcb1-11ee-87fa-815c4c682378','9460af1e-dcb1-11ee-87fa-815c4c682378'),('621c851e-dcb1-11ee-87fa-815c4c682378','9460afb4-dcb1-11ee-87fa-815c4c682378'),('621c85c8-dcb1-11ee-87fa-815c4c682378','9460afb4-dcb1-11ee-87fa-815c4c682378'),('621c85c8-dcb1-11ee-87fa-815c4c682378','9460b04a-dcb1-11ee-87fa-815c4c682378'),('621c85c8-dcb1-11ee-87fa-815c4c682378','9460b0e0-dcb1-11ee-87fa-815c4c682378'),('621c8398-dcb1-11ee-87fa-815c4c682378','9460b176-dcb1-11ee-87fa-815c4c682378'),('621c85c8-dcb1-11ee-87fa-815c4c682378','9460b176-dcb1-11ee-87fa-815c4c682378'),('621c85c8-dcb1-11ee-87fa-815c4c682378','9460b20c-dcb1-11ee-87fa-815c4c682378');
/*!40000 ALTER TABLE `Works` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `log_after_update_works` AFTER UPDATE ON `works` FOR EACH ROW BEGIN
  INSERT INTO TransactionLog (transactionType, tableName, recordID, oldValue, newValue, timestamp)
  VALUES ('UPDATE', 'Works', CONCAT(OLD.doctorID, ',', OLD.hospitalID), NULL, NULL, NOW());
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Dumping routines for database 'medic_history'
--
/*!50003 DROP PROCEDURE IF EXISTS `create_medical_receipt` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `create_medical_receipt`(
    IN appointmentID_param CHAR(36),
    IN patientID_param CHAR(36),
    IN nameField_ VARCHAR(255),
    IN ageField_ INT,
    IN addField_ VARCHAR(255),
    IN genderField_ ENUM('Male','Female','Other'),
    IN heightField_ DECIMAL(5,2),
    IN weightField_ DECIMAL(5,2),
    IN datepresField_ TIMESTAMP,
    IN diagnosisField_ TEXT,
    IN appointment_pdf TEXT
)
BEGIN
    -- Update Patient table
    UPDATE Patient
    SET name = nameField_,
        age = ageField_,
        address = addField_,
        gender = genderField_,
        height = heightField_,
        weight = weightField_
    WHERE patientID = patientID_param;

    -- Update appointment table
    UPDATE appointment_details
    SET pdf_url = appointment_pdf,
        appointment_date = datepresField_,
        diagnosis = diagnosisField_,
        is_completed = true
    WHERE appointmentID = appointmentID_param;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `GetRecentPatientAppointments` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetRecentPatientAppointments`(IN patient_id CHAR(36), IN num_appointments INT)
BEGIN
    SELECT
        a.appointmentID,
        a.appointmentName,
        a.appointment_date,
        a.diagnosis,
        a.prescription,
        a.paymentreceived,
        a.paymentdue,
        a.pdf_url,
        a.is_confirmed,
        d.name
    FROM
        appointment_details a
        INNER JOIN appointment ca ON a.appointmentID = ca.appointmentID
        INNER JOIN doctor d ON ca.doctorID = d.doctorID
    WHERE
        ca.patientID = patient_id and a.is_confirmed = 0
    ORDER BY
        a.appointment_date DESC
    LIMIT
        num_appointments;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-17 22:00:17
