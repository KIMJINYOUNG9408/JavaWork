	package com.lec.sts15_mybatis.board.beans;

import java.sql.Timestamp;

public class BWriteDTO {
	private int uid;     // wr_uid
	private String subject;   //wr_subject
	private String content;   //wr_content
	
	
	private Timestamp regDate;   // wr_regdate
	private int viewCnt;   // wr_viewcnt
	private String category;
	private int u_uid;
	private int c_uid;
	private String writeName;
	private int rownum;
	private String companyName;
	
	
	
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public BWriteDTO() {
		super();
	}

	public BWriteDTO(int uid, String subject, String content, Timestamp regDate, int viewCnt, String category,
			int u_uid, int c_uid) {
		super();
		this.uid = uid;
		this.subject = subject;
		this.content = content;
		this.regDate = regDate;
		this.viewCnt = viewCnt;
		this.category = category;
		this.u_uid = u_uid;
		this.c_uid = c_uid;
	}

	public String getWriteName() {
		return writeName;
	}

	public void setWriteName(String writeName) {
	    String userid = writeName.substring(0, 3);
	    String userid2 = writeName.substring(3);
	    
	    
	    for(int i =0; i<userid2.length(); i++) {
	    	userid += "*";
	    }
	    
		this.writeName = userid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	public String getCategory() {
		return category;
	}

	public int getRownum() {
		return rownum;
	}

	public void setRownum(int rownum) {
		this.rownum = rownum;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getU_uid() {
		return u_uid;
	}

	public void setU_uid(int u_uid) {
		this.u_uid = u_uid;
	}

	public int getC_uid() {
		return c_uid;
	}

	public void setC_uid(int c_uid) {
		this.c_uid = c_uid;
	}
	
	
	
	
	
}
