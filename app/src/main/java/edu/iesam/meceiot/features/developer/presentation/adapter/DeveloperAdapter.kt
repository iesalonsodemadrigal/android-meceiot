import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_meceiot.R
import com.example.android_meceiot.databinding.ItemDeveloperBinding
import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo

class DeveloperAdapter(
    private val onUrlClick: (String) -> Unit
) : RecyclerView.Adapter<DeveloperViewHolder>() {

    private val developers = mutableListOf<DeveloperInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeveloperViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_developer, parent, false)
        return DeveloperViewHolder(view)
    }

    override fun onBindViewHolder(holder: DeveloperViewHolder, position: Int) {
        holder.bind(developers[position], onUrlClick)
    }

    override fun getItemCount(): Int = developers.size


    fun submitList(list: List<DeveloperInfo>) {
        developers.clear()
        developers.addAll(list)
        notifyDataSetChanged()
    }
}
