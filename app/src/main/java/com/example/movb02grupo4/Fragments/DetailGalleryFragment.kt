package com.example.movb02grupo4.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.movb02grupo4.ListGallery
import com.example.movb02grupo4.MainActivity
import com.example.movb02grupo4.R
import com.squareup.picasso.Picasso


class DetailGalleryFragment(private var nombre: String, private var edad: String, private var url: String) : Fragment() {

    private lateinit var name: TextView
    private lateinit var age: TextView
    private lateinit var image: ImageView
    private lateinit var back: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail_gallery, container, false)
        activity?.window?.decorView?.systemUiVisibility =
            View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

        name = view.findViewById(R.id.name)
        age = view.findViewById(R.id.age)
        image = view.findViewById(R.id.image)
        back = view.findViewById(R.id.back)

        Picasso.get().load(url).into(image)
        name.text = "Tipo mascota: " + nombre
        age.text = "Edad: " + edad

        back.setOnClickListener{
            (activity as MainActivity).loadFragment(GalleryFragment())
        }

        return view
    }
}