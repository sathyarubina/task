package com.mvvm.task.activity.utils

import android.content.Context
import androidx.appcompat.widget.AppCompatButton
import android.graphics.Typeface
import android.util.AttributeSet

class CustomButtonBold : AppCompatButton {
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context!!, attrs, defStyle
    ) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
        init()
    }

    constructor(context: Context?) : super(context!!) {
        init()
    }

    fun init() {
        val tf = Typeface.createFromAsset(context.assets, "fonts/GoogleSans-Bold.ttf")
        typeface = tf
    }
}