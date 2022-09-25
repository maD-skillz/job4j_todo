CREATE TABLE IF NOT EXISTS users (
                                     id SERIAL PRIMARY KEY,
                                     name text,
                                     login varchar UNIQUE,
                                     password varchar
);

ALTER TABLE users ADD CONSTRAINT login_unique UNIQUE (login);