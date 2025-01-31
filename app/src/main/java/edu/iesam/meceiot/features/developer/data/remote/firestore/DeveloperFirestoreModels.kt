package edu.iesam.meceiot.features.developer.data.remote.firestore

import com.google.firebase.firestore.PropertyName

data class DeveloperFirestore(
    @get:PropertyName("id") var id: String = "",
    @get:PropertyName("name") var name: String = "",
    @get:PropertyName("url_avatar") var urlAvatar: String = "",
    @get:PropertyName("url_source") var urlSource: String = "",
    @get:PropertyName("college_degree") var collegeDegree: String = ""
)
