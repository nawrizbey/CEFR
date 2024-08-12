package com.imax.cefr.data.models

data class LoginResponseData(
    val token: String,  // masalan, autentifikatsiya tokeni
    val userId: String     // foydalanuvchi identifikatori yoki boshqa zarur ma'lumotlar
)
