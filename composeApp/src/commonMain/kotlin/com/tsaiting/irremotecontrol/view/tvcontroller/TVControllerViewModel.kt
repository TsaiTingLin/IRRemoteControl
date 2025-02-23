package com.tsaiting.irremotecontrol.view.tvcontroller

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tsaiting.irremotecontrol.domain.repo.TVControllerRepository
import com.tsaiting.irremotecontrol.view.tvcontroller.data.TVUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class TVControllerViewModel(
    private val tvControllerRepository: TVControllerRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TVUiState())
    val uiState = _uiState.asStateFlow()

    init {
        tvControllerRepository.observePowerState()
            .onEach {
                _uiState.value = TVUiState(power = it)
            }
            .catch { }
            .launchIn(viewModelScope)
    }

    fun onClickPower() {
        viewModelScope.launch {
            val updatePower = _uiState.value.power.not()
            tvControllerRepository.powerChange(updatePower)
        }
    }

    fun channelUp() {
        viewModelScope.launch {
            tvControllerRepository.channelUp()
        }
    }

    fun channelDown() {
        viewModelScope.launch {
            tvControllerRepository.channelDown()
        }
    }

    fun volumeUp() {
        viewModelScope.launch {
            tvControllerRepository.volumeUp()
        }
    }

    fun volumeDown() {
        viewModelScope.launch {
            tvControllerRepository.volumeDown()
        }
    }

}