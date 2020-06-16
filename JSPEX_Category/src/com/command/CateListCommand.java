package com.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lec.beans.AjaxWriteList;
import com.lec.beans.CategoryDAO;
import com.lec.beans.CategoryDTO;

public class CateListCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		CategoryDAO dao = new CategoryDAO();
		CategoryDTO [] arr = null;
		
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		String param;
		int parent = 1;
		int depth = 1;	
		
		
		param = request.getParameter("parent");
		if(param != null && param.trim().length() != 0) {
			try {				
				parent = Integer.parseInt(param);
			} catch(NumberFormatException e) {
				// 예외 처리 안함
			}
		}
		
		param = request.getParameter("depth");
		if(param != null && param.trim().length() != 0) {
			try {				
				depth = Integer.parseInt(param);
			} catch(NumberFormatException e) {
				// 예외 처리 안함
			}
		}
		
		try {
			dao = new CategoryDAO();
			arr = dao.select(parent, depth);
		}catch(SQLException e) {
			//e.printStackTrace();
			message.append("[트랜잭션 에러:" + e.getMessage()+ "]");
		} //
		
		request.setAttribute("list", arr);
	}

}
