package org.example

fun List<Int>.addElem(): Int{
    var total=0
    for(elem in this){
        total+=elem
    }
    return total
}

fun main() {
    val numbers= listOf<Int>(2,4,5,6)
    val total=numbers.addElem()
    println("sum is $total")
}