CREATE TYPE decision AS ENUM ('APPROVED', 'DISCARDED');

ALTER TABLE resolution
ADD COLUMN decision decision;