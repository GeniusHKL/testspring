DELIMITER $$

CREATE
    TRIGGER `testbank`.`after_balance_update` AFTER UPDATE
    ON `testbank`.`accounts`
    FOR EACH ROW BEGIN
	IF new.balance<0 THEN
	UPDATE accounts SET balance=old.balance WHERE accountid = old.accountid;
    END$$

DELIMITER ;