package com.example.noteapp1.NoteApp.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp1.NoteApp.data.model.NoteModel
import com.example.noteapp1.NoteApp.interfaces.OnClickItem
import com.example.noteapp1.databinding.ItemNoteBinding

class NoteAdapter(private val onLongClick: OnClickItem, private val onClick: OnClickItem): ListAdapter <NoteModel, NoteAdapter.ViewHolder>(DiffCallback()) {
    class ViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item:NoteModel?) = with(binding) {
            itemTitle.text = item?.title
            itemDescription.text = item?.description
            tvDateTime.text = item?.tvDateTime
            item?.color?.let { rvNote1.setBackgroundColor(it) }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
        }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(getItem(position))

        holder.itemView.setOnLongClickListener{
            onLongClick.onLongClick(getItem(position))
            true
        }
        holder.itemView.setOnClickListener {
            onClick.onClick(getItem(position))
        }
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