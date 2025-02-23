package com.tsaiting.irremotecontrol.domain

import com.tsaiting.irremotecontrol.domain.repo.DeviceStateRepository


class SetDeviceStateUseCase(
    private val deviceStateRepository: DeviceStateRepository
) {
    suspend operator fun invoke(key: String, power: Boolean) = deviceStateRepository
        .updateDevicePowerState(key, power)
}