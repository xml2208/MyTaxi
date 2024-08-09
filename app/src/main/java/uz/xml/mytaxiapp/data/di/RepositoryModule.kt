package uz.xml.mytaxiapp.data.di

import org.koin.dsl.module
import uz.xml.mytaxiapp.data.repository.UserLocationsRepositoryImpl
import uz.xml.mytaxiapp.domain.repository.UserLocationsRepository

val RepositoryModule = module {

    single<UserLocationsRepository> { UserLocationsRepositoryImpl(get()) }
}