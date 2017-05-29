package com.micromata.webengineering.myforum.comment;

import com.micromata.webengineering.myforum.post.PostService;
import com.micromata.webengineering.myforum.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CommentService {
    private static final Logger LOG = LoggerFactory.getLogger(PostService.class);

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserService userService;

    @Autowired PostService postService;

    /**
     * Add a comment to an existing post.
     *
     * @param postId id of a post
     * @param text   text of the comment
     * @return id of the corresponding comment
     */
    @Transactional
    public Long addCommentToPost(Long postId, String text) {
        // Persist comment.
        Comment comment = new Comment();
        comment.setText(text);
        comment.setAuthor(userService.getCurrentUser());
        commentRepository.save(comment);

        // Append technically to post.
        postService.addComment(postId, comment);

        return comment.getId();
    }

    public void deleteComment(Long id) {
        Comment comment = commentRepository.findOne(id);
        if (comment == null) {
            LOG.error("No comment with id =  {} found!", id);
            return;
        }
        if (!comment.getAuthor().equals(userService.getCurrentUser())) {
            LOG.error("User {} is not the owner of comment {}!", userService.getCurrentUser(), comment);
            return;
        }
        LOG.info("Comment with id = {} successfully deleted.", id);
        // TODO: update post with this comment
    }

}
