
DROP TABLE IF EXISTS author;
DROP TABLE IF EXISTS books;

CREATE TABLE  author(
author_id INT AUTO_INCREMENT PRIMARY KEY,
author VARCHAR(250) NOT NULL,
yearOfBirth INT DEFAULT NULL
);

CREATE TABLE  books(
books_id INT AUTO_INCREMENT PRIMARY KEY,
author_id INT,
-- имя автора не убрал, чтобы не изменять таблицу books
author VARCHAR(250) NOT NULL,
title VARCHAR(250) NOT NULL,
priceOld  VARCHAR(250) DEFAULT NULL,
price VARCHAR(250) DEFAULT NULL,
FOREIGN KEY(author_id) REFERENCES author(author_id)
);

