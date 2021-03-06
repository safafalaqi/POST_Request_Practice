package com.example.postrequestpractice
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIInterface {

    @GET("/test/")
  fun getUsersInfo(): Call<Users?>?

    @POST("/test/")
  fun addUsersInfo(@Body requestBody: UserItem?): Call<UserItem>

}
