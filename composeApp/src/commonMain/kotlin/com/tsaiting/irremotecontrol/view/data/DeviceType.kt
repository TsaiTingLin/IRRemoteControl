package com.tsaiting.irremotecontrol.view.data

import irremotecontrol.composeapp.generated.resources.Res
import irremotecontrol.composeapp.generated.resources.icon_ac
import irremotecontrol.composeapp.generated.resources.icon_fan
import irremotecontrol.composeapp.generated.resources.icon_tv
import org.jetbrains.compose.resources.DrawableResource

sealed class DeviceType {
    data object AC : DeviceType()
    data object TV : DeviceType()
    data object Fan : DeviceType()

    fun getIconRes(): DrawableResource {
        return when (this) {
            AC -> Res.drawable.icon_ac
            TV -> Res.drawable.icon_tv
            Fan -> Res.drawable.icon_fan
        }
    }

    fun getIconName(): String {
        return when (this) {
            AC -> "AC"
            TV -> "TV"
            Fan -> "Fan"
        }
    }

    companion object {
        fun values() = listOf(TV, AC, Fan)
    }
}