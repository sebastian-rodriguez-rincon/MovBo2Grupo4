package com.example.movb02grupo4.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.movb02grupo4.DataBase.DatabaseHelper
import com.example.movb02grupo4.MainActivity
import com.example.movb02grupo4.R

class PerfilFragment : Fragment() {
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var name: TextView
    private lateinit var lastName: TextView
    private lateinit var email: TextView
    private lateinit var phone: TextView
    private lateinit var back: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_perfil, container, false)

        activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        activity?.window?.decorView?.systemUiVisibility =
            View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

        databaseHelper = DatabaseHelper(requireContext())
        name = view.findViewById(R.id.name)
        lastName = view.findViewById(R.id.lastName)
        email = view.findViewById(R.id.email)
        phone = view.findViewById(R.id.phone)
        back = view.findViewById(R.id.back)

        obtenerDatosUsuario()

        back.setOnClickListener {
            (activity as MainActivity).loadFragment(MenuFragment())
        }

        return view
    }

    private fun obtenerDatosUsuario() {
        val emailGuardado = obtenerEmailDeSharedPreferences(requireContext())

        if (emailGuardado != null) {
            val usuario = databaseHelper.obtenerDatosUsuarioPorEmail(emailGuardado)

            if (usuario != null) {
                name.text = "Nombre: " + usuario["name"]
                lastName.text = "Apellidos: " + usuario["lastname"]
                email.text = "Correo: " + usuario["email"]
                phone.text = "Telefono: " + usuario["phone"]
            } else {
                Toast.makeText(
                    requireContext(),
                    "No se encontró el usuario con el email $emailGuardado",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(
                requireContext(),
                "No hay ningún email guardado en SharedPreferences",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun obtenerEmailDeSharedPreferences(context: Context): String? {
        val sharedPref = context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)
        return sharedPref.getString("email", null)
    }
}