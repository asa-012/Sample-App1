package com.asa.atrae_sample_app1.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asa.atrae_sample_app1.network.RandomUsersDataPage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class UsersApiStatus { LOADING, ERROR, DONE }

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _status = MutableLiveData<UsersApiStatus>()

    val status: LiveData<UsersApiStatus>
        get() = _status

    private val _properties = MutableLiveData<RandomUsersDataPage>()

    val properties: LiveData<RandomUsersDataPage>
        get() = _properties

    init {
        getUsersProperties()
    }

    private fun getUsersProperties() {
        viewModelScope.launch {
            _status.value = UsersApiStatus.LOADING
            try {
                _properties.value = mainRepository.getUsersProperties()
                _status.value = UsersApiStatus.DONE
            } catch (e: Exception) {
                _status.value = UsersApiStatus.ERROR
                _properties.value = null
            }
        }
    }

}