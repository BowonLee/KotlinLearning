package pakage_main.model

data class User(val name:String, private val age:Int) {

    
    //val name = "??"

    init {
        /*action*/
        println("init data class $name")
    }
    fun hello(){
        print("Hello $name")
    }


    operator fun plus(target: User) : String{
        return "name : $name and ${target.name}  age : $age and ${target.age}"
    }
}
