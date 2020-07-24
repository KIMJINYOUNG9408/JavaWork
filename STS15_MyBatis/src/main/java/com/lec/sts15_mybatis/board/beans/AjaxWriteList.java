package com.lec.sts15_mybatis.board.beans;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AjaxWriteList extends AjaxWriteResult {
	
	@JsonProperty("data")
	private List<ReplyWriteDTO> list;

	public List<ReplyWriteDTO> getList() {
		return list;
	}

	public void setList(List<ReplyWriteDTO> list) {
		this.list = list;
	}

}
