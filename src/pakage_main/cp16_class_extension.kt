package pakage_main.cp16

/**
 * 클레스의 확장 : 상위 클레스의 구성 요소에 기능을 추가하여 사용
 *
 *
 * */
fun main(args:Array<String>){
    val obj = Sub()
    println("superData : ${obj.superData}, subData : ${obj.subData}")
    obj.superFun()
    obj.subFun()

    val obj2 = Super()
    println("superData : ${obj2.superData} subData2 : ${obj2.subData2}")
    obj2.superFun()
    obj2.subFun2()

    some(Sub3())
    some4(Sub4())


    val test = Test()
    test.sayHello()

    println("data1 : ${Test3.data1}... data2 : ${Test3.data2}")
    Test3.myFun1()
    Test3.myFun2()

    val obj5:ExtensionClass = ExtensionClass()
    obj5.some1()
//    obj5.some2() <- 에러

    val obj6 : DispatchClass = DispatchClass()
    obj6.test()


}

/**
 * 상속은 클레스 확장의 가장 대표적인 방법
 * */
open class Super{
    val superData = 18
    open fun superFun(){
        println("Super Function")
    }
}

class Sub : Super(){
    val subData : Int = 20
    fun subFun(){
        println("Sub Fun")
    }
}

/**
 * 클레스의 확장을 통한 기능의 추가
 * 기존 클레스를 확장하지 않고 기능을 추가한다.
 *
 * 이와같이 클레스를 확장하는 경우 기존 클레스가 생성될 때 정적으로 추가되는 것이 아님
 * 외부에 작성된 후 확장 클레스만 보고 실행된다.
 * 실행 단계에서 확정처럼 사용하지만  클레스 내부에 있는 것과는 다르다.
 * */


val Super.subData2:Int
    get() = 30

fun Super.subFun2(){
    println("subFun2")
}

class Sub2:Super(){
    var data = 20
    override fun superFun() {
        println("subFun $data")
    }
    fun some1(data:Int){
        this.data = data
        superFun()
        super.superFun()
    }
}
/**
 * 컴파일 단계에서 이 확장클레스를 읽을 시점에는 클레스가 생성된 것이 아니기에
 * super 를 사용할 수 없다.
 * this는 사용 가능하지만 super는 사용 불가능하다.
 * */
fun Sub2.some2(data:Int){
    this.data = data
    superFun()
//    super.superFun() <- 에러
}

/**
 * 상속을 통한 다형성의 구현
 * Sub3는 Super2를 일반적인 상속을 통해 확장하였다
 *
 * */
open class Super2{
    open fun sayHello(){
        println("Super.. sayHello")
    }
}
class Sub3 : Super2(){
    override fun sayHello() {
        println("Sub3.. sayHello()")
    }
}
/**
 *  main에서 호출은 : some(Sub3())
 * 이 경우 super를 인자로 받았지만 sub의 sayHello가 실행되고 있다.
 * 선언시의 인자가 아닌 실행시점의 인자로 인해 실행된다.
 * 단, 상속을 통한 확장이 이루어진 상항이다.
 * */
fun some(obj:Super2){
    obj.sayHello()
}

/**
 * main에서 호출은 : some4(Sub4())
 * 확장을 통한 다형성의 구현
 * 모두 클레스자체는 비어있는 상태에서 확장을 통해 메서드를 추가하였다.
 * 이 경우 Sub4가 아닌 super가 실행된다.
 * 확장에 의한 함수의 추가는 정적등록이다. 따라서 호출 당시의 타입을 정적으로 판단한다는 의미이다.
 * 따라서 정적으로 선언 된 타입인 Super로 판단하여 Super의 확장 함수가 호출된다.
 *
 * */
open class Super4
class Sub4 : Super4()

fun Super4.sayHello(){
    println("Super...sayHello()")
}
fun Sub4.sayHello(){
    println("Sub4...sayHello()")
}

fun some4(obj:Super4){
    obj.sayHello()
}
/**
 * 확장 함수와 멤버함수의 이름이 같은 경우
 * 멤버 함수가 우선시 된다.
 * */
class Test{
    fun sayHello(){
        println("Test.. sayHello()")
    }
}
fun Test.sayHello(){
    println("Test extension.. sayHello()")
}

/**
 * 프로퍼티의 확장
 * 프로퍼티의 확장은 get()을 통해서 이루어져야 한다.
 * */

class Test2{
    val classData : Int = 0
}
val Test.extension1: Int
    get() = 10
/**
 * 컴패니언 오브젝트의 확장
 * */
class Test3{
    companion object {
        val data1:Int = 10
        fun myFun1(){
            println("companion object myFun1()")
        }
    }
}

val Test3.Companion.data2: Int
    get() = 20

fun Test3.Companion.myFun2() {
    println("extension myFun2()...")
}

/**
 * //TODO
 * 자바의 static을 코틀린으로 가져와서 확장시킬수는 없을까?
 * */

/**
 * 클래스 내부에 작성 된 확장구문
 * 특정 클레스 내부에서 확장된 구문은 해당 클래스에서 접근
 *
 * */
class ExtensionClass{
    fun some1(){
        println("ExtensionClass some1()")
    }
    fun sameFun(){
        println("Extension SameFun()")
    }
}

class DispatchClass{
    fun dispatchFun(){
        println("DispatchClass dispatchFun()")
    }
    fun sameFun(){
        println("Dispatch SameFun()")
    }
    /**
     * 이름이 같은 경우 Extension의 함수가 호출된다.
     *
     * 단, this를 사용하여 호출하는 경우 특정 지점을 정할 수 있다.
     * */
    fun ExtensionClass.some2(){
        some1()
        dispatchFun()
        sameFun()
        this.sameFun()
//        this@DispatchClass.sameFun()
    }


    fun test(){
        val obj: ExtensionClass = ExtensionClass()
        obj.some1()
        obj.some2()
    }

}






