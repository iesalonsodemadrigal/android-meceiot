import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.android_meceiot.databinding.ItemDeveloperBinding
import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo

class DeveloperViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val binding: ItemDeveloperBinding = ItemDeveloperBinding.bind(view)

    fun bind(developerInfo: DeveloperInfo) {
        binding.apply {
            developerImage.load(developerInfo.urlAvatar) {
                transformations(CircleCropTransformation())
                size(width = 100, height = 100)
            }

            developerName.text = developerInfo.fullName
            collegeDegree.text = developerInfo.collegeDegree

            // Configurar el botón de acción
            actionButton.setOnClickListener {
                Log.d("DeveloperAdapter", "URL Source: ${developerInfo.urlSource}") // Log de depuración
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(developerInfo.urlSource) // Asegúrate que developerInfo.urlSource es una URL válida
                }

            }
        }
    }
}
