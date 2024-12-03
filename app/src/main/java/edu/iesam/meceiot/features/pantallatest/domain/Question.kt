package  edu.iesam.meceiot.features.pantallatest.domain

data class Question(
    val id: String,
    val text: String,
    val urlimagen: String,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String,
    val correctOption: String
)