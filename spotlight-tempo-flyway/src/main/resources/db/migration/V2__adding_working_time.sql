ALTER TABLE msvc_tempo.focus_unit ADD working_time_id VARCHAR(255);

CREATE TABLE msvc_tempo.working_time (
    working_time_id VARCHAR(255) NOT NULL PRIMARY KEY
);

INSERT INTO msvc_tempo.working_time (working_time_id) VALUES ('1');
INSERT INTO msvc_tempo.focus_unit (entity_id, user_id, working_time_id) VALUES ('4', '2', '1');

