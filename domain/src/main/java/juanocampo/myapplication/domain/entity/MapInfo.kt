package juanocampo.myapplication.domain.entity

object MapInfo {
    val HAMBURG = Coordinate(53.5, 10.02)
    val HAMBURG_BOUND = Pair(Coordinate(53.445324, 9.585010), Coordinate(53.703293, 10.433850))
}


data class Coordinate(val latitude: Double, val longitude: Double)