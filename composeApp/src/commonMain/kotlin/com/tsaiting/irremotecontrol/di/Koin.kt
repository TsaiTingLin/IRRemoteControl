package com.tsaiting.irremotecontrol.di

import com.tsaiting.irremotecontrol.data.DeviceStateRepositoryImpl
import com.tsaiting.irremotecontrol.data.FanControllerRepositoryImpl
import com.tsaiting.irremotecontrol.data.TVControllerRepositoryImpl
import com.tsaiting.irremotecontrol.domain.GetDeviceStateUseCase
import com.tsaiting.irremotecontrol.domain.SetDeviceStateUseCase
import com.tsaiting.irremotecontrol.domain.repo.DeviceStateRepository
import com.tsaiting.irremotecontrol.domain.repo.FanControllerRepository
import com.tsaiting.irremotecontrol.domain.repo.TVControllerRepository
import com.tsaiting.irremotecontrol.view.DashboardViewModel
import com.tsaiting.irremotecontrol.view.fancontroller.FanControllerViewModel
import com.tsaiting.irremotecontrol.view.tvcontroller.TVControllerViewModel
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val viewModelModule = module {
    factoryOf(::DashboardViewModel)
    factoryOf(::TVControllerViewModel)
    factoryOf(::FanControllerViewModel)
}

val dataModule = module {
    single {
        Firebase.firestore
    }
    factory<DeviceStateRepository> {
        DeviceStateRepositoryImpl(fireStore = get())
    }
    factory<TVControllerRepository> {
        TVControllerRepositoryImpl(fireStore = get())
    }
    factory<FanControllerRepository> { FanControllerRepositoryImpl(fireStore = get()) }
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
