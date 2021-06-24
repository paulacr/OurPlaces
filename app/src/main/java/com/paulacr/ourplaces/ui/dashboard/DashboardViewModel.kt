package com.paulacr.ourplaces.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paulacr.sectionedrecyclerview.listitem.ListItem
import com.paulacr.sectionedrecyclerview.listitem.ListItemType

class DashboardViewModel : ViewModel() {

    private val itemsLiveData = MutableLiveData<List<ListItem>>().apply {
        value = listOf(
            ListItem("item 1", 0, ListItemType.RecentVisitedPlaces),
            ListItem("item 2", 0, ListItemType.RecentVisitedPlaces),
            ListItem("item 3", 0, ListItemType.PlacesToVisit),
            ListItem("item 4", 0, ListItemType.PlacesToVisit)
        )
    }
    val items: LiveData<List<ListItem>> = itemsLiveData
}
