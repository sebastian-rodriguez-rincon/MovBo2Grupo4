package com.example.movb02grupo4.Fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.movb02grupo4.MainActivity
import com.example.movb02grupo4.R
import com.google.android.material.navigation.NavigationView

class MenuFragment() : Fragment(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var logout: ImageView
    private lateinit var menuIcon: ImageView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        activity?.window?.decorView?.systemUiVisibility =
            View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

        drawerLayout = view.findViewById(R.id.drawer_layout)
        navigationView = view.findViewById(R.id.navigation_view)
        logout = view.findViewById(R.id.logout)
        menuIcon = view.findViewById(R.id.menu_icon)

        navigationView.setNavigationItemSelectedListener(this)

        logout.setOnClickListener {
            (activity as MainActivity).loadFragment(InicioFragment())
        }

        menuIcon.setOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }

        return view
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
             R.id.nav_perfil-> {
                (activity as MainActivity).loadFragment(PerfilFragment())
            }
            R.id.nav_mascotas -> {
                (activity as MainActivity).loadFragment(GalleryFragment())
            }
            R.id.nav_videos -> {
                (activity as MainActivity).loadFragment(VideoFragment())
            }
            R.id.nav_web -> {
                (activity as MainActivity).loadFragment(UrlWebViewFragment())
            }
            R.id.nav_opciones -> {
                (activity as MainActivity).loadFragment(PostActionsFragment())
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onResume() {
        super.onResume()
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
    }
}