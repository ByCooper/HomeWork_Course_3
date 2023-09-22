ALTER TABLE student ADD CONSTRAINT age_check CHECK (age >= 16);
ALTER TABLE student ADD PRIMARY KEY (name);
ALTER TABLE faculty ADD CONSTRAINT unique_constraint UNIQUE (name, color);
ALTER TABLE student ALTER COLUMN age SET DEFAULT 20;