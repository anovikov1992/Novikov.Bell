INSERT INTO Organization (id, version, name, full_name,  inn, kpp, ur_Address, phone, is_Active) VALUES (0, 0, 'Test', 'OOO Test',  12345678, 87654321, 'NN', 1234568, true);

INSERT INTO Organization (id, version, name, full_name, inn, kpp, ur_Address, phone, is_Active) VALUES (1, 0, 'Test1', 'OOO Test1',  123456781, 876543211, 'NN1', 12345681, true);

INSERT INTO Organization (id, version, name, full_name,  inn, kpp, ur_Address, phone, is_Active) VALUES (2, 0, 'Test2', 'OOO Test2',  123456782, 876543212, 'NN2', 12345682, true);


INSERT INTO Office (id, version, name, Address, is_Active, organization_id) VALUES (0, 0, 'Office0', 'NN office', true, 2);

INSERT INTO Office (id, version, name, Address, is_Active, organization_id) VALUES (1, 0, 'Office1', 'NN office', true, 1);


INSERT INTO Doc (id, version, doc_name, doc_code) VALUES (0, 0, 'Свидетельство о рождении', 03);

INSERT INTO Doc (id, version, doc_name, doc_code) VALUES (1, 0, 'Военный билет', 07);


INSERT INTO Country (id, version, country_name, country_code, citizenship_code) VALUES (0, 0, 'Российская Федерация', 643, 'RUS');


INSERT INTO User (id, version, first_name, middle_name, second_name, position, phone, doc_date, is_Identified, country_id) VALUES (0, 0, 'User0',  'Userovich0', 'Userov0', 'director0', 3215456, '1986-08-15', true, 0);

INSERT INTO User (id, version, first_name, middle_name, second_name, position, phone, doc_date, is_Identified, doc_id) VALUES (1, 0, 'User1',  'Userovich1', 'Userov1', 'director1', 3215116, '1111-11-11', true, 1);