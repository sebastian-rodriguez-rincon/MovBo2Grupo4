package com.example.movb02grupo4.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.movb02grupo4.DataBase.DatabaseHelper
import com.example.movb02grupo4.MainActivity
import com.example.movb02grupo4.R

class LoginFragment : Fragment() {

    private lateinit var etEmailLogin: EditText
    private lateinit var etPasswordLogin: EditText
    private lateinit var continuar: TextView
    private lateinit var cancelar: TextView
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        activity?.window?.decorView?.systemUiVisibility =
            View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY


        etEmailLogin = view.findViewById(R.id.etEmailLogin)
        etPasswordLogin = view.findViewById(R.id.etPasswordLogin)
        continuar = view.findViewById(R.id.continuar)
        cancelar = view.findViewById(R.id.cancelar)

        databaseHelper = DatabaseHelper(requireContext())

        continuar.setOnClickListener {
            hideKeyboard()
            loginUser()
        }

        cancelar.setOnClickListener {
            (activity as MainActivity).loadFragment(InicioFragment())
        }

        view.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val focusedView = activity?.currentFocus
                if (focusedView is EditText) {
                    val outRect = android.graphics.Rect()
                    focusedView.getGlobalVisibleRect(outRect)
                    if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                        hideKeyboard()
                    }
                }
            }
            false
        }

        return view
    }

    private fun hideKeyboard() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val view = activity?.currentFocus
        if (view != null) {
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun loginUser() {
        val email = etEmailLogin.text.toString()
        val password = etPasswordLogin.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(
                requireContext(),
                "Por favor, ingrese el correo y la contraseña",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        if (!databaseHelper.checkUser(email)) {
            Toast.makeText(requireContext(), "El usuario no existe", Toast.LENGTH_SHORT).show()
            etEmailLogin.text.clear()
            etPasswordLogin.text.clear()
            return
        }

        if (databaseHelper.checkUserCredentials(email, password)) {
            Toast.makeText(requireContext(), "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
            guardarEmailEnSharedPreferences(requireContext(), email)
            (activity as MainActivity).loadFragment(MenuFragment())
        } else {
            Toast.makeText(requireContext(), "Correo o contraseña incorrectos", Toast.LENGTH_SHORT)
                .show()
        }
    }

    fun guardarEmailEnSharedPreferences(context: Context, email: String) {
        val sharedPref = context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("email", email)
            apply()
        }
    }
}