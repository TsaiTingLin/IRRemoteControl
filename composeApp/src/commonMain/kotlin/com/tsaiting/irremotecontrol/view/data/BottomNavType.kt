package com.tsaiting.irremotecontrol.view.data

import irremotecontrol.composeapp.generated.resources.Res
import irremotecontrol.composeapp.generated.resources.icon_app_settings
import irremotecontrol.composeapp.generated.resources.icon_dashboard
import irremotecontrol.composeapp.generated.resources.icon_home
import org.jetbrains.compose.resources.DrawableResource

sealed class BottomNavType(val name: String) {
    data object Home : BottomNavType("Home")
    data object Dashboard : BottomNavType("Dashboard")
    data object Settings : BottomNavType("Settings")

    fun getIconRes(): DrawableResource {
        return when (this) {
            Home -> Res.drawable.icon_home
            Dashboard -> Res.drawable.icon_dashboard
            Settings -> Res.drawable.icon_app_settings
        }
    }

    companion object {
        fun values() = listOf(Dashboard, Home, Settings)
    }
}