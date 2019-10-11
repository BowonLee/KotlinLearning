package pakage_main

fun main(args:Array<String>){
    //val obj1 = SuperAbstact()
    val obj2 = SubAbstract1()
    val obj3 = SubAbstract2(30)
    obj2.myFun1()
    obj2.myFun2()

    obj3.myFun1()
    obj3.myFun2()


    println("############Interface###############")
    val obj4 = InterfaceClass()
    obj4.myFun1()
    obj4.myFun2()
    println("############InterfaceSub###############")
    val obj5 = InterfaceClassSub()
    obj5.myFun1()// 상속된 추상화
    obj5.myFun2()// 추상화 + 상속
    obj5.myFun3()// 추상화

    val obj6 = ClassInterfaceProperty1()
    println(obj6.prop1)
    println(obj6.prop3)
    println(obj6.prop4)

    val obj7 = ClassInterfaceProperty2()
    println(obj7.prop1)
    println(obj7.prop3)
    println(obj7.prop4)
    println("############SameFunction###############")
    val obj8 = ClassSameFunction()
    obj8.sameFun()

}

//프로퍼티도 추상 형으로 생성 가능
abstract class SuperAbstact{
    val data1:Int = 10

    abstract val data2:Int

    fun myFun1(){
        println("SuperClass : data1 = $data1 data2 = $data2")
    }
    abstract fun myFun2()
}

// 재정의 하던가
class SubAbstract1() :SuperAbstact(){

    override val data2: Int = 20
    override fun myFun2() {
       println("SubAbstract1 : data1 = $data1 data2 = $data2")
    }
}

//생성자 단계에서 입력 받던가
class SubAbstract2(override val data2: Int) :SuperAbstact(){
    override fun myFun2() {
        println("SubAbstract1 : data1 = $data1 data2 = $data2")

    }
}
//open, abstact 명령어럾이 생성
// 뒤에 중괄호도 없는 경우 abstract로 선언
// 중괄호가 존재하는 경우 open과 같은 취급

interface MyInterface{
    var data1 : String
    fun myFun1(){println("interface's function")}
    fun myFun2()
}
interface MyInterface2{

    fun myFun1(){println("interface's function")}
    fun myFun3()
}
// 인터페이스가 인터페이스를 상속
// 같은 이름을 가진 함수를 2개이상 받는다면 에러


interface MyInterfaceSub : MyInterface, MyInterface2{
      override fun myFun1()
}

// 인터페이스 적용
open class InterfaceClass() : MyInterface{
    override var data1: String = "Hello Interface"
//     override fun myFun1() {
//        super.myFun1()
//        println("Class's Function1")
//    }

    override fun myFun2() {
        println("Class's Function2")
    }
}
// 상속 + 추상화
// 여러 단계를 거쳐 상속받는 경우 한번만 재정의 하면 된다
// MyInterface를 각각 상위 클레스와 해당 인터페이스를 상속받은 클레스를 통해 받음
class InterfaceClassSub() : InterfaceClass(), MyInterfaceSub{

    override fun myFun1() {
        println("InterfaceClassSub Function1")
    }
    override fun myFun2() {
        super.myFun2()
        println("InterfaceClassSub Function2")
    }
    override fun myFun3() {
        println("InterfaceClassSub Function3")
    }



}

// 추상형으로 선언할 수 있다.
// 추상형이 아닌 경우 val 은 get 이 필요하다
// var 의 경우는 get,set 모두 필요하다.
// field는 사용 불가능하다 field만 불가능하고 다른 로직은 추가 가능하다.

interface InterfaceProperty{

    var prop1:Int
    //val prop2:String = "name"
    val prop3:String
        get() = "bowon"
    var prop4:String
        get() = "bowonlee"
        set(value) {

        }

}
class  ClassInterfaceProperty1() : InterfaceProperty{
    override var prop1: Int = 10
    override val prop3: String
        get() = super.prop3
    override var prop4: String
        get() = super.prop4
        set(value) {}
}
// 구현하는 부분에서는 field 값 사용 가능
class  ClassInterfaceProperty2() : InterfaceProperty{
    override var prop1: Int = 30
    override val prop3: String = "123"
        get() = super.prop3 + field
    override var prop4: String
        get() = super.prop4
        set(value) {
        }
}

interface InterfaceSameFunction1{
    val smae:String
    fun sameFun(){println("smaeFun1")}

}
interface InterfaceSameFunction2{
    val smae:String
    fun sameFun(){println("smaeFun2")}
}

// 같은 이름의 함수가 있는 경우 추상형이 아니더라도 반드시 오버라이드 받아야 한다
class ClassSameFunction:InterfaceSameFunction1,InterfaceSameFunction2{
    override val smae: String = "smae"

    // 타입 명시를 통해 이전 클레스 받을 수 있다.
    // 두 클레스 모두 받을 수도 있다.
    override fun sameFun() {
        super<InterfaceSameFunction1>.sameFun()
        super<InterfaceSameFunction2>.sameFun()
        println("smaeFun3")
    }
    // 오버라이드가 아니더라도 받을 수 있다.
    fun fun2(){
        super<InterfaceSameFunction1>.sameFun()
    }


}
