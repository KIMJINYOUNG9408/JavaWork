// 논리 연산자
// && AND
// || OR
// ! not 연산자


// 논리연산자를 이용한 조건문 실행
// Short Circuit Evaluation

// 표현식1 && 표현식2
//  표현식1 이 Falsy 이면 && 의 결과값은 표현식1
//  표현식1 이 Truthy 이면 && 의 결과값은 표현식2

// 표현식1 || 표현식2
//  표현식1 이 Falsy 이면 && 의 결과값은 표현식2
//  표현식1 이 Truthy 이면 && 의 결과값은 표현식1


console.log(true && true)
console.log(true && false)

let a = 100 
console.log(a > 10 || a + 10 < 10 )
console.log(a < 10 || a + 10 < 10 )

console.log(a > 10 && a + 10 < 10 )
console.log(a < 10 && a + 10 < 10 )

console.log("Hello" || "world") // 왼쪽이 맞으면 무조건 왼쪽 값이 나옴 
console.log(0 || 0)

console.log(0 || "Hello") // 왼쪽이 틀릴땐 무조건 오른쪽 값이 나옴

console.log(5 && 100)
console.log(0 && 100)
console.log(null && 'hello')
console.log(100 - 100 && [10 , 20 , 30])

let n = 15;
(n % 5 == 0) && console.log('5의 배수입니다.');
(n % 7 === 0) || console.log('5의 배수가 아닙니다.');