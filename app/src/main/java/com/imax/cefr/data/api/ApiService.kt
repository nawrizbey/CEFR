package com.imax.cefr.data.api


import com.imax.cefr.data.models.LoginRequestData
import com.imax.cefr.data.models.LoginResponseData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

// ApiService interfeysi
interface ApiService {
    @POST("/api/auth/login")
    fun login(@Body request: LoginRequestData): Call<LoginResponseData>
}
