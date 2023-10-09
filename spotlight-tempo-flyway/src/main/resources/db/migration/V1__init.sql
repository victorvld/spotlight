CREATE SCHEMA IF NOT EXISTS msvc_tempo;

CREATE TABLE msvc_tempo.focus_unit (
    entity_id VARCHAR(255) NOT NULL PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL
);

INSERT INTO msvc_tempo.focus_unit (entity_id, user_id) VALUES ('1', '1');
INSERT INTO msvc_tempo.focus_unit (entity_id, user_id) VALUES ('2', '1');
INSERT INTO msvc_tempo.focus_unit (entity_id, user_id) VALUES ('3', '2');
