package  com.mvvm.task.activity.utils

import android.content.Context
import androidx.appcompat.widget.AppCompatButton
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatCheckedTextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.core.widget.NestedScrollView
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatTextView

class CustomTextViewRegular : AppCompatTextView {
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
        val tf = Typeface.createFromAsset(context.assets, "fonts/GoogleSans-Regular.ttf")
        typeface = tf
    }
}