package pakage_main.cp17

import pakage_main.cp16.Super


/**
 * 제네릭 : 형식타입, 임의타입
 * 선언할 때 임의로 타입을 지정한 뒤 실제 이용할 때 정확한 타입을 부여
 *
 * */
fun main(args:Array<String>){

    /** 제네릭 타입의 대표적인 예시 arrayOf() */
    val array = arrayOf("bowon",10,true)

    /** 제네릭을 사용한 기본적인 예시 */
    val obj1 = MyClass<String>()
    obj1.info = "Hello"

    val obj2 = MyClass<Int>()
    obj2.info = 10

    /** 지정한 인자에 따라 자동으로 형식 지정*/
    val obj3 = MyClass2(10)
    obj3.info = 20

    val obj4 = MyClass2("hello")
    obj4.info = "world"

    /** 복수의 인자르 제네릭으로 선언*/
    val obj5: MyClass3<String,Int> = MyClass3()
    obj5.info = "hello"
    obj5.data = 10

    val obj6 : MyClass4<MyHandler1>
//    val obj7 : MyClass4<MyHandler2> <- 에러

    /**
     * 클레스 상하위간의 캐스팅
     * Super1로 선언된 obj8을 다시 Sub1으로 다운 그레이드 시킨다.
     * */
    val obj8 : Super1 = Sub1()
    obj8.sayHello()

    val obj9: Sub1 = obj8 as Sub1
    obj9.sayHello()

    /**
     * 제네릭의 캐스팅 시도
     * 제네릭은 타입이기 때문에 일반적인 캐스팅은 불가능
     * 아래와 같은 사용을 하려면 out, in 어노테이션을 사용해야 한다.
     * 해당 어노테이션이 없으면 제네릭은 형변환이 불가능하다. (invariance : 무공변)
     *
     * */
    val obj10 = MyClass7<Sub2>()
//    val obj11 = MyClass7<Super2> = obj10 <- 에러
    /** out 어노테이션을 사용한 경우 아래와 같은 사용이 가능하다. */
    val obj12 = MyClass8<Sub3>()
    val obj13: MyClass8<Super3> = obj12


    val obj14 = MyClass9<Sub4>(Sub4())
    val obj15 : MyClass9<Super4> = obj14

    val obj16 = MyClass9<Super4>(Super4())
    /**
     * out으로 선언 할 경우 하위 타입을 상위타입으로 캐스팅 할 수 있다.
     * 하지만 상위 타입을 하위타입으로 변환할 수 없다.
     * */
//    val obj17:MyClass9<Sub4> = obj16 <- 에러

    /**
     * out의 사용 예시
     * List VS MutableList = out VS non out
     *
     * */

    val mutableList1 : MutableList<Int> = mutableListOf(10,20)
//    val mutableList2 : MutableList<Number> = mutableList1 <- 에러

    val immutableList1: List<Int> = listOf(10,20)
    val immutableList2 : List<Number> = immutableList1

    /**
     * in 어노테이션 사용 예시
     * **/
    val obj17 = MyClass10<Sub5>()
//    val obj18:MyClass10<Super5> = obj17 <- 에러  하위 타입을 상위 타입으로 선언하려 하는 경우

    val obj19 = MyClass10<Super5>()
    val obj20 : MyClass10<Sub5> = obj19


    tempSome(tempClass<Int>(10))
    tempSome(tempClass<Number>(10))

    /**
     * Array는 제네릭으로 선언되어 있다.
     * Any타입으로 전달으 ㄹ받도록 하면 에러
     * Any -> Int 의 관계는 성립하지만           (prop ->prop)
     * Array<Any> Array<Int> 는 성립하지 않는다.  (type ->type)
     * */
    val arrTest1 = arrayOf(1,2,3)
    val arrTest2 = Array<Int>(3){x->0}
//    val arrTest2 = Array<Any>(3){x->0}


//    copy(arrTest1,arrTest2)
//    arrTest2.forEach { println(it) }


/**
 * mutable을 통한 *와 Any? 의 차이점
 * Any? 가 (거의?)모든 범위를 지정하는 타입인것은 맞지만 어찌되었든 명확한 타입이다.
 * Any? 로 선언했기에 뒤에서 사용할 때 Any로 지정하려하면 에러가 생긴다.
 *
 * * 는 이후 지정되는 타입을 따르겠다는 의미
 *
 * 물론 목적은 다르지만 out Any? 와는 의미가 같다. 상위인 Any? 를 Any로 타입 변환하기에
 * */
    val starList0 : MutableList<out Any?> = mutableListOf<Any>(10,10.0,"bowon")
    val starList1 : MutableList<Any?> = mutableListOf(10,10.0,"bowon")
    val starList2 : MutableList<*> = mutableListOf(10,10.0,"bowon")
    val starList3 : MutableList<*> = mutableListOf<Any>(10,10.0,"bowon")



}

/**
 * 제네릭의 선언과 이용
 * 제네릭을 사용하기 위에서는 클레스 선언에서 T 타입을 선언해야 한다.
 * 단 여기서 T를 사용하는 것은 관습일 뿐이며, 다른 문자를 넣어도 상관은 없다.
 * */

//class MyClass{ <- 에러
//    var info:T? = null
//} Any?
class MyClass<T>{
    var info: T? = null
}
/** 생성자를 제네릭으로 선언 */
class MyClass2<T>(no: T){
    var info:T? = null
}
/** 다수의 인자를 제네릭으로 선언*/
class MyClass3<T1,T2>{
    var info: T1? = null
    var data: T2? = null

    /** 클레스 내부에서 제네릭 인자와 리턴을 사용하는 함수 */
    fun myFun(arg:T1):T2?{
        return data
    }
}
/** 최상위 레벨 함수에서의 제네릭*/
fun <T> someFun(arg: T): T? {
    return null
}
/**
 * 제네릭 제약 : 특정 타입을 지정하여 해당 타입으로만 지정할 수 있도록 범위를 제약시칸다.
 * */

/** 제네릭 제약을 통해 T의 타입을 Number로 한정시킨 예시*/
class MathUtil<T:Number>{
    fun plus(arg1:T,arg2:T):Double{
        return arg1.toDouble() + arg2.toDouble()
    }
}

/**
 * 다수의 제네릭 인자에 제약 조건을 설정 할 수도 있다.
 * 인터페이스와 연계하여 사용하는 예제
 * where 예약어를 사용하여야 한다.
 * 한국어가 맞나......
 * 두가지의 인터페이스를 모두 받은 Handler1은 인자로 사용 가능
 * 하지만 Handler1은 사용가능
 * */
interface MyInterface1
interface MyInterface2
class MyHandler1: MyInterface1,MyInterface2

class MyHandler2: MyInterface1

class MyClass4<T> where T: MyInterface1,T:MyInterface2{

}


/**
 * 제네릭의 Null 불가 제약
 * 제네릭의 형식 타입은 기본적으로 Null을 허용한다. ( Type == Any? )
 * */

class MyClass5<T>{
    fun myFun(arg1:T,arg2:T){
        /**명시적으로 Null을  허용하지는 않았지만 Null 안정성 처리를 해 주어야 한다.*/
        println(arg1?.equals(arg2))
    }
}
/**타입을 명시적으로 Any로 지정하는 경우 Null을 허용하지 않는다.*/

class MyClass6<T :Any>{
    fun myFun(arg1:T,arg2:T){
        println(arg1.equals(arg2))
    }
}

/**
 * Variance - 가변,공란
 * 타입의 변형
 *
 * */

open class Super1{
    open fun sayHello(){
        println("Super sayHello")
    }
}
class Sub1 : Super1(){
    override fun sayHello() {
        println("Sub sayHello")
    }
}

open class Super2
class Sub2 : Super2()
class MyClass7<T>


open class Super3
class Sub3 : Super3()
class MyClass8<out T>


/**
 * out 어노테이션으로 선언할 때 이용 방법
 * 1. 하위 타입 -> 상위타입에 대입 가능
 * 2. 상위 타입-> 하위타입으로 대입 불가능
 * 3. 함수의 반환 타입으로 선언 가능
 * 4. 함수의 매개뱐수 타입으로 선언 불가능
 * 5. val 프로퍼티에 선언 가능
 * 6. var 프로퍼티에 선언 불가능
 *
 * out의 사용 의의 -> 제네릭 타입의 형 변환 가능
 *
 * */
open class Super4
class Sub4 : Super4()

class MyClass9<out T>(val data:T){
    val myVal:T?= null
    /** out으로 선언된 것은 var로 사용하려할 경우 에러 */
//    var myVal2:T?=null

    fun myFun():T{
        return data
    }
    /** out으로 선언된 형식타입을 매개변수로 사용하려 하면 에러 */
//    fun myFun3(arg:T){}
}

/**
 * in 어노테이션
 * 1. 하위 제네릭 타입- > 상위 제네릭 타입에 대입 불가능
 * 2. 상위 제네릭 타입 -> 하위 제네릭 타입에 대입 가능
 * 3. 함수의 반환 타입으로 선언 불가능
 * 4. 함수의 매개변수 타입으로 선언 가능
 * 5. var 또는 val 프로 퍼티에 선언 불가능
 *
 * */
open class Super5
class Sub5:Super5()
class MyClass10<in T>(){

    /**
     * val, var 프로퍼티 선언 하려 하여 오류
     * 반환타입으로 선언하려하기에 불가능
     * */
//    val myVal:T? = null
//    val myVal2:T? = null
//    fun myFun():T?{
//        return null
//    }
    fun myFun(arg:T){}
}



/**
 * 타입 프로젝션
 * 이용측 Variance
 * 선언시에 in, out 어노테이션을 사용 할 수 있다
 * 이 경우 해당 타입을 이용하는 모든 곳에 적용 된다.
 * 하지만 사용사에 in, out 어노테이션을 사용하여 제한적으로 사용할 수도 있다.
 *
 * */

class tempClass<T>(val data:T){
    fun myFun():T{
        return data
    }
    fun myFun2(arg:T){

    }
    fun myFun3(arg:T):T{
        return data
    }
}
/** 함수에서 사용하는 부분에서 in 을 명시 */
/** out 을 명시하면 메인에서 에러
 * 함수 이용에 제약 사항이 있다는 것을 기억해야 한다.
 *
 * */
fun tempSome(arg:tempClass< in Int>){
    arg.myFun()
    arg.myFun2(10)
    arg.myFun3(10)
}
/** out이 사용되고있는 예시 - Array
 * 타입을 명시하지 않고 Any로 사용할 경우
 *
 * */
//fun copy(from:Array<Any>, to:Array<Any>){
//    for (i in from.indices)
//        to[i] = from[i]
//}
/**
 * 제네릭 사이에서의 상위->하위 형변환이 가능해지도록 하는 out을 추가하면 실행
 * */
//fun copy(from:Array<out Any>, to:Array<Any>){
//    for (i in from.indices)
//        to[i] = from[i]
//}

/**
 * star 프로젝션 : 제네릭 타입을 모른다.
 * 어떤 의미로 지정 될 지 모르겠다는 의미
 *
 * **/
//class starClasss<*>
class GenClass<T>

fun starFun(arg:GenClass<*>){}

fun star1(arr:MutableList<Int>){
    arr.add(10)
}

/**
 * out으로 사용되었기에 제네릭 타입 매개변수가 선언된 함수는 이용 불가
 * * 도 out으로 선언된 것과 같은 제약을 보인다.
 * Any?
 * 정리 : *의 사용은  out Any? 와 같다고 볼 수 있다.
 *
 * 작업 이후에 어떤 결과를 받을 때 사용
 * **/
//fun star2(arr:MutableList<out Any?>){
//    arr.add(10)
//}fun star3(arr:MutableList<*>){
//    arr.add(10)
//}
/**
 *<in T> 로 선언한 제네릭 타입에서의 star는 in Nothing과 같다.
 * 반환타입 지정 불가
 * 또한 대입되는 타입을 모르기 때문에 함수 호출 불가
 *
 * in Nothing 은 이후 설명
 * */

class StarClass0<in T>{
    fun myFun(a:T){}
    fun myFun2(){}
}
//fun inStar1(arg :StarClass0<*>){
//    arg.myFun(10)
//    arg.myFun2()
//}
fun inStar2(arg :StarClass0<in Any>){
    arg.myFun(10)
    arg.myFun2()
}
//fun inStar3(arg :StarClass0<in Nothing>){
//    arg.myFun(10)
//    arg.myFun2()
//}
//


/**
 * 제네릭과 as is
 * 제네릭은 컴파일 후에는 사라지는 정보이다. 따라서 형변환과 같이 쓰려면 주의가 필요하다
 *
 * */

fun isFun1(arg:List<Int>){
    if (arg is List<Int>){
        println(arg.sum())
    }
}

/**컴파일 시점엣 제거되는 타입인 * 타입이 Int인지 확인할 수 없어서 에러가 발생*/
//fun isFun2(arg:List<*>){ <- 에러
//    if (arg is List<Int>){
//        println(arg.sum())
//    }
//}
/**
 * 아래와 같이 refiled를 사용하여 시행 시점까지 제네릭 타입을 유지 시킬 수 있다.
 * */
inline fun <reified T> isFun3(arg:List<T>){
    if (arg is T){
        println(arg)
    }
}



/** 확신할 에러 메세지는 아니지만 경고
 * 런타임 에러 발생
 * List<String> 을 List<Int> 로 형변환 시키려는 동작을 수행
 * */
fun asFun1(arg:List<*>){
    val intList = arg as List<Int>
    println(intList.sum())
}
fun asFun2(arg:List<*>){
    asFun1(listOf(10,20))
    asFun1(listOf("hello","bowon"))
}

inline fun <reified> asFun3(arg:List<*>){
    asFun1(listOf(10,20))
    asFun1(listOf("hello","bowon"))
}

/**
 * Unit은 자바에서의 void과 같은 역할을 하지만
 * Unit은 타입이라는 차이점이 있다.
 *
 * 제넬릭에서의 이용 예시 : 제네릭 반환타입을 구현하는 경우
 * **/
interface UnitInterface<T>{
    fun myFun():T
}
class UnitClass1:UnitInterface<String>{
    override fun myFun():String{
        return "hello"
    }
}
class UnitClass2:UnitInterface<Unit>{
    override fun myFun():Unit{
    }
}

/**
 * Unit vs Nothing
 * Nothing은 값이 확실히게 없을을 표시한다. null값만이 들어갈 수 있다.
 * Unit은 반환값이 Unit임을 의미한다. Unit을 리턴을
 *
 * **/

/**
 * Nothing의 쓰임 ->  일반적인 사용
 * 유지보수 측면에서의 용이함
 * 값이 확실하게 없다는 의미로 사용
 *
 *  Nothing의 쓰임 -> 제네릭에서의 이용
 * Nothing타입의 데이터는 어떠한 다른 타입의 데이터에도 적용 가능한 특징
 * 물론 Nothing은 null 값만 가지고 있기에 일반적으로는 의미를 가지기 힘들다.
 * */

/**
 * 제네릭과 함께 사용하는 경우 어떤 타입의 클레스이건 객체를 넣을 수 있는 타입으로 활용 가능하다
 *
 * in Nohinf은 <in T> 타입과 같은 의미로 사용을 할 수 있다.
 * */
class LastClass<T>

fun nothingFun(arg:LastClass<in Nothing>){
    arg.toString()
}

fun nothingTest(){
    val myVal1 : Nothing? = null

    val myVal2:Int? = myVal1
    val myVal3:String? = myVal1

    nothingFun(LastClass<Int>())
    nothingFun(LastClass<String>())
}






