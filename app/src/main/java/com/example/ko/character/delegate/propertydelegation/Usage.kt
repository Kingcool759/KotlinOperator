package com.example.ko.character.delegate.propertydelegation

/**
 * @Author : zhaojianwei02
 * @Date : 2023/3/31 7:34 PM
 * @Description :
 */
class Usage {
    fun main() {
        val e = Example()
        println(e.p)
        Example().p = "Hello, Android!"
    }
}