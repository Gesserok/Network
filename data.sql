
 INSERT INTO location (id, country, city, street, build_number) VALUES (1, 'Ukraine', 'Kiev', 'Antonovicha', '40'),
                                                                (2, 'Ukraine', 'Kiev', 'Tarasovskaya', '19');

 INSERT INTO model (id, manufacturer, model)VALUES (1, 'Cisco', 'ASA-5516-x'),
                                                   (2, 'Cisco', 'ISR 4331'),
                                                   (3, 'Cisco', 'Catalyst 3850'),
                                                   (4, 'Cisco', 'Catalyst 3750'),
                                                   (5, 'Cisco', 'Catalyst 3560'),
                                                   (6, 'Cisco', 'Catalyst 2960');

 INSERT INTO branch (id, title, location_id) VALUES (1, 'GO-DC1', 1),
                                          (2, 'GO-DC2', 2);

 INSERT INTO device (id, model_id, ip, hostname, branch_id) VALUES
 (1, 3, '10.41.255.1', 'kv-dc1-cr-1', 1),
 (2, 1, '10.41.255.11', 'kv-dc1-fw-1', 1),
 (3, 2, '10.41.255.13', 'lvv-dc1-rtr-1', 1),
 (4, 4, '10.41.255.14', 'kv-dc1-ac-4', 1),
 (5, 5, '10.41.255.15', 'kv-dc1-ac-2', 1),
 (6, 6, '10.41.255.16', 'kv-dc1-ac-1', 1),
 (7, 3, '10.41.255.129', 'kv-dc2-cr-1', 2),
 (8, 1, '10.41.255.131', 'kv-dc2-fw-1', 2),
 (9, 2, '10.41.255.132', 'kv-dc2-rtr-1', 2),
 (10, 6, '10.41.255.133', 'kv-dc2-ac-1', 2);

 INSERT INTO serial_number(id, device_id, serial_number) VALUES
 (1, 1, 'FOC21172YWS'),
 (2, 1, 'FOC2118U0S5'),
 (3, 1, 'FOC21208V9C'),
 (4, 1, 'FOC2122L020'),
 (5, 2, 'JAD22020DGU'),
 (6, 3, 'FLM1905W08N'),
 (7, 4, 'CAT110550VS'),
 (8, 4, 'CAT1105NH0J'),
 (9, 5, 'FDO122003CQ'),
 (10, 5, 'FDO1220X0CP'),
 (11, 6, 'FCQ17240KHR'),
 (12, 6, 'FCQ1725X0RJ'),
 (13, 7, 'FOC211320EX'),
 (14, 7, 'FCW2118D0NR'),
 (15, 7, 'FOC21172YTE'),
 (16, 7, 'FCW2118F0NT'),
 (17, 8, 'JAD2152084L'),
 (18, 9, 'FDO2037A0BY'),
 (19, 10, 'FCQ17240KNU'),
 (20, 10, 'FCQ1725X0QV');

 INSERT INTO serial_number(id, device_id, serial_number) VALUES  (5, 2, 'JAD22020DGU');