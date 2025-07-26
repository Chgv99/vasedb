DROP TABLE Users;

ALTER TABLE game
DROP COLUMN user_uuid,
DROP CONSTRAINT fk_game_user;