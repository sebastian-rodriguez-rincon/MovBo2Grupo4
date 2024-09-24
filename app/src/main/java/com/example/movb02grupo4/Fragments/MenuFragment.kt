package com.example.movb02grupo4.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.movb02grupo4.MainActivity
import com.example.movb02grupo4.R

class MenuFragment : Fragment() {

    private lateinit var logout: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        logout = view.findViewById(R.id.logout)

        logout.setOnClickListener {
            (activity as MainActivity).loadFragment(InicioFragment())
        }

        return view
    }
}