package kr.co.ryuboutin.ui.custom

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import kr.co.ryuboutin.R

class ImageTextButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {
    private var imageView: ImageView
    private var text : TextView

    init {

        val a = LayoutInflater.from(context).inflate(R.layout.image_text_button, this)
        val typedArr = context.obtainStyledAttributes(attrs, R.styleable.ImageTextButton)
        imageView = a.findViewById(R.id.imageView)
        text = a.findViewById(R.id.text)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ImageTextButton,
            0,0
        ).apply {
            try {
                var buttonText = typedArr.getString(R.styleable.ImageTextButton_setImageButtonText)
                setButtonText(buttonText)

                var imageUri = typedArr.getString(R.styleable.ImageTextButton_setImageButtonText)?.toUri()
                setImageURI(imageUri)

            }finally {
                recycle()
            }

        }

    }

    private fun setButtonText(buttonText: String?) {
//        when(buttonText) {
//            buttonText -> text.text = buttonText
//            null -> text.text = "defaultText"
//        }
        if(buttonText != null) {
            text.text = buttonText
            invalidate()
            requestLayout()
        }else {
            text.text = "defaultText"
            invalidate()
            requestLayout()
        }

    }

    private fun setImageURI(uri: Uri?) {
//        when(uri) {
//            uri -> imageView.load(uri)
//            null -> imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.nav_menu_10))
//        }

//        if(uri != null) {
//            imageView.load(uri)
//            invalidate()
//            requestLayout()
//        }else {
            imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.nav_menu_06))
            invalidate()
            requestLayout()

//        }
    }



}