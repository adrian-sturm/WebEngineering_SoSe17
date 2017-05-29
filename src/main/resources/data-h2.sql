-- clear the db on restart
DELETE FROM POST_COMMENTS;
DELETE FROM COMMENT;
DELETE FROM POST;
DELETE FROM USER_;

-- insert two test users
INSERT INTO USER_ (id, email, PASSWORD) VALUES
  (1, 'user1', '$2a$10$bn7NeyxcBtVd8F/agL9LQ.x9N.pDsF4ESxbdJ.Dexy0lFnruIpKMG'), -- password is "pw1"
  (2, 'user2', '$2a$10$2/xgVjhXbilYWPl0Vag4KeSFgLVtiNtlQFU5ZL.6N8tjb2yKf0dTW'); -- password is "pw2"

-- insert two test posts
INSERT INTO POST (id, title, Timestamp, AUTHOR_ID) VALUES
  (1, 'post1', CURRENT_TIMESTAMP , 1),
  (2, 'post2', CURRENT_TIMESTAMP, 2);

-- insert three test comments
INSERT INTO COMMENT (id, text, Timestamp, author_id) VALUES
  (1, 'comment1', CURRENT_TIMESTAMP, 1),
  (2, 'comment1', CURRENT_TIMESTAMP, 1),
  (3, 'comment2', CURRENT_TIMESTAMP, 2);

-- link the comments and posts
INSERT INTO POST_COMMENTS (post_id, comments_id) VALUES
  (1, 1),
  (1, 3),
  (2, 2);