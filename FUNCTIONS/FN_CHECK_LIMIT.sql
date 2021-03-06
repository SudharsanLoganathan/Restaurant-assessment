DELIMITER $$

DROP FUNCTION IF EXISTS `FN_CHECK_LIMIT`$$

CREATE  FUNCTION `FN_CHECK_LIMIT`(ORDERNO VARCHAR(10)) 
RETURNS TINYINT(4)

BEGIN
	
IF  (SELECT COUNT(DISTINCT(FOOD_NAME)) FROM food_order WHERE ORDER_ID=ORDERNO AND ORDER_STATUS='Ordered')<(SELECT MAXIMUM FROM max_limit WHERE max_limit.`NAME`='LIMIT')
THEN    
	
RETURN 1;
	
ELSE
	
RETURN 0;
	
END IF;
	
    
END$$

DELIMITER ;