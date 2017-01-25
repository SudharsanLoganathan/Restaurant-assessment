DELIMITER $$


DROP PROCEDURE IF EXISTS `PR_FOOD_ORDER`$$

CREATE  PROCEDURE `PR_FOOD_ORDER`(IN ORDER_NO INT,IN FOOD_ITEM VARCHAR(20),IN SEAT_ID VARCHAR(10),IN QTY INT)

BEGIN
    
DECLARE FOOD_TYPE_ID INT;
    
DECLARE FOOD_NO INT;
    
DECLARE IND_PRICE INT;
    
DECLARE ORDERED_QTY INT;
    
DECLARE REMAINING INT;
    
    
IF (SELECT FN_CHECK_ITEM(FOOD_ITEM))=1 THEN
   
    
IF EXISTS(SELECT SEAT_NO FROM seed_seat WHERE seed_seat.`SEAT_NO`=SEAT_ID) THEN 
    
IF (QTY>0) THEN
    
IF (SELECT FN_CHECK_LIMIT(ORDER_NO))=1 THEN
    
    
SELECT seed_foodtype.`ID` INTO FOOD_TYPE_ID FROM seed_foodtype WHERE CURRENT_TIME BETWEEN START_TIME AND END_TIME;
    
SELECT seed_food.`PRICE`,seed_food.`ID` INTO IND_PRICE,FOOD_NO FROM seed_food WHERE seed_food.`ITEM`=FOOD_ITEM;
    
SET REMAINING=(SELECT food_schedule.`REM_QTY` FROM food_schedule WHERE food_schedule.`FOOD_ID`=FOOD_NO AND food_schedule.`FOODTYPE_ID`=FOOD_TYPE_ID);
    
IF (SELECT food_schedule.`FOODTYPE_ID` FROM food_schedule WHERE food_schedule.`FOOD_ID`=FOOD_NO AND food_schedule.`FOODTYPE_ID`=FOOD_TYPE_ID)=FOOD_TYPE_ID THEN
    
IF (SELECT FN_CHECK_SEAT(SEAT_ID))=1 THEN
    
IF (REMAINING>0) AND (QTY<=REMAINING)  THEN 
    
    
UPDATE food_schedule SET food_schedule.`REM_QTY`=REMAINING-QTY WHERE food_schedule.`FOOD_ID`=FOOD_NO AND food_schedule.`FOODTYPE_ID`=FOOD_TYPE_ID;
    
INSERT INTO food_order(ORDER_ID,FOOD_NAME,IND_QTY,PRICE,ORDER_STATUS) VALUES(ORDER_NO,FOOD_ITEM,QTY,(QTY*IND_PRICE),'Ordered');
    
    
    
ELSE
    
SELECT CONCAT('Available quantity of ',FOOD_ITEM,' is ',REMAINING) AS COMMENT;
    
END IF;
    
    
ELSE
    
SELECT 'Try other seat and available seats are' AS COMMENT;
    
SELECT SEAT_NO FROM seed_seat WHERE isactive=0;
    
END IF;
    
    
ELSE 
    
SELECT CONCAT(FOOD_ITEM,' is not here at this time') AS COMMENT;
    
END IF;
    
    
ELSE
    
SELECT 'Your limit is 5 items only' AS COMMENT;
    
UPDATE seed_seat SET seed_seat.`ISACTIVE`=1 WHERE seed_seat.`SEAT_NO`=SEAT_ID AND seed_seat.`ISACTIVE`=0;
    
END IF;
    
    
ELSE
    
SELECT 'Please give a valid quantity' AS COMMENT;
    
END IF;
    
    
ELSE
    
SELECT 'Invalid seatno' AS COMMENT;
    
END IF;
    
    
ELSE
    
SELECT CONCAT(FOOD_ITEM,' is not available in our menu') AS COMMENT;
UPDATE seed_seat SET seed_seat.`ISACTIVE`=1 WHERE seed_seat.`SEAT_NO`=SEAT_ID AND seed_seat.`ISACTIVE`=0;
END IF;
    
    
END$$
DELIMITER ;