package edu.iesam.meceiot.features.developer.data.remote.firestore

import edu.iesam.meceiot.features.developer.data.firestore.DeveloperFirestoreModel
import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo

object DeveloperFirestoreMappers {


    fun toDomainModel(firestoreModel: DeveloperFirestoreModel): DeveloperInfo {
        return DeveloperInfo(
            id = firestoreModel.id,
            fullName = firestoreModel.fullName,
            collegeDegree = firestoreModel.collegeDegree,
            urlAvatar = firestoreModel.urlAvatar,
            urlSource = firestoreModel.urlSource
        )
    }
}