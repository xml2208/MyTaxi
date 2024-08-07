package uz.xml.mytaxiapp.data.di

import org.koin.dsl.module
import uz.xml.mytaxiapp.presentation.MainViewModel

val MainModule = module {

    factory {
        MainViewModel()
    }
}