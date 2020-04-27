package com.lec.java.inherit07;
// 단축키 alt shift s + v
public class BusinessPerson extends Person {
	
	private String company;
   
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
	// 메소드 재정의 ( Overriding )
    public void showInfo() {
    	super.showInfo();
    	System.out.println("회사: " + company);
    }
    // 메소드 오버로딩
    public void showInfo(int id) {
    	System.out.println("id: " + id);
        showInfo(); 
    }
    
    // final 메소드 오버라이딩 불가.
    /*   @Override
    public void whoAreYou() { // 부모쪽에서 whoAreYou()를 final로 해서 오버라이딩 불가
    	
    	super.whoAreYou();
    }
 */   
    @Override
    public String toString() { // toString 오버라이딩 하기.
    	
    	return "businessPerson: " + getName() + " - " + company;
    }
    

}
