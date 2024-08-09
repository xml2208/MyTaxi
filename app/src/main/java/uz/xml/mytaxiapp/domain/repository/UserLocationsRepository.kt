package uz.xml.mytaxiapp.domain.repository

interface UserLocationsRepository {
    suspend fun saveLocation(longitude: Double, latitude: Double)
}