package com.example.movb02grupo4.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import com.example.movb02grupo4.MainActivity
import com.example.movb02grupo4.R


class InicioFragment : Fragment() {

    private lateinit var login: TextView
    private lateinit var register: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_inicio, container, false)
        activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        activity?.window?.decorView?.systemUiVisibility =
            View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

        login = view.findViewById(R.id.login)
        register = view.findViewById(R.id.register)

        login.setOnClickListener {
            (activity as MainActivity).loadFragment(LoginFragment())
        }

        register.setOnClickListener {
            (activity as MainActivity).loadFragment(RegisterFragment())
        }

        return view
    }

}