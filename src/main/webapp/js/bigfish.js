var fishObj=function () {
    this.x;
    this.y;
    this.angle;             //角度
    this.bigEye=new Image();
    this.bigBody=new Image();
    this.bigTail=new Image();
}
fishObj.prototype.init=function () {
    this.x=can2.width/2;
    this.y=can2.height/2;
    this.angle=0;
    this.bigEye.src="images/bigEye0.png";
    this.bigBody.src="images/bigSwim0.png";
    this.bigTail.src="images/bigTail0.png";
}
fishObj.prototype.draw=function () {
    /*调用lerpDistance函数*/
    this.x =lerpDistance(mx,this.x,0.99);
    this.y =lerpDistance(my,this.y,0.99);

    var deltaY=my-this.y;
    var deltaX=mx-this.x;
    var beta=Math.atan2(deltaY,deltaX)+Math.PI;       //(-PI,PI)

    this.angle=lerpAngle(beta,this.angle,0.9);

    ctx1.save();
    ctx1.translate(this.x,this.y);
    ctx1.rotate(this.angle);
    ctx1.drawImage(this.bigEye,-this.bigEye.width/2,-this.bigEye.height/2);
    ctx1.drawImage(this.bigBody,-this.bigBody.width/2,-this.bigBody.height/2);
    ctx1.drawImage(this.bigTail,-this.bigTail.width/2+30,-this.bigTail.height/2);
    ctx1.restore();
}
function onMousemove(e) {
    if(e.offsetX||e.layerX){
        mx=e.offsetX==undefined?e.layerX:e.offsetX;         //e.layerX：无
        my=e.offsetY==undefined?e.layerY:e.offsetY;
    }
}

function lerpDistance(aim,cur,ratio) {        //aim：目标位置，cur：当前位置，ratio：百分比
    var delta=cur-aim;
    return aim+delta*ratio;
}

function lerpAngle(a,b,t) {              //a是图片与鼠标之间的夹角，b是图片与x轴之间的夹角，t是百分比（此值越小，则旋转越灵敏）
    var d=b-a;
    if(d > Math.PI) d=d-2*Math.PI;
    if(d < -Math.PI) d=d+2*Math.PI;
    return a+d*t;
}