package com.paulacr.sectionedrecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.paulacr.sectionedrecyclerview.common.BaseViewHolder
import com.paulacr.sectionedrecyclerview.databinding.ItemHeaderBinding
import com.paulacr.sectionedrecyclerview.databinding.ItemListBinding
import com.paulacr.sectionedrecyclerview.header.Header
import com.paulacr.sectionedrecyclerview.listitem.ListItem
import com.paulacr.sectionedrecyclerview.listitem.ListItemType

const val VIEW_TYPE_HEADER = 0
const val VIEW_TYPE_ITEM = 1

class BaseAdapter(
    private val items: List<ListItem>,
    private val clickListener: (ListItem) -> Unit
) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    private val recentVisitedItems: List<ListItem> = items.filter {
        it.listItemType == ListItemType.RecentVisitedPlaces
    }.toMutableList()

    private val placesToVisitItems = items.filter {
        it.listItemType == ListItemType.PlacesToVisit
    }.toMutableList()

    private val newList = mutableListOf(
        listOf(ListItem()),
        recentVisitedItems,
        listOf(ListItem()),
        placesToVisitItems).flatten()


    override fun onCreateViewHolder(parent: ViewGroup, position: Int): BaseViewHolder<*> {
        val layoutInflater = LayoutInflater.from(parent.context)
        return if (getItemViewType(position) == VIEW_TYPE_HEADER) {
            val itemBinding = ItemHeaderBinding.inflate(layoutInflater, parent, false)
            HeaderViewHolder(itemBinding)
        } else {
            val itemBinding = ItemListBinding.inflate(layoutInflater, parent, false)
            ListItemViewHolder(itemBinding)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        println("adapter newList -> $newList")
        val item = newList.toMutableList()[position]
        if (getItemViewType(position) == VIEW_TYPE_HEADER) {
            (holder as HeaderViewHolder).bind(item.listItemType.header)
        } else {
            (holder as ListItemViewHolder).apply {
                itemView.setOnClickListener {
                    clickListener(item)
                }
            }.bind(item)
        }
    }

    override fun getItemCount(): Int {
        return newList.size
    }


    override fun getItemViewType(position: Int): Int {

        val positionPlacesToVisit = newList.indexOfFirst {
            it.listItemType == ListItemType.PlacesToVisit
        }

        return when (position) {
            0 -> VIEW_TYPE_HEADER
            in 1..positionPlacesToVisit -> VIEW_TYPE_ITEM
            positionPlacesToVisit + 1 -> VIEW_TYPE_HEADER
            else -> VIEW_TYPE_ITEM
        }
    }

    inner class HeaderViewHolder(private val binding: ItemHeaderBinding) :
        BaseViewHolder<Header>(binding) {

        override fun bind(item: Header) {
            binding.headerTitle.text = item.title
            binding.executePendingBindings()
        }
    }

    inner class ListItemViewHolder(private val binding: ItemListBinding) :
        BaseViewHolder<ListItem>(binding) {

        override fun bind(item: ListItem) {
            binding.listItemTitle.text = item.title
            binding.executePendingBindings()
        }
    }
}
