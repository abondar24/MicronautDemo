CREATE TABLE IF NOT EXISTS person
(
    id SERIAL PRIMARY KEY ,
    first_name VARCHAR(255) NOT NULL ,
    last_name VARCHAR(255) NOT NULL ,
    phone VARCHAR(255) NOT NULL
)
