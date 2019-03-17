CREATE TABLE users (
  username varchar(45) NOT NULL,
  password varchar(45) DEFAULT NULL,
  balance int(11) DEFAULT NULL,
  email varchar(45) DEFAULT NULL,
  PRIMARY KEY (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE toppings (
  name varchar(45) NOT NULL,
  price int(11) DEFAULT NULL,
  PRIMARY KEY (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE bottoms (
  name varchar(45) NOT NULL,
  price int(11) DEFAULT NULL,
  PRIMARY KEY (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE orderdetails (
  id int(11) NOT NULL AUTO_INCREMENT,
  bname varchar(45) DEFAULT NULL,
  tname varchar(45) DEFAULT NULL,
  qty int(11) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY tname_idx (tname),
  KEY bname_idx (bname),
  CONSTRAINT bname FOREIGN KEY (bname) REFERENCES bottoms (name),
  CONSTRAINT tname FOREIGN KEY (tname) REFERENCES toppings (name)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE invoices (
  id int(11) DEFAULT NULL,
  username varchar(45) DEFAULT NULL,
  date datetime DEFAULT CURRENT_TIMESTAMP,
  KEY id_idx (id),
  KEY username_idx (username),
  CONSTRAINT id FOREIGN KEY (id) REFERENCES orderdetails (id),
  CONSTRAINT username FOREIGN KEY (username) REFERENCES users (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO bottoms (name, price)
VALUES ("Almond","7"),("Chocolate","5"),("Nutmeg","5"),("Pistacio","6"),("Vanilla","5");

INSERT INTO toppings (name, price)
VALUES ("Blue cheese","9"),("Chocolate","5"),("Blueberry","5"),("Crispy","6"),("Lemon","8"),
("Orange","8"),("Raspberry","5"),("Rum/Raisin","7"),("Strawberry","6");

