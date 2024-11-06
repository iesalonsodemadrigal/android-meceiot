package edu.iesam.meceiot.features.pantallatest.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import edu.iesam.meceiot.features.pantallatest.data.DataSource
import edu.iesam.meceiot.features.pantallatest.data.OptionsRepository
import edu.iesam.meceiot.features.pantallatest.data.QuestionOption
import edu.iesam.meceiot.features.pantallatest.domain.GetSelectedOptionsUseCase
import edu.iesam.meceiot.features.pantallatest.domain.UpdateSelectedOptionUseCase
import com.example.android_meceiot.R

class TestFragment : Fragment(R.layout.testfragment) {

    private lateinit var getSelectedOptionsUseCase: GetSelectedOptionsUseCase
    private lateinit var updateSelectedOptionUseCase: UpdateSelectedOptionUseCase
    private lateinit var selectedOptionsTextView: TextView
    private lateinit var correctOptions: List<QuestionOption>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.testfragment, container, false)

        val dataSource = DataSource()
        val repository = OptionsRepository(dataSource)
        getSelectedOptionsUseCase = GetSelectedOptionsUseCase(repository)
        updateSelectedOptionUseCase = UpdateSelectedOptionUseCase(repository)
        correctOptions = dataSource.getCorrectOptions()

        selectedOptionsTextView = view.findViewById(R.id.selectedOptionsTextView)

        val submitButton: Button = view.findViewById(R.id.submitButton)
        submitButton.setOnClickListener {
            handleSubmit(view)
        }

        return view
    }

    private fun handleSubmit(view: View) {
        val radioGroups = listOf(
            view.findViewById<RadioGroup>(R.id.radioGroup1),
            view.findViewById<RadioGroup>(R.id.radioGroup2),
            view.findViewById<RadioGroup>(R.id.radioGroup3),
            view.findViewById<RadioGroup>(R.id.radioGroup4),
            view.findViewById<RadioGroup>(R.id.radioGroup5),
            view.findViewById<RadioGroup>(R.id.radioGroup6),
            view.findViewById<RadioGroup>(R.id.radioGroup7)
        )

        radioGroups.forEachIndexed { index, radioGroup ->
            val selectedOptionId = radioGroup.checkedRadioButtonId
            if (selectedOptionId != -1) {
                val selectedOption = view.findViewById<RadioButton>(selectedOptionId).text.toString()
                updateSelectedOptionUseCase.execute(QuestionOption(index + 1, selectedOption, correctOptions[index].correctOption))
            }
        }

        val selectedOptions = getSelectedOptionsUseCase.execute()
        val correctCount = selectedOptions.count { it.option == it.correctOption }
        val selectedOptionsText = selectedOptions.joinToString("\n") { "Pregunta ${it.questionId}: ${it.option} (Correcta: ${it.correctOption})" }
        selectedOptionsTextView.text = "$selectedOptionsText\n\nAciertos: $correctCount"
    }
}