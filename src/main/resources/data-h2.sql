-- clear the db on restart
DELETE FROM POST;
DELETE FROM USER_;

-- insert two test users
INSERT INTO USER_ (id, email, PASSWORD) VALUES
  (1, 'user1', '$2a$10$bn7NeyxcBtVd8F/agL9LQ.x9N.pDsF4ESxbdJ.Dexy0lFnruIpKMG'), -- password is "pw1"
  (2, 'user2', '$2a$10$2/xgVjhXbilYWPl0Vag4KeSFgLVtiNtlQFU5ZL.6N8tjb2yKf0dTW'); -- password is "pw2"

-- insert two test posts
INSERT INTO POST (id, title, AUTHOR_ID) VALUES
  (1, 'post1', 1),
  (2, 'post2', 2);