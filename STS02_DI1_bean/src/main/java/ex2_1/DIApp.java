package ex2_1;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;



public class DIApp implements OperatorBean {
	
	@Override
	public int doOperate() {
		
		return 0;
	}

	
	
	public static void main(String[] args) {
	System.out.println("Main 시작");
		
		AbstractApplicationContext ctx = 
				new GenericXmlApplicationContext("classpath:applicationContext.xml");
		System.out.println("--컨테이너 생성--");
		
		
		OperatorBean operator = null;
		operator = ctx.getBean("Op", OperatorBean.class);
		
		int value = operator.doOperate();
		
		System.out.println("결과:" + value);
		
		operator = ctx.getBean("Op2", OperatorBean.class);
		
		value = operator.doOperate();
		
		System.out.println("결과:" + value);
		
		System.out.println("Main 종료");
	
	
	}	

}
