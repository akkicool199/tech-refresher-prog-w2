package service

import io.grpc.ServerServiceDefinition
import io.grpc.Status
import io.grpc.inprocess.InProcessServerBuilder
import io.grpc.stub.StreamObserver
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CalculatorServiceTest {
    @Test
    fun testDivide() {
        val service = CalculatorServiceImpl()
        val stub = CalculatorServiceGrpc.newStub(InProcessServerBuilder.forName("test").directExecutor().addService(service).build())
        val responseObserver = object : StreamObserver<DivideResponse> {
            override fun onNext(response: DivideResponse) {
                assertEquals(2, response.quotient)
                assertEquals(0, response.remainder)
            }

            override fun onError(t: Throwable) {
                throw AssertionError("Unexpected error", t)
            }

            override fun onCompleted() {}
        }
        val requestObserver = stub.divide(responseObserver)
        requestObserver.onNext(DivideRequest.newBuilder().setDividend(4).setDivisor(2).build())
        requestObserver.onCompleted()
    }

    @Test
    fun testDivideByZero() {
        val service = CalculatorServiceImpl()
        val stub = CalculatorServiceGrpc.newStub(InProcessServerBuilder.forName("test").directExecutor().addService(service).build())
        val responseObserver = object : StreamObserver<DivideResponse> {
            override fun onNext(response: DivideResponse) {
                throw AssertionError("Unexpected response")
            }

            override fun onError(t: Throwable) {
                assertEquals(Status.INVALID_ARGUMENT.code, (t as StatusRuntimeException).status.code)
                assertEquals("Divisor cannot be zero", t.status.description)
            }

            override fun onCompleted() {
                throw AssertionError("Unexpected completion")
            }
        }
        val requestObserver = stub.divide(responseObserver)
        requestObserver.onNext(DivideRequest.newBuilder().setDividend(4).setDivisor(0).build())
        requestObserver.onCompleted()
    }
}