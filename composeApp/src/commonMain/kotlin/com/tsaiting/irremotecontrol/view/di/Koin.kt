package com.tsaiting.irremotecontrol.view.di

import com.tsaiting.irremotecontrol.view.DashboardViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val viewModelModule = module {
    factoryOf(::DashboardViewModel)
}

fun initKoin() {
    startKoin {
        modules(
            viewModelModule,
        )
    }
}
