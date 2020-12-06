package com.example.kotlindemo.widget

import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.example.kotlindemo.R

/**
 *
 * @author :   cb.xu
 * @date   :   2020/12/6 11:12 AM
 * @desc   :   带删除按钮的EditText
 */
@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
class EditTextClear @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatEditText(context, attrs, defStyleAttr) {

    private var drawableIcon: Drawable? = null

    /**
     * 增加的可点击范围 - 像素
     */
    private val clickExtendedEdge = 10

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.EditTextClear, 0, 0)
            .apply {
                try {
                    val iconId = getResourceId(R.styleable.EditTextClear_clearIcon, 0)
                    if (iconId != 0) {
                        drawableIcon = ContextCompat.getDrawable(context, iconId)
                    }
                } finally {
                    recycle()
                }
            }
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        toggleClearIcon()

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let { e ->
            drawableIcon?.let {
                if (e.action == MotionEvent.ACTION_UP
                    && e.x > width - it.intrinsicWidth - clickExtendedEdge
                    && e.x < width + clickExtendedEdge
                    && e.y > height / 2 - it.intrinsicWidth / 2 - clickExtendedEdge
                    && e.y < height / 2 + it.intrinsicWidth / 2 + clickExtendedEdge
                ) {
                    text?.clear()
                }
            }
        }
        performClick()
        return super.onTouchEvent(event)
    }

    override fun performClick(): Boolean {
        return super.performClick()
    }

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        toggleClearIcon()
    }

    private fun toggleClearIcon() {
        val icon = if (isFocused && !text.isNullOrEmpty()) drawableIcon else null
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, icon, null)
    }

}