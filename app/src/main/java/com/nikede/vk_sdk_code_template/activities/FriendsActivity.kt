package com.nikede.vk_sdk_code_template.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.nikede.vk_sdk_code_template.R
import com.nikede.vk_sdk_code_template.adapters.FriendsAdapter
import com.nikede.vk_sdk_code_template.models.FriendModel
import com.nikede.vk_sdk_code_template.presenters.FriendsPresenter
import com.nikede.vk_sdk_code_template.views.FriendsView
import kotlinx.android.synthetic.main.activity_friends.*

class FriendsActivity : MvpAppCompatActivity(), FriendsView {

    @InjectPresenter
    lateinit var friendsPresenter: FriendsPresenter

    private val mAdapter = FriendsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)

        recycler_friends.adapter = mAdapter
        recycler_friends.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        recycler_friends.hasFixedSize()

        txt_friends_search.addTextChangedListener (object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                mAdapter.filter(s.toString())
            }

        })

        swipe_refresh.setOnRefreshListener {
            friendsPresenter.loadFriends()
        }

        friendsPresenter.loadFriends()
    }

    override fun showError(textSource: Int) {
        Toast.makeText(applicationContext, getString(textSource), Toast.LENGTH_SHORT).show()
    }

    override fun setupEmptyList() {
        recycler_friends.visibility = View.GONE
        txt_friends_no_items.visibility = View.VISIBLE
    }

    override fun setupFriendsList(friendsList: ArrayList<FriendModel>) {
        recycler_friends.visibility = View.VISIBLE
        txt_friends_no_items.visibility = View.GONE

        mAdapter.setupFriends(friendsList)
    }

    override fun startLoading() {
        recycler_friends.visibility = View.GONE
        txt_friends_no_items.visibility = View.GONE
        cpv_friends.visibility = View.VISIBLE
    }

    override fun endLoading() {
        swipe_refresh.isRefreshing = false
        cpv_friends.visibility = View.GONE
    }
}
