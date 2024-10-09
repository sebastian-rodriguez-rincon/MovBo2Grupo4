package com.example.movb02grupo4.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.movb02grupo4.MainActivity
import com.example.movb02grupo4.R

class PostActionsFragment : Fragment() {

    private lateinit var btnLike: ImageButton
    private lateinit var btnSave: ImageButton
    private lateinit var back: ImageView

    private var isLiked = false
    private var isSaved = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_post_actions, container, false)

        btnLike = view.findViewById(R.id.btnLike)
        btnSave = view.findViewById(R.id.btnSave)
        back = view.findViewById(R.id.back)


        btnLike.setOnClickListener {
            toggleLike()
        }

        btnSave.setOnClickListener {
            toggleSave()
        }

        back.setOnClickListener {
            (activity as MainActivity).loadFragment(MenuFragment())
        }

        return view
    }

    private fun toggleLike() {
        isLiked = !isLiked

        if (isLiked) {
            btnLike.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.check))
            Toast.makeText(requireContext(), "Te gusta esta publicación", Toast.LENGTH_SHORT).show()
        } else {
            btnLike.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.no_check))
            Toast.makeText(requireContext(), "Ya no te gusta esta publicación", Toast.LENGTH_SHORT).show()
        }
    }

    private fun toggleSave() {
        isSaved = !isSaved

        if (isSaved) {
            btnSave.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.guardado))
            Toast.makeText(requireContext(), "Publicación guardada", Toast.LENGTH_SHORT).show()
        } else {
            btnSave.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.guardar))
            Toast.makeText(requireContext(), "Publicación eliminada de guardados", Toast.LENGTH_SHORT).show()
        }
    }
}