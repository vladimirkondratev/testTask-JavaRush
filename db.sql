USE test;

DROP TABLE IF EXISTS COMPONENTS;

CREATE TABLE test.components (
  ID int(11) NOT NULL AUTO_INCREMENT,
  NAME varchar(255) NOT NULL,
  REQUIRED tinyint(1) NOT NULL DEFAULT 0,
  QUANTITY int(11) NOT NULL,
  PRIMARY KEY (ID)
)
ENGINE = INNODB
AUTO_INCREMENT = 1
CHARACTER SET utf8
COLLATE utf8_general_ci;

INSERT INTO test.components (NAME, REQUIRED, QUANTITY) VALUES
('HDD', 1, 3),
('RJ45 connector',0,6),
('SSD', 0, 5),
('keyboard', 0, 4),
('UTP cabel', 0, 5),
('processor', 1, 10),
('CPU cooling system', 1, 5),
('HDD cooling system', 0, 10),
('motherboard', 1, 3),
('RAM', 1, 5),
('video adapter', 1, 2),
('power supply', 1, 18),
('audio adapter', 0, 11),
('CD/DVD drive', 0, 6),
('Blue - Ray drive', 0, 2),
('USB adapter', 0, 4),
('PCI adapter', 0, 5),
('Wi - Fi adapter', 0, 2),
('Ethernet adapter', 1, 7),
('lighting', 0, 1),
('TV tuner', 0, 3),
('FM tuner', 0, 2),
('video capture adapter', 0, 1),
('Bluetooth adapter', 0, 11),
('mouse', 0, 15),
('card reader', 0, 6);