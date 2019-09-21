package com.nikede.vk_sdk_code_template.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nikede.vk_sdk_code_template.R
import com.nikede.vk_sdk_code_template.models.FriendModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cell_friend.view.*

class FriendsAdapter() : RecyclerView.Adapter<FriendsAdapter.ViewHolder>() {
    private val mSourceList = ArrayList<FriendModel>()
    private val mFriendsList = ArrayList<FriendModel>()

    fun setupFriends(friendsList: ArrayList<FriendModel>) {
        mSourceList.clear()
        mSourceList.addAll(friendsList)
        mFriendsList.clear()
        filter("")
    }

    fun filter(query: String) {
        mFriendsList.clear()
        mSourceList.forEach {
            if (it.name.contains(query, false) || it.surname.contains(query, true) || (it.city != null && it.city.contains(query, true))) {
                mFriendsList.add(it)
            }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsAdapter.ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.cell_friend, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = mFriendsList.size

    override fun onBindViewHolder(holder: FriendsAdapter.ViewHolder, position: Int) {
        holder.bind(mFriendsList[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bind(friendModel: FriendModel) {
            friendModel.avatar?.let {url ->
                Picasso.get().load(url).into(itemView.friend_civ_avatar)
            }

            itemView.friend_txt_username.text = "${friendModel.name} ${friendModel.surname}"
            itemView.friend_txt_city.text = itemView.context.getString(R.string.friend_no_city)
            friendModel.city?.let { itemView.friend_txt_city.text = it }

            itemView.friend_img_online.visibility = if (friendModel.isOnline) View.VISIBLE else View.GONE
        }
    }
}