package com.paulacr.sectionedrecyclerview.header

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.paulacr.sectionedrecyclerview.R

class HeaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AppCompatTextView(context, attrs, defStyle) {

    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.HeaderView)
            val title = typedArray.getString(R.styleable.HeaderView_title)
            val textColor = typedArray.getResourceId(R.styleable.HeaderView_textColor, -1)
            val backgroundColor =
                typedArray.getResourceId(R.styleable.HeaderView_backgroundColor, -1)

            Header(
                title = title.orEmpty(),
                textColor = ContextCompat.getColor(context, textColor),
                backgroundColor = ContextCompat.getColor(context, backgroundColor)
            ).setHeaderAttributes().run {
                typedArray.recycle()
            }
        }
    }

    private fun Header.setHeaderAttributes() {
        text = title
        setBackgroundColor(backgroundColor)
        setTextColor(textColor)
    }
}