package com.lec.sts15_mybatis.board.reply.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.sts15_mybatis.board.C;
import com.lec.sts15_mybatis.board.beans.IAjaxDAO;

public class UpdateCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int cnt = 0;
		IAjaxDAO dao = C.sqlSession.getMapper(IAjaxDAO.class);
		
		StringBuffer message = new StringBuffer();
		String status = "FAIL";   // 기본 FAIL
				
		String param = request.getParameter("uid");
		String content = request.getParameter("content");
		
		if(param == null) {
			message.append("[유효하지 않은 parameter 0 or null]");
		} else if (content == null || content.trim().length() == 0) {
			message.append("[유효하지 않은 parameter : 내용 필수]");
		} else {
			try {		
				int uid = Integer.parseInt(param);
				
				cnt = dao.update(uid,content);
				status = "OK";
				
				if(cnt == 0) {
					message.append("[0 update]");
				}				
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
				message.append("[유효하지 않은 parameter] " + param);
			} catch (Exception e) {
				e.printStackTrace();
				message.append("[트랜잭션 에러:" + e.getMessage() + "]");
			}
			
			request.setAttribute("result", cnt);
			request.setAttribute("status", status);
			request.setAttribute("message", message.toString());

		} // end if
	}

}
