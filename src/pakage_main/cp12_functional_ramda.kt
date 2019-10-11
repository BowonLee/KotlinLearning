package pakage_main



/*
* 순수 함수의 조건
* 1. 매개변수가 같다면, 항상 같은 값을 반환한다.
* 그렇다면 서버와 통신하는 경우는 어떨까?
* 혹은 외부 조건에 따라 결과값이 달라지는 경우는
* - 이 경우는 네트워크의 상태와 같은 외부 조건또한 매개 변수로 보아야 하나
*
* 2. 함수 내부에서 별도의 입출력이 발생하지 않는다.
*
*
*
*
* */
fun main(args:Array<String>){

  //  superFun()

    // 함수를 변수(정확히는 프로퍼티)에 대입하여 사용 할 수 있다.
    // 람다식 함수 대입
    val funVal1 = {x1:Int ->
        println("Hello Ramda")
        x1*10
    }
    // 반환값 까지 출력
    println(funVal1(10))

    // 반환값이 출력되지 않음 또한 함수가 실행 되지도 않음
    println(funVal1)



    // 함수 참조 - Functional Reference
    fun someFun(x:Int):Int{
        println("I'm some function")
        return x*x
    }
    val funVal2 = ::someFun
    /*
    println("$funVal2")
     Introspecting local functions, lambdas, anonymous functions and local variables is not yet fully supported in Kotlin reflection
    해당 코드는 지원되지 않는다 "아직" 일단은 이런식으로 사용하지는 못한다는 것으로 생각

    인자값이 있는 함수를 함수 참조를 이용하여 변수에 대입한 뒤 해당 변수를 그대로 사용하려 하면 런타임 에러가 발생한다.

    람다식의 경우 해당 식의 매개변수와 반환 타입이 출력되는 것을 확인 할 수 있다.
    */
    println(funVal2(5))





    // 선언 된 람다 변수를 새로운 변수에 입력 받는 방식
    val ramdaSum1 = sum1(10,20)
    println(ramdaSum1)

    // 람다함수를 바로 선언하여 사용 하는 경우
    // 해당 경우는 변수 뒤 소괄호가 없는 경우 람다식 양식이 출력된다.
    // 즉시 생성해서 사용하는 것과 생성 된 것을 입력받는 방식 사이에는 차이점이 있는 듯 하다.
    // 반환 타입을 명시한다면 람다식의 인자, 반환 타입 을 명시해야 한다
    val nonParmRamda: () -> Int = {  sum1(5,15) + 100 }
    println(nonParmRamda())
    println(nonParmRamda)

    //typealias
    // 미리 선언해 두는 프로퍼티 타입
    val nonParmRamda2: Mytype  = {  sum1(5,15) + 500 }
    println(nonParmRamda2())
    println(nonParmRamda2)

    // 매개변수 타입을 생략한 람다
    // val nonTypeRamda = {x -> x*x}
    val nonTypeRamda:(Int)->Int = {x -> x*x}

    // 매개 변수가 하나일 때 it을 쓰는 람다
    // 함수의 축약에 의미가 있다.
    // 타입이 정의된 상태인 경우 람다식을 쓸 수 있다.
    val itLamda:(Int)->Int = {it * 100}

     data class User(val name:String,val age:Int){
        fun getKoreanAge():Int { return age - 1 }

    }


    val lamdas1 : (User) -> Int = {user: User -> user.age }
    println(lamdas1(User("bowon",27)))

    val lamdas2 : (User) -> Int = {it.age }
    println(lamdas2(User("bowon",27)))

    // 멤버 참조를 이용한 람다식
    // 클레스의 특정 멤버를 즉시 반환하고 싶다면 아래와 같은 사용이 가능하다
    val lamdas3 : (User) -> Int = User::age
    println(lamdas3(User("bowon",27)))

    // 멤버 프로퍼티 뿐만이 아닌 함수도 사용이 가능하다.
    val lamdas4 : (User) -> Int = User::getKoreanAge
    println(lamdas4(User("bowon",27)))




}
// 함수 혹은 클레스 내부에서는 선언되지 않는다.
// 최상위 블록? 에서만 선언 할 수 있다.
typealias Mytype = () -> Int

val sum1 = { x1:Int, x2:Int -> x1 + x2 }


fun superFun(){
    val superData = "Hello"
    fun subFun1(){
        println("subFun() .. superData : $superData")
    }
    fun subFun2(a:Int,b:Int): Int {
        subFun1()
        return a + b
    }
    // 해당 함수 외부에서는 호출 불가능
    class SubClass{

        fun classFun(){
            println("classFun() .. superData : ${superData}")
        }
    }

    subFun1()
    SubClass().classFun()
}

