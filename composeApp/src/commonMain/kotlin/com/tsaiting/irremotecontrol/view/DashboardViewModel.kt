package com.tsaiting.irremotecontrol.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tsaiting.irremotecontrol.domain.GetDeviceStateUseCase
import com.tsaiting.irremotecontrol.domain.SetDeviceStateUseCase
import com.tsaiting.irremotecontrol.view.data.DashboardItem
import com.tsaiting.irremotecontrol.view.data.DeviceTypeUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val getDeviceStateUseCase: GetDeviceStateUseCase,
    private val setDeviceStateUseCase: SetDeviceStateUseCase
) : ViewModel() {
    private val _dashboardItems = MutableStateFlow<Map<DeviceTypeUi, DashboardItem>>(emptyMap())
    val dashboardItems: StateFlow<Map<DeviceTypeUi, DashboardItem>> = _dashboardItems.asStateFlow()


    fun onPowerClick(deviceTypeUi: DeviceTypeUi, power: Boolean) {
        viewModelScope.launch {
            setDeviceStateUseCase(deviceTypeUi.key, power.not())
        }
    }

    fun getDeviceState() {
        DeviceTypeUi.values().forEach { each ->
            viewModelScope.launch {
                getDeviceStateUseCase(each.key)
                    .catch {}
                    .collect { deviceModel ->
                        val items = _dashboardItems.value.toMutableMap()
                        val device = DeviceTypeUi.fromKey(deviceModel.key)
                        items[device] = DashboardItem(device, deviceModel.place, deviceModel.power)
                        _dashboardItems.value = items
                    }
            }
        }
    }


}