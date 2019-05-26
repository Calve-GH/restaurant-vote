CREATE TRIGGER increment_voting
    AFTER INSERT
    ON vote_log
    REFERENCING NEW ROW AS newrow
    FOR EACH ROW
BEGIN
    ATOMIC
    MERGE INTO menu AS m
    USING (VALUES (newrow.date, newrow.restaurant_id, 0)) AS vals(a, b, c)
    ON (m.date = vals.a AND m.restaurant_id = vals.b)
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
    USING (VALUES (oldrow.date, oldrow.restaurant_id, 0)) AS vals(a, b, c)
    ON (m.date = vals.a AND m.restaurant_id = vals.b)
    WHEN MATCHED THEN
        UPDATE SET m.vote_count=m.vote_count - 1;
end /;

CREATE TRIGGER set_restaurant_daily_menu_exist
    AFTER INSERT
    ON menu
    REFERENCING NEW ROW AS newrow
    FOR EACH ROW
BEGIN
    ATOMIC
    UPDATE restaurant r SET r.menu_exist= true WHERE r.id = newrow.restaurant_id;
end /;

CREATE TRIGGER set_restaurant_daily_menu_no_exist
    AFTER DELETE
    ON menu
    REFERENCING OLD ROW AS oldrow
    FOR EACH ROW
BEGIN
    ATOMIC
    UPDATE restaurant r SET r.menu_exist= false WHERE r.id = oldrow.restaurant_id;
end /;

