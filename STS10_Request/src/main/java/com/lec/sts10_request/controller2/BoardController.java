package com.lec.sts10_request.controller2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
	
	@RequestMapping(value = "/write")
	public String writeBoard() {
		return "board/write";
	}
	
	@RequestMapping(value = "/list")
	public String listBoard() {
		return "board/list";
	}
	
	@RequestMapping(value = "/view")
	public String viewBoard() {
		return "board/view";
	}
	
	@RequestMapping(value = "/update")
	public String updateBoard() {
		return "board/update";
	}
	
	@RequestMapping(value = "/delete")
	public String deleteBoard() {
		return "board/delete";
	}
	

}
