--Creating the table for customer

create table Tbl_Customer(phoneNo number(12)primary key,name varchar2(20),
                          email varchar2(50),age number(3),gender varchar2(10),
                          password varchar2(40),address varchar2(100));