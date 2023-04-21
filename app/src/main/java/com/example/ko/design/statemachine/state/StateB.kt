package com.example.ko.design.statemachine.state

import com.example.ko.design.statemachine.IState
import com.example.ko.design.statemachine.transitionToMe

import android.os.Handler
import android.os.Looper
import android.os.Message
import com.example.ko.design.statemachine.LauncherStarter

/**
 * @Author : zhaojianwei02
 * @Date : 2023/4/21 6:10 PM
 * @Description :
 */
class StateB : IState {
    override fun enter() {
        println("Entering State B")
    }

    override fun exit() {
        println("Exiting State B")
    }

    override fun handle(message: Message): Boolean {
        return when (message.what) {
            LauncherStarter.MSG_GO_TO_C -> {
                LauncherStarter.stateC.transitionToMe()
                true
            }
            else -> {
                false
            }
        }
    }
}