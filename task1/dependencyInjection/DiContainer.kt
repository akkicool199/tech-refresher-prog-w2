package org.example.dependencyInjection

class DiContainer {

    private val services: MutableMap<Class<*>,Any> = mutableMapOf()
    // Register a service implementation
    fun registerService(serviceClass: Class<*>, serviceImpl: Any) {
        services[serviceClass] = serviceImpl
    }

    // Resolve a service implementation
    @Suppress("UNCHECKED_CAST")
    fun <T> resolve(serviceClass: Class<T>): T {
        return services[serviceClass] as T
    }

}