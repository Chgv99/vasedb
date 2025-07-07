CREATE TYPE result AS ENUM ('CORRECT', 'INCORRECT');

ALTER TABLE resolution
ADD COLUMN result result;