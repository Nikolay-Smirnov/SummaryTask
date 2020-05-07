DROP DATABASE IF EXISTS carrent;
CREATE DATABASE carrent CHARACTER SET utf8 COLLATE utf8_bin;
USE carrent;

CREATE TABLE clients (

	id_client INTEGER NOT NULL auto_increment PRIMARY KEY,
	login VARCHAR(20) NOT NULL UNIQUE,
    index_passport VARCHAR(20) NOT NULL UNIQUE,
    client_phone VARCHAR(20) NOT NULL UNIQUE,
	name_client VARCHAR(20)  NOT NULL,
	password_client VARCHAR(20) NOT NULL,	
	surname VARCHAR(20) NOT NULL,
		age VARCHAR(20) NOT NULL,
        role_client VARCHAR(20) NOT NULL,
        status_client VARCHAR(20) NOT NULL
);

	
CREATE TABLE admins (

	id_admin INTEGER NOT NULL auto_increment PRIMARY KEY,
	login_admin VARCHAR(20) NOT NULL UNIQUE,
	name_admin VARCHAR(20)  NOT NULL,
	index_passport VARCHAR(20) NOT NULL UNIQUE,
    admin_phone VARCHAR(20) NOT NULL UNIQUE,
	password_admin VARCHAR(20) NOT NULL,	
	surname_admin VARCHAR(20) NOT NULL,
	age_admin INT NOT NULL,
    role_admin VARCHAR(10) NOT NULL
);

CREATE TABLE managers (

	id_manager INTEGER NOT NULL auto_increment PRIMARY KEY,
	login_manager VARCHAR(20) NOT NULL UNIQUE,
	name_manager VARCHAR(20)  NOT NULL,
	password_manager VARCHAR(20) NOT NULL,	
    manager_phone VARCHAR(20) NOT NULL UNIQUE,
	surname VARCHAR(20) NOT NULL,
	index_passport VARCHAR(20) NOT NULL UNIQUE,
	id_fk_admin INTEGER NOT NULL,
	FOREIGN KEY (id_fk_admin)  REFERENCES  admins (id_admin)
    ON DELETE RESTRICT 
	ON UPDATE RESTRICT,
    status_manager VARCHAR(20) NOT NULL,
    role_manager VARCHAR(10) NOT NULL

);

CREATE TABLE cars (
	id_car INTEGER NOT NULL auto_increment PRIMARY KEY,
	model VARCHAR(20) NOT NULL,
    car_brand VARCHAR(20) NOT NULL,	
	car_level VARCHAR(20) NOT NULL,	
	price INTEGER NOT NULL,
	status_car varchar(20) NOT NULL,
	id_fk_admin INTEGER NOT NULL,
    VIN varchar(40) NOT NULL,
	FOREIGN KEY (id_fk_admin)  REFERENCES  admins (id_admin)
    ON DELETE RESTRICT 
	ON UPDATE RESTRICT,
	condition_car varchar(1000) NOT NULL
  
);
create table functions (
name_function VARCHAR(20) PRIMARY KEY unique NOT NULL,
price integer NOT NULL
);
CREATE TABLE orders (
	id_Order INTEGER NOT NULL auto_increment PRIMARY KEY,
    start_order_date date NOT NULL,
	finish_order_date date NOT NULL,
  	order_Price INTEGER NOT NULL,
  	fk_name_function VARCHAR(20) NULL,
	foreign key (fk_name_function) references functions (name_function),
  	id_fk_car INTEGER NOT NULL,
    FOREIGN KEY (id_fk_car)  REFERENCES  cars (id_car),
	passport_fk_manager varchar(20) NULL,
	FOREIGN KEY (passport_fk_manager)  REFERENCES  managers (index_passport),
    passport_fk_client varchar(20) NULL,
	FOREIGN KEY (passport_fk_client)  REFERENCES  clients ( index_passport),
    status_order varchar(20) NOT NULL,
    order_activity varchar(20) NOT NULL,
    description_order varchar(1000) NULL
);

CREATE TABLE damage (
	id_payment INTEGER NOT NULL  auto_increment PRIMARY KEY,
	id_fk_order INTEGER NOT NULL ,
	foreign key (id_fk_order) references orders (id_Order),
	passport_fk_client varchar(20) NOT NULL,
	FOREIGN KEY (passport_fk_client)  REFERENCES  clients ( index_passport),
    passport_fk_manager varchar(20) NOT NULL,
    FOREIGN KEY (passport_fk_manager)  REFERENCES  managers (index_passport),
	invoice_date date NOT NULL,
    status_order varchar(20) NOT NULL,
    payment_activity varchar(20) NOT NULL,
    price integer NOT NULL,
    description_payment varchar(1000) NOT NULL
); 



    
SET SQL_SAFE_UPDATES = 0;
INSERT INTO functions (name_function, price) Values("Personal Driver", 100);
INSERT INTO functions (name_function, price) Values("Without Function", 0);
INSERT INTO admins (login_admin, name_admin, surname_admin, password_admin, age_admin, role_admin,   index_passport, admin_phone) Values("annatovm123", "Anna", "Tovmasyan", 123, 19, "admin", "AB12345", "095111222333");



DELIMITER $$ 
 CREATE TRIGGER tai_order_to_car_status AFTER INSERT ON orders
  FOR EACH  ROW 
BEGIN
   UPDATE cars SET status_car = "busy" WHERE id_car = NEW.id_fk_car;
END $$;

DELIMITER $$ 
  CREATE TRIGGER order_update_car_status AFTER UPDATE ON orders 
    FOR EACH  ROW
  BEGIN IF (NEW.order_activity = 'no active') THEN
    UPDATE cars SET status_car = 'free' WHERE id_car = NEW.id_fk_car ;
  END IF;
END $$;

DELIMITER $$ 
 CREATE TRIGGER order_delete_car_status AFTER DELETE ON orders
	FOR EACH  ROW 
 BEGIN
   UPDATE cars SET status_car = "free" WHERE id_car = OLD.id_fk_car;
END $$