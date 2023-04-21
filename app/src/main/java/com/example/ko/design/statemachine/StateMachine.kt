package com.example.ko.design.statemachine

import android.os.Handler
import android.os.Looper
import android.os.Message
/**
 * @Author : zhaojianwei02
 * @Date : 2023/4/21 6:08 PM
 * @Description :
 */
class StateMachine {
    var currentState: IState? = null

    private val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            handleMessageInternal(msg)
        }
    }

    private fun handleMessageInternal(msg: Message) {
        currentState?.let {
            currentState!!.handle(msg)
        }
    }

    private fun handleMessage(msg: Message) {
        handler.sendMessage(msg)
    }

    fun sendMessage(what: Int) {
        val msg = Message.obtain()
        msg.what = what
        handleMessage(msg)
    }
}