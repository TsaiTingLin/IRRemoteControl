package com.tsaiting.irremotecontrol.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tsaiting.irremotecontrol.theme.color_232f34
import com.tsaiting.irremotecontrol.theme.color_334a6572
import com.tsaiting.irremotecontrol.theme.color_344955
import com.tsaiting.irremotecontrol.theme.color_4a6572
import com.tsaiting.irremotecontrol.theme.color_f9aa33
import com.tsaiting.irremotecontrol.view.data.DashboardItem
import com.tsaiting.irremotecontrol.view.data.DeviceType
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashBoardScreen(viewModel: DashboardViewModel = koinViewModel()) {
    val itemMap by viewModel.dashboardItems.collectAsState()
    var showType by remember { mutableStateOf<DeviceType?>(null) }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    showType?.let {
        DeviceDetailBottomSheet(
            sheetState = bottomSheetState,
            type = it,
            onDismiss = {
                showType = null
            }
        )
    }
    DashboardView(
        itemMap = itemMap,
        onPowerClick = viewModel::onPowerClick,
        onItemClick = {
            showType = it
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeviceDetailBottomSheet(type: DeviceType, sheetState: SheetState, onDismiss: () -> Unit) {
    ModalBottomSheet(
        modifier = Modifier.heightIn(min = 200.dp),
        onDismissRequest = {
            onDismiss()
        },
        sheetState = sheetState
    ) {
        when (type) {
            DeviceType.TV -> {
            }

            else -> {
                Text(text = "Not implemented yet")
            }
        }
    }
}

@Composable
fun DashboardView(
    modifier: Modifier = Modifier,
    itemMap: Map<DeviceType, DashboardItem>,
    onPowerClick: (DeviceType) -> Unit,
    onItemClick: (DeviceType) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "My Home",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = color_4a6572,
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Devices",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = color_344955,
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(items = DeviceType.values()) { deviceType ->
                val isOn = itemMap[deviceType]?.inOn ?: false
                Item(
                    item = DashboardItem(
                        deviceType = deviceType,
                        place = "Living Room",
                        inOn = isOn
                    ),
                    onPowerClick = {
                        onPowerClick(deviceType)
                    },
                    onItemClick = {
                        onItemClick(deviceType)
                    }
                )
            }
        }
    }
}


@Composable
fun Item(item: DashboardItem, onPowerClick: () -> Unit, onItemClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = color_334a6572,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onItemClick() }
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(32.dp),
                painter = painterResource(item.deviceType.getIconRes()),
                contentDescription = null,
                tint = color_232f34,
            )
            Switch(
                checked = item.inOn,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = color_f9aa33,
                    checkedTrackColor = color_4a6572.copy(alpha = 0.8f),
                    uncheckedThumbColor = color_344955,
                    uncheckedTrackColor = color_344955.copy(alpha = 0.5f),
                ),
                onCheckedChange = { onPowerClick() }
            )
        }
        Column {
            Text(
                text = item.deviceType.getIconName(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = color_232f34,
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = item.place,
                fontSize = 14.sp,
                color = color_344955,
            )
        }
    }
}
