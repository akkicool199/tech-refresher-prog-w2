package service

import com.example.services.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.collect

class CalculatorServiceImpl : CalculatorServiceGrpcKt.CalculatorServiceCoroutineImplBase() {


    override suspend fun streamSquares(requests: Flow<Number>): Flow<Square> = flow {
        requests.collect { number ->
            emit(Square.newBuilder().setValue(number.value * number.value).build())
        }
    }

    override suspend fun calculateAverage(requests: Flow<AverageRequest>): AverageResponse {
        var sum = 0
        var count = 0
        requests.collect { number ->
            sum += number.number
            count++
        }
        val average = if (count > 0) sum.toDouble() / count else 0.0
        return AverageResponse.newBuilder().setNumber(average).build()
    }


    override fun getPrimeNumbers(request: PrimeNumberRequest): Flow<PrimeNumberResponse> = flow {
        val number = request.number
        var current = 2
        while (current <= number) {
            if (isPrime(current)) {
                emit(PrimeNumberResponse.newBuilder().setNumber(current).build())
            }
            current++
        }
    }

    private fun isPrime(n: Int): Boolean {
        if (n <= 1) return false
        if (n <= 3) return true
        if (n % 2 == 0 || n % 3 == 0) return false
        var i = 5
        while (i * i <= n) {
            if (n % i == 0 || n % (i + 2) == 0) return false
            i += 6
        }
        return true
    }




    override suspend fun addition(request: CalculationRequest): CalculationResponse {
        return CalculationResponse.newBuilder()
            .setResult(request.val1 + request.val2)
            .build();
    }

    override suspend fun subtraction(request: CalculationRequest): CalculationResponse {
        return CalculationResponse.newBuilder()
            .setResult(request.val1 - request.val2)
            .build();
    }

    override suspend fun multiplication(request: CalculationRequest): CalculationResponse {
        return CalculationResponse.newBuilder()
            .setResult(request.val1 * request.val2)
            .build();
    }

    override suspend fun division(request: CalculationRequest): CalculationResponse {

        if(request.val2==0.0)
            throw RuntimeException("val2 which is denominator cannot be zero")

        return CalculationResponse.newBuilder()
            .setResult(request.val1 / request.val2)
            .build();
    }


}