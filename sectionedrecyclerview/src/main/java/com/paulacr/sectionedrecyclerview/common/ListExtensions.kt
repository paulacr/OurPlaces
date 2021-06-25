package com.paulacr.sectionedrecyclerview.common

import com.paulacr.sectionedrecyclerview.listitem.ListItem

fun MutableList<ListItem>.buildListWithHeaders(items: List<ListItem>): MutableList<ListItem> {
    items.groupBy {
        it.header
    }.map {
        this.add(ListItem(it.key))
        this.addAll(it.value)
    }

    return this
}