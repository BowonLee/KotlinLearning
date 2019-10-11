package pakage_main

// data 인자를 통해 클레스 생성
// 생성 조건 주 생성자인 경우 반드시 val, var, 매게변수 있어야 한다
data class User(val name:String,val age:Int = 20)

data class User2(val name:String,val age:Int? = 20){

    constructor(name:String, age: Int, code:Int) :this(name,age)
    constructor(name:String) :this(name,null)

}

// 이런 식의 선언도 불가능
//data class User3{
//    constructor(val name){
//
//    }
//}

data class People(val name:String, val age:Int, val code:Int)

// 기본 타입의 열거 상수 선언
enum class Direction{
    NORTH,SOUTH,WEST,EAST
}
// 특정 타입을 지정한 열거 상수 선언
enum class Direction2(val no:Int,val str : String){
    NORTH(0,"N"),SOUTH(1,"S"),WEST(2,"W"),EAST(3,"E")
}

// 열거형 상수가 객체인 이유
enum class NorthandSouth{
    North{
        override fun myFun() {
            println("north is here")
        }
        override val data1: Int = 100
        override val multiTwo: Int = data1 *2
    },
    South{
        override fun myFun() {
            println("south is here")
        }

        override val data1: Int = 200
        override var multiTwo: Int = data1 *2

    }; // 코틀린에서 세미콜론이 반드시 필요한 상황, 열거상수와 내부 멤버를 구분해 주어야 한다.

    abstract val data1:Int
    abstract fun myFun()
    // abstract 가 아니라면 open을 사용해야 위에서 사용 사능
    open val multiTwo:Int = 0
}

//열거형과 비슷한 구조를 가지지만 각각의 구성요소의 인자가 다르다.
//생성자가 private으로 고정되어 있다는 점이 추상 클레스와의 차이점
// 279 ~ 281  에서 seal의 사용에 대해
sealed class Coffee{
    class Espresso(val shot:Int):Coffee(){
        override fun printRecipe() {
            println("$shot Shot")
        }
    }
    class Americano(val shot:Int,val water:Int):Coffee(){
        override fun printRecipe() {
            println("$shot Shot , $water Water")
        }
    }
    abstract fun printRecipe()
}
// 다른 클레스에서는 이와같이 생성 불가
class Latte(val shot:Int,val water: Int,val milk:Int):Coffee(){
    override fun printRecipe() {
        println("$shot Shot , $water Water $milk Milk")
    }
}
// 일반 클레스로 생성
// 이 경우는 abstract로 선언이 불가능하여 사용이 힘들다.
//open class CoffeeDummy{
//    class Espresso(val shot:Int):CoffeeDummy(){
//        override fun printRecipe() {
//            println("$shot Shot")
//        }
//    }
//    class Americano(val shot:Int,val water:Int):CoffeeDummy(){
//        override fun printRecipe() {
//            println("$shot Shot , $water Water")
//        }
//    }
//
////    open fun printRecipe()
//
//}

//열거형 클레스의 구성요소를 직접 정의 할 수도 있다
//익명 클레스 선언

//Nested 자바에서 inner라고 부르던 것
// 클레스 안의 클레스
class Outer{

    private val outerThing:String = "outerTring"
    //예약어없이 사용하면 Nested
    //외부에서 접근 가능
    // Outer의 멤서 사용 불가능

    class NestedClass {
        val name : String = "bowon"
        fun printName(){
            println("my name is $name + Nested")
        }
    }

    //inner예약어 사용시 outer의 멤버 사용 가능
    //private 한 경우도 사용 가능
    //외부에서 접근 불가능
    //outer의 멤버 접근 가능
    inner class InnerClass {
        val name : String = "bowon"
        fun printName(){
            println("my name is $name + $outerThing")

        }
    }
    // 클레스 내부에서는 접근 가능
    fun temp(){
        val innerClass =  InnerClass()
        innerClass.printName()
        val nestedClass = NestedClass()
        nestedClass.printName()
    }


}


//Object Class
// object 예약어를 명시하고 클레스를 선언한다.
class ObjectTestClass{

    private var no:Int = 0

    // 내부에서 해당 object를 사용하려면 private 선언이 필요하다.
    //private 으로 선언시 외부에서 접근 불가, 내부에서 접근 가능
     private val myObjClass = object {
        val name:String = "bowon"
        fun objTestFun(){
            println("objInner")
            no = 100
        }
    }
    //private 선언하지 않는 경우 외부에서 접근 가능, 내부에서 접근 불가
    //protected 선언시 외부에서 접근 불가, 내부에서도 접근 불가
    val myObjClassDummy = object {
        val name:String = "bowon"
        fun objTestFun(){
            println("objInner")
            no = 100
        }

    }
    fun outerTestFun(){
        myObjClass.name
        myObjClass.objTestFun()
        // 에러
//        myObjClassDummy.name
//        myObjClassDummy.objTestFun

    }

}

// object 클레스가 상속 및 인터페이스를 받는 경우
interface SomeInterface{
    fun interfaceFun()
}
open class SomeClass{
    open fun SomeClassFun(){
        println("SomeClassFun")
    }
    protected open val test = object {
        val name = "SomeClass's protected Object member Value"
        // toString 을 통해서 사용
        override fun toString(): String {
            return name
        }
        fun temp(){

        }
    }
}

class Other{
    val myInner:SomeClass = object : SomeClass(),SomeInterface{
        override fun interfaceFun() {
            println("interfaceFun")
        }

        override fun SomeClassFun() {
            super.SomeClassFun()
            println("Override in objectClass $test")
        }

        // 상속은 가능
        override val test: Any
            get() = super.test

    }
    private val mMyInner:SomeClass = object : SomeClass(),SomeInterface{
        override fun interfaceFun() {
            // 이건 어떻게 사용하지..
            println("interfaceFun")
        }
    }

    fun useInnerClass(){

        mMyInner.SomeClassFun()

        val myInnerInFunction:SomeClass = object : SomeClass(),SomeInterface{
            override fun interfaceFun() {
                println("interfaceFun")
            }
        }

        // companion으로 선언된 object의 멤버를 그대로 사용하는 모습
        companionFun()
        val hasnameObjectTest = HasNameObjecClass
        hasnameObjectTest.myFun()
    }

    // 이름을 가진 object
    object HasNameObjecClass{
        //class 내부에서 선언 가능
        //inner 는 불가능
        val name = "bowon"
        fun myFun(){println("myName is $name")}
    }

    companion object HasNameObjecClassCompanion{
        //class 내부에서 선언 가능
        //inner 는 불가능
        // 맴버를 그대로 외부에서 사용 가능
        val name = "bowon"

        fun companionFun(){println("myName is $name - companion" )}
    }


}

// 외부에서 선언 가능
object hasname :SomeInterface{
    override fun interfaceFun() {
        println("interfacef=fub")
    }

}
fun main(args: Array<String>){
    val user1 = User("bowon",27)
    val user2 = User("bowonLee",27)

    val userDummy = User2("bowon")
    println("###################"+userDummy)

    val people = People("bowon",27, 30)

    // 값 비교  == 연산자 같은 데이터 타입 사이 작동
    println("user.equal(User( , )) = ${user1.equals(User("bowon",27))}")
    println(""""==="= ${user1 === User("bowon",27)}""")
    println("user1(User) == user2(User) = ${user1.equals(user2)}")
    println("user1(User) == userDummy(User2) = ${user1.equals(userDummy)}")


    println("equal 다른타입 = ${user1.equals(people)}")

    println("toString = ${user1.toString()}")
    println("toString = ${user1}")

    // componentN은 순서대로
    println("component = ${user1.component1() +" "+ user1.component2()}")

    var user3 = User(age = 27 ,name = "bowon")
    val(name,age) = user3
    println("name : $name age : $age")


    var people2 = People(age = 27 ,name = "bowon",code = 3)
    val(nameP,dummy,codeP) = people2
    println("name : $nameP code : $codeP")

    // copy 하면서 원하는 벨류만 지정해서 변경
    var people3 = people2.copy(name = "bowonlee")
    println(people3)


    println("#################Enum##################")
    val east:Direction = Direction.EAST

    println("East is ${east.name} ordinal is ${east.ordinal}")

    val directions:Array<Direction> = Direction.values()
    directions.forEach { direction -> println(direction)  }

    val directions2:Array<Direction2> = Direction2.values()
    directions2.forEach { direction2 -> println(""" ${"no : ${direction2.no} code : ${direction2.str}" }""")  }

    val south:NorthandSouth = NorthandSouth.South
    println(south)
    println(south.data1)
    println(south.multiTwo)

    south.myFun()

    val espresso:Coffee = Coffee.Espresso(2)
    val americano:Coffee = Coffee.Americano(2,2)
    val latte:Coffee = Latte(2,1,1)

    espresso.printRecipe()
    americano.printRecipe()
    latte.printRecipe()



    println("#################Inner,Nested##################")
    val nested:Outer.NestedClass = Outer.NestedClass()
    //val innerClass:Outer.InnerClass = Outer.InnerClass() <- 에러
    nested.printName()
    val outer = Outer()
    outer.temp()



    println("#################Object##################")
    // 익명 클레스를 정의한다.
    val objectClass1 = object {
        var no1: Int = 10
        init {
            println("createObjectClass $no1")
        }
        //생성자는 사용 불가능
        //constructor(val name:String):this(){}
        fun myObjectFun(){
            println("ObjectClass1 Function")
        }

    }
    objectClass1.myObjectFun()// 가능

    val objTestClass = ObjectTestClass()
    objTestClass.outerTestFun()
    // private은 참조 불가능

    /*
    * object 는 싱글톤 클레스를 사용하기에 최적화 된 구조
    * */
    // 다른 영역에서는 그냥 선언 된 변수 정도로만 판단?
    objTestClass.myObjClassDummy// 여기까지만 가능
    println(objTestClass.myObjClassDummy) //Any 타입의 반환

    val other = Other()
    other.myInner.SomeClassFun()
    other.myInner.SomeClassFun()
    //Interface로 구현된 부분은 받아오지 못함

    // funtion 내부에서는 불가능
//    object hasname{
//
//    }

    // 클레스 명을 직접 명시하면서 가져온다.
    // 이름있는 object 클레스는 static
    Other.HasNameObjecClass.myFun()
    // companion의 경우 Other의 멤버 변수인 것 처럼 사용한다.
    // companion 의 경우 클레스 내부에 하나만 존재 할 수 있다
    Other.companionFun()

    val ooo = Other()
    ooo.useInnerClass()

    // 즉 자바의 static 같이쓰인다.

    // 사족
    //  class 단위로 프로그렘을 짤 필요가 없는 코틀린이라 static을 예약어가 없다.
    // 단, 예약어가 없을 뿐이지 static 기능 자체는 사용되고 있다.

}
