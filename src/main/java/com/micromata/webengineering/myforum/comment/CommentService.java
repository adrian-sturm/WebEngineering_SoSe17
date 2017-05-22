package com.micromata.webengineering.myforum.comment;

import com.micromata.webengineering.myforum.post.PostService;
import com.micromata.webengineering.myforum.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private static final Logger LOG = LoggerFactory.getLogger(PostService.class);

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserService userService;

    public Iterable<Comment> getCommentsForPost(Long post_id) {
        return commentRepository.findByPostId(post_id);
    }

    public void addCommentToPost(Comment comment) {
        comment.setAuthor(userService.getCurrentUser());
        LOG.info("Added new comment to post {}", comment.getPost());
        commentRepository.save(comment);
    }

    public void deleteComment(Long id) {
        Comment comment = commentRepository.findOne(id);
        if (comment == null) {
            LOG.error("No comment with id =  {} found!", id);
            return;
        }
        if (comment.getAuthor() != userService.getCurrentUser()) {
            LOG.error("User {} is not the owner of comment {}!", userService.getCurrentUser(), comment);
            return;
        }
        LOG.info("Comment with id = {} successfully deleted.", id);
    }

}
