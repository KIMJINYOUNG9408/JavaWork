package com.lec.sts15_mybatis.board.reply.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.sts15_mybatis.board.C;
import com.lec.sts15_mybatis.board.beans.IAjaxDAO;

public class WriteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int cnt = 0;
		IAjaxDAO dao = C.sqlSession.getMapper(IAjaxDAO.class);

		// ajax response 에 필요한 값들
		StringBuffer message = new StringBuffer();
		String status = "FAIL"; // 기본 FAIL

		String content = request.getParameter("content");
		String buid = request.getParameter("buid");
		String cuid = request.getParameter("cuid");
		
		int b_uid = Integer.parseInt(buid);
		int c_uid = Integer.parseInt(cuid);
		if (content == null || content.trim().length() == 0) {
			message.append("[유효하지 않은 parameter : 내용 필수]");
		} else {
			try {
				cnt = dao.insertReply(content, b_uid, c_uid);
				if(cnt == 0) {
					message.append("[트랙잭셕 실패: 0 insert");
				} else {
					status = "OK";
				}				
			} catch(Exception e) {
				//e.printStackTrace();
				message.append("[트랜잭션 에러:" + e.getMessage() + "]");
			}
			 
		}
			
		request.setAttribute("result", cnt);
		request.setAttribute("status", status);
		request.setAttribute("message", message.toString());

	}

}
