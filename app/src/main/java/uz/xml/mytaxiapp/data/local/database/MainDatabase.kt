package uz.xml.mytaxiapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.xml.mytaxiapp.data.local.dao.UserLocationsDao
import uz.xml.mytaxiapp.data.local.entity.LocationEntity

@Database(entities = [LocationEntity::class], version = 1, exportSchema = false)
abstract class MainDatabase : RoomDatabase() {
    abstract fun userLocationsDao(): UserLocationsDao

}