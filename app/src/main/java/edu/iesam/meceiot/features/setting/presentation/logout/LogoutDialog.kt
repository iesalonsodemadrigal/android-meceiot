package edu.iesam.meceiot.features.setting.presentation.logout

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import edu.iesam.meceiot.R

class LogoutDialog : DialogFragment() {

    interface LogoutListener {
        fun onLogoutConfirmed()
    }

    private var listener: LogoutListener? = null

    fun setListener(listener: LogoutListener) {
        this.listener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.logout)
            .setMessage(R.string.logout_confirmation)
            .setPositiveButton(R.string.logout_confirmation_affirmative) { _, _ ->
                listener?.onLogoutConfirmed()
            }
            .setNegativeButton(R.string.logout_confirmation_negative, null)
            .create()
    }
}