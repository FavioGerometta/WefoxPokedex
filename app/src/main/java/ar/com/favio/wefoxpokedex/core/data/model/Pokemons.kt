package ar.com.favio.wefoxpokedex.core.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

import kotlinx.android.parcel.Parcelize

@Entity(tableName = "pokemons")
@Parcelize
data class Pokemon(
    @PrimaryKey
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val frontSprite: String,
    val base_experience: Int,
    val order: Int,
    val types: Types,
    var dateTime: String
):Parcelable

@Parcelize
data class Types(var types: List<String>?):Parcelable


