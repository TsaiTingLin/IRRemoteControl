package com.tsaiting.irremotecontrol.domain.repo

import kotlinx.coroutines.flow.Flow

interface FanControllerRepository {
    fun observePowerState(): Flow<Boolean>

    suspend fun powerChange(power: Boolean)

    suspend fun rotate()
}