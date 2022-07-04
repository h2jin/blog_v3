package com.tencoding.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.blog.model.Board;
import com.tencoding.blog.model.Reply;
import com.tencoding.blog.model.User;
import com.tencoding.blog.repository.BoardRepository;
import com.tencoding.blog.repository.ReplyRepository;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
	// 글 저장
	@Transactional
	public void saveBoard(Board board, User user) {
		board.setUserId(user);
		boardRepository.save(board);
	}
	
	// 홈 화면 글 리스트
	@Transactional
	public Page<Board> boardList(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}
	
	// 상세페이지
	@Transactional
	public Board findById(int id) {
		Board board = boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException();
		});
		return board;
	}
	
	// 글 수정
	@Transactional
	public void updateBoard(int id, Board board) {
		Board boardEntity = boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException();
		});
		boardEntity.setTitle(board.getTitle());
		boardEntity.setContent(board.getContent());
	}
	
	// 글 삭제
	@Transactional
	public void deleteBoard(int id) {
		boardRepository.deleteById(id);
	}
	
	@Transactional
	public Page<Board> searchBoardByTitle(String title, Pageable pageable) {
		return boardRepository.findByTitleContaining(title, pageable);
	}
	
	// 댓글 저장
	public Reply saveReply(User user, int boardId, Reply requestReply) {
		Board board = boardRepository.findById(boardId).orElseThrow(() -> {
			return new IllegalArgumentException("해당 게시글이 존재하지 않습니다.");
		});
		
		requestReply.setBoardId(board);
		requestReply.setUserId(user);
		return replyRepository.save(requestReply);
	}
	
	// 댓글 목록 가져오기
	

}
