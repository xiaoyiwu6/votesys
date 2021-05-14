//跳转到投票页面
$(function(){
//	if(${sessionScope.curUser.userId}==0){
//		 setInterval(changetovote,100);
//	}
//	function changetovote(){
//		var url="/OnlineVoteSystem/changeToVote";
//		var param={userId:${sessionScope.curUser.userId},type:${sessionScope.curUser.type}};
//		$.post(url, param, function(data) {
//			
//		});
//	}
	function changetovote(userId){
		var url="/OnlineVoteSystem/changeToVote";
		var param={userId:userId};
		alert(userId);
//		$.post(url, param, function(data) {
//			if(data=="1"){
//				
//			}
//			if(data=="0"){
//				alert("投票未开启!")
//			}
//		});
	}
});