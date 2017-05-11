package com.micromata.webengineering.myforum.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * HTTP endpoint for a post-related HTTP requests.
 */
@RestController
public class PostController {
    @Autowired
    private PostService postService;

    @RequestMapping(value = "/post", method = RequestMethod.GET)
    public Iterable<Post> getPostList() {
        return postService.getPosts();
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public Post getPostById(@PathVariable int id) {
        return postService.getPostById(id);
    }

    @RequestMapping(value = "/post/add", method = RequestMethod.POST)
    public PostResponse addPost(@RequestParam("title") String title)
    {
        return new PostResponse(postService.addPost(new Post(title)));
    }

    @RequestMapping(value = "/post/delete/{id}", method = RequestMethod.DELETE)
    public void deletePost(@PathVariable int id) {
        postService.deletePost(id);
    }

    private class PostResponse {
        private static final String BASE_URL = "http://localhost:8080/post/";

        private String url;

        public PostResponse(long postId) {
            url = BASE_URL + postId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

    }
}
