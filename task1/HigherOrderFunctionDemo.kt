package org.example

val CAR_NAME_LEN=6

fun getFiletredList(cars: List<String>, condition: (String)-> Boolean): List<String>{
    return cars.filter {condition(it) }
}


fun main() {

    val cars: List<String> = listOf("Ducati","Mercedez","Ferrari","Bugati","nano")

    val filteredList= getFiletredList(cars){
        it.length>CAR_NAME_LEN
    }
    filteredList.forEach { println(it)}
}