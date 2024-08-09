package uz.xml.mytaxiapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import uz.xml.mytaxiapp.data.local.entity.LocationEntity

@Dao
interface UserLocationsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentLocation(locationEntity: LocationEntity)

//    @Query("SELECT * FROM user_locations")
//    suspend fun getAllLocations(): Flow<List<LocationEntity>>

}