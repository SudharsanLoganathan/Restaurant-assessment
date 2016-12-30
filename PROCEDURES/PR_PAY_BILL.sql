DELIMITER $$


DROP PROCEDURE IF EXISTS `PR_PAY_BILL`$$

CREATE  PROCEDURE `PR_PAY_BILL`(IN ORDER_NO INT,IN SEAT_ID VARCHAR(20))

BEGIN
	
             
DECLARE STATUS VARCHAR(20);
                  
SET STATUS=(SELECT ORDER_STATUS FROM order_details WHERE ORDER_ID=ORDER_NO);
                  
IF (STATUS='Ordered')
                  
THEN
                  
UPDATE order_details SET ORDER_STATUS='Billed' WHERE ORDER_ID=ORDER_NO;
                  
UPDATE seed_seat SET ISACTIVE=0 WHERE SEAT_NO=SEAT_ID;
                  
ELSE IF (STATUS='billed') THEN
                  
SELECT 'Your bill is paid' AS COMMENT;
                  
ELSE
                  
SELECT 'Your order is cancelled' AS COMMENT;
                  
END IF;
                 
 END IF;
       
   
 END$$

DELIMITER ;