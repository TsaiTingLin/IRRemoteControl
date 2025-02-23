package com.tsaiting.irremotecontrol.domain.repo

import kotlinx.coroutines.flow.Flow


interface TVControllerRepository {

    fun observePowerState(): Flow<Boolean>

    suspend fun powerChange(power: Boolean)

    suspend fun volumeUp()

    suspend fun volumeDown()

    suspend fun channelUp()

    suspend fun channelDown()
}