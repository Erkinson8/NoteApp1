package com.example.noteapp1.NoteApp.ui.fragments.chat

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp1.NoteApp.Message
import com.example.noteapp1.NoteApp.ui.adapter.ChatAdapter
import com.example.noteapp1.databinding.FragmentChatBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding
    private val chatAdapter = ChatAdapter()
    private val db = Firebase.firestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListener()
        observeMessages()
    }
    private fun initialize() {
        binding.rvChat.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = chatAdapter
        }
    }

    private fun setupListener() {
        binding.btnSend.setOnClickListener {
            val messageText = binding.etMassage.text.toString().trim()
            if (messageText.isNotEmpty()) {
                val message = hashMapOf(
                    "text" to messageText
                )
                db.collection("messages").add(message)
                    .addOnSuccessListener { documentReference ->
                        Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error adding document", e)

                        binding.etMassage.text.clear()
                    }
            }
        }
    }
    private fun observeMessages() {
        db.collection("messages")
            //.orderBy("", "desc"))
            .addSnapshotListener { value, error ->
                if (error != null) {
                    Log.w(TAG, "Listen failed", error)
                    return@addSnapshotListener
                }
                val messages = mutableListOf<Message>()
                for (document in value!!) {
                    val message = document.toObject(Message::class.java)
                    messages.add(message)
                    Log.d(TAG, "Message added: $message")
                }
                chatAdapter.submitList(messages)
                binding.etMassage.text.clear()
            }
    }
    companion object {
        private const val TAG = "ChatFragment"
    }
}/*private fun setupListener() {
        binding.btnSend.setOnClickListener {
            val user = hashMapOf(
                "name" to binding.etMassage.text.toString()
            )
            db.collection("user").add(user).addOnCompleteListener {}
            // Получаем текст из EditText
            val messageText = binding.etMassage.text.toString().trim()

            // Проверяем, что текст не пустой
            if (messageText.isNotEmpty()) {
                // Создаем объект сообщения
                val message = Message(messageText)

                // Добавляем сообщение в список
                val currentList = chatAdapter.currentList.toMutableList()
                currentList.add(message)

                // Передаем обновленный список в адаптер
                chatAdapter.submitList(currentList)

                // Прокручиваем RecyclerView к новому сообщению
                binding.rvChat.smoothScrollToPosition(currentList.size - 1)

                // Очищаем EditText
                binding.etMassage.text.clear()

            }
        }
    }*/