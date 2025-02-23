package com.tsaiting.irremotecontrol.di

import com.tsaiting.irremotecontrol.data.DeviceStateRepositoryImpl
import com.tsaiting.irremotecontrol.domain.DeviceStateRepository
import com.tsaiting.irremotecontrol.domain.GetDeviceStateUseCase
import com.tsaiting.irremotecontrol.domain.SetDeviceStateUseCase
import com.tsaiting.irremotecontrol.view.DashboardViewModel
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val viewModelModule = module {
    factoryOf(::DashboardViewModel)
}

val dataModule = module {
    factory<DeviceStateRepository> {
        DeviceStateRepositoryImpl(fireStore = Firebase.firestore)
    }
}

val useCaseModule = module {
    factory { GetDeviceStateUseCase(deviceStateRepository = get()) }
    factory { SetDeviceStateUseCase(deviceStateRepository = get()) }
}


fun initKoin() {
    startKoin {
        modules(
            viewModelModule,
            dataModule,
            useCaseModule
        )
    }
}
