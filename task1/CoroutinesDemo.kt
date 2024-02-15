package org.example
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking{

    val task= async{
        delay(2000)
        "Task Completed"
    }

    val result = task.await()
    println(result)

}