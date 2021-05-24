package core

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

class Vector(
     x:Double=0.0,
     y:Double=0.0
){
    var x by mutableStateOf(x)
    var y by mutableStateOf(y)

    operator fun plus(b:Vector):Vector = Vector(x+b.x, y+b.y)
    operator fun plusAssign(b:Vector){
        x+=b.x
        y+=b.y
    }
    operator fun minus(b:Vector):Vector = Vector(x-b.x, y-b.y)
    operator fun minusAssign(b:Vector){
        x-=b.x
        y-=b.y
    }
    operator fun unaryMinus():Vector =  Vector(-x,-y)
    operator fun times(b:Double):Vector = Vector(x*b, y*b)
    operator fun div(b:Double):Vector = Vector(x/b, y/b)
}

fun direction(a: Vector, b: Vector):Vector{
    val v = a-b
    val len = sqrt(abs(v.x.pow(2)+v.y.pow(2)))
    return Vector(v.x/len, v.y/len)
}

fun distance(a:Vector, b:Vector):Double{
    return sqrt(abs((a.x-b.x).pow(2)+(a.y-b.y).pow(2)))
}