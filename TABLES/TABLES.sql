

CREATE TABLE seed_foodtype(
ID INT PRIMARY KEY,
FOOD_TYPE VARCHAR(30) UNIQUE NOT NULL,
START_TIME TIME NOT NULL,
END_TIME TIME NOT NULL,
QUANTITY INT NOT NULL
); 
INSERT INTO seed_foodtype VALUES (1,'Breakfast','08:00:00','11:00:00',100),(2,'Lunch','11:15:00','15:00:00',75),(3,'Refreshment','15:15:00','23:00:00',200),(4,'Dinner','19:00:00','23:00:00',100);

CREATE TABLE seed_seat(
ID INT,
SEAT_NO VARCHAR(10) UNIQUE PRIMARY KEY,
ISACTIVE INT DEFAULT 0 
);
INSERT INTO seed_seat(ID,SEAT_NO) VALUES(1,'S01'),(2,'S02'),(3,'S03'),(4,'S04'),(5,'S05'),(6,'S06'),(7,'S07'),(8,'S08'),(9,'S09'),(10,'S10');

CREATE TABLE max_limit(
ID INT PRIMARY KEY,
NAME VARCHAR(20) NOT NULL,
MAXIMUM INT NOT NULL
);
INSERT INTO max_limit VALUES(1,'LIMIT',5);

CREATE TABLE seed_food(
ID INT PRIMARY KEY,
ITEM VARCHAR(50) NOT NULL,
PRICE INT NOT NULL
);
INSERT INTO SEED_FOOD VALUES(1,'IDLY',5),(2,'VADA',4),(3,'DOSA',15),(4,'POORI',10),(5,'PONGAL',15),(6,'TEA',5),(7,'COFFEE',8),
(8,'SOUTH INDIAN MEALS',30),(9,'NORTH INDIAN THALI',40),(10,'VARIETY RICE',25),
(11,'SNACKS',5),(12,'FRIED RICE',30),(13,'CHAPATTI',20),(14,'CHAT ITEMS',20);

CREATE TABLE food_schedule(                                                                    
ID INT,
FOODTYPE_ID INT NOT NULL,
FOOD_ID INT NOT NULL,
REM_QTY INT NOT NULL,
PRIMARY KEY(FOODTYPE_ID,FOOD_ID),
CONSTRAINT FOODTYPE_FK FOREIGN KEY(FOODTYPE_ID) REFERENCES seed_foodtype(ID),
CONSTRAINT FOODID_FK FOREIGN KEY(FOOD_ID) REFERENCES seed_food(ID)
);

INSERT INTO food_schedule VALUES(1,1,1,100),(2,1,2,100),(3,1,3,100),(4,1,4,100),(5,1,5,100),(6,1,6,100),(7,1,7,100),
(8,2,8,75),(9,2,9,75),(10,2,10,75),
(11,3,6,200),(12,3,7,200),(11,3,11,200),
(14,4,12,100),(15,4,13,100),(16,4,14,100);   


CREATE TABLE food_order(
ID INT PRIMARY KEY AUTO_INCREMENT,
ORDER_ID INT,
FOOD_NAME VARCHAR(50), 
IND_QTY INT NOT NULL,
PRICE INT NOT NULL,
ORDER_STATUS VARCHAR(20)
);


CREATE TABLE order_details(
ID INT PRIMARY KEY AUTO_INCREMENT,
ORDER_ID INT ,
SEAT_ID VARCHAR(10),
QUANTITY INT,
TOTAL_PRICE INT,
ORDER_STATUS VARCHAR(20),
ORDER_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
CONSTRAINT FK_SEAT_ID FOREIGN KEY(SEAT_ID) REFERENCES seed_seat(SEAT_NO)
);
