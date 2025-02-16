package com.tsaiting.irremotecontrol.view

import androidx.lifecycle.ViewModel
import com.tsaiting.irremotecontrol.view.data.DashboardItem
import com.tsaiting.irremotecontrol.view.data.DeviceType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DashboardViewModel : ViewModel() {
    private val _dashboardItems = MutableStateFlow(DeviceType.values().associateWith {
        DashboardItem(it, "Room", false)
    })
    val dashboardItems: StateFlow<Map<DeviceType, DashboardItem>> = _dashboardItems.asStateFlow()


    fun onPowerClick(deviceType: DeviceType) {
        println("onPowerClick: $deviceType")
    }

}