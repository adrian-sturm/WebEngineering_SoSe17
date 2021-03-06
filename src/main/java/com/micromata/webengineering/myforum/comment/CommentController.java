package com.micromata.webengineering.myforum.comment;

import com.micromata.webengineering.myforum.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post/{postId}/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Object> addComment(@PathVariable Long postId, @RequestBody Comment comment)
    {
        if (userService.isAnonymous()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if (comment != null && comment.getText().length() > Comment.TEXT_LENGTH) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        commentService.addCommentToPost(postId, comment.getText());
        return ResponseEntity.ok(true);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<Comment> getCommentList(@PathVariable Long postId) {
        return commentService.getCommentsForPost(postId);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}
