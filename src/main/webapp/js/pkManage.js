/*分页*/
function goPage(pno,psize){
    var itable = document.getElementById("idData");
    var num = itable.rows.length;//表格所有行数(所有记录数)
    var totalPage = 0;//总页数
    var pageSize = psize;//每页显示行数
    //总共分几页
    if(num/pageSize > parseInt(num/pageSize)){
        totalPage=parseInt(num/pageSize)+1;
    }else{
        totalPage=parseInt(num/pageSize);
    }
    var currentPage = pno;//当前页数
    var startRow = (currentPage - 1) * pageSize+1;//开始显示的行
    var endRow = currentPage * pageSize;//结束显示的行
    endRow = (endRow > num)? num : endRow;

    /*页数跳转*/
    var pageVal=document.getElementById("pageVal");
    var pageBtn=document.getElementById("pageBtn");
    pageBtn.onclick=function () {
        if(pageVal.value<=0||pageVal.value>totalPage||pageVal.value==null){
            alert("页码不正确,请重新输入页码");
        }
        else{
            goPage(pageVal.value,psize)
        }
    }

    //遍历显示数据实现分页
    for(var i=1;i<(num+1);i++){
        var irow = itable.rows[i-1];
        if(i>=startRow && i<=endRow){
            irow.style.display = "block";
        }else{
            irow.style.display = "none";
        }
    }
    var pageEnd = document.getElementById("pageEnd");
    var tempStr = "共"+num+"条记录 分"+totalPage+"页 当前第"+currentPage+"页 ";
    if(currentPage>1){
        tempStr += "<a href=\"#\" onClick=\"goPage("+(1)+","+psize+")\">首页</a>";
        tempStr += "<a href=\"#\" onClick=\"goPage("+(currentPage*1-1)+","+psize+")\"><上一页</a>"
    }else{
        tempStr += " 首页";
        tempStr += "<上一页";
    }

    if(currentPage<totalPage){
        tempStr += "<a href=\"#\" onClick=\" ;goPage("+(currentPage*1+1)+","+psize+")\">下一页></a>";
        tempStr += "<a href=\"#\" onClick=\"goPage("+(totalPage)+","+psize+")\">尾页</a>";
    }else{
        tempStr += "下一页>";
        tempStr += "尾页";
    }

    document.getElementById("barcon").innerHTML = tempStr;

}

