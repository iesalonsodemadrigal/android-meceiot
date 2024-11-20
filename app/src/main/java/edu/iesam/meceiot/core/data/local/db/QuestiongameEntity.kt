package edu.iesam.meceiot.core.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

const val Questiongame_table = "questiongame"
const val Questiongame_id = "id"

@Entity(tableName = Questiongame_table)
class QuestiongameEntity(
    @PrimaryKey @ColumnInfo(name = Questiongame_id) val id: Int,
    @ColumnInfo(name = "question") val question: String,
    @ColumnInfo(name = "url_imagen") val url_imagen: String,
    @ColumnInfo(name = "respuesta1") val respuesta1: String,
    @ColumnInfo(name = "respuesta2") val respuesta2: String,
    @ColumnInfo(name = "respuesta3") val respuesta3: String,
    @ColumnInfo(name = "respuesta4") val respuesta4: String,
    @ColumnInfo(name = "ultimo_dato") val date: Date

    )