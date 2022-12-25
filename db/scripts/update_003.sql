CREATE TABLE IF NOT EXISTS priorities (
                                          id SERIAL PRIMARY KEY,
                                          name TEXT,
                                          position int
);

INSERT INTO priorities (name, position ) VALUES ('Срочно', '1'), ('Средне', '2'), ('Подождёт', '3')