package uz.xml.mytaxiapp.presentation

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import uz.xml.mytaxiapp.domain.model.MyTaxiLngLt
import uz.xml.mytaxiapp.domain.usecase.SaveUserLocationUseCase
import uz.xml.mytaxiapp.presentation.base.BaseViewModel

class MainViewModel(
    private val userLocationUseCase: SaveUserLocationUseCase
) : BaseViewModel<MyTaxiViewState, MyTaxiViewEvents>() {

    private val _cameraOptions = MutableStateFlow<CameraOptions?>(null)
    val cameraOptions = _cameraOptions.asStateFlow()

    private val _currentLocation = MutableStateFlow(TASHKENT_POSITION)

    init {
        _cameraOptions.value = CameraOptions.Builder()
            .center(Point.fromLngLat(TASHKENT_POSITION.longitude, TASHKENT_POSITION.latitude))
            .zoom(DEFAULT_ZOOM_CITY_VIEW)
            .build()
    }

    override fun handleEvents(event: MyTaxiViewEvents) {
        when (event) {
            is MyTaxiViewEvents.ZoomIn -> zoomIn(event.currentZoom ?: 0.0)
            is MyTaxiViewEvents.ZoomOut -> zoomOut(event.currentZoom ?: 0.0)
            is MyTaxiViewEvents.MoveToCurrentLocation -> getCurrentLocation(event.activity)
        }
    }

    override fun setInitialState(): MyTaxiViewState =
        MyTaxiViewState(currentLocation = TASHKENT_POSITION)

    private fun zoomIn(currentZoom: Double) {
        viewModelScope.launch {
            val cameraOptions = CameraOptions.Builder()
                .zoom(currentZoom + 1.0)
                .build()
            _cameraOptions.emit(cameraOptions)
        }
    }

    private fun zoomOut(currentZoom: Double) {
        viewModelScope.launch {
            val cameraOptions = CameraOptions.Builder()
                .zoom(currentZoom - 1.0)
                .build()
            _cameraOptions.emit(cameraOptions)
        }
    }

    private fun fusedLocationClient(context: Context): FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    private fun getCurrentLocation(activity: Activity) {
        if (!hasUserLocationPermission(activity = activity)) {
            requestPermission(activity = activity)
        } else {
            fusedLocationClient(activity).lastLocation.addOnSuccessListener { location ->
                location?.let {
                    _currentLocation.value =
                        MyTaxiLngLt(latitude = location.latitude, longitude = location.longitude)
                    setState { MyTaxiViewState(currentLocation = _currentLocation.value) }
                }
                viewModelScope.launch { userLocationUseCase.invoke(location.longitude, location.latitude) }
            }
        }
    }

    private fun hasUserLocationPermission(activity: Activity) = ActivityCompat.checkSelfPermission(
        activity,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
        activity,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    private fun requestPermission(activity: Activity) = ActivityCompat.requestPermissions(
        activity,
        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
        1
    )

    companion object {
        const val DEFAULT_ZOOM_CITY_VIEW = 6.0
        val TASHKENT_POSITION = MyTaxiLngLt(longitude = 69.2401, latitude = 41.2995)
    }

}