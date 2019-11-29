CREATE TABLE IF NOT EXISTS accounts (
id int PRIMARY KEY auto_increment,
username varchar(20),
password varchar(20)
);

CREATE TABLE IF NOT EXISTS data (
text varchar(200)
);