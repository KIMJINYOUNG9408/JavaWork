package com.lec.sts15_mybatis.board.command;

import org.springframework.ui.Model;

import com.lec.sts15_mybatis.board.C;
import com.lec.sts15_mybatis.board.beans.IWriteDAO;

public class BCompanyListCommand implements BCommand {

	@Override
	public void execute(Model model) {
		IWriteDAO dao = C.sqlSession.getMapper(IWriteDAO.class);
		model.addAttribute("comlist", dao.selectCompany());
		
	}

}
