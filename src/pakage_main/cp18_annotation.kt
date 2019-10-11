package pakage_main.cp18

import kotlin.reflect.KClass

/**
 * 어노테이션
 * 코드에 부가 정보를 추가하기 위해 클래스, 함수, 프로퍼티 앞에 붙는 구문
 * 목적
 * 1. 컴파일러에게 문법에러를 체크하기 위해
 * 2. 개발 툴이나 빌더에게 코드 자동 추가를 위해 정보 제공
 * 3. 실행 시 특정 기능을 추가하기 위한 정보
 *
 *
 * 단순 값을 지정해 놓는 경우 메타에데이터를 지정해놓고 사용하는 방식으로 사용이 가능하다.
 *
 * 특정 케이스에 대한 체크를 할 수도 있다.
 * */

/** 어노테이션은 직접작인 바디를 가지지 않는다. */
annotation class TestAnnotation

/** 이와 같은 방식으로 사용 */
@TestAnnotation
class ATest{
    @TestAnnotation
    val myVal: String = "hello"
    @TestAnnotation
    fun myFun(){}
}
/** 생성자에 어노테이션을 추가하려면 항상 생정자를 명시해야 한다.*/
@TestAnnotation
class ATest2 @TestAnnotation constructor(){
    @TestAnnotation
    val myVal: String = "hello"
    @TestAnnotation
    fun myFun(){}
}

fun main(args:Array<String>){
    val methods = ATest::class.java!!.methods

    /**
     * 클레스 내부의 요소를 검사하여, 어느 부분에 어노테이션이 등록되어 있는지 알아낼 수 있다.
     * 이 의미는 어노테이션을 설정한 부분에 다른 로직을 추가 할 수 있다는 의미이다.
     * */
    for(method in methods){
        val methodName = method.name
        if(method.isAnnotationPresent(TestAnnotation::class.java)){
            println("$methodName function has TestAnnotation")
        }
    }

    /**
     * 어노테이션의 생성자에 추가 된 count 데이터를 가져온 예제
     * */
    val obj:ATestValue = ATestValue()
    val methods2 = ATestValue::class.java!!.methods

    for(method in methods2){
        if (method.isAnnotationPresent(TestAnnotation2::class.java)){
            val annotation2 = method.getAnnotation(TestAnnotation2::class.java)
            val count = annotation2.count
            for(i in 1..count){
                obj.some()
            }
        }
    }
}

/**
 *  어노테이션에 특정 데이터를 설정하고 데이터를 이용하려는 경우
 *  생성자를 포함하는 방법을 사용할 수 있다.
 *
 *  들어갈 수 있는 타입들 : 자바의 기초타입, 문자열, 클레스(Foo::class), 이넘, 기타 어노테이션, 배열<기본적으로 들어갈 수 있는타입>
*/
annotation class TestAnnotation2(val count:Int)

class ATestValue{
    @TestAnnotation2(count = 3)
    fun some(){
        println("some")
    }
}

annotation class TestAnnotation3(val count:Int)
annotation class TestAnnotation4(val otherAnn:TestAnnotation3 , val arg1:KClass<*>)

class User
//annotation class TestAnnotation5(val user:User) <- Error

@TestAnnotation3(10)
@TestAnnotation4(TestAnnotation3(20),String::class)
class TestT{ }

const val myData:Int = 10
@TestAnnotation3(myData)
class TestT2{}

/**
 * 어노테이션 옵션 을 추가하여 특정 영역을 제한할 수 있다.
 * 클레스에만 추가하거나, 하는 등
 * @Target : 어디서 쓸지
 * */










