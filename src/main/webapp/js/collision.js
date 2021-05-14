function bigfishFruitsCollision() {
    for(var i=0;i<fruit.num;i++){
        if (fruit.alive[i]){
            var l=calLength2(fruit.x[i],fruit.y[i],bigfish.x,bigfish.y)
            if(l<900){
                fruit.dead(i);
            }
        }
    }
}
function calLength2(x1,y1,x2,y2) {
    return Math.pow(x1-x2,2)+Math.pow(y1-y2,2);       //x1-x2的平方.....
}