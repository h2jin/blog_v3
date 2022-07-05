package com.tencoding.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tencoding.blog.auth.PrincipalDetail;
import com.tencoding.blog.dto.ResponseDto;
import com.tencoding.blog.model.Board;
import com.tencoding.blog.model.Reply;
import com.tencoding.blog.service.BoardService;

@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	
	
	// 글 저장
	@PostMapping("/api/board")
	public ResponseDto<Integer> saveBoard(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail detail) {
		// 서비스 요청
		boardService.saveBoard(board, detail.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	// 글 수정
	@PutMapping("/board/update/{id}")
	public ResponseDto<Integer> updateBoard(@PathVariable int id, @RequestBody Board board) {
		// 서비스에서 요청
		boardService.updateBoard(id, board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	// 글 삭제
	@DeleteMapping("/board/delete/{id}")
	public ResponseDto<Integer> deleteBoard(@PathVariable int id) {
		boardService.deleteBoard(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	// 댓글 쓰기
	@PostMapping("/board/{boardId}/reply")
	public ResponseDto<Reply> replyList(@PathVariable int boardId, @RequestBody Reply reply, @AuthenticationPrincipal PrincipalDetail principalDetail ) {
		// 서비스에 요청
		Reply replyEntity = boardService.saveReply(principalDetail.getUser(), boardId, reply);
		return new ResponseDto<Reply>(HttpStatus.OK.value(), replyEntity);
	}
	

}
