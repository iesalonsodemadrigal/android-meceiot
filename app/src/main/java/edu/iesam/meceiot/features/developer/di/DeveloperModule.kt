package edu.iesam.meceiot.features.developer.di

import com.google.firebase.firestore.FirebaseFirestore
import edu.iesam.meceiot.core.data.local.db.MeceiotDataBase
import edu.iesam.meceiot.features.developer.data.local.db.DeveloperDao
import edu.iesam.meceiot.features.developer.data.remote.api.DeveloperApiService
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import retrofit2.Retrofit

@Module
@ComponentScan
class DeveloperModule {

    @Single
    fun provideDeveloperApiService(retrofit: Retrofit): DeveloperApiService =
        retrofit.create(DeveloperApiService::class.java)

    @Single
        fun provideDeveloperDao(db: MeceiotDataBase): DeveloperDao {
        return db.developerDao()
    }

    @Single
    fun provideFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

}