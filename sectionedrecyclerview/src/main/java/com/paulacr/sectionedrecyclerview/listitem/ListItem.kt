package com.paulacr.sectionedrecyclerview.listitem

import androidx.annotation.DrawableRes
import com.paulacr.sectionedrecyclerview.header.Header

data class ListItem(val header: Header, val title: String? = "", @DrawableRes val  imageRes: Int = 0, val location: String = "") {

    val isHeader = title.isNullOrEmpty()
}
