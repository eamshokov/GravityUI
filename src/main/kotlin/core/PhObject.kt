package core

val G = 0.000000000667

class PhObject(
    var a:Vector = Vector(),
    var v:Vector = Vector(),
    var force:Vector = Vector(),//:MutableList<Vector> = mutableListOf(),
    var position:Vector = Vector(),
    var mass:Double = 0.0
){

    fun calcA(time:Long=1){
        a = (force/mass)*time.toDouble()//forces.reduce{v1,v2->v1+v2}/mass
    }

    fun calculateVelocity(time:Long){
        calcA(time)
        v.plusAssign(a)
        position.plusAssign(v)
    }
}
fun applyGravityForTwoObjects(a:PhObject, b:PhObject, it:Long){
    if(a.position.x != b.position.x || a.position.y != b.position.y){
        val gravityForce = ((a.mass * b.mass) / distance(a.position, b.position)) * G
        a.force = direction(b.position, a.position) * gravityForce
        b.force = direction(a.position, b.position) * gravityForce
    }
    a.calculateVelocity(it)
    b.calculateVelocity(it)
}
