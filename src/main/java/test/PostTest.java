package test;

import com.micromata.webengineering.myforum.post.Post;
import com.micromata.webengineering.myforum.post.PostService;
import org.junit.Test;

import java.util.List;

public class PostTest{

    @Test
    public void testGetPosts() {
        PostService postService = new PostService();
        List<Post> posts = (List<Post>) postService.getPosts();

        assert !posts.isEmpty();
    }
}
