CREATE DATABASE BLOG

USE BLOG

CREATE TABLE blogentry 
(
ID INT NOT NULL AUTO_INCREMENT,
title VARCHAR(256),
body LONGTEXT,
PRIMARY KEY (ID)
)

select * from blogentry

drop table blogentry