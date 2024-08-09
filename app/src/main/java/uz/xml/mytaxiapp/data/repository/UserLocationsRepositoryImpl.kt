package uz.xml.mytaxiapp.data.repository

import uz.xml.mytaxiapp.data.local.dao.UserLocationsDao
import uz.xml.mytaxiapp.data.local.entity.LocationEntity
import uz.xml.mytaxiapp.domain.repository.UserLocationsRepository

class UserLocationsRepositoryImpl(private val dao: UserLocationsDao) : UserLocationsRepository {
    override suspend fun saveLocation(longitude: Double, latitude: Double) {
        val locationEntity = LocationEntity(longitude = longitude, latitude = latitude)
        dao.insertCurrentLocation(locationEntity)
    }
}