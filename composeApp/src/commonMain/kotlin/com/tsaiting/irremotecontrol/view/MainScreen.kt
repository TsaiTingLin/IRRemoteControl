package com.tsaiting.irremotecontrol.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tsaiting.irremotecontrol.theme.color_4a6572
import com.tsaiting.irremotecontrol.theme.color_a7a7a7
import com.tsaiting.irremotecontrol.theme.color_f9aa33
import com.tsaiting.irremotecontrol.view.data.BottomNavType
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun MainScreen() {
    val naveController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(naveController)
        },
        content = { padding ->
            Content(navController = naveController, modifier = Modifier.padding(padding))
        }
    )
}

@Composable
fun BottomBar(naveController: NavHostController) {
    var selectedType by remember {
        mutableStateOf<BottomNavType>(BottomNavType.Dashboard)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(color_4a6572)
    ) {
        BottomNavType.values().forEach {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable {
                        selectedType = it
                        naveController.navigate(it.name)
                    },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val color = if (it.name == selectedType.name) {
                    color_f9aa33
                } else {
                    color_a7a7a7
                }
                val res = it.getIconRes()
                Icon(
                    painter = painterResource(res),
                    contentDescription = "",
                    tint = color,
                )
            }
        }
    }
}

@Composable
fun Content(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController,
        startDestination = BottomNavType.Dashboard.name,
        modifier = modifier
    ) {
        composable(BottomNavType.Dashboard.name) {
            DashBoardScreen()
        }
        composable(BottomNavType.Home.name) {
            Box {
                Text(text = "Home")
            }
        }
        composable(BottomNavType.Settings.name) {
            Box {
                Text(text = "Settings")
            }
        }
    }
}