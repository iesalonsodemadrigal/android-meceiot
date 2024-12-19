package edu.iesam.meceiot.features.pantallatest.presentation
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import edu.iesam.meceiot.R
import edu.iesam.meceiot.databinding.TestfragmentBinding
import edu.iesam.meceiot.features.pantallatest.domain.Question
import edu.iesam.meceiot.features.pantallatest.presentation.adapter.QuestionsAdapter
class TestFragment : Fragment() {
    private lateinit var binding: TestfragmentBinding
    private lateinit var questionsAdapter: QuestionsAdapter
    private val viewModel: TestViewModel by viewModels()
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
            Question(1, "1º pregunta", "logo_lorawan", "a", "b", "c", "d", "a"),
            Question(2, "2º pregunta", "logo_lorawan", "a", "b", "c", "d", "b"),
            Question(3, "3º pregunta", "logo_lorawan", "a", "b", "c", "d", "c"),
            Question(4, "4º pregunta", "logo_lorawan", "a", "b", "c", "d", "d"),
            Question(5, "5º pregunta", "logo_lorawan", "a", "b", "c", "d", "a"),
            Question(6, "6º pregunta", "logo_lorawan", "a", "b", "c", "d", "b"),
            Question(7, "7º pregunta", "logo_lorawan", "a", "b", "c", "d", "c")
        )
        questionsAdapter = QuestionsAdapter(questions)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = questionsAdapter
        viewModel.setQuestions(questions)
        binding.submitButton.setOnClickListener {
            handleSubmit()
        }
        viewModel.selectedOptions.observe(viewLifecycleOwner, Observer { selectedOptions ->
            questionsAdapter.notifyDataSetChanged()
        })
        viewModel.correctCount.observe(viewLifecycleOwner, Observer { correctCount ->
            showResultDialog(correctCount)
        })

        showSkeleton()
        binding.recyclerView.visibility = View.INVISIBLE
        binding.submitButton.visibility = View.INVISIBLE
        viewModel.questions.observe(viewLifecycleOwner, Observer { questions ->
            if (questions.isNotEmpty()) {
                // Agregar un retraso artificial de 2 segundos
                binding.recyclerView.postDelayed({
                    hideSkeleton()
                    binding.skeletonLayout.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.submitButton.visibility = View.VISIBLE
                }, 2000)
            }
        })
    }

    private fun handleSubmit() {
        val selectedOptions = questionsAdapter.getSelectedOptions()
        if (selectedOptions.size == questionsAdapter.itemCount) {
            binding.errorMessageTextView.visibility = View.GONE
            viewModel.setSelectedOptions(selectedOptions)
            viewModel.calculateCorrectAnswers()
            // Guardar cada pregunta en la base de datos
            viewModel.questions.value?.forEach { question ->
                viewModel.saveQuestionToDatabase(question)
            }
        } else {
            Toast.makeText(context, "Por favor, complete todas las opciones.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showSkeleton() {
        binding.skeletonLayout.showSkeleton()
    }

    private fun hideSkeleton() {
        binding.skeletonLayout.showOriginal()
    }

    private fun showResultDialog(correctCount: Int) {
        val selectedOptionsText = StringBuilder()
        val questions = viewModel.questions.value ?: return
        val selectedOptions = viewModel.selectedOptions.value ?: return
        for ((questionId, selectedOption) in selectedOptions) {
            val correctOption = questions.find { it.id == questionId }?.correctOption
            selectedOptionsText.append("Pregunta $questionId: $selectedOption (Correcta: $correctOption)\n")
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