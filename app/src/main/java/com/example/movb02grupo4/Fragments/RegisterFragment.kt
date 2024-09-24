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

class RegisterFragment : Fragment() {

    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var etName: EditText
    private lateinit var etLastname: EditText
    private lateinit var etPhone: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etMascota: EditText
    private lateinit var continuar: TextView
    private lateinit var cancelar: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        activity?.window?.decorView?.systemUiVisibility =
            View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY


        databaseHelper = DatabaseHelper(requireContext())
        etName = view.findViewById(R.id.etName)
        etLastname = view.findViewById(R.id.etLastname)
        etPhone = view.findViewById(R.id.etPhone)
        etEmail = view.findViewById(R.id.etEmail)
        etPassword = view.findViewById(R.id.etPassword)
        etMascota = view.findViewById(R.id.etMascota)
        continuar = view.findViewById<Button>(R.id.continuar)
        cancelar = view.findViewById<Button>(R.id.cancelar)

        continuar.setOnClickListener {
            hideKeyboard()
            registerUser()
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

    private fun registerUser() {
        val name = etName.text.toString()
        val lastname = etLastname.text.toString()
        val phone = etPhone.text.toString()
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()

        if (name.isEmpty() || lastname.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(
                requireContext(),
                "Todos los campos son obligatorios",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        if (databaseHelper.checkUser(email)) {
            Toast.makeText(requireContext(), "Este correo ya está registrado", Toast.LENGTH_SHORT)
                .show()
        } else {
            val result = databaseHelper.addUser(name, lastname, phone, email, password)
            if (result > 0) {
                Toast.makeText(requireContext(), "Registro exitoso", Toast.LENGTH_SHORT).show()
                (activity as MainActivity).loadFragment(LoginFragment())
            } else {
                Toast.makeText(requireContext(), "Error al registrar", Toast.LENGTH_SHORT).show()
            }
        }
    }
}