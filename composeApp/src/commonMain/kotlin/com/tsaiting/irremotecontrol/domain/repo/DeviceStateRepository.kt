package com.tsaiting.irremotecontrol.domain.repo

import com.tsaiting.irremotecontrol.domain.data.DeviceStateResponse
import kotlinx.coroutines.flow.Flow

interface DeviceStateRepository {
    fun getDeviceState(id: String): Flow<DeviceStateResponse?>
    suspend fun updateDevicePowerState(id: String, power: Boolean, place: String = "Living Room")
}