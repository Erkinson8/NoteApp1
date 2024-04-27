package com.example.noteapp1.NoteApp.ui.fragments.detail

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.noteapp1.NoteApp.App
import com.example.noteapp1.NoteApp.data.model.NoteModel
import com.example.noteapp1.databinding.FragmentDetailBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class NoteDetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private var noteDetailColor: Int? = Color.WHITE
    private var noteId: Int = -1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        update()
        setupListener()
        chooseColor()
    }

    private fun update() {
        arguments?.let { args ->
            noteId = args.getInt("noteId", -1)
        }
        if (noteId != -1) {
            val argsNote = App().getInstance()?.noteDao()?.getNoteById(noteId)
            argsNote?.let { model ->
                binding.etTitle.setText(model.title)
                binding.etDescriptions.setText(model.description)
            }

            val dateFormat = SimpleDateFormat("dd MMMM HH:mm", Locale.getDefault())

            val currentDateAndTime = dateFormat.format(Date())

            binding.tvDateTime.text = currentDateAndTime

        }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun setupListener() {
        binding.btnAddText.setOnClickListener {
            val etTitle = binding.etTitle.text.toString()
            val etDescription = binding.etDescriptions.text.toString()
            val dateFormat = SimpleDateFormat("dd MMMM HH:mm", Locale.getDefault())
            val currentDateAndTime = dateFormat.format(Date())
            val color = noteDetailColor
            if (noteId != -1) {
                val updateNote = NoteModel(etTitle, etDescription, currentDateAndTime, color)
                updateNote.id = noteId
                App().getInstance()?.noteDao()?.updateNote(updateNote)
            } else
                App().getInstance()?.noteDao()
                    ?.insertNote(NoteModel(etTitle, etDescription, currentDateAndTime, color))
                findNavController().navigateUp()
            }
        }
        private fun chooseColor() {
            with(binding) {
                radioButton1.setOnClickListener {
                    if (!radioButton1.isChecked)
                        radioButton1.isChecked = true

                    radioButton2.isChecked = !radioButton1.isChecked
                    radioButton3.isChecked = !radioButton1.isChecked
                    noteDetailColor = Color.GRAY
                }
                radioButton2.setOnClickListener {
                    if (!radioButton2.isChecked)
                        radioButton2.isChecked = true

                    radioButton1.isChecked = !radioButton2.isChecked
                    radioButton3.isChecked = !radioButton2.isChecked
                    noteDetailColor = Color.WHITE
                }
                radioButton3.setOnClickListener {
                    if (!radioButton2.isChecked)
                        radioButton2.isChecked = true

                    radioButton1.isChecked = !radioButton3.isChecked
                    radioButton2.isChecked = !radioButton3.isChecked
                    noteDetailColor = Color.RED
                }
            }
        }
    }










/*val note =
                NoteModel(etTitle, etDescription, currentDateAndTime, selectedColor)

            App.getInstance().noteDao().insertNote(note)
            findNavController().navigateUp()*/


/*App().getInstance()?.noteDao()?.insertNote(NoteModel(etTitle, etDescription, currentDateAndTime))
           findNavController().navigateUp()*/
