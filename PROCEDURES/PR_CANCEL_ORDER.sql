DELIMITER $$


DROP PROCEDURE IF EXISTS `PR_CANCEL_ORDER`$$

CREATE  PROCEDURE `PR_CANCEL_ORDER`(IN ORDER_NO INT,IN ITEM_NAME VARCHAR(30))

BEGIN
	
DECLARE STATUS VARCHAR(20);
	
DECLARE QTY INT;
	
DECLARE FOOD_NO INT;
	
DECLARE RATE INT;
	
DECLARE FOOD_TYPE_ID INT;
DECLARE SEAT_NUM VARCHAR(20);
	
SELECT seed_foodtype.`ID` INTO FOOD_TYPE_ID FROM seed_foodtype WHERE CURRENT_TIME BETWEEN START_TIME AND END_TIME;
	
SELECT seed_food.`ID` INTO FOOD_NO FROM seed_food WHERE ITEM=ITEM_NAME;
	
SET STATUS=(SELECT ORDER_STATUS FROM food_order WHERE ORDER_ID=ORDER_NO AND FOOD_NAME=ITEM_NAME);
	
IF (SELECT food_schedule.`FOODTYPE_ID` FROM food_schedule WHERE food_schedule.`FOOD_ID`=FOOD_NO AND food_schedule.`FOODTYPE_ID`=FOOD_TYPE_ID)=FOOD_TYPE_ID THEN
	
IF EXISTS(SELECT ORDER_ID FROM order_details WHERE ORDER_ID=ORDER_NO) THEN
	
IF (STATUS='Ordered')
	THEN	
        
IF (SELECT ORDER_STATUS FROM order_details WHERE ORDER_ID=ORDER_NO)='Ordered' THEN 
	      
              SELECT IND_QTY INTO QTY FROM food_order WHERE FOOD_NAME=ITEM_NAME AND ORDER_ID=ORDER_NO;
	      
              SELECT PRICE INTO RATE FROM food_order WHERE FOOD_NAME=ITEM_NAME AND ORDER_ID=ORDER_NO; 
              
              UPDATE food_order SET ORDER_STATUS='Cancelled' WHERE FOOD_NAME=ITEM_NAME AND ORDER_ID=ORDER_NO;
              
              UPDATE food_schedule SET food_schedule.`REM_QTY`=food_schedule.`REM_QTY`+QTY WHERE food_schedule.`FOOD_ID`=FOOD_NO AND food_schedule.`FOODTYPE_ID`=FOOD_TYPE_ID;
              
              UPDATE order_details SET order_details.`QUANTITY`=order_details.`QUANTITY`-QTY,order_details.`TOTAL_PRICE`=order_details.`TOTAL_PRICE`-RATE WHERE ORDER_ID=ORDER_NO;
              
IF (SELECT QUANTITY FROM order_details WHERE ORDER_ID=ORDER_NO)=0 THEN
              SELECT SEAT_ID INTO SEAT_NUM FROM order_details WHERE ORDER_ID=ORDER_NO;
              UPDATE order_details SET ORDER_STATUS='Cancelled' WHERE ORDER_ID=ORDER_NO;
              UPDATE seed_seat SET seed_seat.`ISACTIVE`=0 WHERE seed_seat.`SEAT_NO`=SEAT_NUM;
   END IF;
              
SELECT CONCAT(ITEM_NAME,' is Cancelled') AS COMMENT;
         
ELSE
        
SELECT 'Your order is billed' AS COMMENT;
        
END IF;
	
ELSE 
	
SELECT 'Your order cannot be cancelled' AS COMMENT;
        
END IF;
        
ELSE
        
SELECT 'Invalid order id' AS COMMENT;
        
END IF;
        
ELSE 
	
SELECT 'Your order cannot be cancelled' AS COMMENT;
        
END IF;
    
END$$
DELIMITER ;