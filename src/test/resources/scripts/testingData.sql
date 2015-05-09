-- MySQL dump 10.13  Distrib 5.6.19, for osx10.7 (i386)
--
-- Host: 127.0.0.1    Database: andatu
-- ------------------------------------------------------
-- Server version	5.5.38

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table 'authorities'
--

LOCK TABLES 'authorities' WRITE;
/*!40000 ALTER TABLE 'authorities' DISABLE KEYS */;
INSERT INTO 'authorities' VALUES ('ernst.lustig@gmail.com','user'),('friedensreich.kriege@gmail.com','user'),('gotthelf.freibier@gmail.com','user'),('john.neumann@gmail.com','user'),('josef.mitterhofer@gmail.com','user'),('konrad.schreckenhammer@gmail.com','user'),('marta.knusperhoeuschen@gmail.com','user');
/*!40000 ALTER TABLE 'authorities' ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table 'categories'
--

LOCK TABLES 'categories' WRITE;
/*!40000 ALTER TABLE 'categories' DISABLE KEYS */;
INSERT INTO 'categories' VALUES (1,'Antiques'),(2,'Art'),(3,'Baby'),(4,'Books, Comics &amp; Magazines'),(5,'Business, Office &amp; Industrial'),(6,'Cameras &amp; Photography'),(7,'Cars, Motorcycles &amp; Vehicles'),(8,'Clothes, Shoes &amp; Accessories'),(9,'Coins'),(10,'Collectables'),(11,'Computers/Tablets &amp; Networking'),(12,'Crafts'),(13,'Dolls &amp; Bears'),(14,'DVDs, Films &amp; TV'),(15,'Events Tickets'),(16,'Garden &amp; Patio'),(17,'Health &amp; Beauty'),(18,'Holidays &amp; Travel'),(19,'Home, Furniture &amp; DIY'),(20,'Jewellry &amp; Watches'),(21,'Mobile Phones &amp; Communication'),(22,'Music'),(23,'Musical Instruments'),(24,'Pet Supplies'),(25,'Pottery, Porcelain &amp; Glass'),(26,'Property'),(27,'Sound &amp; Vision'),(28,'Sporting Goods'),(29,'Sports Memorabilia'),(30,'Stamps'),(31,'Toys &amp; Games'),(32,'Vehicle Parts &amp; Accessories'),(33,'Video Games &amp; Consoles'),(34,'Wholesale &amp; Job Lots'),(35,'Everything Else');
/*!40000 ALTER TABLE 'categories' ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table 'city'
--

LOCK TABLES 'city' WRITE;
/*!40000 ALTER TABLE 'city' DISABLE KEYS */;
INSERT INTO 'city' VALUES (1,'Wien',15),(2,'Graz',15),(3,'Linz',15),(4,'Salzburg',15),(5,'Innsbruck',15),(6,'Klagenfurt',15);
/*!40000 ALTER TABLE 'city' ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table 'comment'
--

LOCK TABLES 'comment' WRITE;
/*!40000 ALTER TABLE 'comment' DISABLE KEYS */;
/*!40000 ALTER TABLE 'comment' ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table 'customer'
--

LOCK TABLES 'customer' WRITE;
/*!40000 ALTER TABLE 'customer' DISABLE KEYS */;
INSERT INTO 'customer' VALUES (3,'John','Neumann','Mr.','2015-04-27 19:58:57','john.neumann@gmail.com','1def1e54d17382102fb4743d3cf8857926e08f4a9d9817523f5c5f5d46b4b6ad5ee9fb72e5648c0d','en','',0,0,'0976784721',0,''),(4,'Josef','Mitterhofer','Mr.','2015-04-27 22:59:11','josef.mitterhofer@gmail.com','27e39214609bdf4cae6f7438432189fca5a935a35803557ac94e689c654a8e2a6be2eb5c3c900e97','en','',0,0,'0349339293',0,''),(5,'Konrad','Shreckenhammer','Mr.','2015-04-27 23:01:07','konrad.schreckenhammer@gmail.com','e21b3d5b113238403c83bfb4ac5c6afcef27af61eae15cfd6325a7a1755d7d73393733367bc4b1cb','en','',0,0,'0698980938',0,''),(6,'Ernst','Lustig','Dr.','2015-04-27 23:01:41','ernst.lustig@gmail.com','8b70b8284fbdaf7cd0d9ee9abe12a61cdaa0fa4407776cb9a27146153894ca2cebdcba0b0b2dfd97','de','',0,0,'0987384928',0,''),(7,'Friedensreich','Krieger','Mr.','2015-04-27 23:03:32','friedensreich.kriege@gmail.com','7a37c935978fdb0dc714dacc5b66d16a3755e7653bef1b913fbbf792f235056e191bbb19d4dcb733','de','',0,0,'0893849275',0,''),(8,'Marta','Knusperhoeuschen','Mrs.','2015-04-27 23:05:59','marta.knusperhoeuschen@gmail.com','1b5cb22feb31d7e083db9a7132aa3e675bb3a7527d9d548e067f6f2200e1eca87330c2379595c536','de','',0,0,'0788938274',0,''),(9,'Gotthelf','Freibier','Dr.','2015-04-27 23:06:49','gotthelf.freibier@gmail.com','8e9a4f27d2998adb589d544c0c6b79cc7f0422d710f232be88a3d1c3b75664df421f3ff335cee589','de','',0,0,'0783947789',0,'');
/*!40000 ALTER TABLE 'customer' ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table 'customer_address'
--

LOCK TABLES 'customer_address' WRITE;
/*!40000 ALTER TABLE 'customer_address' DISABLE KEYS */;
INSERT INTO 'customer_address' VALUES (1,'Taubstummengasse 8',NULL,1040,3,1),(2,'Taubstummengasse 8',NULL,8010,4,2),(3,'Taubstummengasse 8',NULL,8010,5,2),(4,'Blaugasse 87',NULL,5027,6,4),(5,'Volksplatz 9',NULL,2030,7,3),(6,'Erdstraße 32',NULL,6020,8,5),(7,'Taubstummengasse 8',NULL,9020,8,6);
/*!40000 ALTER TABLE 'customer_address' ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table 'customer_bank_account'
--

LOCK TABLES 'customer_bank_account' WRITE;
/*!40000 ALTER TABLE 'customer_bank_account' DISABLE KEYS */;
INSERT INTO 'customer_bank_account' VALUES (1,'Austrian Bank','AL47 2121 1009 0000 0002 3569 8741','987654321',3),(2,'Austrian Bank','AL47 2121 1009 0000 0002 3569 8732','987654976',4),(3,'Bawag','AL47 2121 1009 0000 0002 3569 8721','987654909',5);
/*!40000 ALTER TABLE 'customer_bank_account' ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table 'delivery_destination'
--

LOCK TABLES 'delivery_destination' WRITE;
/*!40000 ALTER TABLE 'delivery_destination' DISABLE KEYS */;
INSERT INTO 'delivery_destination' VALUES (1,'Österreich'),(2,'Europäische Union'),(3,'Weltweit');
/*!40000 ALTER TABLE 'delivery_destination' ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table 'delivery_method'
--

LOCK TABLES 'delivery_method' WRITE;
/*!40000 ALTER TABLE 'delivery_method' DISABLE KEYS */;
INSERT INTO 'delivery_method' VALUES (1,'Standardversand (unversichert)',1),(2,'Versicherteer Versand',1),(3,'Einschreiben (Versand inkl. Einschreibegebühr)',1),(4,'Nachname (Versand inkl. Nachnahmegebühr)',1),(5,'Express- oder Kurierversand',1),(6,'Versicherter Express- oder Kurierversand',1),(7,'Sonder-/Speditionsversand (z.B. Möbel, KFZ)',1),(8,'Sonstige (Siehe Artikelbeschreibung)',1),(9,'Sparversand aus dem Ausland',1),(10,'Standardversand aus dem Ausland',1),(11,'Expressversand aus dem Ausland',1),(12,'Versand mit Nachverfolgung aus dem Ausland',1),(13,'Ware muss abgeholt werden',1),(14,'Unversicherter Versand',2),(15,'Versicherter Versand',2),(16,'Unversicherter Express - Versand',2),(17,'Versicherter Express - Versand',2),(18,'Sonstiger Versand (Siehe Artikelbeschreibung)',2),(19,'Unversicherter Versand',3),(20,'Versicherter Versand',3),(21,'Unversicherter Express - Versand',3),(22,'Versicherter Express - Versand',3),(23,'Sonstiger Versand (Siehe Artikelbeschreibung)',3);
/*!40000 ALTER TABLE 'delivery_method' ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table 'donation'
--

LOCK TABLES 'donation' WRITE;
/*!40000 ALTER TABLE 'donation' DISABLE KEYS */;
INSERT INTO 'donation' VALUES (1,50,1,2),(2,50,1,1),(3,50,1,2),(4,50,1,1),(5,50,1,2),(6,50,1,1),(7,50,1,2);
/*!40000 ALTER TABLE 'donation' ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table 'ngo'
--

LOCK TABLES 'ngo' WRITE;
/*!40000 ALTER TABLE 'ngo' DISABLE KEYS */;
INSERT INTO 'ngo' VALUES (1,'123456','donations@greepeace.com','','en','Greepeace ist eine 1971 von Friedensaktivisten in Vancouver, Kanada, gegründete transnationale politische Non-Profit-Organisation, die den Umweltschutz zum Thema hat. Sie wurde vor allem durch Kampagnen gegen Kernwaffentests und Aktionen gegen den Walfang bekannt. Später konzentrierte sich die Organisation darüber hinaus auf weitere Themen wie Überfischung, die globale Erwärmung, die Zerstörung von Urwäldern und die Gentechnik.',NULL,NULL,1),(2,'234987','donations@redcross.at','','de','Als nationale Rotkreuz- und Rothalbmond-Gesellschaften werden Organisationen bezeichnet, die in ihrem jeweiligen Heimatland als freiwillige Hilfsgesellschaften Aufgaben übernehmen, die sich aus den Genfer Konventionen und den Statuten der Internationalen Rotkreuz- und Rothalbmond-Bewegung ergeben. Zu diesen Aufgaben gehören vor allem die humanitäre Hilfeleistung im Fall von Kriegen und bewaffneten Konflikten sowie anderen Notfällen von großem Ausmaß wie Naturkatastrophen, die Organisation von Such- und Auskunftsdiensten für diese Situationen sowie die Verbreitung von Kenntnissen des humanitären Völkerrechts. In vielen Ländern mit mehrheitlich islamischer Bevölkerung verwenden die entsprechenden Organisationen anstelle des Symbols des Roten Kreuzes den Roten Halbmond und werden dementsprechend als nationale Rothalbmond-Gesellschaften bezeichnet. Wenn eindeutig erkennbar ist, dass der Kontext der Beschreibung einen Bezug zur Rotkreuz- und Rothalbmond-Bewegung hat, wird oft nur der Begriff nationale Gesellschaft verwendet. Auch die israelische Organisation Magen David Adom, die seit ihrer Gründung einen roten Davidstern als Emblem nutzt, wird in der Regel ohne weiteren Zusatz als nationale Gesellschaft Israels im Sinne des humanitären Völkerrechts und der Statuten der Rotkreuz- und Rothalbmond-Bewegung bezeichnet.',NULL,NULL,1),(3,'234324','donations@savethechildren.at','','de','Save the Children ist nach eigenen Angaben die größte unabhängige Kinderrechtsorganisation der Welt. Die Nichtregierungsorganisation ist konfessionell und politisch ungebunden und setzt sich für die Rechte und den Schutz von Kindern ein.',NULL,NULL,0),(4,'234323','donations@unicef.com','','en','Das Kinderhilfswerk der Vereinten Nationen ist eines der entwicklungspolitischen Organe der Vereinten Nationen. Es wurde am 11. Dezember 1946 gegründet, zunächst um Kindern in Europa nach dem Zweiten Weltkrieg zu helfen.',NULL,NULL,1),(5,'234324','donations@caritas.at','','de','Caritas Internationalis ist die Vereinigung der 165 nationalen Caritasverbände. Diese Wohlfahrtsorganisationen der römisch-katholischen Kirche sind in mehr als 200 Ländern in der Nothilfe, Entwicklungshilfe und den Sozialdiensten tätig. Mitglieder sind unter anderem der deutsche Caritasverband, die Caritas Österreich und die Caritas Schweiz.',NULL,NULL,1),(6,'092834','donations@msf.com','','en','Ärzte ohne Grenzen ist die deutsche Übersetzung des Namens der 1971 gegründeten größten internationalen Organisation für medizinische Nothilfe Médecins Sans Frontières.',NULL,NULL,0);
/*!40000 ALTER TABLE 'ngo' ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table 'ngo_address'
--

LOCK TABLES 'ngo_address' WRITE;
/*!40000 ALTER TABLE 'ngo_address' DISABLE KEYS */;
/*!40000 ALTER TABLE 'ngo_address' ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table 'ngo_bank_account'
--

LOCK TABLES 'ngo_bank_account' WRITE;
/*!40000 ALTER TABLE 'ngo_bank_account' DISABLE KEYS */;
/*!40000 ALTER TABLE 'ngo_bank_account' ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table 'ngo_pictures'
--

LOCK TABLES 'ngo_pictures' WRITE;
/*!40000 ALTER TABLE 'ngo_pictures' DISABLE KEYS */;
/*!40000 ALTER TABLE 'ngo_pictures' ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table 'product'
--

LOCK TABLES 'product' WRITE;
/*!40000 ALTER TABLE 'product' DISABLE KEYS */;
INSERT INTO 'product' VALUES (1,'Rubik\'s cube','Nice rubik\'s cube','rubik\'s, toy',5.00,0.00,1,'new',3,31),(2,'Euros collection germany','old euros collection from germany','coins, euros, germany',100.00,0.00,1,'good',4,9),(3,'Twin bed','twin bed almost never used','twin bed',45.00,0.00,1,'good',4,19);
/*!40000 ALTER TABLE 'product' ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table 'product_delivery_method'
--

LOCK TABLES 'product_delivery_method' WRITE;
/*!40000 ALTER TABLE 'product_delivery_method' DISABLE KEYS */;
INSERT INTO 'product_delivery_method' VALUES (1,3.00,1,1),(2,3.00,1,2),(3,3.00,1,3);
/*!40000 ALTER TABLE 'product_delivery_method' ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table 'product_pictures'
--

LOCK TABLES 'product_pictures' WRITE;
/*!40000 ALTER TABLE 'product_pictures' DISABLE KEYS */;
/*!40000 ALTER TABLE 'product_pictures' ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table 'state'
--

LOCK TABLES 'state' WRITE;
/*!40000 ALTER TABLE 'state' DISABLE KEYS */;
INSERT INTO 'state' VALUES (1,'Afghanistan'),(2,'Aland Islands'),(3,'Albania'),(4,'Algeria'),(5,'American Samoa'),(6,'Andorra'),(7,'Angola'),(8,'Anguilla'),(9,'Antarctica'),(10,'Antigua And Barbuda'),(11,'Argentina'),(12,'Armenia'),(13,'Aruba'),(14,'Australia'),(15,'Austria'),(16,'Azerbaijan'),(17,'Bahamas'),(18,'Bahrain'),(19,'Bangladesh'),(20,'Barbados'),(21,'Belarus'),(22,'Belgium'),(23,'Belize'),(24,'Benin'),(25,'Bermuda'),(26,'Bhutan'),(27,'Bolivia, Plurinational State Of'),(28,'Bonaire, Sint Eustatius And Saba'),(29,'Bosnia And Herzegovina'),(30,'Botswana'),(31,'Bouvet Island'),(32,'Brazil'),(33,'British Indian Ocean Territory'),(34,'Brunei Darussalam'),(35,'Bulgaria'),(36,'Burkina Faso'),(37,'Burundi'),(38,'Cambodia'),(39,'Cameroon'),(40,'Canada'),(41,'Cape Verde'),(42,'Cayman Islands'),(43,'Central African Republic'),(44,'Chad'),(45,'Chile'),(46,'China'),(47,'Christmas Island'),(48,'Cocos (Keeling) Islands'),(49,'Colombia'),(50,'Comoros'),(51,'Congo'),(52,'Congo, The Democratic Republic Of The'),(53,'Cook Islands'),(54,'Costa Rica'),(55,'CÃëte D\'Ivoire'),(56,'Croatia'),(57,'Cuba'),(58,'CuraÃçao'),(59,'Cyprus'),(60,'Czech Republic'),(61,'Denmark'),(62,'Djibouti'),(63,'Dominica'),(64,'Dominican Republic'),(65,'Ecuador'),(66,'Egypt'),(67,'El Salvador'),(68,'Equatorial Guinea'),(69,'Eritrea'),(70,'Estonia'),(71,'Ethiopia'),(72,'Falkland Islands (Malvinas)'),(73,'Faroe Islands'),(74,'Fiji'),(75,'Finland'),(76,'France'),(77,'French Guiana'),(78,'French Polynesia'),(79,'French Southern Territories'),(80,'Gabon'),(81,'Gambia'),(82,'Georgia'),(83,'Germany'),(84,'Ghana'),(85,'Gibraltar'),(86,'Greece'),(87,'Greenland'),(88,'Grenada'),(89,'Guadeloupe'),(90,'Guam'),(91,'Guatemala'),(92,'Guernsey'),(93,'Guinea'),(94,'Guinea-Bissau'),(95,'Guyana'),(96,'Haiti'),(97,'Heard Island And Mcdonald Islands'),(98,'Holy See (Vatican City State)'),(99,'Honduras'),(100,'Hong Kong'),(101,'Hungary'),(102,'Iceland'),(103,'India'),(104,'Indonesia'),(105,'Iran, Islamic Republic Of'),(106,'Iraq'),(107,'Ireland'),(108,'Isle Of Man'),(109,'Israel'),(110,'Italy'),(111,'Jamaica'),(112,'Japan'),(113,'Jersey'),(114,'Jordan'),(115,'Kazakhstan'),(116,'Kenya'),(117,'Kiribati'),(118,'Korea, Democratic People\'S Republic Of'),(119,'Korea, Republic Of'),(120,'Kuwait'),(121,'Kyrgyzstan'),(122,'Lao People\'S Democratic Republic'),(123,'Latvia'),(124,'Lebanon'),(125,'Lesotho'),(126,'Liberia'),(127,'Libya'),(128,'Liechtenstein'),(129,'Lithuania'),(130,'Luxembourg'),(131,'Macao'),(132,'Macedonia, The Former Yugoslav Republic Of'),(133,'Madagascar'),(134,'Malawi'),(135,'Malaysia'),(136,'Maldives'),(137,'Mali'),(138,'Malta'),(139,'Marshall Islands'),(140,'Martinique'),(141,'Mauritania'),(142,'Mauritius'),(143,'Mayotte'),(144,'Mexico'),(145,'Micronesia, Federated States Of'),(146,'Moldova, Republic Of'),(147,'Monaco'),(148,'Mongolia'),(149,'Montenegro'),(150,'Montserrat'),(151,'Morocco'),(152,'Mozambique'),(153,'Myanmar'),(154,'Namibia'),(155,'Nauru'),(156,'Nepal'),(157,'Netherlands'),(158,'New Caledonia'),(159,'New Zealand'),(160,'Nicaragua'),(161,'Niger'),(162,'Nigeria'),(163,'Niue'),(164,'Norfolk Island'),(165,'Northern Mariana Islands'),(166,'Norway'),(167,'Oman'),(168,'Pakistan'),(169,'Palau'),(170,'Palestine, State Of'),(171,'Panama'),(172,'Papua New Guinea'),(173,'Paraguay'),(174,'Peru'),(175,'Philippines'),(176,'Pitcairn'),(177,'Poland'),(178,'Portugal'),(179,'Puerto Rico'),(180,'Qatar'),(181,'Reunion'),(182,'Romania'),(183,'Russian Federation'),(184,'Rwanda'),(185,'Saint BarthÃålemy'),(186,'Saint Helena, Ascension And Tristan Da Cunha'),(187,'Saint Kitts And Nevis'),(188,'Saint Lucia'),(189,'Saint Martin (French Part)'),(190,'Saint Pierre And Miquelon'),(191,'Saint Vincent And The Grenadines'),(192,'Samoa'),(193,'San Marino'),(194,'Sao Tome And Principe'),(195,'Saudi Arabia'),(196,'Senegal'),(197,'Serbia'),(198,'Seychelles'),(199,'Sierra Leone'),(200,'Singapore'),(201,'Sint Maarten (Dutch Part)'),(202,'Slovakia'),(203,'Slovenia'),(204,'Solomon Islands'),(205,'Somalia'),(206,'South Africa'),(207,'South Georgia And The South Sandwich Islands'),(208,'South Sudan'),(209,'Spain'),(210,'Sri Lanka'),(211,'Sudan'),(212,'Suriname'),(213,'Svalbard And Jan Mayen'),(214,'Swaziland'),(215,'Sweden'),(216,'Switzerland'),(217,'Syrian Arab Republic'),(218,'Taiwan, Province Of China'),(219,'Tajikistan'),(220,'Tanzania, United Republic Of'),(221,'Thailand'),(222,'Timor-Leste'),(223,'Togo'),(224,'Tokelau'),(225,'Tonga'),(226,'Trinidad And Tobago'),(227,'Tunisia'),(228,'Turkey'),(229,'Turkmenistan'),(230,'Turks And Caicos Islands'),(231,'Tuvalu'),(232,'Uganda'),(233,'Ukraine'),(234,'United Arab Emirates'),(235,'United Kingdom'),(236,'United States'),(237,'United States Minor Outlying Islands'),(238,'Uruguay'),(239,'Uzbekistan'),(240,'Vanuatu'),(241,'Venezuela, Bolivarian Republic Of'),(242,'Vietnam'),(243,'Virgin Islands, British'),(244,'Virgin Islands, U.S.'),(245,'Wallis And Futuna'),(246,'Western Sahara'),(247,'Yemen'),(248,'Zambia'),(249,'Zimbabwe');
/*!40000 ALTER TABLE 'state' ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table 'student'
--

LOCK TABLES 'student' WRITE;
/*!40000 ALTER TABLE 'student' DISABLE KEYS */;
/*!40000 ALTER TABLE 'student' ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table 'transaction'
--

LOCK TABLES 'transaction' WRITE;
/*!40000 ALTER TABLE 'transaction' DISABLE KEYS */;
/*!40000 ALTER TABLE 'transaction' ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table 'wishlist'
--

LOCK TABLES 'wishlist' WRITE;
/*!40000 ALTER TABLE 'wishlist' DISABLE KEYS */;
/*!40000 ALTER TABLE 'wishlist' ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-05-07 18:10:02
