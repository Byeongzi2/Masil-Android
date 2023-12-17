package com.posomo.masil.feature.map.adpater

import android.util.Log
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.posomo.masil.R
import com.posomo.masil.databinding.ListItemMapStoreImageBinding
import com.posomo.masil.domain.model.FilterListItem
import com.posomo.masil.domain.model.StoreOnMap
import com.posomo.masil.feature.map.Event

@BindingAdapter("showOnLoading")
fun ViewGroup.showOnLoading(event: Event) {
    isVisible = event == Event.Loading
}

@BindingAdapter("mapFilterToTitle")
fun TextView.mapFilterToTitle(type: FilterListItem.Type) {
    setText(
        when (type) {
            FilterListItem.Type.KEYWORD -> R.string.filter_keyword
            FilterListItem.Type.LIQUOR -> R.string.filter_liquor
            FilterListItem.Type.STORE -> R.string.filter_store
        }
    )
}

@BindingAdapter("filterClickListener")
fun ViewGroup.filterClickListener(type: FilterListItem.Type) {
    setOnClickListener {
        when (type) {
            FilterListItem.Type.KEYWORD -> {
                Log.d("lololo", "$type")
            }

            FilterListItem.Type.LIQUOR -> {
                Log.d("lololo", "$type")
            }

            FilterListItem.Type.STORE -> {
                Log.d("lololo", "$type")
            }
        }
    }
}

@BindingAdapter("mapToBookmark")
fun ImageView.mapToBookmark(isBookMarked: Boolean) {
    setImageDrawable(
        ContextCompat.getDrawable(
            context,
            if (isBookMarked) R.drawable.icn_28_bookmark_active else R.drawable.ic_bookmark_black
        )
    )
}

@BindingAdapter("imageList")
fun LinearLayout.imageList(images: List<String>) {
    removeAllViews()
    images.forEach {
        val binding = ListItemMapStoreImageBinding.inflate(LayoutInflater.from(context))
        Glide.with(context)
            .load(it)
            .placeholder(R.color.gray_200)
            .into(binding.ivStore)
        addView(binding.root)
    }
}

@BindingAdapter("filterItems")
fun RecyclerView.filterItems(items: List<FilterListItem>) {
    if (adapter == null) {
        val filterAdapter = FilterListAdapter()
        adapter = filterAdapter
    }
    val adapter = this.adapter as FilterListAdapter
    adapter.submitList(items)
}

@BindingAdapter("storeItems")
fun RecyclerView.storeItems(items: List<StoreOnMap>) {
    if (adapter == null) {
        val storeListAdapter = StoreListAdapter()
        adapter = storeListAdapter
    }
    val adapter = this.adapter as StoreListAdapter
    adapter.submitList(items)
}