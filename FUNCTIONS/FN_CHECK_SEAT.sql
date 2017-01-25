DELIMITER $$

CREATE FUNCTION FN_CHECK_SEAT(seat_no VARCHAR(30))
    RETURNS INT
    
    BEGIN
	IF EXISTS(SELECT seed_seat.`SEAT_NO` FROM seed_seat WHERE seed_seat.`SEAT_NO`=seat_no AND seed_seat.`ISACTIVE`=0) THEN
	RETURN 1;
	ELSE
	RETURN 0;
	END IF;
    END$$

DELIMITER ;
