-- clear the db on restart
DELETE FROM POST;
DELETE FROM USER_;

-- insert two test users
INSERT INTO USER_ (id, email, PASSWORD) VALUES
  (1, 'user1', 'pw1'),
  (2, 'user2', 'pw2');

-- insert two test posts
INSERT INTO POST (id, title, AUTHOR_ID) VALUES
  (1, 'post1', 1),
  (2, 'post2', 2);