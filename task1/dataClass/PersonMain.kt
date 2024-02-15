package org.example.dataClass

fun main() {
    //create a list of `Person` objects
    val person1: Person=Person("akash",18,"akash@email.com")
    val person2: Person=Person("singh",31,"singh@email.com")
    val person3: Person=Person("akshay",40,"akashay@email.com")

    val persons= listOf<Person>(person1,person2,person3)

    val personOlderThanThirty= persons.filter { it.age>30 }

    println("Persons older than 30 are:")
    personOlderThanThirty.forEach { println(it)}


    val personDto = mapPersonToDto(person1)
    println("Person DTO: $personDto")


}

fun mapPersonToDto(person: Person): PersonDto {
    return PersonDto(person.name, person.age,person.email)
}