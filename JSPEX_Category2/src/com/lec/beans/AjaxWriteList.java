package com.lec.beans;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AjaxWriteList extends AjaxWriteResult{
	
	@JsonProperty("data")
	private List<CategoryDTO> list;
	
	
	public AjaxWriteList(List<CategoryDTO> list) {
		super();
		this.list = list;
	}

	public AjaxWriteList() {
		super();
	}
	
	public List<CategoryDTO> getList() {
		return list;
	}

	public void setList(List<CategoryDTO> list) {
		this.list = list;
	}

}
