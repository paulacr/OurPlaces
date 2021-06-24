package com.paulacr.sectionedrecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.paulacr.sectionedrecyclerview.common.BaseViewHolder
import com.paulacr.sectionedrecyclerview.databinding.ItemHeaderBinding
import com.paulacr.sectionedrecyclerview.databinding.ItemListBinding
import com.paulacr.sectionedrecyclerview.header.Header
import com.paulacr.sectionedrecyclerview.listitem.ListItem

const val VIEW_TYPE_HEADER = 0
const val VIEW_TYPE_ITEM = 1

class BaseAdapter(
    private val items: List<ListItem>,
    private val clickListener: (ListItem) -> Unit
) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    data class ListItemWithHeader(val listItem: ListItem, val isHeader: Boolean? = false)

    private val newList: MutableList<ListItemWithHeader> = mutableListOf()

    init {
        setupList()
    }

    private fun setupList() {
        items.groupBy {
            it.listItemType
        }.onEach {
            newList.add(ListItemWithHeader(ListItem(listItemType = it.key), true))
            it.value.map { listItem ->
                newList.add(ListItemWithHeader(listItem))
            }
        }
    }

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
        val item = newList[position]

        if (getItemViewType(position) == VIEW_TYPE_HEADER) {
            item.listItem.listItemType.header?.let { (holder as HeaderViewHolder).bind(it) }
        } else {
            (holder as ListItemViewHolder).apply {
                itemView.setOnClickListener {
                    item.listItem.let {  listItem ->
                        clickListener(listItem)
                    }
                }
            }.bind(item.listItem)
        }
    }

    override fun getItemCount(): Int = newList.size

    override fun getItemViewType(position: Int): Int {

        return if (newList[position].isHeader == true) {
            VIEW_TYPE_HEADER
        } else {
            VIEW_TYPE_ITEM
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
//            binding.itemImage.setime = item.imageRes
            //glide

            binding.itemTitle.text = item.title
            binding.itemLocation.text = item.location
            binding.executePendingBindings()
        }
    }
}
