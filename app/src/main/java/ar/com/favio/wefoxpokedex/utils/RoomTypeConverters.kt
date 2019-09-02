package ar.com.favio.wefoxpokedex.utils
import androidx.room.TypeConverter
import ar.com.favio.wefoxpokedex.core.data.model.Types

class RoomTypeConverters {

    @TypeConverter
    fun stringToTypes(value: String): Types {
        val types =value.split("\\s*,\\s*".toRegex()).dropLastWhile { it.isEmpty() }
        return  Types(types)
    }

    @TypeConverter
    fun typesToString(cl: Types): String {
        var value = ""
        for (lang in cl.types!!)
            value += "$lang,"
        return value
    }
}