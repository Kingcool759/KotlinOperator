package com.example.ko.design.statemachine


import android.os.Message

/**
 * @Author : zhaojianwei02
 * @Date : 2023/4/21 6:09 PM
 * @Description :
 */
interface IState {
    fun enter()
    fun exit()
    fun handle(message: Message): Boolean
}

fun IState.transitionToMe() {
    LauncherStarter.transitionTo(this)
}