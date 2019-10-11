package pakage_main.cp15

/**
 * 코틀린에서의 NPE(NullPointException)를 회피하는 방법들 및
 * 예외처리의 기법들
 * */

fun main(args: Array<String>){

    /**
     * 코틀린의 Null
     * 프로퍼티의 타입을 Null 허용과 Null 비허용으로 나누어 사용한다.
     * nullable객체에 notNull을 입력할 수 있지만 그 반대는 불가능하다.
     *
     * nullable이 notNull 보다 더 상위의 객체
     * */

    var dataNotNull:String = "bowon"
    var dataNullable:String? = null

    println("[notNull : $dataNotNull] , [nullable : $dataNullable]")
    /**
     * nullable로 선언된 프로퍼티를 이용(접근)하는 방법들
     *
     * nullable로 선언 된 프로퍼티는 원래 .length 의 사용이 불가능하다.
     * 사용을 위해서는 아래와 같은 방법들을 사용해야 한다.
     **/

    /** 1. try - catch를 활용한 null 예외 처리*/
    var data1:String? = "bowon"
    val length = if(data1!=null){
        data1.length
    }else{
        null
    }
    println("[data1 : $data1],[length : $length]")

    /**
     * 2. 프로퍼티 뒤에 ?.를 붙여준다.
     * 또한 이 방법은 클레스간의 연결에서도 사용할 수 있다.
     *
     * ?. 을 이용하면 위에서의 예외처리와 같은 루틴을 거쳐 사용된다.
     * null이 아닐때만 사용되고, null인경우 null을 리턴한다.
     * */
    var length2 = data1?.length
    println("[data1 : $data1],[length : $length2]")

    class Address{
        var city:String?="seoul"
    }
    class User{
        val address:Address? = Address()
    }
    val user:User? = User()
    println(user?.address?.city)


    val array = arrayOf("Hello",null,"bowon")

    array.forEach {
        if (it != null){ println("$it ..${it.length}")}
    }

    /**
     * 중간에 들어있는 null을 회피할 수 있도록 한다.
     * */
    array.forEach {
        it?.let {
            println("$it .. ${it.length}")
        }
    }



    /**
     * :? - 엘비스 연산자
     * null을 사용하는 경우를 포함한다.
     * null 일경우 null을 리턴하는 것이 아니라 특정 데이터를 반환한다.
     * */
    var dataElvis:String? = "bowon"


    var dataElvisLenth:Int = if (dataElvis!=null){
        dataElvis.length
    }else{
        -1
    }
    /**
     * 위의 조건문과 같은 기능을 한다.
     * */
    dataElvisLenth = dataElvis?.length ?: -1

    dataElvis = null
    println(dataElvisLenth)

    dataElvis ?: println("data is null")


    /**
     * !! : 일부러 NullPointException 을 발생시키는 연산자
     * 105 에서 NPE를 발생시킨다.
     * 코틀린에서는 !!를 붙이지 않으면 컴파일단계에서 에러가 난다.
     * */
//    data1!!.length
//    data1 =  null
//    data1!!.length

    /**
     * as? : 안전한 캐스팅
     * null이 아닐경우 캐스팅을 하고 null인경우 null을 반환한다.
     * 만일 as로 하는경우 이렇게 캐스팅하는 경우 컴파일 단계에서 ClassCastExcption이 일어난다
     * */

    val dataStr:String = "bowon"
    val dataInt:Int? = dataStr as? Int
    println(dataInt)


}





