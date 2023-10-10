-- Interruptions
INSERT INTO interruption (entity_id, recorded_at, duration, reason, type)
VALUES ('interruption_1', '2023-10-10 10:30:00+00', '10 minutes', 'Technical Issue', 'System');
INSERT INTO interruption (entity_id, recorded_at, duration, reason, type)
VALUES ('interruption_2', '2023-10-10 15:00:00+00', '20 minutes', 'Meeting', 'Human');

-- Working time
INSERT INTO working_time (entity_id, started_at, completed_at, duration, interruptions_id)
VALUES ('working_time_1', '2023-10-10 08:45:00+00', '2023-10-10 09:00:00+00', '15 minutes', 'interruption_1');
INSERT INTO working_time (entity_id, started_at, completed_at, duration)
VALUES ('working_time_2', '2023-10-10 12:15:00+00', '2023-10-10 12:30:00+00', '15 minutes');
INSERT INTO working_time (entity_id, started_at, completed_at, duration)
VALUES ('working_time_3', '2023-10-10 15:30:00+00', '2023-10-10 15:45:00+00', '15 minutes');

-- Break time
INSERT INTO break_time (entity_id, started_at, completed_at, duration, interruptions_id)
VALUES ('break_time_1', '2023-10-10 09:00:00+00', '2023-10-10 09:15:00+00', '15 minutes', 'interruption_2');
INSERT INTO break_time (entity_id, started_at, completed_at, duration)
VALUES ('break_time_2', '2023-10-10 12:30:00+00', '2023-10-10 12:45:00+00', '15 minutes');
INSERT INTO break_time (entity_id, started_at, completed_at, duration)
VALUES ('break_time_3', '2023-10-10 15:45:00+00', '2023-10-10 16:00:00+00', '15 minutes');

-- User assessment
INSERT INTO user_assessment (entity_id, mood, feedback)
VALUES ('user_assessment_1', 'Tired', 'I did not focus');
INSERT INTO user_assessment (entity_id, mood, feedback)
VALUES ('user_assessment_2', 'Good', 'Delivery man');
INSERT INTO user_assessment (entity_id, mood, feedback)
VALUES ('user_assessment_3', 'Excellent', 'Phone call');

-- Focus unit
INSERT INTO focus_unit (entity_id, user_id, working_time_id, break_time_id, user_assessment_id, linked_project_point_id)
VALUES ('focus_unit_1', 'user_1', 'working_time_1', 'break_time_1', 'user_assessment_1', 'linked_project_point_1');
INSERT INTO focus_unit (entity_id, user_id, working_time_id, break_time_id, user_assessment_id, linked_project_point_id)
VALUES ('focus_unit_2', 'user_1', 'working_time_2', 'break_time_2', 'user_assessment_2', 'linked_project_point_1');
INSERT INTO focus_unit (entity_id, user_id, working_time_id, break_time_id, user_assessment_id, linked_project_point_id)
VALUES ('focus_unit_3', 'user_2', 'working_time_3', 'break_time_3', 'user_assessment_3', 'linked_project_point_1');
