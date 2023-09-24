ALTER TABLE student ADD CONSTRAINT age_check CHECK (age >= 16);
ALTER TABLE student ADD CONSTRAINT name_constraint UNIQUE (name);
ALTER TABLE student MODIFY name VARCHAR NOT NULL;
ALTER TABLE faculty ADD CONSTRAINT unique_constraint UNIQUE (name, color);
ALTER TABLE student ALTER COLUMN age SET DEFAULT 20;