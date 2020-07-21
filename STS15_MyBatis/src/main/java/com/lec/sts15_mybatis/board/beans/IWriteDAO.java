package com.lec.sts15_mybatis.board.beans;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface IWriteDAO {
	public List<BWriteDTO> select();
//	public List<BWriteDTO> selectByCategory(String categoryName);
	public List<String> selectCompany();
	public int insert(final BWriteDTO dto);
//	public int insert(String subject, String content, String name);
//	public BWriteDTO readByUid(final int uid);
	
	public BWriteDTO selectByUid(final int uid);
	public int update(final BWriteDTO dto);
	public int update(int uid, @Param("a") BWriteDTO dto);
	public int deleteByUid(final int uid);
	
	public int countAll(); // 전체 개수
	public int countAllByCategory(String category); // 카테고리 개수
	public int countAllBySearch(String word); // 검색 개수
	
	
	public List<BWriteDTO> selectByPages(int from, int pageRows); // 전체뽑기
	public List<BWriteDTO> selectByCategory(String categoryName, int from, int pageRows); // 카테고리별뽑기
	public List<BWriteDTO> selectNotice(); // 공지뽑기
	public List<BWriteDTO> selectBySearch(String word, int from, int pageRows); // 검색 1
	public List<BWriteDTO> selectBySearch2(String word, int from, int pageRows); // 검색 2
	public List<BWriteDTO> selectBySearch3(String word, int from, int pageRows); // 검색 3
	public int incViewCnt(int uid); // 조회수 증가
	
	public BWriteDTO searchBySubject(String subject);
	
}
