package com.nikede.vk_sdk_code_template.providers

import com.nikede.vk_sdk_code_template.requests.VKFriendsRequest
import com.nikede.vk_sdk_code_template.models.FriendModel
import com.nikede.vk_sdk_code_template.presenters.FriendsPresenter
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.exceptions.VKApiExecutionException


class FriendsProvider(val presenter: FriendsPresenter) {
    fun loadFriends() {
        VK.execute(VKFriendsRequest(), object: VKApiCallback<ArrayList<FriendModel>> {
            override fun success(result: ArrayList<FriendModel>) {
                presenter.friendsLoaded(result)
            }
            override fun fail(error: VKApiExecutionException) {
                presenter.showError()
            }
        })
    }
}