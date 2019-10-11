package pakage_main

import kotlin.Int


class OperaterFunction{

    fun plusTest(): Int {
        return 10 + 5
    }

}
operator fun OperaterFunction.plus(no:Int):Int{
    return 100
}
