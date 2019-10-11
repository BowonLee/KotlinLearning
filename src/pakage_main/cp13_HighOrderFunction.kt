package pakage_main.cp13


/*
* 고차함수 : 함수를 인자로 받는 함수
* 또한 리턴 타입이 함수 일수도 있다.
* */

/*
*
*
*
* */


fun main(args: Array<String>) {

    val array = arrayOf(1,2,3,4,5)
    hoFun(10,{x->x*x})
    // 이와 같이 소괄호 없이 사용하거나, 소괄호 바깥으로 빠져 나올 수 있다.
    // 단, 이경우는 람다식을 받는 인자가 뒤에 있을 경우 해당된다.
    hoFun(10) { x->x*x}
    array.filter { it->it>3 }.forEach { println(it) }


    //인자를 명시한 첫번째 호출은 x>20 이기에 false
    hoFun2(4,{it*it},{it>20});
    //인자를 명시하지 않은 기본 함인 it>10 이기에 true
    hoFun2(4,{it*it});
    // 함수를 반환받아 사용하는 모습
    println("###########")
    println(hoFun3("*")(10,5))


//    함수 참조를 통한 매게변수 전달
    hoFun4 (::nameFun)

    /*
    * 익명 함수의 활용
    * */
    //에러 return을 추가하는 경우 에러가 난다. 람다에서는 명시적으로 return을 사용할 수 없다. 즉, 반환 타입을 지정할 수 없다.
//    val lamdaFun={x:Int->println("Lamda Function") return x*10}
    /*
    * 아래와 같이 함수를 만들어 사용하는 경우 명시적으로 반환 타입을 지정하고 사용 할 수 있다.
    * */
    val anonyFun1= fun(x:Int):Int = x*10
    val anonyFun2= fun(x:Int):Int {
        println("Lamda Function")
        return x*10
    }

    /*
    * 함수의 형태
    * 일반 함수 fun 함수명(매게변수):리턴타입{}
    * 익명 함수 fun(매개변수):리턴타입{}
    * 람다 함수 {매게변수 -> 리턴타입}
    * */

    /*
    * 코틀린 API에서 제공하는 고차 함수들
    * */

    //run 람다 매개변수로 함수를 전달하여 사용
    // 기본적인 사용법
    // run을 독립적으로 사용하려는 경우 매게면스룰 받을 수 없다.
    val result = run { println("Run Function Call")
        10 + 20}

    run{ println("Run Function Call")
        10 + 20}

    /*
    * run을 이용하여 한번에 모든 객체 멤버에 접근하는 모습
    * 객체의 요소들을 바로 접근 가능하다.
    * 이후 해당 프로퍼티 호출 시 명시한 내부 변수들이 입력되고, 함수가 호출된다, 또한 해당 변수의 값은 마지막 리턴값이다.
    * */
    val user = User()
    val runResult = user.run {
        name = "bowon"
        age = 27
        printHello()
        printInfo()
        10+20
    }

    println(runResult)

//    val runResult2 = user.run {
//        printHello()
//        printInfo()
//    }
//    println(runResult2)

    /*
    * apply
    * run과 기본 문법은 비슷하지만 객체 자체를 반환한다.
    *
    * 안드로이드에서 intent를 사용하는 경우 apply를 이용하는 경우  flag나 혹은 다른 요소들을 대입하요 사용할 때
    * 이 경우는 해당 intent를 지속적으로 이용해야 하기에 apply를 사용해야만 한다.
    * 자동 로그인 기능을 사용하는 경우
    *
    * # pjax-> 사용을 잘 하는 경우 효과적
    * */
    val user2 = User()
    val userApplied = user.apply {
        name = "Lee"
        age = 26
        printHello()
        printInfo()
    }


    println("name : ${user.name}, user 3 name : ${userApplied.name}")
    user.name = "aaa"
    userApplied.name = "bbb"
    println("name : ${user.name}, user 3 name : ${userApplied.name}")

    // 레퍼런스 비교를 실행하면 확실하게 확인 할 수 있다.
    println("User === applyUser ${user === userApplied}")

    /*
    * run vs apply
    * 클레스의 멤버들을 더 쉽게 접근하여 초기화, 사용한다는 점에서는 비슷하지만 반환타입의 차이가 있다.
    * run은 마지막 값을 리턴 타입으로 하며, apply는 해당 클레스 자체를 리턴한다.
    * 즉, run은 해당 클레스의 1차적인 값 설정과 사용에
    * apply는 해당 클레스 자체를 지속적으로 가지고 있어야 하는 경우에 사용하는  것이 맞을 것 같다.
    *
    * */

    //let을 이용한 객체의 선언
    // 따로 선언을 하지않고 바로 객체의 멤버를 이용한다.
    User2("Lee",27).let {
        println("name : ${it.name} age: ${it.age}")
    }

    //with
    //인자로 받은 프로퍼티를 즉시 사용
    with(user){
        name = "bowonLee"
        printInfo()
    }


    /*
    * Inline 예약어
    * 고차함수의 선언시 inline예약어를 추가할 수 있다.
    * 코틀린환경에서의? 고차함수는 개발자의 의도보다 더 많은 과정을 거치게 되는 경우가 있다 - 성능 이슈
    * 고차함수의 사용이 빈번한 경우 inline예약어를 이용하여 컴파일 단계에서 정적으로 포함된다? - 뭔 뜻이지
    * 즉, 결론적으로 inline을 사용하나, 하지않나 실행결과는 같지만, 런타임에서의 실행 결과가 다르다.
    *
    * 또한 고차함수가 만일 inline으로 선언되어 있다면 해당 람다함수에서 return을 사용할 수 있다.
    *
    *
    *
    * */

    /*
    * noinline 예약어
    * inline으로 선언된 함수의 특정 부분을 inline으로 사용하고 싶지 않은 경우 사용한다.
    * */

    /*
    * Non local Return
    * 람다함수 내에서 람다함수를 포함하는 함수를 벗어나게 한다.
    * */


    /*
    * 라벨을 이용한 반환
    * 람다함수의 경우 inline계열 함수를 사용하여 return을 사용해 반환을 사용 할 수 있다.
    * 하지만 단순 return을 사용하는 경우 의도하지 않은 흐름이 될 수 있다.
    * */

    // for each는 inline으로 선언된 고차함수이기에 return이 사용 가능하다.
    // 아래와 같은 경우 foreach를 끝내고 싶었지만 arrayLoop 함수 자체가 종료된다.
    val arr = arrayOf(1,-1,2)
    fun arrayLoop1(){
        println("loop1 start")
        arr.forEach {if(it<0) return
            println(it) }
        println("loop1 end")
    }
    arrayLoop1()

    // 아래와 같이 라벨을 사용하여 정확히 어떤 람다에서 반환을 사용할지 명시할 수 있다.
    fun arrayLoop2(){
        println("loop2 start")
        arr.forEach {if(it<0) return@forEach
            println(it) }
        println("loop2 end")
    }
    arrayLoop2()

    /*
    * 클로저와 스코프
    * 함수가 호출될 때 발생하는 데이터를 함수 호출 후에도 그대로 유지해 이용하는 기법
    * */
    fun closureTest(x:Int):(Int)->Int{
        println("arg : $x")
        return {it*x}
    }

    val someFun1 = closureTest(2)
    val someFun2 = closureTest(3)

    println("${someFun1(10)}")
    println("${someFun2(10)}")

    /*
    * 클로저는 명시적으로 사용하는 개념은 아니고, 코틀린에서의 람다와 함수형 프로그레밍 사용에서 적용되는 개념이다.
    *
    * 근대 이건 도대체 무슨 소리인지 모르겠다.
    * */
}
/*
* 인자를 함수로 받는 함수
* */
fun hoFun(x1: Int,argFun:(Int)->Int){
    val result = argFun(10)
    println("x1 : $x1, somefun : $result ${argFun}")
}

/*
* 기본값을 명시한 고차함수
* */
fun hoFun2(x1:Int,argFun1:(Int)->Int,argFun2:(Int)->Boolean = {it>10}){

    val result =argFun1(x1)
    println("result : ${argFun2(result)}")
}
/*
* 함수를 반환 하는 고차함수
* when 앞에 리턴을 명시해 준다면 when의 구성요소 각각에 return을 명시 하지 않아도 된다.
* */
fun hoFun3(str: String):(x1:Int,x2:Int)->Int{
    return when(str){
        "+" -> {x3,x4 -> x3+x4}
        "-" -> {x1,x2 -> x1-x2}
        "/" -> {x1,x2 -> x1/x2}
        "*" -> {x1,x2 -> x1*x2}
        else -> {return {x1, x2 -> 0  }}
    }
}
/*
* 함수 참조를 이용하는 상황
* */
fun hoFun4(argFun:(x:Int)->Int){
    println("${argFun(10)}")
}
fun nameFun(x:Int):Int{
    return x*5
}

class User(){
    lateinit var name:String
    var age:Int? = null

    fun printHello(){println("hello $name")}
    fun printInfo(){println("name : $name, age: $age")}
}
class User2(){
    lateinit var name:String
    var age:Int? = null

    constructor(name:String, age:Int):this(){
        this.name = name
        this.age = age
    }
}

inline fun inlineTest2(argFun:(x:Int,y:Int)->Int):Int{
    return argFun(10,0)
}
/*
*   if(y<=0)return 에서 해당 람사함수를 끝내고 싶지만 람다는 return을 사용하지 못하기에 에러가 발생
*   솔루션 1 해당 함수를 inline으로 선언
* */
fun callFun(){
    println("callFun top")
    val result = inlineTest2 { x, y ->
        if(y<=0)return
        x/y
    }
    println("$result")
    println("callFun.. bottom")
}


/*
* 클레스를 상속받은 뒤 인라인으로 명시된 클레스(솔루션1)에서 사용하려하면 오류가 난다.
* inline으로 사용하려는 함수를 다른 함수에 대입하는 경우 에러가 발생한다.
* 이 경우 사용되는 예약어 crossinline
* crossinline으로 선언된 함수에 return을 사용하려 하면 해당 동작을 막는다.
* 함수를 inline으로 선언하였다고 해도 return을 하지 못하게 하려는 경우 사용하는 함수
*
*
*
*
* */
open class TestClass{
        open fun some(){}
}

inline fun inlneTest3(crossinline argFun:()->Unit){
    val obj = object : TestClass(){
        override fun some() = argFun()
    }
}





