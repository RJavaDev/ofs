-- create default user
INSERT INTO ofs_user(status, created_date, firstname,lastname, password, role_list, username)
VALUES
      ('CREATED', now(), 'admin','admin','$2a$10$U2vm/KOlGwVaqXW551e.zOlaAgrJ/PflMDlxNjBEapDvl2m4fljYW','{ADMIN}', 'admin')
