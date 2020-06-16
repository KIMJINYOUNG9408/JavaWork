package com.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.beans.CategoryDAO;
import com.lec.beans.CategoryDTO;

public class CateListCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		CategoryDAO dao = new CategoryDAO();
		CategoryDTO[] arr = null;

		StringBuffer message = new StringBuffer();
		String status = "FAIL";

		String param;
		int parent;
		int depth;

		param = request.getParameter("depth");
		if (param == null || param.trim().length() == 0) {
			depth = 1;
		} else {
			depth = Integer.parseInt(param);
		}
		if (depth == 1) {
			parent = 0;
		}
		param = request.getParameter("parent");

		if (param == null || param.trim().length() == 0) {
			parent = 0;
		} else {
			parent = Integer.parseInt(param);
		}

		if (depth >= 2 && parent == 0) {
			message.append("0개의 데이터");
		} else {
			try {
				arr = dao.Select(depth, parent);

				if (arr == null) {
					message.append("0개의 데이터");
				} else {
					status = "OK";
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		request.setAttribute("status", status);
		request.setAttribute("message", message.toString());
		request.setAttribute("list", arr);
	}

}
