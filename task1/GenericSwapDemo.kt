package org.example

fun <T> swap(list: MutableList<T>,ind1: Int,ind2: Int): MutableList<T>{

    if(ind1 in list.indices && ind2 in list.indices){
        val elem=list[ind1]
        list[ind1]=list[ind2]
        list[ind2]=elem
    }else{
        println("indices does not exist")
    }
    return list
}

fun main() {
    val numbers: MutableList<Int> = mutableListOf(2,4,6,8)
    println(swap(numbers,1,2))

    val strings: MutableList<String> = mutableListOf("name1","name2","name3")
    println(swap(strings,1,2))

}