package juanocampo.myapplication.data.source.remote.mapper

interface IMapper<in R, out T> {
    fun mapResponseToAppModel(toParse: R) : T
}