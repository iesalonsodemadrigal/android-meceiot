package edu.iesam.meceiot.core.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module


@Module
@ComponentScan
class RemoteModule {

    private val url = "https://sandbox.aulapragmatica.es/"
}