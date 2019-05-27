
 INSERT INTO location (id, country, city, street, build_number) VALUES (1, 'Country-1', 'City-1', 'Street-1', '1'),
                                                                (2, 'Country-2', 'City-2', 'Street-2', '2');

 INSERT INTO model (id, title, serial) VALUES (1, 'Title-1', 'Serial-1'),
                                               (2, 'Title-2', 'Serial-2'),
                                               (3, 'Title-3', 'Serial-3');

 INSERT INTO branch (id, title, location_id) VALUES (1, 'Branch-1', 1),
                                          (2, 'Branch-2', 2),
                                          (3, 'Branch-3', 2);

 INSERT INTO device (id, model_id, ip, hostname, branch_id) VALUES (1, 1, 'ip-1', 'hostname-1', 1),
                                              (2, 2, 'ip-2', 'hostname-2', 2),
                                              (3, 3, 'ip-3', 'hostname-3', 3),
                                              (4, 2, 'ip-4', 'hostname-4', 1);

--  INSERT INTO branch_devices (branch_id, devices_id) VALUES  (1,1), (2,2), (2,3), (1,4);

--  INSERT INTO model_devices (model_id, devices_id) VALUES (1,1), (2,2), (2,3), (1,4);