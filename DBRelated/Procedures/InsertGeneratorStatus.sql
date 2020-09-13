USE BackboneTier1;
DROP PROCEDURE IF EXISTS GetConfiguration;

DELIMITER //

CREATE PROCEDURE GetConfiguration 
(
	IN ComponentId VARCHAR(255)
) 
BEGIN
	#call DebugLog(ComponentId);
	
    SELECT Name, Value from Config config WHERE config.ComponentId = ComponentId;
END //
DELIMITER ;
