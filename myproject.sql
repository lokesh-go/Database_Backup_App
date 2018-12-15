CREATE TABLE `found` (
  `RegNo` varchar(10) NOT NULL,
  `Name` varchar(45) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `Time` varchar(10) DEFAULT NULL,
  `Place` varchar(150) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Contact` varchar(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci



CREATE TABLE `lost` (
  `RegNo` varchar(10) DEFAULT NULL,
  `Name` varchar(100) NOT NULL,
  `Date` date NOT NULL,
  `Time` varchar(10) NOT NULL,
  `Place` varchar(100) NOT NULL,
  `Description` varchar(255) NOT NULL,
  `Contact` varchar(10) NOT NULL,
  PRIMARY KEY (`Contact`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

 insert into lost (RegNo, Name, Date, Time, Place, Description, Contact) values ('2017CA25', 'Localhost', '2018-12-12', '12:12', 'MP Hall', 'white car', '1234567654');


