import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionScene.Transition.TransitionOnClick
import androidx.recyclerview.widget.ListAdapter
import edu.iesam.meceiot.R
import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo
import edu.iesam.meceiot.features.developer.presentation.adapter.DeveloperDiffUtil
import retrofit2.http.Url

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
        // Llamamos a bind para pasar la información del desarrollador y el click event
        holder.bind(getItem(position)) { url ->
            // Aquí el código que se ejecutará cuando se haga clic en el item
            Log.d("DeveloperAdapter", "URL clicked: $url")
            onClick(url)  // Esto llama al onClick que se pasa como parámetro
        }
    }

}