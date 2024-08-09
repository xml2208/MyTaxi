package uz.xml.mytaxiapp.data.di

import org.koin.dsl.module
import uz.xml.mytaxiapp.domain.usecase.SaveUserLocationUseCase

val UseCasesModule = module{

    single { SaveUserLocationUseCase(get()) }
}