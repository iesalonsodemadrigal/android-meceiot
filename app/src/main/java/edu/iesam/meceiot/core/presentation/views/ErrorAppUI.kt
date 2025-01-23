package edu.iesam.meceiot.core.presentation.views

import android.content.Context
import androidx.fragment.app.Fragment
import edu.iesam.meceiot.R

interface ErrorAppUI {
    fun getImageError(): Int
    fun getTitleError(): String
    fun getDescriptionError(): String
    fun getRetryFragment(): Fragment
}

class ConnectionErrorAppUI(
    private val context: Context,
    private val retryFragment: Fragment
) : ErrorAppUI {
    override fun getImageError(): Int {
        return R.drawable.image_error_connection
    }

    override fun getTitleError(): String {
        return context.getString(R.string.title_error_connection)
    }

    override fun getDescriptionError(): String {
        return context.getString(R.string.description_error_connection)
    }

    override fun getRetryFragment(): Fragment {
        return retryFragment
    }

}

class ServerErrorAppUI(
    private val context: Context,
    private val retryFragment: Fragment
) : ErrorAppUI {
    override fun getImageError(): Int {
        return R.drawable.image_error_server
    }

    override fun getTitleError(): String {
        return context.getString(R.string.title_error_server)
    }

    override fun getDescriptionError(): String {
        return context.getString(R.string.description_error_server)
    }

    override fun getRetryFragment(): Fragment {
        return retryFragment
    }

}

class UnknownErrorAppUI(
    private val context: Context,
    private val retryFragment: Fragment
) : ErrorAppUI {
    override fun getImageError(): Int {
        return R.drawable.image_error_unknown
    }

    override fun getTitleError(): String {
        return context.getString(R.string.title_error_unknown)
    }

    override fun getDescriptionError(): String {
        return context.getString(R.string.description_error_unknown)
    }

    override fun getRetryFragment(): Fragment {
        return retryFragment
    }


}
