package uz.xml.mytaxiapp.presentation

import uz.xml.mytaxiapp.domain.model.MyTaxiLngLt
import uz.xml.mytaxiapp.presentation.base.CoreState

data class MyTaxiViewState(
    val zoomLevel: Double = 10.0,
    val currentLocation: MyTaxiLngLt
) : CoreState