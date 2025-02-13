import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import edu.iesam.meceiot.R
import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo
import edu.iesam.meceiot.features.developer.presentation.adapter.DeveloperDiffUtil

class DeveloperAdapter : ListAdapter<DeveloperInfo, DeveloperViewHolder>(DeveloperDiffUtil()) {

    lateinit var onClick: (id: String) -> Unit

    fun setEvent(onClick: (url: String) -> Unit) {
        this.onClick = onClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeveloperViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_developer, parent, false)
        return DeveloperViewHolder(view)
    }
    override fun onBindViewHolder(holder: DeveloperViewHolder, position: Int) {
        holder.bind(getItem(position)) { url ->
            onClick(url)
        }
    }

}