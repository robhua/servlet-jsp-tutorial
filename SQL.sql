CREATE database Wish;

USE Wish;

SET SQL_SAFE_UPDATES = 1;

CREATE TABLE UID (
	USER_ID int  NOT NULL primary key,
	USER_NAME varchar(255) NULL,
	PASSWORD varchar(255) NULL,
	SELL_FLAG int NULL,
	ADDMIN_FLAG int NULL
);
    
SELECT * FROM UID;

DELETE FROM UID where USER_ID = 1;

ALTER TABLE UID CHANGE ADDMIN_FLAG ADMIN_FLAG  INT;

ALTER TABLE UID CHANGE USER_ID USER_ID INT NOT NULL PRIMARY KEY;


INSERT INTO UID (USER_ID, USER_NAME, PASSWORD, SELL_FLAG, ADMIN_FLAG) VALUES (1, 'Adam', '123456', 1, 0)
,(2, 'Anjolie', 'SNZ6HE', 0, 1)
,(3, 'Ferris', 'RXH3XJ', 0, 1)
,(4, 'Katell', 'HWV8Z', 0, 0)
,(5, 'Zahir', 'NPX7OF', 1, 0)
,(6, 'Cona', 'WIZ5VZ', 1, 0)
,(7, 'Cade', 'ZSW2LU', 1, 0)
,(8, 'Micah', 'RVV5SR', 0, 0)
,(9, 'Rowa', 'VAI6XR', 1, 1)
,(10, 'Kirby', 'DNX6JK', 1, 0)
,(11, 'Tanisha', 'XWU7JP', 0, 1)
,(12, 'Howard', 'TSR5MR', 0, 1)
,(13, 'Tana', 'EHS8CM', 0, 0)
,(14, 'Hadassah', 'YOY7ZW', 1, 0)
,(15, 'Echo', 'IGE8T', 1, 0)
,(16, 'Slade', 'OFO6QS', 0, 0)
,(17, 'Devi', 'IBM6RZ', 1, 0)
,(18, 'Rowa', 'ZYS9VI', 1, 0)
,(19, 'Rafael', 'WZA0IH', 1, 0)
,(20, 'Madaline', 'QMW4E', 0, 1)
,(21, 'Vera', 'CZB2VM', 0, 1)
,(22, 'Decla', 'ZKE7QZ', 1, 1)
,(23, 'Katell', 'SFS0IW', 1, 1)
,(24, 'Summer', 'PSQ7LC', 0, 1)
,(25, 'Robi', 'KIS9AF', 1, 1)
,(26, 'Dominique', 'IKV0IX', 0, 0)
,(27, 'admi', '123', 1, 1)
,(28, 'mra', 'mra', 0, 0)
,(29, 'mrb', '123', 0, 0)
,(30, 'Camde', '123', 0, 0);
