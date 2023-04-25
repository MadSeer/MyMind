package com.mymind.core.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel<T> : ViewModel() {
    protected abstract val state: T

    val currentState = MutableLiveData<T>()
    val currentStateValue: T
        get() = currentState.value ?: state

    protected fun update(stateUpdate: T.() -> T) = viewModelScope.launch(Dispatchers.Main) {
        currentState.value = currentStateValue.stateUpdate()
    }
}