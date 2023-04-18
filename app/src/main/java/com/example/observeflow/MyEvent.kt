package com.example.observeflow

sealed class MyEvent {
    object Loading : MyEvent()
    data class Success(val data: String) : MyEvent()
    data class Error(val message: String) : MyEvent()
}
