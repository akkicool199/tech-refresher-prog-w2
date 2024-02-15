import org.example.dependencyInjection.ActionClass
import org.example.dependencyInjection.DiContainer
import org.example.dependencyInjection.DiService
import org.example.dependencyInjection.DiServiceImpl

fun main() {
    //Instantiate the DiContainer
    val diContainer = DiContainer()

    //Register the service implementation
    val diService = DiServiceImpl()
    diContainer.registerService(DiService::class.java, diService)

    //Create an instance of ActionClass and inject DiService
    val actionClassInstance = ActionClass(diContainer.resolve(DiService::class.java))

    // Use the class that depends on the service
    val performedAction = actionClassInstance.doSomeAction()

    println(performedAction)
}