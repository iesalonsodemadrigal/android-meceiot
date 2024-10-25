package edu.iesam.meceiot.features.developer.data.remote

import android.content.Context
import com.example.android_meceiot.R
import com.google.gson.Gson
import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo
import java.io.InputStreamReader

class DeveloperMockRemoteDataSource(private val context: Context) {

    suspend fun getDevelopers(): List<DeveloperInfo> {
        val inputStream = context.resources.openRawResource(R.raw.developer_info)
        val reader = InputStreamReader(inputStream)
        val infoFile: List<DeveloperInfo> =
            Gson().fromJson(reader, Array<DeveloperInfo>::class.java).toList()

        reader.close()
        return infoFile
    }


}