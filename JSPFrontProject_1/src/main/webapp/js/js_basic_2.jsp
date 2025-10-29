<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	자바스크립트 -> 연산자
	1) 단항연산자
		- 증감 연산자(++, --)
		- 부정 연산자(!)
		- 형변환
			-> 숫자 변환 -> Number("10"), parseInt("10")
							| -> 결제 통계
			-> 문자열 변환 -> String(10) -> "10"
			-> 논리형 변환 (true/false)
				0, 0.0, null이 아닌 경우는 true
				-------------> flase
				Boolean(1) = true
				Boolean(0) = false
	2) 이항 연산자
		- 산술 연산자 : +(덧셈, 문자열 결합) - * /(5/2 -> 정수 / 정수 = 실수, 0으로 나누면 오류 발생Infinity, NaN -> 연산처가 안되는 경우) %(나머지 -> 왼쪽편 부호 )
		- 비교 연산자 : ==(===) : 결과값은 boolean, !=(!==) : 결과값은, <, >, <=, >=
		- 논리 연산자 : && : 직렬(양쪽 조건이 true일때만 true), 범위나 기간 안에 포함
					 || : 병렬 (양쪽 조건 중 한 개 이상일 경우 true), 범위나 기간이 벗어난 경우
		- 대입 연산자 : =, +=, -=
	3) 삼항 연산자 : 조건 ? 값1 : 값2
					true -> 값1
					false -> 값2
					-> if~else
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
/* function calc()
{
	let a = document.getElementById("first").value;
	let b = document.getElementById("second").value;
	//console.log("a=" + a + "," + typeof a);
	//console.log("b=" + b + "," + typeof b); 
	let c=Number(a) + parseInt(b);
	document.getElementById("print").innerHTML=c;
} */
window.onload=function(){
	let a = Boolean(1);
	let b = Boolean(0); //false
	let c = Boolean(0.0); //false
	let d = Boolean(0.1);
	let e = Boolean('JavaScript');
	let f = Boolean(''); //false
	let k = Boolean(""); //false
	
	let m = "a=" + a + "<br>"
			+ "b=" + b + "<br>"
			+ "c=" + c + "<br>"
			+ "d=" + d + "<br>"
			+ "e=" + e + "<br>"
			+ "f=" + f + "<br>"
			+ "k=" + k + "<br>"
	document.write(m);
	document.write((5/0) + "<br>");
	document.write("Java" * 5 +"<br>");
	document.write((5/2) + "<br>");
	doucment.write(("10"==10) + "<br>");
	doucment.write(("10"===10) + "<br>");
	// 기준 -> 데이터형 -> 권장
}
</script>
</head>
<body>
	<!-- 첫번쨰 값 : <input type=text id="first" size=10><br>
	두번째 값 : <input type=text id="second" size=10><br>
	<input type=button value="계산" onclick="calc()"><br>
	<span id="print"></span> -->
	
</body>
</html>