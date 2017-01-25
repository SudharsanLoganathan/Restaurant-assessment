DELIMITER $$


DROP PROCEDURE IF EXISTS `PR_REQ_NEXT_ITEM`$$

CREATE PROCEDURE `PR_REQ_NEXT_ITEM`(ORDER_ID INT,ITEM VARCHAR(20),QTY INT)

BEGIN
    
    DECLARE seat_no VARCHAR(20);

    DECLARE IND_PRICE INT;
    
    DECLARE QUANT INT;
    DECLARE FOOD_ID INT;
    DECLARE FOOD_TYPE_ID INT;
    
    SELECT SEAT_ID INTO seat_no FROM order_details WHERE order_details.`ORDER_ID`=ORDER_ID;
    SELECT seed_foodtype.`ID` INTO FOOD_TYPE_ID FROM seed_foodtype WHERE CURRENT_TIME BETWEEN START_TIME AND END_TIME;
    SELECT seed_food.`PRICE`,seed_food.`ID` INTO IND_PRICE,FOOD_ID FROM seed_food WHERE seed_food.`ITEM`=ITEM;
    
    IF (SELECT food_schedule.`FOODTYPE_ID` FROM food_schedule WHERE food_schedule.`FOOD_ID`=FOOD_ID AND food_schedule.`FOODTYPE_ID`=FOOD_TYPE_ID)=FOOD_TYPE_ID THEN
    IF (SELECT FN_CHECK_LIMIT(ORDER_ID))=1 THEN
    
UPDATE seed_seat SET seed_seat.`ISACTIVE`=0 WHERE seed_seat.`SEAT_NO`=seat_no AND seed_seat.`ISACTIVE`=1;
    
    CALL PR_FOOD_ORDER(ORDER_ID,ITEM,seat_no,QTY);
    
    IF (ITEM IN(SELECT DISTINCT(FOOD_NAME) FROM food_order WHERE ORDER_ID=ORDER_ID)) THEN
        SELECT QTY INTO QUANT;
    ELSE
        SELECT IND_QTY INTO QUANT FROM food_order WHERE ORDER_ID=ORDER_ID AND FOOD_NAME=ITEM;
    END IF;
    
UPDATE seed_seat SET seed_seat.`ISACTIVE`=1 WHERE seed_seat.`SEAT_NO`=seat_no AND seed_seat.`ISACTIVE`=0;
    
    UPDATE order_details SET QUANTITY=QUANTITY+QUANT,TOTAL_PRICE=TOTAL_PRICE+(QUANT*IND_PRICE) WHERE order_details.`ORDER_ID`=ORDER_ID;
    
    ELSE 
    
    SELECT 'Your limit is 5 items only' AS COMMENT;
    
    END IF;
    ELSE
    SELECT CONCAT(ITEM,' is not available at this time');
    END IF;
    
    END$$
DELIMITER ;