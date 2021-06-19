package com.paulacr.sectionedrecyclerview

import com.paulacr.sectionedrecyclerview.listitem.ListItem
import com.paulacr.sectionedrecyclerview.listitem.ListItemType
import junit.framework.Assert.assertEquals
import org.junit.Test

class BaseAdapterTest {

    @Test
    fun shouldReturnTwoHeadersWhenGetItemCountTest() {
        val currentItems = listOf(
            ListItem("item 1", 0, ListItemType.RecentVisitedPlaces),
            ListItem("item 2", 0, ListItemType.PlacesToVisit),
            ListItem("item 3", 0, ListItemType.RecentVisitedPlaces),
            ListItem("item 4", 0, ListItemType.PlacesToVisit)

        )

        val adapterCount = BaseAdapter(currentItems) {}.itemCount

        val expectedCount = currentItems.size + 2
        assertEquals(expectedCount, adapterCount)
    }

    @Test
    fun shouldReturnOneHeaderWhenGetItemCountTest() {
        val currentItems = listOf(
            ListItem("item 1", 0, ListItemType.RecentVisitedPlaces),
            ListItem("item 3", 0, ListItemType.RecentVisitedPlaces)

        )

        val adapterCount = BaseAdapter(currentItems) {}.itemCount

        val expectedCount = currentItems.size + 1
        assertEquals(expectedCount, adapterCount)
    }

    @Test
    fun shouldReturnHeaderViewTypeTest() {
        val currentItems = listOf(
            ListItem("item 1", 0, ListItemType.RecentVisitedPlaces),
            ListItem("item 2", 0, ListItemType.PlacesToVisit),
            ListItem("item 3", 0, ListItemType.RecentVisitedPlaces),
            ListItem("item 4", 0, ListItemType.PlacesToVisit)

        )

        val actualViewType = BaseAdapter(currentItems) {}.getItemViewType(0)

        val expectedViewType = VIEW_TYPE_HEADER
        assertEquals(expectedViewType, actualViewType)
    }

    @Test
    fun shouldReturnItemViewTypeTest() {
        val currentItems = listOf(
            ListItem("item 1", 0, ListItemType.RecentVisitedPlaces),
            ListItem("item 2", 0, ListItemType.PlacesToVisit),
            ListItem("item 3", 0, ListItemType.RecentVisitedPlaces),
            ListItem("item 4", 0, ListItemType.PlacesToVisit)
        )

        val actualViewType = BaseAdapter(currentItems) {}.getItemViewType(0)

        val expectedViewType = VIEW_TYPE_HEADER
        assertEquals(expectedViewType, actualViewType)
    }
}