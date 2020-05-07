// 변수의 유효범위
// scope

// const, let 의 유효범위
//  ==> Block Scope
//    { ... }


// 블럭
{
    const name = 'Mark'
    console.log(`name = ${name}`)
}

// console.log(`name = ${name}`) // 블럭 밖에서 사용하면 에러.

name = 'John' // Var와 동일한 효과가 나옴.
console.log(`name = ${name}`)

{
  //  console.log('name2 ' ,name) // 초기화 안되었다고 에러 !
    const name = 'Mark'
}

{
    console.log('value1 = ' , value1); // 에러는 아니다, undefined가 나올뿐
    var value1 = 200;

    //console.log('value2 = ', value2); // 에러 ! 선언된적 X
}

// Hoisting
// https://developer.mozilla.org/ko/docs/Glossary/Hoisting

// 이러한 현상을 hoisting 이라 하는데
// hoisting 현상은 함수에서만 발생하는게 아니다.

// hoisting 
// 아래에 있는 선언을(만) 끌어올린다.

// hoising 때문에 동작의 오류처럼 보이는 증상 겪게 됨

// hoising 현상은 처음부터 있었으나
// 용어 자체는 ES2015 및 그 이전에는 사용되지 않음

{
    console.log(`nick= ${nick}`); // 2 번이 동작

    nick = 'Mighty';   // 1 선언
    console.log(`nick = ${nick}`);   // 1 동작
    var nick = '아이언맨' //2 선언
    console.log(`nick = ${nick}`); 

}

age = 6;
age++;
console.log('1. age =',age)

{
        console.log(`2. age = ${age}`)
      
        age = 30;
        console.log(`3. age = ${age}`)

        // var age; // var age는 hoisting 발생 오류 X 
        // let age; // let 은 hoisting 이 발생안한다. 오류 O 
       
}



console.log('4. age =',age)