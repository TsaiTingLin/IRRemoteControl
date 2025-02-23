package com.tsaiting.irremotecontrol.domain.data

import kotlinx.serialization.Serializable

@Serializable
data class DeviceStateResponse(
    val power: Boolean,
    val place: String,
    val timeInMillis: Long,
)