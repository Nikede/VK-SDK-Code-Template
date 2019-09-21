package com.nikede.vk_sdk_code_template.models

import org.json.JSONObject

class FriendModel(val name: String, val surname: String, val city: String?, val avatar: String?, val isOnline: Boolean) {

    companion object {
        fun parse(friend: JSONObject): FriendModel {
            var name = ""
            if (friend.has("first_name"))
                name = friend.getString("first_name")
            var surname = ""
            if (friend.has("last_name"))
                surname = friend.getString("last_name")
            var city: String? = null
            if (friend.has("city"))
                city = friend.getJSONObject("city").getString("title")
            var avatar: String? = null
            if (friend.has("photo_50"))
                avatar = friend.getString("photo_50")
            var isOnline = false
            if (friend.has("online"))
                isOnline = friend.getInt("online") == 1
            return FriendModel(name, surname, city, avatar, isOnline)
        }
    }
}