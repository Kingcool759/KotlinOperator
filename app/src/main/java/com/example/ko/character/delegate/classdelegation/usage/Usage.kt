package com.example.ko.character.delegate.classdelegation.usage

import com.example.ko.character.delegate.classdelegation.DelegateGamePlayer
import com.example.ko.character.delegate.classdelegation.IPlayGame
import com.example.ko.character.delegate.classdelegation.entity.GoldPlayer
import com.example.ko.character.delegate.classdelegation.entity.KingPlayer
import com.example.ko.character.delegate.classdelegation.entity.SilverPlayer

/**
 * @Author : zhaojianwei02
 * @Date : 2023/3/31 6:40 PM
 * @Description : 类委托实现
 */
class Usage {
    fun main() {
        val playerList = mutableListOf<IPlayGame>(
            GoldPlayer("小金"),
            SilverPlayer("小银"),
            KingPlayer("王者大佬")
        )
        playerList.forEach {
            val delegateGamePlayer = DelegateGamePlayer(it)
            delegateGamePlayer.rank()
            delegateGamePlayer.upgrade()
        }
    }
}