
 INSERT INTO location (id, country, city, street, build_number) VALUES (1, 'Country-1', 'City-1', 'Street-1', '1'),
                                                                (2, 'Country-2', 'City-2', 'Street-2', '2');

 INSERT INTO model (id, title, serial) VALUES (1, 'Title-1', 'Serial-1'),
                                               (2, 'Title-2', 'Serial-2'),
                                               (3, 'Title-3', 'Serial-3');

 INSERT INTO branch (id, title, location_id) VALUES (1, 'Branch-1', 1),
                                          (2, 'Branch-2', 2),
                                          (3, 'Branch-3', 2);

 INSERT INTO device (id, model_id, ip, hostname, branch_id) VALUES (1, 1, '10.41.255.132', 'kv-dc2-rtr-1', 1);
