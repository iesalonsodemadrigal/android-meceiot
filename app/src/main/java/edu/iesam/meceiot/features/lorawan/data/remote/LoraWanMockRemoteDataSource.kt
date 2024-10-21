package edu.iesam.meceiot.features.lorawan.data.remote

import android.content.Context
import com.example.android_meceiot.R
import com.google.gson.Gson
import edu.iesam.meceiot.features.lorawan.domain.LoraWanInfo
import java.io.InputStreamReader

class LoraWanMockRemoteDataSource(private val context: Context) {

    suspend fun getInfoLoraWan(): List<LoraWanInfo> {
        val inputStream = context.resources.openRawResource(R.raw.lorawan_info)
        val reader = InputStreamReader(inputStream)

        val infoFile: List<LoraWanInfo> =
            Gson().fromJson(reader, Array<LoraWanInfo>::class.java).toList()

        reader.close()
        return infoFile
    }
}