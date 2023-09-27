-- liquibase formatted sql

--changeset akupriyanov:1
CREATE INDEX student_name_index ON student (name);

--changeset akupriyanov:2
CREATE INDEX faculty_search_ind ON faculty (name, color);