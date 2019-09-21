package com.nikede.vk_sdk_code_template.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.nikede.vk_sdk_code_template.models.FriendModel

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface FriendsView:MvpView {
    fun showError(textSource: Int)
    fun setupEmptyList()
    fun setupFriendsList(friendsList: ArrayList<FriendModel>)
    fun startLoading()
    fun endLoading()
}