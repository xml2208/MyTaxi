package uz.xml.mytaxiapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_locations")
data class LocationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo("longitude")
    val longitude: Double,

    @ColumnInfo("latitude")
    val latitude: Double,
)