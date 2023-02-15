insert into users (id, email, password, role, address, birth_date, full_name, phone_number, username, dtype) values (10001, 'clientEmail', 'clientPass', 'USER', 'strada 2', '1993-02-03', 'Clientul fericit', '0742032456', 'clientUsername', 'Client');
insert into job_information (id, employment_status, hiring_date, salary, supervisor, title, work_phone) values (10001, null, null, null, null, null, null);
insert into users (id, email, password, role, address, birth_date, full_name, phone_number, username, job_information_id, dtype) VALUES (10002, 'cristiEmail', 'cristiPass', 'EMPLOYEE', 'strada 3', null, 'cristi sam', '0745231754', 'cristiUser', 10001, 'Employee');
insert into cleaning_details (home_access, parking, square_meters, bathrooms, bedrooms, kitchens, dtype, id) VALUES ('CALL', 'FREE', '200', 3, 5, 2, 'StandardCleaningDetails', 10001);
insert into cleaning_service (id, cleaning_details_id, cleaning_type, client_id, email, first_name, last_name, phone_number, frequency, address, city, county, payment, time_estimation, total) values (10001, 10001, 'STANDARD', 10001, null, null, null, null, 'ONE_TIME', null, 'Pecica', 'Arad', 'CARD', 3, 100);
insert into messages (cleaning_service_id, messages_order, content, message_date, sender) values (10001, 0, 'message1', '2023-02-03', 'cristi sam');
insert into messages (cleaning_service_id, messages_order, content, message_date, sender) values (10001, 1, 'message1', '2023-02-03', 'cristi sam');
insert into appointment (id, cleaning_date, cleaning_service_id, employee_id, status, ending_hour, starting_hour) values (10001, '2023-02-07', 10001, 10002, 'ACTIVE', 11, 9);
