create database employeedb;
use employeedb;
create table employee(
   id INT NOT NULL AUTO_INCREMENT,
   name VARCHAR(40) NOT NULL,
   age INT NOT NULL,
   gender CHAR(6),
   updated_time timestamp,
   PRIMARY KEY ( id )
);
