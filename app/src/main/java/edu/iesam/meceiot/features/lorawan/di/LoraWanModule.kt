package edu.iesam.meceiot.features.lorawan.di

import edu.iesam.meceiot.core.data.local.db.MeceiotDataBase
import edu.iesam.meceiot.features.lorawan.data.local.db.LoraWanDao
import edu.iesam.meceiot.features.lorawan.data.remote.LoraWanApiService
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import retrofit2.Retrofit


@Module
@ComponentScan
class LoraWanModule {

    @Single
    fun provideInfoLorawanService(retrofit: Retrofit): LoraWanApiService =
        retrofit.create(LoraWanApiService::class.java)

    @Single
    fun provideLoraWanInfoDao(db: MeceiotDataBase): LoraWanDao {
        return db.loraWanDao()
    }
}
