package com.example.waitflow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class FlowViewModel : ViewModel() {
    private val namesFlow = flow {
        val names = listOf("Jody", "Steve", "Lance", "Joe")
        for (name in names) {
            delay(5000)
            emit(name)
        }
    }

    val namesAsLiveData = namesFlow.asLiveData()
}