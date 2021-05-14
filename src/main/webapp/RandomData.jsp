<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-3.3.1.min.js"></script>
<style>
.kuang {
	width: 1200px;
	height: 600px;
	margin-right: auto;
	margin-left: auto;
	background: url("${pageContext.request.contextPath}/images/veer-146685394.png");
}

input {
	width: 150px;
	height: 40px;
	position: relative;
	top: 100px;
	left: 800px;
	background: #c1fffe;
}
a{
	display:block;
	width: 100px;
	height: 20px;
	position: relative;
	top: 100px;
	left: 800px;
	background: #cccccc;
	color:#000;
	text-align: center;
	text-decoration:none;/*超链接去下划线*/
	
}
</style>
</head>
<body>
<div class="kuang">
	<input type="button" value="生成keywords" id="randomdata"/>
	<input type="text" value="" name="shuzi"  id="zi"  />
	<a href="back">回到主页</a>
</div>
<script type="text/javascript">
$(function () {
	  var zimu=["a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"];//字母数组
	  var k=-1;//控制次数
	  var a=0;
	  var b=0;
	  var c=0;
	  var d=0;
	  $("#randomdata").on("click",function () {
	    var num="";
	    
	    k++;

	    if(k>=26){
	      k=0;
	      a++;
	    }
	    if(a>=26){
	      a=0;
	      b++;
	    }
	    if(b>=26){
	      b=0;
	      c++;
	    }
	    if(b>=26){
	      b=0;
	      d++;
	    }
	    if(d>=26){
	      alert("不能注册了");
	    }
	    num+=zimu[d]+zimu[c]+zimu[b]+zimu[a]+zimu[k];
	    for(var i=0;i<4;i++)
	    {
	      num+=Math.floor(Math.random()*9);
	    }
	    add(num);
	    function add(num){
	    	 var url="/OnlineVoteSystem/RandomData.do";
	    	    var param={num:num};
	    	    $.post(url,param,function(data) {
	    	    	if(data=="1"){
	    	    		$("#zi").val(num);
	    			}else{
	    				alert("随机数生成失败！")
	    			}
	    	    });
	    }
	  });
	});
</script>
</body>
</html>