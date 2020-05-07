// if ~ else 
// if ~ else if ~ else ... 
// 자바, C 언어와 구조 동일

a = 100

if (a + 1 > 100) {
    // console.log('if 안의 a 값은', a) // var 였으면 가능하지만 불가능.
    let a = 10;
    console.log('if 안의 a 값은', a) // 10
}

// 조건식에서 참 거짓 판정시 !

// Falsy 로 평가될때!
// false, 0, '', null, undefine, NaN   <-- 'Falsy 한 값'이라 한다

// Truthy 로 평가될때
// true, 37, 'Mark', {}, []   <-- 'Truthy 한 값' 이라 한다 {} -> object [] -> object ( 배열 )

function print(data) {
    if (data) { // 참 ( Truthy ) 거짓 ( Falsy 판정 )
        console.log(data, typeof data, '--Truthy');
    } else {
        console.log(data, typeof data, '--Falsy');
    }
}


print(true)
print(false)
print(37)
print(0)
print('Mark')
print('')
print(null)
print({})
print({'name' : 'John' , 'age': 32})
print([])
print(undefined)
print(NaN)
print(100 / 0)
console.log(100 / 0)
print() // 호출자 전달인자 없으면 undefined 값으로 동작함

console.log()
// Fansy 값에 ! 결과 => true ( boolean )
console.log(!undefined)
console.log(!null)
console.log(!0)
console.log(!"")
console.log(!NaN)

// Truthy 값에 ! 결과 => false (boolean)
console.log(!3)
console.log(!"Hello")
console.log(!" ")
console.log(![])
console.log(!{})
console.log(![10, 20, 30])


// Truthy ,Falsy 판경결과 -0- -- > boolean
// 1. 삼항연산자 사용
let value = {'a' : 100}
let isTrue = value ? true : false;

console.log('isTrue =', isTrue, typeof isTrue)

// 2. !! 사용하면 가능!
isTrue = !!value;
console.log('isTrue =', isTrue, typeof isTrue)

console.log() // 줄바꿈



function printName(person){
    console.log('이름은', person.name)
}

let myBrother = { name : "John"}

printName(myBrother)
// printName()  // 에러다 undefined 값이 person 에 넘어감.


// 함수 정의할때 매개변수 체크 필요
function printName(person){
    // if(person === undefined || person === null) return;
    if(!person) return;
    console.log('이름은', person.name)
}

printName()
printName(myBrother)