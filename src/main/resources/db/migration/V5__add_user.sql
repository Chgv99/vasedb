CREATE TABLE Users (
    uuid UUID PRIMARY KEY,
    nickname VARCHAR(15),
    created_at TIMESTAMP DEFAULT now()
);

INSERT INTO Users (uuid, nickname) VALUES (
    '00000000-0000-0000-0000-000000000000',
    'none'
);

ALTER TABLE game
ADD COLUMN user_uuid UUID;

UPDATE game SET user_uuid = '00000000-0000-0000-0000-000000000000';

ALTER TABLE game
ALTER COLUMN user_uuid SET NOT NULL,
ADD CONSTRAINT fk_game_user FOREIGN KEY (user_uuid) REFERENCES Users(uuid);