package org.example
/*
Universidad del Valle de Guatemala
Programacion de Plataformas Moviles
2024
Vianka Castro 23201
Laboratorio 2
Ejercicios con funciones con parametros
 */
fun main(){

    //Calcular el promedio
    val numeros = listOf(1,2,3,4,5,6,7,8,9,10)
    calcularPromedio(numeros)

    //Utilizar la funcion filter
    val impares = numeros.filter{ it % 2 != 0}
    println("Numeros impares: $impares")

    //Crear funcion palindromo
    isPalindrome("ojo")

    //Saludar a todos de la lista
    val nombres = listOf("Mario","Nadissa","Felipe","Ricardo","Diego")
    println(nombres.map{"¡Hola, $it!"})

    //Funcion de performOperation
    val suma = performOperation(3,4){a,b -> a + b}
    val resta = performOperation(9,8){a,b -> a - b}
    println("Suma: $suma \nResta: $resta")

    //Mapeo de Persona a Estudiante
    val personas = listOf(
        Person("Flores", 21, "Femenino"),
        Person("Ricardo", 22, "Masculino"),
        Person("Ana", 23, "Femenino")
    )
    val estudiantes = personas.map{ mapeoPersonAStudent(it)}
    for (estudiante in estudiantes){
        println("El Estudiante ${estudiante.name} tiene edad ${estudiante.age}")
    }
}


fun calcularPromedio(num: List<Int>):Int{
    val suma = num.reduce { acc, num -> acc + num}
    val promedio = suma/num.size
    println("Promedio final: $promedio")
    return promedio
}

fun isPalindrome(cadena: String) {
    if (cadena == cadena.reversed()) {
        println("SI es un palindromo")
    } else
        println("NO es un palindromo")
}

fun performOperation(a: Int, b: Int, operacion: (Int, Int) -> Int): Int {
    return operacion(a, b)
}

data class Person(val name:String, val age:Int, val gender: String)
data class Student(val name:String, val age:Int, val gender: String, val studentId:String)

fun mapeoPersonAStudent(person:Person):Student{
    val studentId = "ID = ${person.name}"
    return Student(person.name, person.age, person.gender, studentId)
}