package com.micromata.webengineering.myforum.comment;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c WHERE c.post = :post_id")
    Iterable<Comment> findByPostId(@Param("post_id") Long post_id);

}
