package com.example.ko.character.delegate.classdelegation.entity

import com.example.ko.character.delegate.classdelegation.IPlayGame

/**
 * @Author : zhaojianwei02
 * @Date : 2023/3/31 6:31 PM
 * @Description : 黄金玩家
 */
class GoldPlayer(private val name: String) : IPlayGame {
    override fun rank() {
        println("GoldPlayer:$name 排位中...")
    }

    override fun upgrade() {
        println("GoldPlayer:$name 升级了")
    }
}