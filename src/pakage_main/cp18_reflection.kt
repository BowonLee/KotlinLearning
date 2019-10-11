package pakage_main.cp18

import com.sun.org.apache.xpath.internal.operations.Bool
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.full.declaredMemberFunctions

/**
 * Reflection
 * kotlin-reflect 의 의존성을 추가하여 사용한다.
 * 현 프로젝트는 사용법을 익히기 위한 임시 프로젝트이기에 라이브러리를 직접 받아 사용한다.
 * Gradle을 사용하는 프로젝트는 Gradle의 의존성을 추가하는 방식으로 의존성을 추가할 수 있다.
 * **/


/**
 * Reflaction이란?
 * 런타임에서 프로그램의 구조를 분석해 내는 기법,
 * 런타임에서 클레스명, 클레스, 함수, 프로퍼티를 동적으로 판단
 * */
fun main(args:Array<String>){
    analyzeClass(Number::class)
    analyzeClassMember(MyClass::class)
    reflectionFunction(::myFun1)
    println()
    /**
     * 리플렉션을 활용한 고차함수의 호출
     * */
    reflectionFun { n-> isOdd(n) }
    reflectionFun (::isOdd)

}
/**
 * 기본적인 구현방식
 * <*> 타입으로 프로퍼티를 선언되었다. 해당 프로퍼티에는 클래스의 객체가 표현
 * <클레스 타입> 이라면 특정 클레스로 한정지을 수 있다.
 * */
val myVal1:KClass<*> = String::class
fun myFun1(arg:KClass<*>){}
/**
 * 리플렉션의 목적은 클레스, 함수, 프로퍼티의 레퍼런스를 런타입에 동적으로 분석하는 것이 목적이다.
 * 따라서 레퍼런스를 분석하기 위한 다양한 방법을 제공한다.
 * */
open class MyClass

/** 클레스의 상태를 확인할 수 있는 함수들*/
fun analyzeClass(arg:KClass<*>){
    println("---------------------class Info---------------------")
    println("class's name is ${arg.simpleName}")
    println("isAbstract : ${arg.isAbstract}")
    println("isCompanion : ${arg.isCompanion}")
    println("isData : ${arg.isData}")
    println("isFinal : ${arg.isFinal}")
    println("iisInner : ${arg.isInner}")
    println("isOpen : ${arg.isOpen}")
    println("isSealed : ${arg.isSealed}")
}

/**
 * 클래스가 생성자를 포함하고 있는지, 매게변수는 어떻게 가지고 있느지를 확인.
 * p454 어떤 형식으로 만든 함수인지 구분해서 확인이 가능하다.
 * */

fun analyzeClassMember(arg : KClass<*>){
    println("declaredMemberFunctions")
    val functions = arg.declaredMemberFunctions
    for(functions in functions){
        println("${functions.name} : ${functions.returnType}")
    }

    println("memberFunctions")
    val functions2 = arg.declaredMemberFunctions
    for(functions2 in functions){
        println("${functions2.name} : ${functions2.returnType}")
    }

    println("declareMemberExtensionFunctions")
    val functions3 = arg.declaredMemberFunctions
    for(functions3 in functions){
        println("${functions3.name} : ${functions3.returnType}")
    }
}
/**
 * 함수의 이름이나, 매개변수, 타입도 구분하여 정보를 추출 할 수 있다.
 * */
fun reflectionFunction(argFun : KFunction<*>){
    print("${argFun.name}")
    val parms = argFun.parameters
    print("(")
    parms.forEachIndexed { index, parm ->
        print("${parm.name} : ${parm.type}")
        if(index<parms.size-1){print(",")}
    }
    print("):")

    print("${argFun.returnType}")
}
/**
 * 리플렉션을 활용하여 고차함수를 호출한다
 *
 * */
fun isOdd(x:Int):Boolean = x%2 != 0

fun reflectionFun(argFun:(Int)->Boolean){
    println("result:${argFun(3)}")
}









