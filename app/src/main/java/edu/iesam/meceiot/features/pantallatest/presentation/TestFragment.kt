package edu.iesam.meceiot.features.pantallatest.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import edu.iesam.meceiot.databinding.TestfragmentBinding
import edu.iesam.meceiot.features.pantallatest.domain.Question
import edu.iesam.meceiot.features.pantallatest.presentation.adapter.QuestionsAdapter

class TestFragment : Fragment() {

    private lateinit var binding: TestfragmentBinding
    private lateinit var questionsAdapter: QuestionsAdapter
    private lateinit var viewModel: TestViewModel

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

        viewModel = ViewModelProvider(this).get(TestViewModel::class.java)

        val questions = listOf(
            Question("1", "1º pregunta", "url1", "a", "b", "c", "d", "a"),
            Question("2", "2º pregunta", "url2", "a", "b", "c", "d", "b"),
            Question("3", "3º pregunta", "url3", "a", "b", "c", "d", "c"),
            Question("4", "4º pregunta", "url4", "a", "b", "c", "d", "d"),
            Question("5", "5º pregunta", "url5", "a", "b", "c", "d", "a"),
            Question("6", "6º pregunta", "url6", "a", "b", "c", "d", "b"),
            Question("7", "7º pregunta", "url7", "a", "b", "c", "d", "c")
        )

        questionsAdapter = QuestionsAdapter(questions)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = questionsAdapter

        viewModel.setQuestions(questions)

        binding.submitButton.setOnClickListener {
            handleSubmit()
        }

        viewModel.correctCount.observe(viewLifecycleOwner, { correctCount ->
            showResultDialog(correctCount)
        })
    }

    private fun handleSubmit() {
        // Assuming the adapter has a method to get selected options
        val selectedOptions = questionsAdapter.getSelectedOptions()
        if (selectedOptions.size == questionsAdapter.itemCount) {
            binding.errorMessageTextView.visibility = View.GONE
            viewModel.calculateCorrectAnswers(selectedOptions)
        } else {
            Toast.makeText(context, "Por favor, complete todas las opciones.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showResultDialog(correctCount: Int) {
        val selectedOptionsText = StringBuilder()
        val questions = viewModel.questions.value ?: return

        for (question in questions) {
            selectedOptionsText.append("Pregunta ${question.id}: (Correcta: ${question.correctOption})\n")
        }

        val bundle = Bundle().apply {
            putString("selectedOptionsText", selectedOptionsText.toString())
            putInt("correctCount", correctCount)
        }

        val resultDialogFragment = ResultDialogFragment().apply {
            arguments = bundle
        }

        resultDialogFragment.show(parentFragmentManager, "ResultDialogFragment")
    }
}