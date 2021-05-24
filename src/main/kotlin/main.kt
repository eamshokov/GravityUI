import androidx.compose.desktop.Window
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize
import core.*
import kotlinx.coroutines.flow.MutableStateFlow


fun main() = Window(
    size = IntSize(1024, 780)
) {

    MaterialTheme {
        Surface()
    }
}

fun tick(a:PhObject, b:PhObject, it:Long){
    if(a.position.x != b.position.x || a.position.y != b.position.y){
        val gravityForce = ((a.mass * b.mass) / distance(a.position, b.position)) * G
        a.force = direction(b.position, a.position) * gravityForce
        b.force = direction(a.position, b.position) * gravityForce
    }
    a.tick(it)
    b.tick(it)
}

@Composable
fun Surface(modifier: Modifier = Modifier){
    val objs by remember { mutableStateOf(listOf(
        PhObject(
            position = Vector(0.0, 0.0),
            mass= 50000.0,
            v= Vector(0.0, 0.0)
        ),
        PhObject(
            position = Vector(0.0, 1500.0),
            mass= 50.0,
            v=Vector(-15.9, 0.0)),
        PhObject(
            position = Vector(0.0, -3000.0),
            mass= 50.0,
            v=Vector(16.9, 0.0)),
        PhObject(
            position = Vector(-0.0, -5000.0),
            mass= 100.0,
            v=Vector(17.9, 0.0))
    )) }
    val colors  = listOf(Color.Blue, Color.Red, Color.Green, Color.Yellow)
    Canvas(
        modifier = modifier.fillMaxSize().background(Color.Black),
        onDraw={
            objs.forEachIndexed() {index,it->
                drawCircle(
                    color = colors[index],
                    radius = 5f,
                    center = Offset(size.width/2+it.position.x.toFloat()/30, size.height/2-it.position.y.toFloat()/20)
                )
            }
        })
    LaunchedEffect(Unit) {
        while (true) {
            withFrameMillis {
                objs.forEach { a->
                    objs.forEach { b->
                        if(a!=b){
                            tick(a,b, it)
                        }
                    }
                }
            }
        }
    }
}