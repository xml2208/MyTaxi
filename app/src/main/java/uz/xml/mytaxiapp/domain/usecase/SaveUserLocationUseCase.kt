package uz.xml.mytaxiapp.domain.usecase

import uz.xml.mytaxiapp.domain.repository.UserLocationsRepository

class SaveUserLocationUseCase(private val repository: UserLocationsRepository) {
    suspend operator fun invoke(longitude: Double, latitude: Double) =
        repository.saveLocation(longitude, latitude)
}