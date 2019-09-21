package com.nikede.vk_sdk_code_template.presenters

import android.content.Intent
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.nikede.vk_sdk_code_template.R
import com.nikede.vk_sdk_code_template.views.LoginView
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback

@InjectViewState
class LoginPresenter : MvpPresenter<LoginView>() {
    fun login(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        viewState.startLoading()
        if (!VK.onActivityResult(requestCode, resultCode, data, object : VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {
                viewState.endLoading()
                viewState.openFriends()
            }

            override fun onLoginFailed(errorCode: Int) {
                viewState.endLoading()
                viewState.showError(R.string.login_error)
            }

        }))
            return false

        return true
    }
}