package com.tsaiting.irremotecontrol.view.fancontroller

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tsaiting.irremotecontrol.theme.color_232f34
import com.tsaiting.irremotecontrol.theme.color_344955
import com.tsaiting.irremotecontrol.theme.color_f9aa33
import com.tsaiting.irremotecontrol.view.data.DeviceTypeUi
import com.tsaiting.irremotecontrol.view.tvcontroller.data.TVUiState
import irremotecontrol.composeapp.generated.resources.Res
import irremotecontrol.composeapp.generated.resources.icon_fan
import irremotecontrol.composeapp.generated.resources.icon_power
import irremotecontrol.composeapp.generated.resources.icon_rotate
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FanControllerScreen(
    viewModel: FanControllerViewModel = koinViewModel(),
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    TVControllerView(
        uiState = uiState,
        onClickPower = viewModel::onClickPower,
        onClickRotate = viewModel::onClickRotate,
        onBackClick = {
            onBackClick()
        }
    )
}

@Composable
fun TVControllerView(
    uiState: TVUiState,
    onBackClick: () -> Unit,
    onClickPower: () -> Unit,
    onClickRotate: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.TopEnd
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .size(32.dp)
                .clickable { onBackClick() }
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Icon(
                    painter = painterResource(Res.drawable.icon_fan),
                    contentDescription = null,
                    tint = color_232f34,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = DeviceTypeUi.Fan.getIconName(),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = color_232f34,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                val backgroundColor = if (uiState.power) {
                    color_f9aa33
                } else {
                    color_344955.copy(alpha = 0.5f)
                }
                Icon(
                    modifier = Modifier
                        .size(88.dp)
                        .clickable { onClickPower() },
                    painter = painterResource(Res.drawable.icon_power),
                    contentDescription = "",
                    tint = backgroundColor
                )
                Icon(
                    modifier = Modifier
                        .size(88.dp)
                        .clickable { onClickRotate() },
                    painter = painterResource(Res.drawable.icon_rotate),
                    contentDescription = "",
                    tint = backgroundColor
                )
            }
            Spacer(modifier = Modifier.height(44.dp))
        }
    }

}