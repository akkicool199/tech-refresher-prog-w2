package client


import com.example.services.CalculationRequest
import com.example.services.CalculationResponse
import com.example.services.CalculatorServiceGrpcKt
import io.grpc.ManagedChannel
import java.io.Closeable
import java.util.concurrent.TimeUnit

class CalculatorServiceClient(private val channel: ManagedChannel) : Closeable {

    private val stub: CalculatorServiceGrpcKt.CalculatorServiceCoroutineStub =
        CalculatorServiceGrpcKt.CalculatorServiceCoroutineStub(channel)


    suspend fun addition(val1 : Double , val2 : Double) : Double{

        val result : CalculationResponse =
            stub.addition(CalculationRequest.newBuilder().setVal1(val1).setVal1(val2).build())
        return result.result
    }


    suspend fun subtraction(val1 : Double , val2 : Double) : Double {
        val result : CalculationResponse =
            stub.subtraction(CalculationRequest.newBuilder().setVal1(val1).setVal2(val2).build())
        return result.result
    }

    suspend fun multiplication(val1: Double , val2: Double) : Double {
        val result: CalculationResponse =
            stub.multiplication(CalculationRequest.newBuilder().setVal1(val1).setVal2(val2).build())
        return result.result

    }

    suspend fun division(val1: Double , val2: Double) : Double {
        val result: CalculationResponse =
            stub.division(CalculationRequest.newBuilder().setVal1(val1).setVal2(val2).build())
        return result.result

    }
    override fun close() {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
    }

}