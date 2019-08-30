package com.example.mylittleencyclopedia.data.model

import com.google.gson.annotations.SerializedName

data class DataExampleEncyclopedia(

    @SerializedName("objectId")
    val idObject: String,

    @SerializedName("category")
    var category: String,

    @SerializedName("id_category")
    var id_category: String,

    @SerializedName("category_image")
    var category_image: String,

    @SerializedName("count_likes")
    var count_likes: String,

    @SerializedName("created")
    var created: String
) {

    @SerializedName("d_object")
    var name: String = ""

    @SerializedName("text_1")
    var text_box_one: String = ""

    @SerializedName("text_2")
    var text_box_two: String = ""

    @SerializedName("image_1")
    var url_box_one: String = ""

    @SerializedName("text_5")
    var text_box_five: String = ""

    @SerializedName("text_4")
    var text_box_four: String = ""

    @SerializedName("text_9")
    var text_box_nine: String = ""

    @SerializedName("text_7")
    var text_box_seven: String = ""

    @SerializedName("text_6")
    var text_box_six: String = ""

    @SerializedName("text_3")
    var text_box_three: String = ""

    @SerializedName("image_5")
    var url_box_five: String = ""

    @SerializedName("image_4")
    var url_box_four: String = ""

    @SerializedName("image_6")
    var url_box_six: String = ""

    @SerializedName("image_7")
    var url_box_seven: String = ""

    @SerializedName("image_3")
    var url_box_three: String = ""

    @SerializedName("image_2")
    var url_box_two: String = ""
}