package com.tsaiting.irremotecontrol.data

import com.tsaiting.irremotecontrol.domain.data.DeviceStateResponse
import com.tsaiting.irremotecontrol.domain.repo.DeviceStateRepository
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

class DeviceStateRepositoryImpl(
    private val fireStore: FirebaseFirestore,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : DeviceStateRepository {

    override fun getDeviceState(id: String): Flow<DeviceStateResponse?> = fireStore.collection(PATH)
        .document(id)
        .snapshots
        .map { document ->
            document.data<DeviceStateResponse>()
        }
        .flowOn(ioDispatcher)

    override suspend fun updateDevicePowerState(id: String, power: Boolean, place: String) =
        withContext(ioDispatcher) {
            val data = mapOf(
                POWER to power,
                TIME_IN_MILLIS to Timestamp.now().toMilliseconds().toLong(),
                COMMAND to "0",
            )
            fireStore.collection(PATH)
                .document(id)
                .set(data = data, merge = true)
        }

    companion object {
        private const val PATH = "deviceState"
        private const val TIME_IN_MILLIS = "timeInMillis"
        private const val POWER = "power"
        private const val COMMAND = "command"
    }
}