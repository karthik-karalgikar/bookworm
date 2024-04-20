package com.example.bookworm.api

import com.example.bookworm.BookData
import com.example.bookworm.model.BookDataResponse
import com.example.bookworm.model.VolumeInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface BookApiService {

    @GET("volumes?q=flowers+inauthor:keyes&key=AIzaSyBmf-LfwyfUQTJnT-7cMTNjDDbSt2y0p_0")

    fun BookApiStatus(
        @Header("Content-Type") headerValue1: String,
        @Header("Accept") headerValue2: String,
        @Header("Accept-Encoding") headerValue3: String,
        @Header("Connection") headerValue4: String
    ) : Call<BookData>
}