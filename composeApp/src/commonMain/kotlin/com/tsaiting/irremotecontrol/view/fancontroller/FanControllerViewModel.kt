package com.tsaiting.irremotecontrol.view.fancontroller

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tsaiting.irremotecontrol.domain.repo.FanControllerRepository
import com.tsaiting.irremotecontrol.view.tvcontroller.data.TVUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class FanControllerViewModel(
    private val fanControllerRepository: FanControllerRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TVUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fanControllerRepository.observePowerState()
            .onEach {
                _uiState.value = TVUiState(power = it)
            }
            .catch { }
            .launchIn(viewModelScope)
    }

    fun onClickPower() {
        viewModelScope.launch {
            val updatePower = _uiState.value.power.not()
            fanControllerRepository.powerChange(updatePower)
        }
    }

    fun onClickRotate() {
        viewModelScope.launch {
            fanControllerRepository.rotate()
        }
    }
}