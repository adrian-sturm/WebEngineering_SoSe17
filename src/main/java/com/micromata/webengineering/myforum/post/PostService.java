package com.micromata.webengineering.myforum.post;

import com.micromata.webengineering.myforum.user.User;
import com.micromata.webengineering.myforum.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Handle all CRUD operations for posts.
 */
@Service
public class PostService {
    private static final Logger LOG = LoggerFactory.getLogger(PostService.class);

    @Autowired
    private PostRepository repository;

    @Autowired
    private UserService userService;

    /**
     * Retrieve the list of all posts.
     *
     * @return post list
     */
    public Iterable<Post> getPosts() {
        LOG.info("Retrieved all posts");
        return repository.findAll();
    }

    /**
     * Retrive a single Post specified by its id
     * @param id the id of the requested Post
     * @return the Post wih the specified id or null if the id is not in use
     */
    public Post getPostById(long id) {
        LOG.info("Retrieved post with id {}", id);
        return repository.findOne(id);
    }

    /**
     * Add a new post.
     *
     * @param post the post to add.
     */
    public void addPost(Post post) {
        // set the currently logged in user as the author of the post
        post.setAuthor(userService.getCurrentUser());
        LOG.info("New post added: {}", post);
        repository.save(post);
    }

    /**
     * Deletes a Post from the list
     * @param id the id of the Post to delete
     */
    public void deletePost(long id) {
        Post post = repository.findOne(id);
        if (post == null) {
            LOG.error("No post found with id = "+ id);
            return;
        }
        if (!post.getAuthor().equals(userService.getCurrentUser())) {
            LOG.error("User {} is not the owner of post with id = {} !", userService.getCurrentUser(), id);
            return;
        }
        LOG.info("Post with id {} successfully deleted.", id);
        repository.delete(id);
    }
}
