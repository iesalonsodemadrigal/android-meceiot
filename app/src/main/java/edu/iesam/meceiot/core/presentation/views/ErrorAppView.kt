package edu.iesam.meceiot.core.presentation.views

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import edu.iesam.meceiot.core.presentation.hide
import edu.iesam.meceiot.core.presentation.visible
import edu.iesam.meceiot.databinding.ViewErrorBinding

class ErrorAppView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {


    private val binding = ViewErrorBinding
        .inflate(LayoutInflater.from(context), this, true)

    init {
        hide()
    }

    fun render(errorAppUI: ErrorAppUI) {
        binding.apply {
            imageError.setImageResource(errorAppUI.getImageError())
            titleError.text = errorAppUI.getTitleError()
            descriptionError.text = errorAppUI.getDescriptionError()
            buttonRetryError.setOnClickListener {
                    val intent = Intent(context, errorAppUI.getRetryActivity())
                    context.startActivity(intent)
            }
            visible()
        }
    }
}