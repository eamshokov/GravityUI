package core

import kotlin.math.*

val G = 0.000000000667

class PhObject(
    var a:Vector = Vector(),
    var v:Vector = Vector(),
    var force:Vector = Vector(),//:MutableList<Vector> = mutableListOf(),
    var position:Vector = Vector(),
    var mass:Double = 0.0
){
    /*fun applyForce(force:Vector){
        this.forces.add(force)
    }*/

    fun calcA(time:Long=1){
        a = (force/mass)*time.toDouble()//forces.reduce{v1,v2->v1+v2}/mass
    }

    fun tick(time:Long){
        calcA(time)
        v.plusAssign(a)
        position.plusAssign(v)
    }
}
fun direction(a: Vector, b: Vector):Vector{
    val v = a-b
    val len = sqrt(abs(v.x.pow(2)+v.y.pow(2)))
    return Vector(v.x/len, v.y/len)
}

fun distance(a:Vector, b:Vector):Double{
    return sqrt(abs((a.x-b.x).pow(2)+(a.y-b.y).pow(2)))
}
