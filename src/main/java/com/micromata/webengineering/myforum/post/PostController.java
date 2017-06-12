package com.micromata.webengineering.myforum.post;

import com.micromata.webengineering.myforum.user.UserService;
import com.micromata.webengineering.myforum.util.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * HTTP endpoint for a post-related HTTP requests.
 */
@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<Post> getPostList() {
        return postService.getPosts();
    }

    @RequestMapping(value = "/{postId}", method = RequestMethod.GET)
    public Post getPostById(@PathVariable int postId) {
        return postService.getPostById(postId);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Object> addPost(@RequestBody Post post)
    {
        if (userService.isAnonymous()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if (post != null && post.getTitle().length() > Post.TITLE_LENGTH) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        postService.addPost(post);
        return ResponseEntity.ok(new PostResponse(post.getId()));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }

    private class PostResponse {

        private String url;

        public PostResponse(long postId) {
            url = addressService.getServerURL() +"api/post/"+ postId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

    }
}
