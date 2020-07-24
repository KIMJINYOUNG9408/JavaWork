package com.lec.sts15_mybatis.board.beans;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReplyWriteDTO {
	
	@JsonProperty("uid")
	private int ruid;
	
	@JsonProperty("content")
	private String rcontent;
	
	@JsonProperty("regdate")
	private Timestamp rregDate;
	
	@JsonProperty("b_uid")
	private int rb_uid;	
	
	@JsonProperty("u_uid")
	private int ru_uid;

	public int getRuid() {
		return ruid;
	}

	
	public ReplyWriteDTO() {
		super();
	}
	
	public ReplyWriteDTO(int ruid, String rcontent, Timestamp rregDate, int rb_uid, int ru_uid) {
		super();
		this.ruid = ruid;
		this.rcontent = rcontent;
		this.rregDate = rregDate;
		this.rb_uid = rb_uid;
		this.ru_uid = ru_uid;
	}



	public void setRuid(int ruid) {
		this.ruid = ruid;
	}

	public String getRcontent() {
		return rcontent;
	}

	public void setRcontent(String rcontent) {
		this.rcontent = rcontent;
	}

	public String getRregDate() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rregDate);
	}

	public void setRregDate(Timestamp rregDate) {
		this.rregDate = rregDate;
	}

	public int getRb_uid() {
		return rb_uid;
	}

	public void setRb_uid(int rb_uid) {
		this.rb_uid = rb_uid;
	}

	public int getRu_uid() {
		return ru_uid;
	}

	public void setRu_uid(int ru_uid) {
		this.ru_uid = ru_uid;
	}
	
	
	

}
