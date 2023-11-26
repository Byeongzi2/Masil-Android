package com.posomo.masil.common.ui.adapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.posomo.masil.common.util.dp

@BindingAdapter("setViewHeight")
fun View.setHeight(height: Int) {
	val lp = this.layoutParams
	lp.height = height.dp
	this.layoutParams = lp
}

@BindingAdapter("imageUrl")
fun ImageView.loadImageUrl(imageUrl: String) {
	if (imageUrl.isEmpty()) return

	Glide.with(this.context)
		.load(imageUrl)
		.into(this)
}