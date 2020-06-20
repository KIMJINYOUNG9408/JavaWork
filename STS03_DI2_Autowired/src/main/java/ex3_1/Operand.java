package ex3_1;

public class Operand {
	int value;
	
	public Operand() {
		
	}
	
	

	public Operand(int value) {
		super();
		this.value = value;
	}



	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		
		return String.format("[operand %d]", value);
	}
	
	

}
