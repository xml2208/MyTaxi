package uz.xml.mytaxiapp.presentation

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.xml.mytaxiapp.presentation.base.BaseViewModel

class MainViewModel : BaseViewModel<MyTaxiViewState, MyTaxiViewEvents>() {

    private val tashkentMapDefaultPosition = Point.fromLngLat(69.2401, 41.2995)

    private val _cameraOptions = MutableStateFlow<CameraOptions?>(null)
    val cameraOptions = _cameraOptions.asStateFlow()

    init {
        _cameraOptions.value = CameraOptions.Builder()
            .zoom(10.0)
            .center(tashkentMapDefaultPosition)
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
        MyTaxiViewState()

    private fun zoomIn(currentZoom: Double) {
        viewModelScope.launch {
            val cameraOptions = CameraOptions.Builder()
                .zoom(currentZoom + 1.0)
                .build()
            _cameraOptions.emit(cameraOptions)
        }
        setState { MyTaxiViewState(zoomLevel = cameraOptions.value?.zoom ?: currentZoom) }
    }

    private fun zoomOut(currentZoom: Double) {
        viewModelScope.launch {
            val cameraOptions = CameraOptions.Builder()
                .zoom(currentZoom - 1.0)
                .build()
            _cameraOptions.emit(cameraOptions)
        }
        setState { MyTaxiViewState(zoomLevel = cameraOptions.value?.zoom ?: currentZoom) }
    }

    private fun fusedLocationClient(context: Context): FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    private fun getCurrentLocation(activity: Activity) {
            if (ActivityCompat.checkSelfPermission(
                    activity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    activity,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    1
                )
            } else {
                fusedLocationClient(activity).lastLocation.addOnSuccessListener { location ->
                    location?.let {
                        Log.d("xml22", "getCurrentLocation: $location")
                    }
                }
            }
        }
    }