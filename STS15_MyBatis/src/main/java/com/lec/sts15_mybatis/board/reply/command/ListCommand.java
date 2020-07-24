package com.lec.sts15_mybatis.board.reply.command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.sts15_mybatis.board.C;
import com.lec.sts15_mybatis.board.beans.IAjaxDAO;
import com.lec.sts15_mybatis.board.beans.ReplyWriteDTO;

public class ListCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
	
			IAjaxDAO dao = C.sqlSession.getMapper(IAjaxDAO.class);
			List<ReplyWriteDTO> arr = null;
			
			StringBuffer message = new StringBuffer();
			String status = "FAIL";
			
			String param;
			
			param = request.getParameter("uid");
			int uid = Integer.parseInt(param);
			
			try {
				
				arr = dao.select(uid);
				
				if(arr == null) {
					message.append("[리스트할 데이터가 없습니다]");
				} else {
					status = "OK";
				}
				
			} catch (Exception e) {
				message.append("[에러:" + e.getMessage() + "]");
			}
			
			request.setAttribute("status", status);
			request.setAttribute("message", message.toString());
			request.setAttribute("replyList", arr);
		
		
	}

}
