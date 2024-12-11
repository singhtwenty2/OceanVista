package com.singhtwenty2.oceanvista.feature_home.presentation.map_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.singhtwenty2.oceanvista.feature_home.domain.model.dto.response.BeachColorCode
import com.singhtwenty2.oceanvista.feature_home.domain.repository.MapRepository
import com.singhtwenty2.oceanvista.feature_home.util.HomeApiResponseHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MapViewModel(
    private val mapRepository: MapRepository
) : ViewModel() {

    private val _beaches = MutableStateFlow<List<BeachColorCode>>(emptyList())
    val beaches: StateFlow<List<BeachColorCode>> = _beaches.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    init {
        fetchBeaches()
    }

    private fun fetchBeaches() {
        viewModelScope.launch {
            _loading.value = true
            try {
                val beaches = mapRepository.getBeachesColorCode()
                Log.d("MapViewModel", "Fetched Beaches: $beaches")
                _beaches.value = beaches
            } catch (e: Exception) {
                Log.e("MapViewModel", "Error fetching beaches: ${e.message}")
            } finally {
                _loading.value = false
            }
        }
    }
}