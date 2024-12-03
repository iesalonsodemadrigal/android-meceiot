package edu.iesam.meceiot.features.pantallatest.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import edu.iesam.meceiot.databinding.ItemQuestionBinding
import edu.iesam.meceiot.features.pantallatest.domain.Question

class QuestionsAdapter(
    private val questions: List<Question>
) : RecyclerView.Adapter<QuestionsAdapter.QuestionViewHolder>() {

    private val selectedOptions = mutableMapOf<String, String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val binding =
            ItemQuestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuestionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(questions[position])
    }

    override fun getItemCount(): Int = questions.size

    inner class QuestionViewHolder(private val binding: ItemQuestionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(question: Question) {
            binding.questionText.text = question.text
            binding.optionsGroup.removeAllViews()
            val options = listOf(question.option1, question.option2, question.option3, question.option4)
            options.forEach { option ->
                val radioButton = RadioButton(binding.root.context).apply {
                    text = option
                    setOnClickListener { selectedOptions[question.id] = option }
                }
                binding.optionsGroup.addView(radioButton)
            }
        }
    }

    fun getSelectedOptions(): Map<String, String> = selectedOptions
}