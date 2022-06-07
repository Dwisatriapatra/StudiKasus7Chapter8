package com.example.studikasus7chapter8.networking

import com.example.studikasus7chapter8.model.GetAllNewsResponseItem
import com.example.studikasus7chapter8.model.GetAllUserResponseItem
import com.example.studikasus7chapter8.model.PostUser
import com.example.studikasus7chapter8.model.RequestUser
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiServices {
    @GET("user")
    suspend fun getAllUser(): List<GetAllUserResponseItem>

    @POST("user")
    suspend fun addNewUser(@Body requestUser: RequestUser): Call<PostUser>

    @GET("news")
    suspend fun getAllNews(): List<GetAllNewsResponseItem>
}