package com.carry.wanandroid.core.api

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

/**
 * Create by SunnyDay /09/25 22:49:34
 */
interface ApiService {
    @GET("/")
    fun getUsers(): Flow<String>
}