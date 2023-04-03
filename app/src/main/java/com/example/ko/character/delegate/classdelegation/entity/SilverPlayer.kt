package com.example.ko.character.delegate.classdelegation.entity

import com.example.ko.character.delegate.classdelegation.IPlayGame

/**
 * @Author : zhaojianwei02
 * @Date : 2023/3/31 6:33 PM
 * @Description : 白银玩家
 */
class SilverPlayer(private val name: String) : IPlayGame {
    override fun rank() {
        println("SilverPlayer: $name 排位中...")
    }

    override fun upgrade() {
        println("SilverPlayer: $name 升级了")
    }
}