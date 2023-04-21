package com.example.ko.design.productconsum

/**
 * @Author : zhaojianwei02
 * @Date : 2023/4/21 5:07 PM
 * @Description : 生产者 - 消费者
 */

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect

class ProducerConsumer(private val queueCapacity: Int) {

    private val sharedFlow = MutableSharedFlow<Int>(replay = queueCapacity)

    fun start() {
        runBlocking {
            launch {
                repeat(10) { i ->
                    println("Producing element: $i")
                    sharedFlow.emit(i)
                    delay(1000)
                }
            }
            launch {
                sharedFlow.collect { number ->
                    println("Consuming element: $number")
                    delay(1000)
                }
            }
        }
    }

    fun addElement(element: Int) {
        sharedFlow.tryEmit(element)
    }
}

fun main() {
    val producerConsumer = ProducerConsumer(5)
    producerConsumer.start()

    for (i in 0 until 10) {
        producerConsumer.addElement(i)
    }

    Thread.sleep(5000)
}
