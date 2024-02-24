package server

import LoggingInterceptor
import io.grpc.ServerBuilder
import service.CalculatorServiceImpl
import java.util.concurrent.TimeUnit

val PORT_NO: Int=50051

fun calculatorServer() {
    println("Starting the server for calculator service")
    val calculatorServerImpl : CalculatorServiceImpl = CalculatorServiceImpl()
    val server = ServerBuilder.forPort(PORT_NO)
        .addService(calculatorServerImpl)
        .intercept(LoggingInterceptor())
        .build()
    Runtime.getRuntime().addShutdownHook(Thread{
        server.shutdown().awaitTermination(5 , TimeUnit.SECONDS)
    })

    server.start()
    server.awaitTermination()
    println("server shutdown")
}

fun main() {
    calculatorServer()
}