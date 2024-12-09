package com.example.postsservice.web.repository;

import com.example.postsservice.web.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
