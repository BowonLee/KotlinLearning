package pakage_main

class ForTest1{}
class ForTest2(name: String){

    init {
        println("ForTest init")
    }
    /* error 반대는 성립 불가
    constructor() : this() {
        println("ForTest Construct")
    }*/
}
class ForTest3(){

    init {
        //init은 무조건 실행 됨
        println("ForTest init")
    }
    //this에 의해 무조건 실행됨을확인
    constructor(name: String) : this() {
        println("ForTest Construct  name : $name" )
    }
    constructor(name: String, age :Int) : this() {
        println("ForTest Construct  name : $name age : $age")
    }
}
class ForTest4(){

    init {
        //init은 무조건 실행 됨
        println("ForTest4 init")
    }

    constructor(name: String) : this() {
        println("ForTest4 Construct  name : $name" )
    }
    constructor(name: String, age :Int) : this() {
        println("ForTest4 Construct  name : $name age : $age")
    }
}