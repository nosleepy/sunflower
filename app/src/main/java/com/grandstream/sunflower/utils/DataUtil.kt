package com.grandstream.sunflower.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.grandstream.sunflower.entity.Plant

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}

@BindingAdapter("isShowAdd")
fun bindIsShowAdd(view: View, isShowAdd: String?) {
    view.visibility = if (isShowAdd == "1") {
        View.GONE
    } else {
        View.VISIBLE
    }
}

@BindingAdapter("isShowDelete")
fun bindIsShowDelete(view: View, isShowDelete: String?) {
    view.visibility = if (isShowDelete == "1") {
        View.VISIBLE
    } else {
        View.GONE
    }
}

fun getPlantList(): List<Plant> {
    return listOf(
        Plant(generateRandomName("苹果"), "https://raw.githubusercontent.com/nosleepy/picture/master/img/fruit/apple.png"),
        Plant(generateRandomName("香蕉"), "https://raw.githubusercontent.com/nosleepy/picture/master/img/fruit/banana.png"),
        Plant(generateRandomName("蓝莓"), "https://raw.githubusercontent.com/nosleepy/picture/master/img/fruit/blueberry.png"),
        Plant(generateRandomName("胡萝卜"), "https://raw.githubusercontent.com/nosleepy/picture/master/img/fruit/carrot.png"),
        Plant(generateRandomName("樱桃"), "https://raw.githubusercontent.com/nosleepy/picture/master/img/fruit/cherry.png"),
        Plant(generateRandomName("榴莲"), "https://raw.githubusercontent.com/nosleepy/picture/master/img/fruit/durian.png"),
        Plant(generateRandomName("葡萄"), "https://raw.githubusercontent.com/nosleepy/picture/master/img/fruit/grape.png"),
        Plant(generateRandomName("柠檬"), "https://raw.githubusercontent.com/nosleepy/picture/master/img/fruit/lemon.png"),
        Plant(generateRandomName("芒果"), "https://raw.githubusercontent.com/nosleepy/picture/master/img/fruit/mango.png"),
        Plant(generateRandomName("橙子"), "https://raw.githubusercontent.com/nosleepy/picture/master/img/fruit/orange.png"),
        Plant(generateRandomName("桃子"), "https://raw.githubusercontent.com/nosleepy/picture/master/img/fruit/peach.png"),
        Plant(generateRandomName("梨子"), "https://raw.githubusercontent.com/nosleepy/picture/master/img/fruit/pear.png"),
        Plant(generateRandomName("西瓜"), "https://raw.githubusercontent.com/nosleepy/picture/master/img/fruit/watermelon.png"),
    )
}

private fun generateRandomName(name: String): String {
    val count = (1..5).random()
    val res = StringBuilder()
    repeat(count - 1) {
        res.append(name)
    }
    res.append(name)
    return res.toString()
}