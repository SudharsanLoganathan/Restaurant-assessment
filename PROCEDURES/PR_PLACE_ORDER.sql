DELIMITER $$

DROP PROCEDURE IF EXISTS PR_PLACE_ORDER $$

CREATE  PROCEDURE `PR_PLACE_ORDER`(IN SEAT_NO VARCHAR(10),IN  ITEM_LIST MEDIUMTEXT,IN QTY_LIST MEDIUMTEXT)

BEGIN

DECLARE ITEM TEXT DEFAULT NULL ;

DECLARE ITEM_LENGTH INT DEFAULT NULL;

DECLARE LEAVE_ITEM TEXT DEFAULT NULL;        

DECLARE QTY TEXT DEFAULT NULL ;         

DECLARE QTY_LENGTH INT DEFAULT NULL;        

DECLARE LEAVE_QTY TEXT DEFAULT NULL;

DECLARE ORDER_ID INT;

DECLARE TOT_QTY INT;

DECLARE TOT_PRICE INT;

DECLARE ORD_ID INT;

DECLARE i INT DEFAULT 0;

SET ORDER_ID=(FLOOR(100+RAND()*(100)));
         

iterator :
         

LOOP            

IF LENGTH(TRIM(ITEM_LIST)) = 0 OR ITEM_LIST IS NULL OR LENGTH(TRIM(QTY_LIST)) = 0 OR QTY_LIST IS NULL THEN

LEAVE iterator;

END IF;
               

SET ITEM = SUBSTRING_INDEX(ITEM_LIST,',',1);

SET ITEM_LENGTH = LENGTH(ITEM);             

SET LEAVE_ITEM = TRIM(ITEM);
                                 

SET QTY = SUBSTRING_INDEX(QTY_LIST,',',1);                

SET QTY_LENGTH = LENGTH(QTY);
SET LEAVE_QTY = TRIM(QTY);

IF (SELECT FN_CHECK_ITEM(ITEM))=1 THEN
CALL PR_FOOD_ORDER(ORDER_ID,ITEM,SEAT_NO,QTY); 
 SET i=i+1;                                                    
 ELSE
    SELECT CONCAT(ITEM,' is not available in our menu') AS COMMENT;
    UPDATE seed_seat SET seed_seat.`ISACTIVE`=1 WHERE seed_seat.`SEAT_NO`=SEAT_NO AND seed_seat.`ISACTIVE`=0;
    END IF;
    
                                

SET ITEM_LIST = INSERT(ITEM_LIST,1,ITEM_LENGTH + 1,'');              

SET QTY_LIST = INSERT(QTY_LIST,1,QTY_LENGTH + 1,'');

END LOOP;

SELECT DISTINCT(ORDER_ID) INTO ORD_ID FROM food_order WHERE food_order.`ORDER_ID`=ORDER_ID;

SELECT SUM(IND_QTY) INTO TOT_QTY FROM food_order WHERE food_order.`ORDER_ID`=ORDER_ID;

SELECT SUM(PRICE) INTO TOT_PRICE FROM food_order WHERE food_order.`ORDER_ID`=ORDER_ID;

IF (SELECT COUNT(IND_QTY) FROM food_order WHERE food_order.`ORDER_ID`=ORD_ID)=i THEN

UPDATE seed_seat SET seed_seat.`ISACTIVE`=1 WHERE seed_seat.`SEAT_NO`=SEAT_NO AND seed_seat.`ISACTIVE`=0;

INSERT INTO order_details(ORDER_ID,SEAT_ID,QUANTITY,TOTAL_PRICE,ORDER_STATUS,ORDER_DATE) VALUES(ORD_ID,SEAT_NO,TOT_QTY,TOT_PRICE,'Ordered',CURRENT_TIMESTAMP);

END IF;   

IF (SELECT COUNT(IND_QTY) FROM food_order WHERE food_order.`ORDER_ID`=ORD_ID)=(SELECT MAXIMUM FROM max_limit WHERE max_limit.`NAME`='LIMIT') 
THEN

INSERT INTO order_details(ORDER_ID,SEAT_ID,QUANTITY,TOTAL_PRICE,ORDER_STATUS,ORDER_DATE) VALUES(ORD_ID,SEAT_NO,TOT_QTY,TOT_PRICE,'Ordered',CURRENT_TIMESTAMP);

END IF;

END$$

DELIMITER ;