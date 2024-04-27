package com.example.noteapp1.NoteApp.ui.fragments.note

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp1.NoteApp.App
import com.example.noteapp1.NoteApp.data.model.NoteModel
import com.example.noteapp1.NoteApp.interfaces.OnClickItem
import com.example.noteapp1.NoteApp.ui.adapter.NoteAdapter
import com.example.noteapp1.R
import com.example.noteapp1.databinding.FragmentNoteBinding


class NoteFragment : Fragment(), OnClickItem {

    private lateinit var binding: FragmentNoteBinding
    private var noteAdapter = NoteAdapter(this, this)
    //private val list: ArrayList<NoteModel> = ArrayList()
    private var isConstraintLayout = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListener()
        getData()
    }
    private fun initialize() {
        binding.rvNote.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteAdapter
        }
    }
    private fun setupListener() = with(binding) {
        btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_noteFragment_to_noteDetailFragment)
        }
        ivMenu2.setOnClickListener {
            toggleMenuVisibility(ivMenu2, ivMenu3)
            isConstraintLayout = true
            updateLayoutManager()
        }

        ivMenu3.setOnClickListener {
            toggleMenuVisibility(ivMenu3, ivMenu2)
            isConstraintLayout = false
            updateLayoutManager()
        }
    }
    private fun toggleMenuVisibility(viewToHide: View, viewToShow: View) {
        viewToHide.visibility = View.GONE
        viewToShow.visibility = View.VISIBLE
    }
    private fun updateLayoutManager() {
        val layoutManager = if(isConstraintLayout) {
            GridLayoutManager(requireContext(), 2)
        } else {
            LinearLayoutManager(requireContext())
        }
        binding.rvNote.layoutManager = layoutManager
    }
    private fun getData() {
        App().getInstance()?.noteDao()?.getAll()?.observe(viewLifecycleOwner) {
            noteAdapter.submitList(it)
         }
        }
    override fun onLongClick(noteModel: NoteModel) {
        val builder = AlertDialog.Builder(requireContext())
        with(builder) {
            setTitle("Вы точно хотите удалить?")
            setPositiveButton("Да"){dialog, which->
               App.appDataBase?.noteDao()?.deleteNote(noteModel)
            }
            setNegativeButton("Нет"){dialog,which->
                dialog.cancel()
            }
            show()
        }
        builder.create()
    }
    override fun onClick(noteModel: NoteModel) {
        val action = NoteFragmentDirections.actionNoteFragmentToNoteDetailFragment(noteModel.id!!)
        findNavController().navigate(action)
    }
}
