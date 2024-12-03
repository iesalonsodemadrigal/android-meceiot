package edu.iesam.meceiot.features.pantallatest.data

import edu.iesam.meceiot.features.pantallatest.domain.Question

interface OptionsDataSource {
    fun getSelectedOptions(): List<Question>
}