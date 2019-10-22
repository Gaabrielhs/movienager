package com.example.movienager.adapters

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso


@BindingAdapter("urlToImg")
fun urlToImg(view: ImageView, url: String?){
    if(url == null) return
    val width = view.context.resources.displayMetrics.widthPixels / 3
    val height = (width * 1.5).toInt()
    Picasso.get().load(url).resize(width,height).into(view)
}

@BindingAdapter("urlToImgHeader")
fun urlToImgHeader(view: ImageView, url: String?){
    if(url == null) return
    val width = view.context.resources.displayMetrics.widthPixels
    val height = width
    Picasso.get().load(url).centerCrop().resize(width,height).into(view)
}

@BindingAdapter("visibleGone")
fun showHide(view: View, show: Boolean) {
    view.setVisibility(if (show) View.VISIBLE else View.GONE)
}

