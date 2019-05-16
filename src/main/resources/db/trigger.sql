CREATE TRIGGER increment_voting
    AFTER INSERT
    ON vote_log
    REFERENCING NEW ROW AS newrow
    FOR EACH ROW
BEGIN
    ATOMIC
    MERGE INTO menu AS m
    USING (VALUES (now(), newrow.restaurant_id, 1)) AS vals(a, b, c)
    ON m.restaurant_id = vals.b
    WHEN MATCHED THEN
        UPDATE SET m.vote_count=m.vote_count + 1;
end /;

CREATE TRIGGER decrement_voting
    AFTER DELETE
    ON vote_log
    REFERENCING OLD ROW AS oldrow
    FOR EACH ROW
BEGIN
    ATOMIC
    MERGE INTO menu AS m
    USING (VALUES (now(), oldrow.restaurant_id, 1)) AS vals(a, b, c)
    ON m.restaurant_id = vals.b
    WHEN MATCHED THEN
        UPDATE SET m.vote_count=m.vote_count - 1;
end /;