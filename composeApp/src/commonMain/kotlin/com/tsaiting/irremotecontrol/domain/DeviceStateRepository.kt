package com.tsaiting.irremotecontrol.domain

import com.tsaiting.irremotecontrol.domain.data.DeviceStateResponse
import kotlinx.coroutines.flow.Flow

interface DeviceStateRepository {
    fun getDeviceState(id: String): Flow<DeviceStateResponse?>
    suspend fun updateDeviceState(id: String, power: Boolean, place: String = "Living Room")
}