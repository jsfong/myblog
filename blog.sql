CREATE DATABASE BLOG

USE BLOG

CREATE TABLE blogpost 
(
ID INT NOT NULL AUTO_INCREMENT,
title VARCHAR(256),
body LONGTEXT,
PRIMARY KEY (ID)
)

select * from blogpost

drop table blogpost