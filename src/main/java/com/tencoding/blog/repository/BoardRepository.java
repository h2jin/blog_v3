package com.tencoding.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tencoding.blog.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{
	
	Page<Board> findByTitleContaining(String title, Pageable pageable);
	

}
