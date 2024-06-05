package com.example.bookworm.api

import com.example.bookworm.BookData
import com.example.bookworm.model.VolumeInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface BookApiService {

    @GET("volumes?q=inferno+inauthor:dan&key=AIzaSyBmf-LfwyfUQTJnT-7cMTNjDDbSt2y0p_0")

    fun BookApiStatus() : Call<BookData>
}