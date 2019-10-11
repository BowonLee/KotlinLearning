package pakage_main

import kotlin.properties.Delegates


/*
* class 혹은 파일 역역에서 선언해야 getter/setter 적용 확인 할 수 있음
* val로 선언되어 있을 경우 문법 오류
* */
class PropertyTest{
    var greeting : String = "Property"

        set(value) {
        println("running setter !!!")
        field = "Hello : " + value
        }
        get() {
        println("running getter !!!")
        return field
        }

    var temp : String  by Delegates.observable("noting", { property, oldValue, newValue ->
        println("old : $oldValue new : $newValue prop : ${property.getter}")
    })

}