package com.paulacr.ourplaces.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paulacr.ourplaces.R
import com.paulacr.sectionedrecyclerview.header.Header
import com.paulacr.sectionedrecyclerview.listitem.ListItem

class DashboardViewModel : ViewModel() {

    private val itemsLiveData = MutableLiveData<List<ListItem>>().apply {
        val headerRecentVisitedPlaces = Header("Recent visited places", 0, 0)
        val headerPlacesToVisit = Header("Places to visit", 0, 0)
        value = listOf(
            ListItem(headerRecentVisitedPlaces, "item 1", R.drawable.ic_launcher_foreground, "Sunnyvale"),
            ListItem(headerRecentVisitedPlaces, "item 2", R.drawable.ic_launcher_foreground, "Sunnyvale"),
            ListItem(headerPlacesToVisit, "item 3", R.drawable.ic_launcher_foreground, "Berlin"),
            ListItem(headerPlacesToVisit, "item 4", R.drawable.ic_launcher_foreground, "Berlin")
        )
    }
    val items: LiveData<List<ListItem>> = itemsLiveData
}
