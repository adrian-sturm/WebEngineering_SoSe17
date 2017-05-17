-- clear the db on restart
DELETE FROM POST;
DELETE FROM USER_;

-- insert two test users
INSERT INTO USER_ (id, email, PASSWORD) VALUES
  (1, 'user1', '$2a$10$ippT5/cVx88ng1gEiMChNOt5HZE3MDkB.trvaXQDcS9zXJQhdv99q'),--password is "pw1"
  (2, 'user2', '$2a$10$JOWp3p0qLdv7X41DxFuhr.Q/o4RBvmny/1oD/n6hFH.xpYjCC2Y/W');--password is "pw2"

-- insert two test posts
INSERT INTO POST (id, title, AUTHOR_ID) VALUES
  (1, 'post1', 1),
  (2, 'post2', 2);