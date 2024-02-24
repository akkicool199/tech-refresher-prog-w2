package client

import com.example.services.CalculatorServiceGrpc.CalculatorServiceImplBase
import com.example.services.CalculatorServiceGrpcKt
import com.example.services.PrimeNumberRequest
import io.grpc.ManagedChannel
import kotlinx.coroutines.flow.flow
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking

val PORT_NO: Int=50051

suspend fun main() {

    val channel : ManagedChannel =
        ManagedChannelBuilder.forAddress("localhost" , PORT_NO)
            .usePlaintext().build()


    val clientStub = CalculatorServiceGrpcKt.CalculatorServiceCoroutineStub(channel)


    // handle error gracefully
    handleErrorGracefully()


    //Bi-Directional Streaming
    runBlocking {
        val numbers = flow {
            repeat(10) {
                emit(Number.newBuilder().setValue(it).build())
            }
        }

        clientStub.streamSquares(numbers).collect { square ->
            println("Received square: ${square.value}")
        }
    }


    //print all prime numbers upto n
    runBlocking {
        clientStub.getPrimeNumbers(PrimeNumberRequest.newBuilder().setNumber(10).build())
            .take(10)
            .collect { response ->
                println("Prime: ${response.number}")
            }
    }


    // print avg of provided numbers
    val numbers = flow {
        emit(Number.newBuilder().setValue(1).build())
        emit(Number.newBuilder().setValue(2).build())
        emit(Number.newBuilder().setValue(3).build())
    }
    val response = clientStub.calculateAverage(numbers)
    println("Average: ${response.number}")


    //CalculatorServiceClient method calling
    val calculatorServiceClient : CalculatorServiceClient = CalculatorServiceClient(channel)
    calculatorServiceClient.addition(4.0, 2.0)

    calculatorServiceClient.subtraction(4.0, 2.0)

    calculatorServiceClient.multiplication(4.0, 2.0)

    calculatorServiceClient.division(4.0, 2.0)

    channel.shutdown()
}

fun handleErrorGracefully() {
    val dividend = 10
    val divisor = 0

    try {
        val result = divide(dividend, divisor)
        println("Result: $result")
    } catch (e: IllegalArgumentException) {
        println("Error: ${e.message}")
    }
}

fun divide(dividend: Int, divisor: Int): Int {
    if (divisor == 0) {
        throw IllegalArgumentException("Cannot divide by zero")
    }
    return dividend / divisor
}