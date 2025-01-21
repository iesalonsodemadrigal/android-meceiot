package edu.iesam.meceiot.core.presentation.views

import android.content.Context
import android.content.Intent
import edu.iesam.meceiot.R

interface ErrorAppUI {
    fun getImageError(): Int
    fun getTitleError(): String
    fun getRetryActivity(): Class<*>
    fun getDescriptionError(): String
    fun getActionRetry(): Unit

}

class ConnectionErrorAppUI(val context: Context) : ErrorAppUI {
    override fun getImageError(): Int {
        return R.drawable.image_error_connection
    }

    override fun getTitleError(): String {
        return context.getString(R.string.title_error_connection)
    }

    override fun getDescriptionError(): String {
        return context.getString(R.string.description_error_connection)
    }

    override fun getActionRetry() {
        val intent = Intent(context, getRetryActivity()).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        context.startActivity(intent)
    }
    override fun getRetryActivity(): Class<*> {
        return edu.iesam.meceiot.MainActivity::class.java

    }

}

class ServerErrorAppUI(val context: Context) : ErrorAppUI {
    override fun getImageError(): Int {
        return R.drawable.image_error_server
    }

    override fun getTitleError(): String {
        return context.getString(R.string.title_error_server)
    }

    override fun getDescriptionError(): String {
        return context.getString(R.string.description_error_server)
    }

    override fun getActionRetry() {
        val intent = Intent(context, getRetryActivity()).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        context.startActivity(intent)
    }
    override fun getRetryActivity(): Class<*> {
        return edu.iesam.meceiot.MainActivity::class.java
}

class UnknownErrorAppUI(val context: Context) : ErrorAppUI {
    override fun getImageError(): Int {
        return R.drawable.image_error_unknown
    }

    override fun getTitleError(): String {
        return context.getString(R.string.title_error_unknown)
    }

    override fun getDescriptionError(): String {
        return context.getString(R.string.description_error_unknown)
    }

    override fun getActionRetry() {
        val intent = Intent(context, getRetryActivity()).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        context.startActivity(intent)
    }
    override fun getRetryActivity(): Class<*> {
        return edu.iesam.meceiot.MainActivity::class.java

}
}
}