package com.imax.cefr.data.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.imax.cefr.data.models.LoginRequestData
import com.imax.cefr.data.models.LoginResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private val _loginResult = MutableLiveData<Result<LoginResponseData>>()
    val loginResult: LiveData<Result<LoginResponseData>> = _loginResult

    fun login(email: String, password: String) {
        val loginRequest = LoginRequestData(email, password)
        RetrofitClient.apiService.login(loginRequest).enqueue(object : Callback<LoginResponseData> {
            override fun onResponse(call: Call<LoginResponseData>, response: Response<LoginResponseData>) {
                if (response.isSuccessful) {
                    _loginResult.postValue(Result.success(response.body()) as Result<LoginResponseData>?)
                } else {
                    val errorMessage = response.errorBody()?.string() ?:"Login failed"
                    _loginResult.postValue(Result.failure(Exception(errorMessage)))
                }
            }

            override fun onFailure(call: Call<LoginResponseData>, t: Throwable) {
                _loginResult.postValue(Result.failure(t))
            }
        })
    }
}
