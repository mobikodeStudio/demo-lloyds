package com.sapient.lloyds_android_demo.domain.model

sealed class Resource<T> (
    val status: Status,
    val data: T? = null,
    val message: String? = null,
        ){

     class Success<T>( data: T) : Resource<T>(Status.Success,data)
     class Error<T>(data:T? = null,message:String) : Resource<T>(Status.Error,data, message)



}

enum class Status{
    Success,
    Error,
    Loading
}
