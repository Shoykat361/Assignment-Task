package com.shoykat.assignmentapp.Adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.shoykat.assignmentapp.R
import com.shoykat.assignmentapp.formatToK

@BindingAdapter("app:setUserImg")
fun setUserImg(iv: ImageView, url: String) {
    Glide.with(iv.context)
        .load(url)
        .into(iv)
}

@BindingAdapter("app:setCoin")
fun setCoin(textView: TextView, value: Int) {
    textView.text = "${formatToK(value)}"
}

@BindingAdapter("app:setLevel")
fun setLevel(textView: TextView, value: Int) {
    textView.text = "$value"
}

@BindingAdapter("app:setGenderIcon")
fun setGenderIcon(iv: ImageView, status: String) {
    val icon = when (status) {
        "male" -> R.drawable.male
        "female" -> R.drawable.female
        else -> {R.drawable.male}
    }
    iv.setImageResource(icon)
}