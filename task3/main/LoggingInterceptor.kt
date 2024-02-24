
import io.grpc.*

class LoggingInterceptor : ServerInterceptor {
    override fun <ReqT : Any?, RespT : Any?> interceptCall(
        call: ServerCall<ReqT, RespT>?,
        headers: Metadata?,
        next: ServerCallHandler<ReqT, RespT>?
    ): ServerCall.Listener<ReqT> {
        val methodName = call?.methodDescriptor?.fullMethodName
        println("Incoming request: $methodName")
        val listener = next?.startCall(call, headers)
        return object : ForwardingServerCallListener.SimpleForwardingServerCallListener<ReqT>(listener) {
            override fun onMessage(message: ReqT) {
                println("Received message: $message")
                super.onMessage(message)
            }

            override fun onHalfClose() {
                println("Half-closed")
                super.onHalfClose()
            }

            override fun onReady() {
                println("Ready")
                super.onReady()
            }
        }
    }
}
