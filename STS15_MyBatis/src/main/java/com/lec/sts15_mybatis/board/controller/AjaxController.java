package com.lec.sts15_mybatis.board.controller;

import java.util.List;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lec.sts15_mybatis.board.beans.AjaxWriteList;
import com.lec.sts15_mybatis.board.beans.AjaxWriteResult;
import com.lec.sts15_mybatis.board.beans.ReplyWriteDTO;
import com.lec.sts15_mybatis.board.reply.command.DeleteCommand;
import com.lec.sts15_mybatis.board.reply.command.ListCommand;
import com.lec.sts15_mybatis.board.reply.command.WriteCommand;

@RestController
@RequestMapping("/board/*.ajax")
public class AjaxController {
	
	@RequestMapping("/board/list.ajax")
	public AjaxWriteList list(HttpServletRequest request, HttpServletResponse response) {
		new ListCommand().execute(request, response);
		return buildList(request);
	}
	
	@RequestMapping(value = "/board/writeOk.ajax", method=RequestMethod.POST)
	public AjaxWriteResult writeOk(HttpServletRequest request, HttpServletResponse response) {
		new WriteCommand().execute(request, response);
		return buildResult(request);
	}
	
	
	@RequestMapping(value= "/board/deleteOk.ajax", method=RequestMethod.POST)
	public AjaxWriteResult deleteOk(HttpServletRequest request, HttpServletResponse response) {
		new DeleteCommand().execute(request, response);
		return buildResult(request);
	}
	
	
	public AjaxWriteResult buildResult(HttpServletRequest request) {
		AjaxWriteResult result = new AjaxWriteResult();
		
		result.setStatus((String)request.getAttribute("status"));
		result.setMessage((String)request.getAttribute("message"));
		result.setCount((Integer)request.getAttribute("result"));
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public AjaxWriteList buildList(HttpServletRequest request) {
		List<ReplyWriteDTO> list = (List<ReplyWriteDTO>)request.getAttribute("replyList");
		
		AjaxWriteList result = new AjaxWriteList();
		result.setStatus((String)request.getAttribute("status"));
		result.setMessage((String)request.getAttribute("message"));
		
		if(list != null) {			
			result.setCount(list.size());
			result.setList(list);
		}
		
		return result;
	}
	

}
