window.onload=game;
var can1=document.querySelector("#canvas1");
var ctx1=can1.getContext("2d");
var can2=document.querySelector("#canvas2");
var ctx2=can2.getContext("2d");

var lastTime;            //上一帧的时间
var deltaTime;           //两帧间隔的时间差

var bgPic=new Image();

var ane;
var fruit;
var bigfish;
var smallfish;
var mx;
var my;

function game() {
    init();
    lastTime=Date.now();
    deltaTime=0;
    gameloop();
}

function init() {
    bgPic.src="images/background.jpg";

    ane=new aneObj();
    ane.init();

    fruit=new fruitObj();
    fruit.init();

    bigfish=new fishObj();
    bigfish.init();
    smallfish=new smallfishObj();
    smallfish.init();

    mx=can2.width/2;
    my=can2.height/2;
    can1.addEventListener("mousemove",onMousemove,false);
}

function gameloop() {
    window.requestAnimationFrame(gameloop);
    var now=Date.now();
    deltaTime=now-lastTime;
    lastTime=now;
    if(deltaTime>40)deltaTime=40;

    drawBackground();
    ane.draw();
    fruit.draw();
    fruitMonitor();

    ctx1.clearRect(0,0,can2.width,can2.height);
    bigfish.draw();
    smallfish.draw();

    bigfishFruitsCollision();
}