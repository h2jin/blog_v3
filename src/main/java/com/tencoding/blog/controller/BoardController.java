package com.tencoding.blog.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tencoding.blog.model.Board;
import com.tencoding.blog.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	// 홈화면
	@GetMapping({ "", "/" })
	public String index(@PageableDefault(size = 2, sort = "id", direction = Direction.DESC) Pageable pageable,
			Model model) {
		Page<Board> boards = boardService.boardList(pageable);
		
		int nowPage = boards.getPageable().getPageNumber() +1;
		int startPage = Math.max(nowPage - 2, 1);
		int endPage = Math.min(nowPage + 2, boards.getTotalPages());
		
		ArrayList<Integer> pageNumbers = new ArrayList<>();
		
		for(int i = startPage; i <= endPage; i++) {
			pageNumbers.add(i);
		}
		
		model.addAttribute("pageable", boards);
		model.addAttribute("pageNumbers", pageNumbers);
		return "index";
	}

	// 상세페이지
	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id, Model model) {
		Board board = boardService.findById(id);
		board.setCount( board.getCount() + 1);
		model.addAttribute("board", board);
		return "/board/detail";
	}
	
	// 글 수정
	@GetMapping("/board/updateForm/{id}")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.findById(id));
		return "/board/update_form";
	}
	
	
}
