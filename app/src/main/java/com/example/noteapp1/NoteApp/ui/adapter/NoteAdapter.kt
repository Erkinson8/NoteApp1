package com.example.noteapp1.NoteApp.ui.adapter


import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp1.NoteApp.data.model.NoteModel
import com.example.noteapp1.databinding.ItemNoteBinding

class NoteAdapter: ListAdapter <NoteModel, NoteAdapter.ViewHolder>(DiffCallback()) {
    class ViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item:NoteModel?) = with(binding) {
            itemTitle.text = item?.title
            itemDescription.text = item?.description
            tvDateTime.text = item?.tvDateTime
            item?.color?.let { rvNote1.setBackgroundColor(it) }


            itemTitle.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    // Обновляем высоту rvNote1 при изменении текста itemTitle
                    updateNoteHeight()
                }

                override fun afterTextChanged(s: Editable?) {}
            })

            itemDescription.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    // Обновляем высоту rvNote1 при изменении текста itemDescription
                    updateNoteHeight()
                }

                override fun afterTextChanged(s: Editable?) {}
            })
        }

        // Метод для обновления высоты rvNote1
        private fun updateNoteHeight() {
            binding.rvNote1.layoutParams.height = calculateNoteHeight()
            binding.rvNote1.requestLayout()
        }

        // Метод для вычисления высоты rvNote1 на основе высоты itemTitle и itemDescription
        private fun calculateNoteHeight(): Int {
            val titleHeight = binding.itemTitle.height
            val descriptionHeight = binding.itemDescription.height
            return titleHeight + descriptionHeight
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
        }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(getItem(position))
        }
    }
    class DiffCallback : DiffUtil.ItemCallback<NoteModel>() {
            override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
                return oldItem == newItem
    }
            override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
                return oldItem.title == newItem.title
    }
}