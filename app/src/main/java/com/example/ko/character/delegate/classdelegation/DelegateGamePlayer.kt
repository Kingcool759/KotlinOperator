package com.example.ko.character.delegate.classdelegation

import com.example.ko.logD

/**
 * @Author : zhaojianwei02
 * @Date : 2023/3/31 6:25 PM
 * @Description : 代理类
 */
class DelegateGamePlayer(private val player: IPlayGame): IPlayGame by player{

    companion object {
        const val TAG = "DelegateGamePlayer"
    }

    override fun rank() {
        logD(TAG, "准备排位进游戏")
        player.rank()
        logD(TAG, "排位结束")
    }

//    override fun upgrade() {
//        player.upgrade()
//    }
}