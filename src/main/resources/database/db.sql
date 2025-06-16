CREATE DATABASE CMS;
USE CMS;

CREATE TABLE User (
                      uId VARCHAR(255),
                      name VARCHAR(255),
                      email VARCHAR(255),
                      password VARCHAR(255),
                      role VARCHAR(255)
);

CREATE TABLE Complain (
                          complainId VARCHAR(255),
                          uId VARCHAR(255),
                          subject VARCHAR(255),
                          message VARCHAR(255),
                          status VARCHAR(255),
                          FOREIGN KEY (uId) REFERENCES User(uId) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Answer (
                        ansId VARCHAR(255),
                        complainId VARCHAR(255),
                        subject VARCHAR(255),
                        message VARCHAR(255),
                        FOREIGN KEY (complainId) REFERENCES Complain(complainId) ON DELETE CASCADE ON UPDATE CASCADE
);
