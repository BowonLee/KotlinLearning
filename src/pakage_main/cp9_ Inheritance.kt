package pakage_main



fun main(args: Array<String>) {

    var rect = Rect()

    rect.name = "Rect"
    rect.print()

    println("############생성자 흐름####################")
    var sub1 = Sub1("Bowon",27)
    println("############ as 캐스팅####################")

    // 하위타입으로 선언 후 상위 타입으로 캐스팅

    val obj3:SuperCast = SubCast1() // -> 스마트 캐스팅 : 하위 Sub를 상위 Super로 변환하여 생성
    val obj4:SubCast1 = obj3 as SubCast1 // -> as를 통한 명시적 케스팅 상위 Super를 하위 Sub1으로 변환

    obj4.superFun()
    obj4.subFun1()


    // Class Cast Exception
    //
//    val obj5:SubCast1 = SuperCast() as SubCast1
//    obj5.subFun1()
  //  val obj6 : SubCast2 = SubCast1() as SubCast2

    //Type Cast Exception
    //as? 를 사용하ㄴ다면
    println("############ as? 캐스팅####################")

    val obj7:SuperCast? = null
    val obj8:SubCast1? = obj7 as? SubCast1

    // +  null 허용 객체는 관련 된 안정화 용법들이 있다
    obj8?.subFun1()
}

open class Rect : Shape(){
    var width: Int = 0
    set(value) {
        if(value<0) field = 0
        else field = value
    }
    var height:Int = 0
    set(value){
        if(value<0)field =0
        else field = value
    }

    // 상속 받은 함수를 다시 ovveride를 막으려면 final
    final override fun print() {
        //super를 통해 상위 클레스의 동작을 가져올 수 있음
        super.print()
        println("Rect : $name : location : $x, $y width : $width" )

    }
}
class Circle : Shape(){
    var r: Int = 0
        set(value) {
            if(value<0) field = 0
            else field = value
        }

}
class RoundRect : Rect(){
}
// open 명령어를 명시해야만 상속 가능
// 또한 각각의 구성 요소들 또한 각각 open을 명시해 주어야만 한다
open class Shape{
    var x:Int = 0
    set(value) {
        if(value<0) field = 0
        else field = value
    }
    var y:Int = 0
    set(value) {
        if(value<0) field = 0
        else field = value
    }

    lateinit var name:String

    // open 을 명시해 주어야 override 가능

    open fun print(){
        println("Shape : $name : location : $x, $y" )
    }
}

// null 허용 -> null 불가 관계이기에 이 관계를 역전 시킬 수는 없다
// 변수 변경 가능 -> 변수 변경 불가 관계 이기에
// 상속 관계에서 상위 클레스에서 정의 된 이름과 동일한 변수명은 사용 불가

//상위 클레스의 생성자는 하위 클레서에서 반드시 실행 되어야 한다.
open class Super{

    init {
        println("Super Init")
    }
    constructor(name:String){
        println("Super Constructor $name")
    }

}


class Sub1:Super{

    init {
        println("Sub1 Init")
    }

    constructor(subName:String):super(subName){
        println("Sub1 Constructor $subName")
    }
    constructor(subName:String,age:Int):this(subName){
        println("Sub1 Constructor $subName , $age")
    }


}

//as를 활용한 캐스팅
// as는 상위 , 하위관계에서만 사용되도록 한다.
open class SuperCast{
    fun superFun(){println("SuperFun()")}
}
class SubCast1:SuperCast(){
    fun subFun1(){println("SubFun1()")}
}
class SubCast2:SuperCast(){
    fun subFun2(){println("SubFun2()")}
}
