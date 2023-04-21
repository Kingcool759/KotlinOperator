package com.example.ko

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.ko.character.delegate.classdelegation.usage.Usage
import com.example.ko.design.statemachine.LauncherStarter

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
//        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//        assertEquals("com.example.ko", appContext.packageName)
    }

    @Test
    fun testClassProxy() { // 类委托
        val usage = Usage()
        usage.main()
    }

    @Test
    fun testPropertyProxy() {
        val usage = com.example.ko.character.delegate.propertydelegation.Usage()
        usage.main()
    }

    @Test
    fun testStateMachine() {
        // Start the state machine
        LauncherStarter.start()

        // Send messages to transition between states
        LauncherStarter.stateMachine?.sendMessage(LauncherStarter.MSG_GO_TO_B)
        LauncherStarter.stateMachine?.sendMessage(LauncherStarter.MSG_GO_TO_C)
        LauncherStarter.stateMachine?.sendMessage(LauncherStarter.MSG_GO_TO_A)
        LauncherStarter.stateMachine?.sendMessage(LauncherStarter.MSG_GO_TO_B)
    }
}