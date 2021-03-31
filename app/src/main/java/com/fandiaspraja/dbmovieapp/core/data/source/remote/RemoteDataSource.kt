package com.fandiaspraja.dbmovieapp.core.data.source.remote

import android.util.Log
import com.fandiaspraja.dbmovieapp.core.data.source.remote.network.ApiResponse
import com.fandiaspraja.dbmovieapp.core.data.source.remote.network.ApiService
import com.fandiaspraja.dbmovieapp.core.data.source.remote.response.TourismResponse
import com.fandiaspraja.moviedbapp.core.data.source.remote.response.MovieItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    fun getAllTourism(): Flow<ApiResponse<List<TourismResponse>>> {
//        val resultData = MutableLiveData<ApiResponse<List<TourismResponse>>>()

        //get data from remote api
        return flow {
            try {
                val response = apiService.getList()
                val dataArray = response.places
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.places))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getBanners(): Flow<ApiResponse<List<MovieItem>>> {

        return flow {
            try {
                val response = apiService.getBanners(
                    "7e6dc9e445f1edd16330cb045b7ba4c0",
                    "en-US",
                    "popularity.desc",
                    false,
                    false,
                    1
                )
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    Log.d("banner nich ", "${response.results}")
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getPopular(): Flow<ApiResponse<List<MovieItem>>> {

        return flow {
            try {
                val response = apiService.getPopularMovie(
                    "7e6dc9e445f1edd16330cb045b7ba4c0",
                    "en-US",
                    "popularity.desc",
                    false,
                    false,
                    1
                )
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getCooming(): Flow<ApiResponse<List<MovieItem>>> {

        return flow {
            try {
                val response = apiService.getComingsoon(
                    "7e6dc9e445f1edd16330cb045b7ba4c0",
                    "en-US",
                    "popularity.desc",
                    false,
                    false,
                    1,
                    "2021"
                )
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}

