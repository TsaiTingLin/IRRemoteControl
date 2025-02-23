package com.tsaiting.irremotecontrol.domain


class SetDeviceStateUseCase(
    private val deviceStateRepository: DeviceStateRepository
) {
    suspend operator fun invoke(key: String, power: Boolean) = deviceStateRepository
        .updateDeviceState(key, power)
}