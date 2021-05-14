var smallfishObj=function () {
    this.x;
    this.y;
    this.smallEye=new Image();
    this.smallBody=new Image();
    this.smallTail=new Image();
}
smallfishObj.prototype.init=function () {
    this.x=can2.width/2-50;
    this.y=can2.height/2+50
    this.smallEye.src="images/babyEye0.png";
    this.smallBody.src="images/babyFade0.png";
    this.smallTail.src="images/babyTail0.png";
}
smallfishObj.prototype.draw=function () {

    this.x =lerpDistance(bigfish.x,this.x,0.99);
    this.y =lerpDistance(bigfish.y,this.y,0.99);

    var deltaY=bigfish.y-this.y;
    var deltaX=bigfish.x-this.x;
    var beta=Math.atan2(deltaY,deltaX)+Math.PI;       //(-PI,PI)
    this.angle=lerpAngle(beta,this.angle,0.9);

    this.angle=lerpAngle(beta,this.angle,0.9);

    ctx1.save();
    ctx1.translate(this.x,this.y);
    ctx1.rotate(this.angle);
    ctx1.drawImage(this.smallTail,-this.smallTail.width/2+23,-this.smallTail.height/2);
    ctx1.drawImage(this.smallBody,-this.smallBody.width/2,-this.smallBody.height/2);
    ctx1.drawImage(this.smallEye,-this.smallEye.width/2,-this.smallEye.height/2);
    ctx1.restore();
}