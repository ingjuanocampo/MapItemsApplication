package com.juanocampo.mytaxy.test.model.source.remote.mapper

interface IMapper<in R, out T> {
    fun mapResponseToAppModel(toParse: R) : T
}