package com.tsaiting.irremotecontrol.domain

import com.tsaiting.irremotecontrol.domain.data.DeviceModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class GetDeviceStateUseCase(
    private val deviceStateRepository: DeviceStateRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {
    operator fun invoke(key: String): Flow<DeviceModel> = deviceStateRepository
        .getDeviceState(key)
        .filterNotNull()
        .map {
            DeviceModel(
                key = key,
                power = it.power,
                place = it.place,
                timeInMillis = it.timeInMillis
            )
        }
        .flowOn(defaultDispatcher)
}