package cal;

public class Calculator {
	private int memory = 0; // 마지막 값을 가진다
	private int num2 = 0; // 2번째 피연산자를 가진다.
	private int operator = 0; // 연산자
	private int status = 0; // 상태
	int i, j = 0;

	public Calculator() { // 기본생성자 (안만들어도 되는데 그냥 만듬).

	}

	public void run(String inputString) { // inputString을 통하여 입력
		// run 메소드는 inputString을 통해 입력 데이터가 제대로 되는지 확인하기 위해
		int check = checkOperator(inputString); // inputString을통해 ch eck
		switch (check) {
		case 99:
			calculate(); // 연산실행
			initField(); // 필드초기화
			System.out.println("Result=" + memory); // 결과출력
			break;

		case -1: // -1일떄는

			setData(inputString); // setData사용 피연산자를 필드에 저장
			break;

		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
			if (status == 3) { // 상태가 3일때는
				calculate(); // 계산을수행
			}
			setOperator(check); // 연산자를 필드에저장
			break;
		}
		printStatus(); // run 에서 출력해줌
	}

	public void initField() { // 필드를 초기화한다 0으로 메모리 빼고
		operator = 0;
		num2 = 0;
		status = 0;
	}

	public void calculate() { // operator에 따라 연산 출력

		switch (operator) {
		case 1:

			add();
			break;

		case 2:
			sub();
			break;
		case 3:
			mul();
			break;
		case 4:

			div();
			break;

		case 5:
			mod();
			break;

		case 6:

			pow();
			break;
		}
	}

	public void printStatus() { // print문을 사용해서 출력해준다.

		System.out.print("status[" + status + "] memory[" + memory + "] operator[" + operator + "] num2[" + num2 + "]");
	}

	public int checkOperator(String op) { // 1~6을 반환해준다
		switch (op) {
		case "+":
			return 1;

		case "-":
			return 2;

		case "*":
			return 3;

		case "/":
			return 4;

		case "%":
			return 5;

		case "^":
			return 6;

		case "=": // =일경우 99
			return 99;

		default: // 지정된연산자 x
			return -1;
		}
	}

	public void setData(String data) {// data로 전달된 값을 정수료 변환하여 memory나 num2에 저장
		if (status == 0) {
			memory = Integer.parseInt(data); // 정수로 변환해서 memory에 저장
			status = 1; // status를 1로 바꿔줌
		} else if (status == 2) { // status가 2일때
			num2 = Integer.parseInt(data); // 정수로 변환해서 num2에 저장
			status = 3; // status를 3으로 바꿔줌
		}
	}

	public int add() {
		memory = memory + num2;
		return memory; // memory와 num2를 더한값을 memory에
	}

	public int sub() {
		memory = memory - num2;
		return memory; // memory와 num2를 -을 memory에
	}

	public int mul() {
		memory = memory * num2;
		return memory; // memory와 num2를 *을 memory에
	}

	public int div() {
		if (num2 == 0) { // num2가 0이면 0이니까 에러
			System.out.println("에러입니다. 값은 0으로 돌려드립니다.");
			memory = 0; // 0 값을 반환해준다.
			return memory;
		}
		memory = memory / num2;
		return memory; // memory와 num2를 나눈값을 memory에
	}

	public int mod() {
		memory = memory % num2;
		return memory; // memory와 num2의 나머지값을 memory에
	}

	public int pow() {

		for (i = 1; i < num2; i++) {
			if (i == 1) {
				j = memory;
			}
			memory *= j;
		}

		return memory;
	}

	public int getMemory() {
		return memory;
	}

	public void setMemory(int memory) {
		this.memory = memory;
	}

	public int getNum2() {
		return num2;
	}

	public void setNum2(int num2) {
		this.num2 = num2;
	}

	public int getOperator() {
		return operator;
	}

	public void setOperator(int operator) {
		this.status = 2;
		this.operator = operator;
	}

	public int getStatus() {
		return status;
	}

	public void status(int status) {
		this.status = status;
	}
}
