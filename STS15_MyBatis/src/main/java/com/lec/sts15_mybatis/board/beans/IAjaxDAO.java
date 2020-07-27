package com.lec.sts15_mybatis.board.beans;

import java.util.List;

import org.apache.ibatis.annotations.Param;
public interface IAjaxDAO {
	
	public List<ReplyWriteDTO> select(int uid);
	public int insertReply(String content, int buid, int cuid); 
	public int deleteByUid(int uid);
	public int update(
			@Param("uid") int uid,			
			@Param("content") String content);
}
