package ar.com.favio.wefoxpokedex.core.data.db

interface BaseDao {
    fun flushData()
    fun getItemCount() : Int
}
