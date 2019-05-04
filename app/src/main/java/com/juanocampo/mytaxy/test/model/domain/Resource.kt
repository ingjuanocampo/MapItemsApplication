package com.juanocampo.mytaxy.test.model.domain

import android.support.annotation.Nullable


class Resource<T>(val status: Status = Status.LOADING, val info: T? = null, val message: String = "") {

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(status = Status.SUCCESS, info = data)
        }

        fun <T> error(msg: String, @Nullable data: T? = null): Resource<T> {
            return Resource(status = Status.ERROR, info = data, message = msg)
        }

        fun <T> loading(@Nullable data: T? = null): Resource<T> {
            return Resource(status = Status.LOADING, info = data)
        }
    }
}

enum class Status {
    SUCCESS, ERROR, LOADING
}
