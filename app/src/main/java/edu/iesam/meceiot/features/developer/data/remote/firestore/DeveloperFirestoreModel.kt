package edu.iesam.meceiot.features.developer.data.remote.firestore

import com.google.firebase.firestore.PropertyName

data class DeveloperFirestoreModel(
    @get:PropertyName("id") @set:PropertyName("id") var id: Int = 0,
    @get:PropertyName("fullName") @set:PropertyName("fullName") var fullName: String = "",
    @get:PropertyName("collegeDegree") @set:PropertyName("collegeDegree") var collegeDegree: String = "",
    @get:PropertyName("urlAvatar") @set:PropertyName("urlAvatar") var urlAvatar: String = "",
    @get:PropertyName("urlSource") @set:PropertyName("urlSource") var urlSource: String = ""
)