package uz.xml.mytaxiapp.data.di

import androidx.room.Room
import org.koin.dsl.module
import uz.xml.mytaxiapp.data.local.database.MainDatabase
import uz.xml.mytaxiapp.domain.usecase.SaveUserLocationUseCase
import uz.xml.mytaxiapp.presentation.MainViewModel

val MainModule = module {

    single {
        Room.databaseBuilder(
            context = get(),
            klass = MainDatabase::class.java,
            name = "my-database"
        ).build()
    }

    single { get<MainDatabase>().userLocationsDao() }

    factory {
        MainViewModel(userLocationUseCase = get<SaveUserLocationUseCase>())
    }

}