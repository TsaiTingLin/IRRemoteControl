package com.tsaiting.irremotecontrol.domain.data

data class DeviceModel(
    val key: String,
    val power: Boolean,
    val place: String,
    val timeInMillis: Long,
)