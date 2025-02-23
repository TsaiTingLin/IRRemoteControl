package com.tsaiting.irremotecontrol.view.tvcontroller

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tsaiting.irremotecontrol.theme.color_232f34
import com.tsaiting.irremotecontrol.theme.color_344955
import com.tsaiting.irremotecontrol.theme.color_4a6572
import com.tsaiting.irremotecontrol.theme.color_f9aa33
import com.tsaiting.irremotecontrol.view.data.DeviceTypeUi
import com.tsaiting.irremotecontrol.view.tvcontroller.data.TVUiState
import irremotecontrol.composeapp.generated.resources.Res
import irremotecontrol.composeapp.generated.resources.icon_power
import irremotecontrol.composeapp.generated.resources.icon_tv
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TVControllerScreen(
    viewModel: TVControllerViewModel = koinViewModel(),
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    TVControllerView(
        uiState = uiState,
        onClickPower = viewModel::onClickPower,
        onClickChannelUp = viewModel::channelUp,
        onClickChannelDown = viewModel::channelDown,
        onClickVolumeUp = viewModel::volumeUp,
        onClickVolumeDown = viewModel::volumeDown,
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
    onClickChannelUp: () -> Unit,
    onClickChannelDown: () -> Unit,
    onClickVolumeUp: () -> Unit,
    onClickVolumeDown: () -> Unit
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
                    painter = painterResource(Res.drawable.icon_tv),
                    contentDescription = null,
                    tint = color_232f34,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = DeviceTypeUi.TV.getIconName(),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = color_232f34,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                ChannelButton(
                    isOn = uiState.power,
                    onClickChannelUp = onClickChannelUp,
                    onClickChannelDown = onClickChannelDown
                )
                val backgroundColor = if (uiState.power) {
                    color_f9aa33
                } else {
                    color_344955.copy(alpha = 0.5f)
                }
                Spacer(modifier = Modifier.width(32.dp))
                Icon(
                    modifier = Modifier
                        .size(96.dp)
                        .clickable { onClickPower() },
                    painter = painterResource(Res.drawable.icon_power),
                    contentDescription = "",
                    tint = backgroundColor
                )
                Spacer(modifier = Modifier.width(32.dp))
                VolumeButton(
                    isOn = uiState.power,
                    onClickVolumeUp = onClickVolumeUp,
                    onClickVolumeDown = onClickVolumeDown
                )
            }
            Spacer(modifier = Modifier.height(44.dp))
        }
    }

}

@Composable
private fun ChannelButton(
    isOn: Boolean,
    onClickChannelUp: () -> Unit,
    onClickChannelDown: () -> Unit
) {
    val backgroundColor = if (isOn) {
        color_4a6572
    } else {
        color_344955.copy(alpha = 0.5f)
    }
    val textColor = if (isOn) {
        color_f9aa33
    } else {
        color_344955.copy(alpha = 0.5f)
    }
    Box(
        modifier = Modifier
            .size(width = 60.dp, height = 150.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(color = backgroundColor)
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(50.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 16.dp)
        ) {
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onClickChannelUp() },
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "Channel Up",
                tint = textColor,
            )
            Text(
                text = "CH",
                color = textColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Channel Down",
                tint = textColor,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onClickChannelDown() }
            )
        }
    }
}

@Composable
private fun VolumeButton(
    isOn: Boolean,
    onClickVolumeUp: () -> Unit,
    onClickVolumeDown: () -> Unit
) {
    val backgroundColor = if (isOn) {
        color_4a6572
    } else {
        color_344955.copy(alpha = 0.5f)
    }
    val textColor = if (isOn) {
        color_f9aa33
    } else {
        color_344955.copy(alpha = 0.5f)
    }
    Box(
        modifier = Modifier
            .size(width = 60.dp, height = 150.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(color = backgroundColor)
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(50.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "Volume Up",
                tint = textColor,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onClickVolumeUp() }
            )
            Text(
                text = "VOL",
                color = textColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Volume Down",
                tint = textColor,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onClickVolumeDown() }
            )
        }
    }
}