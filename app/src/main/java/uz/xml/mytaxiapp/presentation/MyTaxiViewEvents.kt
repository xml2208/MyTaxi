package uz.xml.mytaxiapp.presentation

import uz.xml.mytaxiapp.presentation.base.CoreEvent

sealed interface MyTaxiViewEvents: CoreEvent {

    data class ZoomIn(val currentZoom: Double?): MyTaxiViewEvents
    data class ZoomOut(val currentZoom: Double?): MyTaxiViewEvents

}