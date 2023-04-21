package com.example.ko.design.statemachine.state

import com.example.ko.design.statemachine.IState
import com.example.ko.design.statemachine.transitionToMe

import android.os.Handler
import android.os.Looper
import android.os.Message
import com.example.ko.design.statemachine.LauncherStarter

/**
 * @Author : zhaojianwei02
 * @Date : 2023/4/21 6:11 PM
 * @Description :
 */
class StateC : IState {
    override fun enter() {
        println("Entering State C")
    }

    override fun exit() {
        println("Exiting State C")
    }

    override fun handle(message: Message): Boolean {
        return when (message.what) {
            LauncherStarter.MSG_GO_TO_A -> {
                LauncherStarter.stateA.transitionToMe()
                true
            }
            else -> {
                false
            }
        }
    }
}