package edu.iesam.meceiot.features.iot.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import edu.iesam.meceiot.R

class IoTFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_iot, container, false)


        setItemText(view, R.id.item_1, R.string.title_item_1, R.string.description_item_1)
        setItemText(view, R.id.item_2, R.string.title_item_2, R.string.description_item_2)
        setItemText(view, R.id.item_3, R.string.title_item_3, R.string.description_item_3)
        setItemText(view, R.id.item_4, R.string.title_item_4, R.string.description_item_4)
        setItemText(view, R.id.item_5, R.string.title_item_5, R.string.description_item_5)

        return view
    }

    private fun setItemText(view: View, itemId: Int, titleId: Int, descriptionId: Int) {
        val itemView = view.findViewById<View>(itemId)
        itemView.findViewById<TextView>(R.id.itemTitle).setText(titleId)
        itemView.findViewById<TextView>(R.id.itemDescription).setText(descriptionId)
    }
}