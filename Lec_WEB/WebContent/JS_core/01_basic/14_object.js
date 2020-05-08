console.log('object');

// property : value 쌍 
x = {firstName: 'John', lastName: "Doe"};
console.log(x, typeof x )
console.log(x['firstName'], x.firstName);
// console.log(x[firstname]) // X 

console.log(x.length); // object 는 length 값 없다

// for ~ in 사용
for(key in x){
    console.log(`x[${key}] = ${x[key]}`)
}

x = {
    name : "kim"
    , age : 34
    , height : 172.3
    , score : [94, 35, 79]
    , sayHello : function(){
        console.log('안녕하세요')
    }
    , getScore : function(n){
        return this.score[n]; // this 를 빼면 에러가난다. 동일 object의 property 접근할때 this 사용 !
    }
    ,getTotal : function(){
        var total=0;
        for(i = 0 ; i <this.score.length; i++){
           total += this.score[i];
        }
        return total;
    }
    ,getAvg : function(){       
        var avg =  this.getTotal() / this.score.length;
        return avg.toFixed(2)
        
    }
}

console.log(x['name'], typeof x['name']);
console.log(x['age'], typeof x['age']);
console.log(x['height'], typeof x['height']);
console.log(x['firstName'], typeof x['firstName']);
console.log(x.score, x.score.length, typeof x['score']);
console.log(x.sayHello, typeof x['sayHello']);
x.sayHello()
console.log(x.getScore(0));
console.log(x.getTotal()); // score 점수 합계
console.log(x.getAvg()); 
console.log(x['getAvg']());
console.log()

x = {firstName: 'John', lastName: "Doe"};
console.log(x);
x.firstName = "Mike";
console.log(x);

x['age'] = 45; 
console.log(x);

delete x.firstName;
console.log(x);

// 없는 property 접근하려 하면
console.log(x.firstName); // undefined

//--------------------------
// console.log(a);  // 오류
var b;
console.log(b);
b = '김진영';
console.log(b);

//  '함수'가 'object 생성자'로 사용 가능.
function Person(firstName, lastName, age){
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
  
}

Person('aaa', 'bbb', 30);

var p1 = new Person('aaa', 'bbb', 30);
console.log(p1);
var p2 = new Person('김', '재현', 25);
console.log(p2);