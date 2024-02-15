package org.example.dependencyInjection

class ActionClass(private val myService: DiService) {
    fun doSomeAction(): String {
        return myService.doSomeAction()
    }
}