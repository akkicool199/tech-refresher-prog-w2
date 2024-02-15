package org.example

// using extension prop
val Int.isDivBy2:Boolean
    get() = this.mod(2)==0

fun main() {
    val numbers :List<Int> = mutableListOf(1,2,3,4,5)
    val sumOfSquaresOfEvenNumbers=numbers.filter { it.isDivBy2 }
        .map { it*it }
        .reduceOrNull { acc, sum -> sum+acc }

    println("sum of squares of even numbers $sumOfSquaresOfEvenNumbers")
}