package com.example.ko.character.delegate.classdelegation.entity

import com.example.ko.character.delegate.classdelegation.IPlayGame

/**
 * @Author : zhaojianwei02
 * @Date : 2023/3/31 6:34 PM
 * @Description : 王者玩家
 */
class KingPlayer(private val name: String) : IPlayGame {
    override fun rank() {
        println("KingPlayer: $name 排位中...")
    }

    override fun upgrade() {
        println("KingPlayer: $name 升级了")
    }
}