import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import edu.iesam.meceiot.databinding.ItemDeveloperBinding
import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo

class DeveloperViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

   private  val itemDeveloper : ItemDeveloperBinding = ItemDeveloperBinding.bind(view)

    fun bind(developerInfo: DeveloperInfo, onClick: (String) -> Unit) {
        itemDeveloper.apply {
            developerImage.load(developerInfo.urlAvatar) {}
            developerTextName.text = developerInfo.fullName
            developerDescprition.text = developerInfo.collegeDegree

        }
        view.setOnClickListener {
        Log.d("DeveloperViewHolder", "URL: ${developerInfo.urlSource}")
        onClick(developerInfo.urlSource)
    }
}
    }

