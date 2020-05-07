var str1, str2, str3;

str1 = "John";
str2 = new String("John"); // 비추

console.log(str1)
console.log(str2)
console.log(str2.toString())

console.log(str1==str2);
console.log(str1===str2);

console.log(str1.length);
console.log(str2.length); // 문자의 개수

str2 ="Hello Java Web Fram"
+"eWork";

console.log(str2)

str2 = "Hello Java Web Fr\
ameWork2"

console.log(str2);
console.log()

// indexOf(), lastIndexOf(), search() : 문자열 안에서 문자열 찾기
// 찾은 위치 인덱스 리턴, 못찾으면 -1 리턴
console.log("문자열 검색 \nindexOf(), lastIndexOf(), search()");
str1 = "Please locate where 'locate' occurs!";
console.log(str1.indexOf("locate"))
console.log(str1.lastIndexOf("locate"))

console.log(str1.indexOf("John"))
console.log(str1.indexOf("locate",10))

//search() : 문자열 안에서 문자열 찾기
console.log(str1.search("locate"))

// search() vs indexOf() : 둘은 다르다!
// 	search() : 두번째 매개변수 없다
// 	indexOf() : regexp 사용 불가

//문자열 추출
//slice(start, end)
//substring(start, end)
//substr(start, length)

console.log();
console.log("문자열 추출\nslice(), substring(), substr()");
str1 = "Apple, Banana, Kiwi";

console.log(str1.slice(7,13))
console.log(str1.slice(-12,-6))
console.log(str1.slice(7)) // 7부터 끝까지

console.log(str1.substring(7,13)) // slice와 유사하나 음수인덱싱 없음
console.log(str1.substring(7)) // 7 부터 끝까지

console.log(str1.substr(7,6)) // 7 부터 6글자
console.log(str1.substr(7)) // 7부터 끝까지

// 문자열 치환
// replace() : 치환한 결과 문자열 리턴, 정규표현식 사용 가능
// 기본적으로 첫번째 '매칭된 문자열 만 치환
console.log("문자열 치환 replace()");
str1 = "Please visit Japan!";
console.log(str1.replace("Japan", "Korea")) // 원본  str1 변경안함
console.log(str1.replace("JAPAN", "Korea"))
console.log(str1.replace(/JAPAN/i,"Korea"))

str2 = "please visit Japan! Go Japan";
console.log(str2.replace("Japan", "Korea")); // 기본적으로 첫번째만 치환
console.log(str2.replace(/Japan/g, "Korea"));

str3 = "Hello World!";
console.log(str3.toUpperCase())
console.log(str3.toLowerCase())

str1 = "Hello"; str2 ="World";
console.log(str1 + " " + str2);
console.log(str1.concat(" ",str2))

// trim() : 좌우 공백 제거
str1 = "                         Hello World!       ";
console.log(`str1 = [${str1}]`)
str2 = str1.trim();
console.log(`str2 = [${str2}]`)


// split() : String 쪼개서 -> Array
str3 = '2020-05-07';
console.log(str3.split('-'))

