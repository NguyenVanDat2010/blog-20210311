CREATE DATABASE blog;

CREATE USER 'blogdata'@'localhost' IDENTIFIED BY '123456';
GRANT ALL PRIVILEGES ON `blog`.* TO 'blogdata'@'localhost';

FLUSH PRIVILEGES;