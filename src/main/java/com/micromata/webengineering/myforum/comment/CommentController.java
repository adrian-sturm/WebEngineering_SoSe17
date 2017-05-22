package com.micromata.webengineering.myforum.comment;

import com.micromata.webengineering.myforum.post.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    private static final Logger LOG = LoggerFactory.getLogger(PostService.class);

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<Comment> getCommentsForPost(Long post_id) {
        return commentService.getCommentsForPost(post_id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Object> addComment(@RequestBody Comment comment)
    {
        if (comment != null && comment.getText().length() > Comment.TEXT_LENGTH) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (comment.getPost() == null) {
            LOG.error("The comment has no post!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        commentService.addCommentToPost(comment);
        return ResponseEntity.ok(true);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}
