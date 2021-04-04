-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: mysql-25492-db.mysql-25492:25492
-- Generation Time: Apr 03, 2021 at 02:46 PM
-- Server version: 8.0.22
-- PHP Version: 7.2.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `agency`
--

-- --------------------------------------------------------

--
-- Table structure for table `agent`
--

CREATE TABLE `agent` (
  `agent_id` bigint NOT NULL,
  `agent_address` varchar(255) NOT NULL,
  `agent_date_of_birth` datetime NOT NULL,
  `agent_description` varchar(255) NOT NULL,
  `agent_email` varchar(255) NOT NULL,
  `agent_first_name` varchar(255) NOT NULL,
  `agent_id_number` varchar(255) NOT NULL,
  `agent_last_name` varchar(255) NOT NULL,
  `agent_password` varchar(255) NOT NULL,
  `agent_phone_number` varchar(255) NOT NULL,
  `agent_username` varchar(255) NOT NULL,
  `role_id` bigint NOT NULL,
  `location_id` bigint NOT NULL,
  `profile_picture_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `agent`
--

INSERT INTO `agent` (`agent_id`, `agent_address`, `agent_date_of_birth`, `agent_description`, `agent_email`, `agent_first_name`, `agent_id_number`, `agent_last_name`, `agent_password`, `agent_phone_number`, `agent_username`, `role_id`, `location_id`, `profile_picture_id`) VALUES
(51, 'Agents address 156', '1996-05-22 00:00:00', 'Our very first and best agent', 'agentmail@company.com', 'Agent', 'agentidnubmer123', 'Agentovich', 'agentpassword123', '+35623456781', 'agentagentovich', 1, 11, 14);

-- --------------------------------------------------------

--
-- Table structure for table `company_details`
--

CREATE TABLE `company_details` (
  `company_details_id` bigint NOT NULL,
  `company_address` varchar(255) NOT NULL,
  `company_email` varchar(255) NOT NULL,
  `company_logo_url` varchar(255) NOT NULL,
  `company_name` varchar(255) NOT NULL,
  `company_phone_number` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `company_details`
--

INSERT INTO `company_details` (`company_details_id`, `company_address`, `company_email`, `company_logo_url`, `company_name`, `company_phone_number`) VALUES
(10, 'Company address 156', 'companymail@company.com', 'https://seeklogo.com/images/S/spring-logo-9A2BC78AAF-seeklogo.com.png', 'Company from Malta', '+35623456781');

-- --------------------------------------------------------

--
-- Table structure for table `DATABASECHANGELOGLOCK`
--

CREATE TABLE `DATABASECHANGELOGLOCK` (
  `ID` int NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `DATABASECHANGELOGLOCK`
--

INSERT INTO `DATABASECHANGELOGLOCK` (`ID`, `LOCKED`, `LOCKGRANTED`, `LOCKEDBY`) VALUES
(1, b'0', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(61);

-- --------------------------------------------------------

--
-- Table structure for table `location`
--

CREATE TABLE `location` (
  `location_id` bigint NOT NULL,
  `location_description` varchar(255) DEFAULT NULL,
  `location_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `location`
--

INSERT INTO `location` (`location_id`, `location_description`, `location_name`) VALUES
(11, 'Capital of Malta', 'Valletta'),
(12, 'Some city', 'Birgu'),
(13, 'Also some city', 'Mdina');

-- --------------------------------------------------------

--
-- Table structure for table `owner`
--

CREATE TABLE `owner` (
  `owner_id` bigint NOT NULL,
  `owner_address` varchar(255) NOT NULL,
  `owner_description` varchar(255) DEFAULT NULL,
  `owner_email` varchar(255) NOT NULL,
  `owner_first_name` varchar(255) NOT NULL,
  `owner_last_name` varchar(255) NOT NULL,
  `owner_phone_number` varchar(255) NOT NULL,
  `agent_id` bigint DEFAULT NULL,
  `location_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `owner`
--

INSERT INTO `owner` (`owner_id`, `owner_address`, `owner_description`, `owner_email`, `owner_first_name`, `owner_last_name`, `owner_phone_number`, `agent_id`, `location_id`) VALUES
(1, 'First owners address 143', 'First owners some description.', 'firstownermail@google.com', 'Owner1', 'Ownerovich1', '+3564567812', 51, 12),
(52, 'First owners address 143', 'First owners some description.', 'firstownermail@google.com', 'Owner1', 'Ownerovich1', '+3564567812', 51, 12),
(53, 'Second owners address 143B', 'Second owners some description.', 'secondowner@google.com', 'Owner2', 'Ownerovich2', '+3564590652', 51, 13),
(54, 'Third owners address 143C', 'Third owners some description.', 'thirdowner@google.com', 'Owner3', 'Ownerovich3', '+3561231897', 51, 11);

-- --------------------------------------------------------

--
-- Table structure for table `profile_picture`
--

CREATE TABLE `profile_picture` (
  `profile_picture_id` bigint NOT NULL,
  `profile_picture_url` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `profile_picture`
--

INSERT INTO `profile_picture` (`profile_picture_id`, `profile_picture_url`) VALUES
(14, 'https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `property_attribute`
--

CREATE TABLE `property_attribute` (
  `property_attribute_id` bigint NOT NULL,
  `property_attribute_description` varchar(255) DEFAULT NULL,
  `property_attribute_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `property_attribute`
--

INSERT INTO `property_attribute` (`property_attribute_id`, `property_attribute_description`, `property_attribute_name`) VALUES
(15, '', 'A - Ultra Modern'),
(16, '', 'Bath Tub'),
(17, '', 'Energy Saving Bulbs'),
(18, '', 'Furnished'),
(19, '', 'Insulation'),
(20, '', 'Parking Space'),
(21, '', 'Seafront'),
(22, '', 'Study'),
(23, '', 'Unfurnished'),
(24, '', 'Whole Roof'),
(25, '', 'Air Conditioning'),
(26, '', 'C - Standard'),
(27, '', 'Field'),
(28, '', 'Garage'),
(29, '', 'Internet'),
(30, '', 'Part of Roof'),
(31, '', 'Semi Detached'),
(32, '', 'Swimming Pool'),
(33, '', 'Utility Bills Included'),
(34, '', 'Yard'),
(35, '', 'Airspace'),
(36, '', 'Cable TV'),
(37, '', 'Finished'),
(38, '', 'Garden'),
(39, '', 'Jacuzzi'),
(40, '', 'Pet friendly'),
(41, '', 'Semi finished'),
(42, '', 'Terrace'),
(43, '', 'Utility room'),
(44, '', 'Basement'),
(45, '', 'Dishwasher'),
(46, '', 'Open Rent'),
(47, '', 'Open Plan'),
(48, '', 'Sea View'),
(49, '', 'Studio'),
(50, '', 'Wheelchair Accessible');

-- --------------------------------------------------------

--
-- Table structure for table `property_media`
--

CREATE TABLE `property_media` (
  `property_media_id` bigint NOT NULL,
  `property_media_type` varchar(255) NOT NULL,
  `property_media_url` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `property_type`
--

CREATE TABLE `property_type` (
  `property_type_id` bigint NOT NULL,
  `property_type_description` varchar(255) DEFAULT NULL,
  `property_type_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `property_type`
--

INSERT INTO `property_type` (`property_type_id`, `property_type_description`, `property_type_name`) VALUES
(5, 'Luxurious apartments', 'Lux apartment'),
(6, 'Regular apartments', 'Regular apartment'),
(7, 'Commercial space', 'Commercial space'),
(8, 'Big house with large backyard', 'Vila'),
(9, 'Regular house', 'House');

-- --------------------------------------------------------

--
-- Table structure for table `rent_type`
--

CREATE TABLE `rent_type` (
  `rent_type_id` bigint NOT NULL,
  `rent_type_description` varchar(255) DEFAULT NULL,
  `rent_type_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `rent_type`
--

INSERT INTO `rent_type` (`rent_type_id`, `rent_type_description`, `rent_type_name`) VALUES
(3, 'Renting of of residential properties', 'Residential'),
(4, 'Renting of of commercial properties', 'Commercial');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `role_id` bigint NOT NULL,
  `role_description` varchar(255) DEFAULT NULL,
  `role_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`role_id`, `role_description`, `role_name`) VALUES
(1, 'ADMIN', 'Has all access rights'),
(2, 'EMPLOYEE', 'Has limited access rights');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `agent`
--
ALTER TABLE `agent`
  ADD PRIMARY KEY (`agent_id`),
  ADD UNIQUE KEY `UK_ovd2qsiqref4xunrl8ubbvdig` (`agent_id_number`),
  ADD UNIQUE KEY `UK_rh8q18r8mhuc7d5j8umqx2cjs` (`agent_username`),
  ADD KEY `FK62bop6ygdgye7vrdbyudlcjr` (`role_id`),
  ADD KEY `FK5639h4rm7dg5iivxokesw5v6a` (`location_id`),
  ADD KEY `FKkn5a6j0vn0oy4xngifdw02khy` (`profile_picture_id`);

--
-- Indexes for table `company_details`
--
ALTER TABLE `company_details`
  ADD PRIMARY KEY (`company_details_id`);

--
-- Indexes for table `DATABASECHANGELOGLOCK`
--
ALTER TABLE `DATABASECHANGELOGLOCK`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`location_id`),
  ADD UNIQUE KEY `UK_pfamglnq4wwr7p5snoifen1gs` (`location_name`);

--
-- Indexes for table `owner`
--
ALTER TABLE `owner`
  ADD PRIMARY KEY (`owner_id`),
  ADD KEY `FKaftw0u8q6uw712caqk59bq6dh` (`agent_id`),
  ADD KEY `FKf3ryakojqdl55hs4j32qfb2a4` (`location_id`);

--
-- Indexes for table `profile_picture`
--
ALTER TABLE `profile_picture`
  ADD PRIMARY KEY (`profile_picture_id`);

--
-- Indexes for table `property_attribute`
--
ALTER TABLE `property_attribute`
  ADD PRIMARY KEY (`property_attribute_id`),
  ADD UNIQUE KEY `UK_3u0o4cmdl91o90yowy21k94po` (`property_attribute_name`);

--
-- Indexes for table `property_media`
--
ALTER TABLE `property_media`
  ADD PRIMARY KEY (`property_media_id`);

--
-- Indexes for table `property_type`
--
ALTER TABLE `property_type`
  ADD PRIMARY KEY (`property_type_id`),
  ADD UNIQUE KEY `UK_9fq3x4c0cg3y3icuyntpglgim` (`property_type_name`),
  ADD UNIQUE KEY `UK_mvffc5d6jt1xc3mnkmmqipklm` (`property_type_description`);

--
-- Indexes for table `rent_type`
--
ALTER TABLE `rent_type`
  ADD PRIMARY KEY (`rent_type_id`),
  ADD UNIQUE KEY `UK_m5qwr85rj5boagdx4wlyni5jt` (`rent_type_name`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`),
  ADD UNIQUE KEY `UK_iubw515ff0ugtm28p8g3myt0h` (`role_name`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `agent`
--
ALTER TABLE `agent`
  ADD CONSTRAINT `FK5639h4rm7dg5iivxokesw5v6a` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`),
  ADD CONSTRAINT `FK62bop6ygdgye7vrdbyudlcjr` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  ADD CONSTRAINT `FKkn5a6j0vn0oy4xngifdw02khy` FOREIGN KEY (`profile_picture_id`) REFERENCES `profile_picture` (`profile_picture_id`);

--
-- Constraints for table `owner`
--
ALTER TABLE `owner`
  ADD CONSTRAINT `FKaftw0u8q6uw712caqk59bq6dh` FOREIGN KEY (`agent_id`) REFERENCES `agent` (`agent_id`),
  ADD CONSTRAINT `FKf3ryakojqdl55hs4j32qfb2a4` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
