package com.jsfong.myblog.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsfong.myblog.entities.Blogpost;

public interface BlogRepository extends JpaRepository<Blogpost, Integer> {

}
