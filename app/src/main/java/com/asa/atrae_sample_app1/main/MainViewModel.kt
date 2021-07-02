package com.asa.atrae_sample_app1.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asa.atrae_sample_app1.network.RandomUsersDataPage
import com.asa.atrae_sample_app1.network.UsersApi
import com.asa.atrae_sample_app1.network.UsersProperties
import kotlinx.coroutines.launch

enum class UsersApiStatus { LOADING, ERROR, DONE }

class MainViewModel : ViewModel() {

    private val userInfo by lazy { UsersApi.retrofitService }

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<UsersApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<UsersApiStatus>
        get() = _status

    // Internally, we use a MutableLiveData, because we will be updating the List of MarsProperty
    // with new values
    //リストにする List<UserProperty>
    private val _properties = MutableLiveData<RandomUsersDataPage>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val properties: LiveData<RandomUsersDataPage>
        get() = _properties

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getUsersProperties()
    }

    /**
     * Gets filtered Mars real estate property information from the Mars API Retrofit service and
     * updates the [MarsProperty] [List] and [MarsApiStatus] [LiveData]. The Retrofit service
     * returns a coroutine Deferred, which we await to get the result of the transaction.
     * @param filter the [MarsApiFilter] that is sent as part of the web server request
     */
    private fun getUsersProperties() {

        viewModelScope.launch {
            _status.value = UsersApiStatus.LOADING
            try {
                _properties.value = userInfo.getProperties()
                _status.value = UsersApiStatus.DONE
            } catch (e: Exception) {
                _status.value = UsersApiStatus.ERROR
                _properties.value = null
            }
        }
    }

}