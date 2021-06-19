package com.paulacr.sectionedrecyclerview.common

import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<T>(itemViewBinding: ViewBinding) :
    androidx.recyclerview.widget.RecyclerView.ViewHolder(itemViewBinding.root) {

    abstract fun bind(item: T)
}