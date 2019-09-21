package com.nikede.vk_sdk_code_template.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.nikede.vk_sdk_code_template.R
import com.nikede.vk_sdk_code_template.models.FriendModel
import com.nikede.vk_sdk_code_template.providers.FriendsProvider
import com.nikede.vk_sdk_code_template.views.FriendsView

@InjectViewState
class FriendsPresenter: MvpPresenter<FriendsView>() {
    fun loadFriends() {
        viewState.startLoading()
        FriendsProvider(this).loadFriends()
    }

    fun friendsLoaded(friendsList: ArrayList<FriendModel>) {
        viewState.endLoading()
        if (friendsList.size == 0) {
            viewState.setupEmptyList()
        } else {
            viewState.setupFriendsList(friendsList)
        }
    }

    fun showError() {
        viewState.endLoading()
        viewState.showError(R.string.request_error)
    }
}