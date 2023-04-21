package com.example.ko.design.statemachine

import com.example.ko.design.statemachine.state.StateA
import com.example.ko.design.statemachine.state.StateB
import com.example.ko.design.statemachine.state.StateC

/**
 * @Author : zhaojianwei02
 * @Date : 2023/4/21 6:12 PM
 * @Description :
 */
class LauncherStarter {
    companion object {
        var stateMachine: StateMachine? = null

        const val MSG_GO_TO_A = 1
        const val MSG_GO_TO_B = 2
        const val MSG_GO_TO_C = 3

        val stateA = StateA()
        val stateB = StateB()
        val stateC = StateC()

        fun start() {
            stateMachine = StateMachine()
            transitionTo(stateA)
        }

        fun transitionTo(state: IState) {
            // Exit the current state before transitioning to the new state
            stateMachine?.currentState?.exit()

            // Update the state machine's current state to the new state
            stateMachine?.currentState = state

            // Enter the new state
            state.enter()
        }
    }
}