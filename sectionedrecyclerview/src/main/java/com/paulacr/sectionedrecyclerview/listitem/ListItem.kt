package com.paulacr.sectionedrecyclerview.listitem

import androidx.annotation.DrawableRes
import com.paulacr.sectionedrecyclerview.header.Header

data class ListItem(val title: String? = "", @DrawableRes val  imageRes: Int = 0, val listItemType: ListItemType = ListItemType.RecentVisitedPlaces)

sealed class ListItemType(val header: Header) {
    object RecentVisitedPlaces: ListItemType(Header("test", 0, 0))
    object PlacesToVisit: ListItemType(Header("test 2", 0, 0))
}
