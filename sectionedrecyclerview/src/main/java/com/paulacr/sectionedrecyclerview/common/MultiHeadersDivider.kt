package com.paulacr.sectionedrecyclerview.common

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.forEachIndexed
import androidx.recyclerview.widget.RecyclerView
import com.paulacr.sectionedrecyclerview.R
import com.paulacr.sectionedrecyclerview.listitem.ListItem

class MultiHeadersDivider(private val context: Context, private val items: List<ListItem>) : RecyclerView.ItemDecoration() {

    private val divider = ContextCompat.getDrawable(context, R.drawable.list_divider)

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val start = parent.paddingStart
        val end = parent.paddingEnd

        val itemsWithHeaders = mutableListOf<ListItem>().buildListWithHeaders(items)

        parent.forEachIndexed { index, _ ->
            val child = parent.getChildAt(index)
            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + divider!!.intrinsicHeight

            if (itemsWithHeaders[index].isHeader.not()) {
                divider.setBounds(start, top, end, bottom)
                divider.draw(c)
            }
        }


    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.set(0, 0, divider?.intrinsicHeight?: 0, 0)
    }
}