package com.micromata.webengineering.myforum.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Handle all CRUD operations for posts.
 */
@Service
public class PostService {
    @Autowired
    private PostRepository repository;

    /**
     * Retrieve the list of all posts.
     *
     * @return post list
     */
    public Iterable<Post> getPosts() {
        return repository.findAll();
    }

    /**
     * Retrive a single Post specified by its id
     * @param id the id of the requested Post
     * @return the Post wih the specified id or null if the id is not in use
     */
    public Post getPostById(long id) {
        return repository.findOne(id);
    }

    /**
     * Add a new post.
     *
     * @param post the post to add.
     * @return the id of the newly added post
     */
    public long addPost(Post post) {
        repository.save(post);
        return post.getId();
    }

    /**
     * Deletes a Post from the list
     * @param id the id of the Post to delete
     */
    public void deletePost(long id) {
        repository.delete(id);
    }
}
