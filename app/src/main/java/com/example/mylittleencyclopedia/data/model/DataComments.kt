package com.example.mylittleencyclopedia.data.model

import com.google.gson.annotations.SerializedName

class DataComments(

    @SerializedName("name_object")
    val exampleForComments: String,

    @SerializedName("text_comments")
    val textComments: String,

    @SerializedName("user_name")
    val userName: String
) {
    @SerializedName("created")
    val dataCreateComments: String = ""
}