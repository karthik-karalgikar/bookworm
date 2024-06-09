package com.example.bookworm.api

import com.example.bookworm.BookData
import com.example.bookworm.model.VolumeInfo
import com.google.android.gms.common.api.internal.ApiKey
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface BookApiService {

//    @GET("volumes?q=deception+inauthor:&key=AIzaSyBmf-LfwyfUQTJnT-7cMTNjDDbSt2y0p_0")
    @GET("volumes")

    fun BookApiStatus(
        @Query("q") query: String,
        @Query("key") apiKey: String
    ) : Call<BookData>
}