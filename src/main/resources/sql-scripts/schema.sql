CREATE SCHEMA IF NOT EXISTS test;

USE test;

DROP TABLE IF EXISTS PRICES;
DROP TABLE IF EXISTS BRANDS;

CREATE TABLE BRANDS (
                        ID INT PRIMARY KEY,
                        NAME VARCHAR(255) NOT NULL
);

CREATE TABLE PRICES (
                        PRICE_LIST INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
                        BRAND_ID INT,
                        START_DATE DATETIME,
                        END_DATE DATETIME,
                        PRODUCT_ID INT,
                        PRIORITY INT,
                        PRICE NUMERIC(19, 2),
                        CURR VARCHAR(3),
                        FOREIGN KEY (BRAND_ID) REFERENCES BRANDS(ID)
);
