package com.lec.sts15_mybatis.board.command;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.lec.sts15_mybatis.board.C;

import com.lec.sts15_mybatis.board.beans.BWriteDTO;
import com.lec.sts15_mybatis.board.beans.IWriteDAO;

public class BListCommand implements BCommand {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		System.out.println("야");
		String category = (String) model.getAttribute("category");
		System.out.println("카테고리: " + category);
		String categoryName = "";
		
		int categoryNum;
		int cnt = 0;

//		BWriteDAO dao = new BWriteDAO();
//		List<BWriteDTO> list = dao.select();
//		model.addAttribute("list", list);

		int curPage = 1; // 현재 페이지 (디폴트 1 page)

		// 현재 몇 페이지인지 parameter 받아오기 + 검증
		String pageParam = (String) model.getAttribute("page");
		System.out.println("전: getAttribute page:" + pageParam);
		
		if (pageParam == null) {
			pageParam = "1";
		}
		System.out.println("중간: getAttribute page:" + pageParam);

		if (pageParam != null && !pageParam.trim().equals("")) {
			try {
				// 1이상의 자연수 이어야 한다
				int p = Integer.parseInt(pageParam);
				if (p > 0)
					curPage = p;
				model.addAttribute("page",curPage);
				
			} catch (NumberFormatException e) {
				// page parameter 오류는 별도의 exception 처리 안함
			}
		} // end if
		int fromRow = (curPage - 1) * 10 + 1;
        System.out.println(fromRow);
		String param =  (String) model.getAttribute("col");
		String param1 =  (String) model.getAttribute("word");
		System.out.println("전: getAttribute col:" + param);
		System.out.println("전: getAttribute word:" + param1);
		if (category == null) {
			categoryName = "";
		} else {
			categoryNum = Integer.parseInt(category);
			switch (categoryNum) {
			case 1:
				categoryName = "전체";
				break;
			case 2:
				categoryName = "기업후기";
				break;
			case 3:
				categoryName = "면접후기";
				break;
			case 4:
				categoryName = "정보공유";
				break;

			}
		}
		System.out.println(categoryName);

		if (categoryName.equals("전체") || categoryName.equals("")) {
			
			IWriteDAO dao = C.sqlSession.getMapper(IWriteDAO.class);
			cnt = dao.countAll();
			int totalPage = (int)Math.ceil(cnt / (double)10);
			System.out.println(cnt);
			model.addAttribute("list",  dao.selectByPages(fromRow, 10));
			model.addAttribute("cnt",totalPage);
			}
		 else {
			IWriteDAO dao = C.sqlSession.getMapper(IWriteDAO.class);
			cnt = dao.countAllByCategory(categoryName);
			int totalPage = (int)Math.ceil(cnt / (double)10);
			
			model.addAttribute("list", dao.selectByCategory(categoryName, fromRow, 10));
			model.addAttribute("cnt",totalPage);
		}  
		
		
		if((param != null && param.trim().length() > 0) && (param1 != null && param1.trim().length() > 0)) {
			String col = param;
			String word = param1;
			IWriteDAO dao = C.sqlSession.getMapper(IWriteDAO.class);
			
			if(col.equals("none")) {
				
				cnt = dao.countAllBySearch(word);				
				int totalPage = (int)Math.ceil(cnt / (double)10);
				model.addAttribute("list",dao.selectBySearch(word, fromRow, 10));
				model.addAttribute("cnt",totalPage);
			} else if(col.equals("subject")) {
				
				cnt = dao.countAllBySearch2(word);				
				int totalPage = (int)Math.ceil(cnt / (double)10);
				model.addAttribute("list",dao.selectBySearch2(word, fromRow, 10));
				model.addAttribute("cnt",totalPage);
				
			
			} else if(col.equals("company")) {
				cnt = dao.countAllBySearch3(word);				
				int totalPage = (int)Math.ceil(cnt / (double)10);
				model.addAttribute("list",dao.selectBySearch3(word, fromRow, 10));
				model.addAttribute("cnt",totalPage);
				
			}
		} 
			
			
			IWriteDAO dao = C.sqlSession.getMapper(IWriteDAO.class);
		 	model.addAttribute("notice", dao.selectNotice());

	}

}
