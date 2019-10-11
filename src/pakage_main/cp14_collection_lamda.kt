package pakage_main.cp14


/**
* 컬렉션 타입 이란?
* 리스트 형태의 데이터 타입을 의미
*
* 순서가 있거나, 없거나, 별도의 인덱스가 존재하기도 한다.
* array
* Dictionary
* Set
* */

data class User(var name:String, var age:Int)
fun main(args: Array<String>){

    /**
    * 람다함수를 이용하여 컬렉션 타입을 제어하는 방법들
    * */
    val list = List(10,{it*3})

    val userList = listOf<User>(User("bowon",26),User("kkang",30),User("jiuen",35),User("juck",35))
    list.forEach { println("forEach : $it") }
    list.forEachIndexed { index, i -> println("forEachIndex : ($index,$i) ")  }

    /**
    * 리스트의 모든 데이터가 조건을 만족하는지 알아보는 all
    * 리스트의 데이터 중 하나라도 해당 조건을 만족하는지 알아보는 any
    * */
    println("all Test : ${userList.all { it.age>30 }}")
    println("any Test : ${userList.any { it.age>30 }}")

    /**
    * count : 해당 조건을 만족하는 결과가 얼마나 있는지 확인
    * find : 해당 조건을 만족하는 가장 처음값을 반환
    * findLast : 해당 조건을 만족하는 가장 마지막을 반환
    * */
    println("count test : ${userList.count { it.age>=30 }}")
    println("find test : ${userList.find { it.age>=30 }}")
    println("findLast test : ${userList.findLast { it.age>=30 }}")

    /**
    * fold 혹은 reduce로 지정을 하고 값을 지정한다면 이전까지의 값들의 상태가 저장된다.
    *
    * 초기값을 지정할 수 있는 fold와 지정할 수없는 reduce
    * right를 사용시 입력의 숫서가 반대로, 또한 매게변수의 순서도 반대이다.
    * reduce
    * reduceRight
    * fold
    * foldRight
    * */
    var result = listOf(1,2).fold(10) {total, next ->
        println("$total ... $next")
        total + next
    }
    println("fold test : $result")

    var result2 = listOf(1,2).foldRight(10) {next, total ->
        println("$total ... $next")
        total + next
    }
    println("foldRight test : $result2")

    /**
    * fold 를 이용하여 가장 큰 값을 반환하도록 한다.
    * */
    var result3 = listOf(1,11,15).fold(10){ max, next->
        if(next>max)next else max
    }
    println("fold test 2 : $result3")


    /**
     * 단순히 최대값을 가져오는 max
     * 특정 로직을 거친 뒤 최대값을 가져오는 maxBy
     * */
    var maxResult = listOf(1,11,5).max()
    println("max test : $maxResult")

    var maxByResult = listOf(1,11,5).maxBy { it%10 }
    println("maxBy test : $maxByResult")

    /**
    * 지정한 조건에 맞는 데이터가 있는지 알아보는 none
    * */
    val noneResult = listOf(1,11,5).none { it == 0 }
    println("none test : $noneResult")

    /**
    * 람다 함수를 거쳐 반환한 모든 값을 더하는 sumBy
    * 내장 된 람다의 반환타입은 반드시 Int타입이여야 한다..
    * */
    val sumByTest = listOf(1,2,3).sumBy { it }
    println("sumBy test : $sumByTest")


    /**
    * 특정 조건을 이용한 필터링을 가능하게 하는 filer
    * filterNot은 특정 조건에 해당하지 않는 값들을 반환한다. , filter와 반대의 기능
    * filterNotNull은 Null이 아닌 값들을 리턴한다.
    * list 형태와 map 형태를 다룰 수 있다.
    *
    * 활용도가 높은 함수
    * */
    val dummyList = listOf(12,9,5,28)
    val filterList = dummyList.filter { it>10 }
    val filterNotList = dummyList.filterNot { it>10 }


    filterList.forEach { println("filter List : $it") }
    filterNotList.forEach { println("filterNot List : $it") }
    dummyList.filterNotNull().forEach { println("filterNotNull test : $it") }


    val dummyMap = mapOf("one" to 15,"two" to 5)
    val filterList2 = dummyMap.filter { it.value > 10  }
    filterList2.forEach { println("filter Map : $it") }


    /**
     * drop : 앞에서부터 특정 지정한 만큼 데이터를 무시하고 나머지를 추출한다,
     * dropWhile : 매게변수로 람다함수를 지정하며 해당 람다식을 만족하지 않는 데이터 부터 추출한다.
     * Last의 경우 해당 기능을 반대로 한다.
     */

    listOf(1,2,3,4).drop(2).forEach { println("drop test : $it") }
    listOf(1,2,3,4).dropLast(2).forEach { println("dropLast test : $it") }

    /** 아래의 경우 10 이하인 2,1 은 drop하며, 해당 람다식을 만족하는 12부터 데이터를 추출한다.
     * dropWhileLast의 경우 해당 동작을 마지막부터 시작한다. 따라서 뒤의 3, 5 가 drop된 것을 확인할 수 있다.
     * */
    listOf(2,1,12,5,3).dropWhile{it<10}.forEach { println("dropWhile test : $it") }
    listOf(2,1,12,5,3).dropLastWhile{it<10}.forEach { println("dropLastWhile test : $it") }

    /**
     * slice, take, takeLAst, takeWhile, 종류의 함수들은 특정 기준으로 값을 추출한다.
     * 각각의 함수들의 차이점은 매개변수, 시작순서의 차이이다.
     * slice  : 인덱스를 기준으로 해당 값만을 추출한다.
     * take   : 숫자를 인자로 받아 해당 인자만큼 추출한다.
     * takeWhile   : 람다를 인자로 받아 조건에 맞지 않는 데이터가 나올 때 까지 추출한다.
     *
     * */
    listOf(11,12,5,23,4).slice(1 .. 3).forEach { println("slice test : $it") }
    listOf(11,12,5,23,4).take(3).forEach { println("take test : $it") }
    listOf(11,12,5,23,4).takeLast(3).forEach { println("takeLast test : $it") }
    listOf(11,12,5,23,5,4).takeWhile { it>10 }.forEach { println("takeWhile test : $it") }

    /**
    * 매핑 함수들 map, mapIndexed
     * 동작 자체는 foreach를 생각하면 편하다.
     * 하지만 실행 결과를 반환하기 때문에 map을 사용하여 데이터를 특정한 형태로 바꾸는대 사용된다.
    * */
    listOf(1,2,3,4).map { it*10 }.forEach { println("map test : $it") }
    listOf(1,2,3,4).mapIndexed { index, i -> index * i }.forEach { println("mapIndexed test : $it") }

    /**
     * 컬렉션 타입의 데이터를 특정 데이터로 묶을 때 사용하는 groupBy
     * 묶기위한 조건을 매개변수로 받아 반환값으로 Map 객체를 받는다.
     *
     * 아래의 경우 age 라는 조건을 groupBy에 인자로 넣었다.
     * 따라서 age를 key로 하는 Map 객체들 를 반환하여 사용할 수 있게 하였다.
     * 결과를 본다면 age가 같은 juck과 jieun이 35라는 key에 같이 들어가 있는 것을 볼 수 있다.
     * */
    userList.groupBy { it.age }.forEach{
        println("key : ${it.key} ... count : ${it.value.count()}")
        it.value.forEach { println("${it.name} .. ${it.age}") }
    }

    /**
     * 요소 함수 - 특정 데이터를 추출하거나, 존재 여부를 알아낸다.
     * */

    val tempList = listOf(1,2,3,4,5,6)
    /**
     * 특정 요소가 포함되어 있는지 알아보는 contains이다. 반환값은 Boolean 이다.
     * */
    println("contain test : ${tempList.contains(7)}")
    /**
     * elementAt : index를 기준으로 특정 위치의 데이터를 추출한다.
     * elementAtOrElse : index를 기준으로 특정 위치의 데이터를 추출한다. 해당 인덱스가 존재하지 않을 경우 같이 인자로 전달한 람다식이 실행된다.
     * elementAtOrNull : index를 기준으로 특정 위치의 데이터를 추출한다. 해당 인덱스가 존재하지 않을 경우 null 을 리턴한다.
     * */

    println("elementAt test : ${tempList.elementAt(2)}")
    println("elementAtOrElse test : ${tempList.elementAtOrElse(100,{  "$it is Wrong value" })}")
    println("elementAtOrNull test : ${tempList.elementAtOrNull(100)}")

    /**
     * first        : 람다함수를 받고 조건에 맞는 가장 첫번째 값을 반환
     * firstOrNull  : 조건에 맞는 데이터가 없으면 null 반환
     * last         : 조건에 맞는 가장 마직막 값 반환
     * lastOrNull   : 조건에 맞는 데이터가 없으면 null 반환
     * indexOf      : 메게변수가 위치하는 첫번째 인덱스 값
     * indexOfFirst : 람다식을 인자로 받아 조건을 만족하는 첫번째 값이 있는 인덱스를 반환
     * indexOfLast  : 람다식을 인자로 받아 조건을 만족하는 마지막 값이 있는 인덱스를 반환
     * */

    println("first test : ${tempList.first { it%2 == 0 }}")
    println("firstOrNull test : ${tempList.firstOrNull() { it == 0 }}")
    println("last test : ${tempList.last { it%2 == 0 }}")
    println("lastOrNull test : ${tempList.lastOrNull { it == 0 }}")
    println("index test : ${tempList.indexOf(2) }")
    println("indexOfFirst test : ${tempList.indexOfFirst { it%2==0 } }")
    println("indexOfLast test : ${tempList.indexOfLast { it%2==0 } }")


    /**
    *  정렬 함수
     *  reversed            : 데이터의 순서를 뒤집는다.
     *  sorted              : 숫자를 오름차순으로, 문자를 알파벳순으로 정렬한다.
     *  sortedBy            : 람다함수를 인자로 받아 람다식의 결과를 정렬한다.
     *  sortedDescending    : 결과는 같으나 내림차순으로 한다.
     *  sortedDescendingBy  : 람다함수를 인자로 받아 결과를 내림차순으로 정렬한다.
    * */

    var tempString  = "12345678"
    tempString = tempString.drop(2)
    println(tempString)


}