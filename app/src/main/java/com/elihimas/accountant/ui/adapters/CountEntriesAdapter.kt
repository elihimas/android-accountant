package com.elihimas.accountant.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elihimas.accountant.databinding.RowCoutingItemBinding
import com.elihimas.accountant.model.CountEntry
import java.util.function.Consumer

class CountEntriesAdapter(private val entrySelectionListener: Consumer<CountEntry>) :
    RecyclerView.Adapter<CountEntriesAdapter.CountEntryHolder>() {

    class CountEntryHolder(val binding: RowCoutingItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entry: CountEntry) {
            binding.tvQuantity.text = entry.quantity.toString()
            binding.btRemove.tag = entry
        }
    }

    private var entries: List<CountEntry>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountEntryHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowCoutingItemBinding.inflate(inflater, parent, false)
        binding.btRemove.setOnClickListener { button ->
            val selectedEntry = button.tag as CountEntry
            entrySelectionListener.accept(selectedEntry)
        }

        return CountEntryHolder(binding)
    }

    override fun onBindViewHolder(countEntryHolder: CountEntryHolder, position: Int) {
        entries?.let { entries ->
            val entry = entries[position]
            countEntryHolder.bind(entry)
        }
    }

    override fun getItemCount() = entries?.size ?: 0

    fun updateEntries(entries: List<CountEntry>) {
        this.entries = entries
        notifyDataSetChanged()
    }

}
