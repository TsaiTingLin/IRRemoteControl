package com.tsaiting.irremotecontrol.data

import com.tsaiting.irremotecontrol.data.data.TVCommand
import com.tsaiting.irremotecontrol.domain.data.DeviceStateResponse
import com.tsaiting.irremotecontrol.domain.repo.TVControllerRepository
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.Timestamp
import dev.gitlive.firebase.firestore.toMilliseconds
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class TVControllerRepositoryImpl(
    private val fireStore: FirebaseFirestore,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : TVControllerRepository {

    override fun observePowerState(): Flow<Boolean> = fireStore.collection(PATH)
        .document(ID)
        .snapshots
        .map { document ->
            document.data<DeviceStateResponse>().power
        }
        .flowOn(ioDispatcher)


    override suspend fun powerChange(power: Boolean) {
        updateState(TVCommand.POWER.id)
        updatePower(power)

    }

    override suspend fun channelDown() {
        return updateState(TVCommand.CHANNEL_DOWN.id)
    }

    override suspend fun channelUp() {
        return updateState(TVCommand.CHANNEL_UP.id)
    }

    override suspend fun volumeDown() {
        return updateState(TVCommand.VOLUME_DOWN.id)
    }

    override suspend fun volumeUp() {
        return updateState(TVCommand.VOLUME_UP.id)
    }

    private suspend fun updateState(id: String) = withContext(ioDispatcher) {
        val data = mapOf(
            COMMAND to id,
            TIME_IN_MILLIS to Timestamp.now().toMilliseconds().toLong()
        )
        fireStore.collection(PATH)
            .document(ID)
            .set(data = data, merge = true)
    }

    private suspend fun updatePower(power: Boolean) = withContext(ioDispatcher) {
        val data = mapOf(
            POWER to power
        )
        fireStore.collection(PATH)
            .document(ID)
            .set(data = data, merge = true)
    }

    companion object {
        private const val PATH = "deviceState"
        private const val ID = "tvState"
        private const val COMMAND = "command"
        private const val TIME_IN_MILLIS = "timeInMillis"
        private const val POWER = "power"
    }
}