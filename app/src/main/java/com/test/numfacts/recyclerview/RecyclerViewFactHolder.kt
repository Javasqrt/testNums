package com.test.numfacts.recyclerview

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.test.numfacts.databinding.TemplateFactNumbersBinding
import com.test.numfacts.db.FactViewElementEntity

class RecyclerViewFactHolder(val binding: TemplateFactNumbersBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bindView(
        factViewElement: FactViewElementEntity
    ) {
        binding.apply {
            numberView.text = "Number: ${factViewElement.number}"
            factView.text = "${factViewElement.fact.take(17)}..."
        }
    }
}