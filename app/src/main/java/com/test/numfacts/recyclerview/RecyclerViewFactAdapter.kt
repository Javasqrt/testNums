package com.test.numfacts.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.numfacts.databinding.TemplateFactNumbersBinding
import com.test.numfacts.db.FactViewElementEntity

class RecyclerViewFactAdapter(
    private val listFacts: List<FactViewElementEntity>,
    private val onClickFact: OnNumberFactClickListener
) : RecyclerView.Adapter<RecyclerViewFactHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewFactHolder =
        RecyclerViewFactHolder(
            TemplateFactNumbersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun getItemCount(): Int = listFacts.size

    override fun onBindViewHolder(holder: RecyclerViewFactHolder, position: Int) {
        val fact = listFacts[position]
        holder.bindView(fact)
        holder.itemView.setOnClickListener {
            onClickFact.onClick(
                position,
                fact
            )
        }
    }

}