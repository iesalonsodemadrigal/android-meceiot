import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import edu.iesam.meceiot.databinding.ItemDeveloperBinding
import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo

class DeveloperViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val binding: ItemDeveloperBinding = ItemDeveloperBinding.bind(view)

    fun bind(developerInfo: DeveloperInfo, onUrlClick: (String) -> Unit) {
        binding.apply {

            developerImage.load(developerInfo.urlAvatar) {
                transformations(CircleCropTransformation())
                size(width = 100, height = 100)
            }

            developerName.text = developerInfo.fullName
            collegeDegree.text = developerInfo.collegeDegree
            buttonOpenUrl.text = "Ver perfil"


            buttonOpenUrl.setOnClickListener {
                developerInfo.urlSource?.let { url ->
                    onUrlClick(url)
                }
            }
        }
    }
}
