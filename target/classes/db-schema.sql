
--CREATE TABLE IF NOT EXISTS BOOK (id varchar(250), title varchar(250), author varchar(250), category varchar(250),year int, description varchar(500), available boolean, primary key(id))

DROP TABLE IF EXISTS User_roles
DROP TABLE IF EXISTS users
DROP TABLE IF EXISTS BOOK

CREATE TABLE IF NOT EXISTS BOOK (id INT Auto_INCREMENT, title varchar(250), author varchar(250), category varchar(250),year int, description varchar(500), available boolean, checkoutuser varchar(45), primary key(id))
CREATE TABLE IF NOT EXISTS users ( USER_ID INT(10) UNSIGNED NOT NULL AUTO_INCREMENT, USERNAME VARCHAR(45) NOT NULL,  PASSWORD VARCHAR(45) NOT NULL,  ENABLED tinyint(1) NOT NULL, PRIMARY KEY (USER_ID)) 
CREATE TABLE IF NOT EXISTS user_roles (  USER_ROLE_ID INT(10) UNSIGNED NOT NULL,  USER_ID INT(10) UNSIGNED NOT NULL,  AUTHORITY VARCHAR(45) NOT NULL,  PRIMARY KEY (USER_ROLE_ID),  KEY FK_user_roles (USER_ID),  CONSTRAINT FK_user_roles FOREIGN KEY (USER_ID) REFERENCES users (USER_ID))
 

INSERT INTO book (id, title, author, category, year, description, available, checkoutuser ) values ('1', 'Java for dinner','Igor Mihalik', 'Cloud', '2013','Real Life java..',false, 'john' )
INSERT INTO book (id, title, author, category, year, description, available, checkoutuser ) values ('2', 'Java for beginners','Igor Mihalik', 'Cloud', '2011','Action packed java..',true, 'avail' )
INSERT INTO book (id, title, author, category, year, description, available, checkoutuser ) values ('3', 'java for novice','John OBrien', 'Computing', '2011','Action packed cloud..',true,'avail' )
INSERT INTO book (id, title, author, category, year, description, available, checkoutuser ) values ('4', 'In Rainbows','Radiodead', 'Music', '2011','Action packed music..',true,'' )
INSERT INTO book (id, title, author, category, year, description, available, checkoutuser ) values ('5', 'Screamadelica','Primal Scream', 'Music', '1990','Action packed Bobby Gillespie..',true,'avail')
INSERT INTO book (id, title, author, category, year, description, available, checkoutuser ) values ('6', 'Ulysses','James Joyce', 'Literature', '1984','Action packed joyce..',true, 'avail')

INSERT INTO users (USER_ID, USERNAME,PASSWORD, ENABLED) VALUES (100, 'john', 'john', TRUE)
INSERT INTO user_roles (USER_ROLE_ID, USER_ID,AUTHORITY) VALUES (1, 100, 'ROLE_USER')
INSERT INTO users (USER_ID, USERNAME,PASSWORD, ENABLED) VALUES (200, 'admin', 'admin', TRUE)
INSERT INTO user_roles (USER_ROLE_ID, USER_ID,AUTHORITY) VALUES (2, 200, 'ROLE_ADMIN')


