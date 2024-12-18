package edu.iesam.meceiot.features.pantallatest.data

import edu.iesam.meceiot.features.pantallatest.domain.Question

class OptionsDataRepository : OptionsDataSource {
    private val questions = mutableListOf<Question>()

    init {
        // Inicializar las preguntas aquí
        questions.addAll(
            listOf(
                Question(
                    1,
                    "¿Qué es LoRaWAN?",
                    "logo_lorawan",
                    "Un protocolo de comunicación de largo alcance",
                    "Un tipo de red social",
                    "Un lenguaje de programación",
                    "Un sistema operativo",
                    "Un protocolo de comunicación de largo alcance"
                ),
                Question(
                    2,
                    "¿Qué significa LoRa en LoRaWAN?",
                    "logo_lorawan",
                    "Long Range",
                    "Local Radio",
                    "Low Range",
                    "Local Range",
                    "Long Range"
                ),
                Question(
                    3,
                    "¿Cuál es la principal ventaja de LoRaWAN?",
                    "logo_lorawan",
                    "Bajo consumo de energía",
                    "Alta velocidad de datos",
                    "Alta capacidad de almacenamiento",
                    "Compatibilidad con Wi-Fi",
                    "Bajo consumo de energía"
                ),
                Question(
                    4,
                    "¿Qué tipo de dispositivos utilizan LoRaWAN?",
                    "logo_lorawan",
                    "Dispositivos IoT",
                    "Teléfonos móviles",
                    "Computadoras portátiles",
                    "Televisores inteligentes",
                    "Dispositivos IoT"
                ),
                Question(
                    5,
                    "¿Cuál es la frecuencia típica de operación de LoRaWAN?",
                    "logo_lorawan",
                    "868 MHz",
                    "2.4 GHz",
                    "5 GHz",
                    "900 MHz",
                    "868 MHz"
                ),
                Question(
                    6,
                    "¿Qué significa la 'WAN' en LoRaWAN?",
                    "logo_lorawan",
                    "Wide Area Network",
                    "Wireless Area Network",
                    "Wired Area Network",
                    "Web Area Network",
                    "Wide Area Network"
                ),
                Question(
                    7,
                    "¿Qué tipo de modulación utiliza LoRaWAN?",
                    "logo_lorawan",
                    "Chirp Spread Spectrum",
                    "Frequency Hopping",
                    "Amplitude Modulation",
                    "Phase Modulation",
                    "Chirp Spread Spectrum"
                )
            )
        )
    }

    override fun getSelectedOptions(): List<Question> {
        return questions
    }

    override fun updateSelectedOption(question: Question) {
        val index = questions.indexOfFirst { it.id == question.id }
        if (index != -1) {
            questions[index] = question
        } else {
            questions.add(question)
        }
    }

    fun getQuestions(): List<Question> {
        return questions
    }
}