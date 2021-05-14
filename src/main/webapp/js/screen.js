$(function(){
	//定时器
	 setInterval(changePillar,100);
	 //票数及柱子高度显示相关
	  function changePillar(){
	  	var url = "/OnlineVoteSystem/vote/checkTicket";
	  	var param="";
	  	$.post(url, param, function(data) {
	  		if(data=="0"){
	  			console.log("暂停");
	  		}else{
	  			//json字符串转为json对象
	  			data=JSON.parse(data);
	  			//Pa、Pb分别记录选手A和B的票数
	  			var Pa=data.votesContrastA;
		      	var Pb=data.votesContrastB;
		      	//更新选手票数
		      	$("#p1").html(Pa);
		      	$("#p2").html(Pb);
		      	//更改柱子高度
		      	if (Pa<=400){
		      		$("#player1").css("height",Pa*2+"px");
		      	}
		      	if (Pb<=400){
		      		$("#player2").css("height",Pb*2+"px");
		      	}
	  		}      
	  	});
	  	}

});

function VoteRTS(value,userId){
	var url = "/OnlineVoteSystem/vote/addTicket";
	//var param = $("form").serialize();
	var param="value="+value+"&userId="+userId;
	//console.log(param);
	$.post(url, param, function(data) {
		if(data=="0"){
			alert("您已投票，不能再投票了!");
		}
		if(data=="1"){
			alert("投票成功!");
		}
		
	});
}

