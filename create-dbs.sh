#!/bin/bash
set -e
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "postgres" <<-EOSQL
    
    CREATE DATABASE metavase;
    GRANT ALL PRIVILEGES ON DATABASE metavase TO santobucle;
EOSQL