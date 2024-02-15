package org.example


sealed class Shape{
    abstract fun area():Double
}

data class Circle(val radius: Double): Shape(){
    override fun area(): Double {
        return Math.PI * radius * radius
    }
}

data class Rectangle(val width:Double,val height: Double):Shape(){
    override fun area(): Double {
        return width*height
    }
}

fun calculateArea(shape: Shape): Double=shape.area()

fun main() {

    val circle= Circle(5.0)
    val rectangle=Rectangle(2.0,3.0)


    val circleArea: Double= calculateArea(circle)
    val rectangleArea: Double= calculateArea(rectangle)
    println("circle area is $circleArea")
    println("rectangle area is $rectangleArea")

}