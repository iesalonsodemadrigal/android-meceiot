package edu.iesam.meceiot.features.pantallatest.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import edu.iesam.meceiot.R
import edu.iesam.meceiot.databinding.TestfragmentBinding
import edu.iesam.meceiot.features.pantallatest.data.QuestionOption
import edu.iesam.meceiot.features.pantallatest.domain.GetSelectedOptionsUseCase
import edu.iesam.meceiot.features.pantallatest.domain.UpdateSelectedOptionUseCase

class TestFragment : Fragment() {

    private lateinit var binding: TestfragmentBinding
    private lateinit var questionsAdapter: QuestionsAdapter
    private lateinit var getSelectedOptionsUseCase: GetSelectedOptionsUseCase
    private lateinit var updateSelectedOptionUseCase: UpdateSelectedOptionUseCase

    private val correctOptions = listOf(
        QuestionOption(1, "a", "a"),
        QuestionOption(2, "b", "b"),
        QuestionOption(3, "c", "c"),
        QuestionOption(4, "d", "d"),
        QuestionOption(5, "a", "a"),
        QuestionOption(6, "b", "b"),
        QuestionOption(7, "c", "c")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TestfragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val questions = listOf(
            Question(1, "1º pregunta", R.drawable.logo_lorawan, listOf("a", "b", "c", "d"), "a"),
            Question(2, "2º pregunta", R.drawable.logo_lorawan, listOf("a", "b", "c", "d"), "b"),
            Question(3, "3º pregunta", R.drawable.logo_lorawan, listOf("a", "b", "c", "d"), "c"),
            Question(4, "4º pregunta", R.drawable.logo_lorawan, listOf("a", "b", "c", "d"), "d"),
            Question(5, "5º pregunta", R.drawable.logo_lorawan, listOf("a", "b", "c", "d"), "a"),
            Question(6, "6º pregunta", R.drawable.logo_lorawan, listOf("a", "b", "c", "d"), "b"),
            Question(7, "7º pregunta", R.drawable.logo_lorawan, listOf("a", "b", "c", "d"), "c")
        )

        questionsAdapter = QuestionsAdapter(questions)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = questionsAdapter

        binding.submitButton.setOnClickListener {
            handleSubmit()
        }
    }

    private fun handleSubmit() {
        val selectedOptions = questionsAdapter.getSelectedOptions()
        if (selectedOptions.size == questionsAdapter.itemCount) {
            binding.errorMessageTextView.visibility = View.GONE
            var correctCount = 0
            val selectedOptionsText = StringBuilder()
            for ((questionId, selectedOption) in selectedOptions) {
                val correctOption =
                    correctOptions.find { it.questionId == questionId }?.correctOption
                if (selectedOption == correctOption) {
                    correctCount++
                }
                selectedOptionsText.append("Pregunta $questionId: $selectedOption (Correcta: $correctOption)\n")
            }
            binding.selectedOptionsTextView.text = "$selectedOptionsText\n\nAciertos: $correctCount"
            binding.selectedOptionsTextView.visibility = View.VISIBLE
        } else {
            binding.errorMessageTextView.visibility = View.VISIBLE
        }
    }
}