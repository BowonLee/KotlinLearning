package pakage_main

import pakage_main.model.User

fun main(args: Array<String>) {

    val obj:MyOpraterFunction = MyOpraterFunction(10)
    val my:User = User("bowon",27)
    var who:User = User("who",27)
    val functionTest:OperaterFunction = OperaterFunction();
    /*init만 실행됨, */
  //  val forTest = ForTest3("name")
    /*constructer까지 실행 됨 */
    //val forTest3 = ForTest3("name",27)

    print (who.name)

    val forTest4 = ForTest4("name",27)

    val propertyTest1 = PropertyTest()


    println("before : " + propertyTest1.greeting)
  //  propertyTest1.greeting = "Who's name"
    println("after : " + propertyTest1.greeting)

    propertyTest1.temp = "first"
    propertyTest1.temp = "Second"
    propertyTest1.temp = "Third"




    my.hello()
    //my.age = 50

    println(10 + 5)
    println(10 .plus(5))

    println(functionTest.plusTest())
    println(functionTest + 10)

    println(obj.plus(5))
    println(obj + 5)

    println(my)
    println(my + who)

    who = User("bowon",27)

    println(my.equals(who))
    println(my == who)
    //println(my.equals(who))

    println(my === who)


    val data = 5
    var par = 3
    when(data){
        par -> println("par")
        3->println("3")
   //     data == 3 -> println("3")
        else->println("123")
    }
}
/*
data class Int(val no :Int){
    operator fun plus(arg:Int): kotlin.Int {
        return 5
    }
}*/


data class MyOpraterFunction(val no:Int){
    operator fun plus(arg:Int): Int {
        return no + arg + 10
    }
}