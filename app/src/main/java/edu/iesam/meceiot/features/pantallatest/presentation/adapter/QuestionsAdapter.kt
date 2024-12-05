package edu.iesam.meceiot.features.pantallatest.presentation.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import edu.iesam.meceiot.databinding.ItemQuestionBinding
import edu.iesam.meceiot.features.pantallatest.domain.Question


class QuestionsAdapter(private val questions: List<Question>) :
    RecyclerView.Adapter<QuestionsAdapter.QuestionViewHolder>() {

    private val selectedOptions = mutableMapOf<Int, String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val binding =
            ItemQuestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuestionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val question = questions[position]
        holder.bind(question)
    }

    override fun getItemCount(): Int = questions.size

    fun getSelectedOptions(): Map<Int, String> = selectedOptions

    inner class QuestionViewHolder(val binding: ItemQuestionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(question: Question) {
            binding.questionText.text = question.text
            binding.questionImage.setImageResource(question.imageResId)
            binding.optionsGroup.removeAllViews()
            val options = listOf(question.option1, question.option2, question.option3, question.option4)
            options.forEach { option ->
                val radioButton = RadioButton(binding.root.context).apply {
                    text = option
                    isChecked = selectedOptions[question.id] == option
                    setOnClickListener {
                        selectedOptions[question.id] = option
                        notifyDataSetChanged()
                    }
                }
                binding.optionsGroup.addView(radioButton)
            }
        }
    }
}