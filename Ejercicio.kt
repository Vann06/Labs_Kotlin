package org.example

import kotlin.math.round

/*
Universidad del Valle de Guatemala
Programacion de Plataformas Moviles
2024
Vianka Castro 23201
Laboratorio 3
Ejercicio Sistema de pedido de alimentos
 */
fun main() {

    //Inicializacion de comidas
    val hamburguer = Burger("BigMac", 25.99)
    val pizza = Pizza("Chipotle Pizza", 35.24)
    val helado = IceCream("Menta", 15.64)
    val jugo = Juice("Jugo de Naranja", 35.50)
    val cafe = Coffee("Capuccino", 19.20)
    val pie = Pie("Pie de manzana", 35.99)

    //Cocinar cada comida
    println(hamburguer.cook())
    println(pizza.cook())
    println(helado.cook())
    println(jugo.cook())
    println(cafe.cook())
    println(pie.cook())

    //Comer
    println(helado.eat())
    println(pie.eat())

    //Pour
    println(jugo.pour())
    println(cafe.pour())

    //Precio de Descuento
    println("¡Se ha generado un descuento del 20% a ${hamburguer.name}! Ahora solamente a Q${hamburguer.discountPrice(0.20)}!!")
}

//Clase Base Food
abstract class Food(val name:String, var price: Double){
    abstract fun cook():String
}

//Subclase Food - Burguer
class Burger(name: String, price: Double): Food(name, price){
     override fun cook(): String {
        return "Cocinar carne, calendar panito, cortar vegetales, agregar salsa y juntar todo en stack"
    }
}

//Subclase Food - Pizza
class Pizza(name: String, price: Double):Food(name, price){
    override fun cook():String{
        return "Aplastar la masa, agregar salsa, agregar queso, agregar topings extras, colocar en el horno"
    }
}

//Interfaz de Dessert
interface Dessert{
    fun eat():String
}

//Subclase Food - IceCream
class IceCream(name: String, price: Double): Food(name,price),Dessert{
    override fun eat(): String {
        return "Agarrar cuchara y comer helado "
    }
    override fun cook(): String {
        return "Colocar jugo en una paleta y congelarlo en el freezer"
    }
}

class Pie(name:String, price:Double):Food(name, price), Dessert{
    override fun eat(): String {
        return "cortar un pezado y comer una parte"
    }

    override fun cook(): String {
        return "colocar relleno y luego masa de pie encima y colocar al horno"
    }
}

// Clase abstracta Drink
abstract class Drink(name: String, price: Double) : Food(name, price) {
    abstract fun pour(): String
}

// Subclase Drink - Juice
class Juice(name: String, price: Double) : Drink(name, price) {
    override fun cook(): String {
        return "Exprimir el jugo de las frutas."
    }
    override fun pour(): String {
        return "Verter el jugo en un vaso."
    }
}

class Coffee(name:String, price:Double):Drink(name,price){
    override fun cook(): String {
        return "Colocar cafe y azucar en una taza, vertir agua hirviendo, remover"
    }
    override fun pour():String{
        return "Verter cafe en una taza"
    }
}

fun Food.discountPrice(percentageDiscount:Double): Double {
    val disc = price * percentageDiscount
    return round((price-disc)*100)/100
}




