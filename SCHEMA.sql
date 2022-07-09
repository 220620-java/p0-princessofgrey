CREATE TABLE users (
  id SERIAL,
  user_name varchar(15) NOT NULL UNIQUE,
  pwd varchar(15) NOT NULL,
  first_name varchar(45) DEFAULT NULL,
  last_name varchar(45) DEFAULT NULL,
  address varchar(45) DEFAULT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE accounts ( 
  user_id int not null,
  account_type varchar(15) NOT NULL,
  account_number varchar(15) NOT NULL UNIQUE,
  balance float not null,
  PRIMARY KEY (account_type, account_number),
  CONSTRAINT user_id FOREIGN key (user_id) REFERENCES users (id)
);

