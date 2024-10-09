package com.example.movb02grupo4.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.movb02grupo4.MainActivity
import com.example.movb02grupo4.R

class UrlWebViewFragment : Fragment() {

    private lateinit var webView: WebView
    private lateinit var etUrl: EditText
    private lateinit var btnLoadUrl: Button
    private lateinit var back: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_url_web_view, container, false)

        webView = view.findViewById(R.id.webView)
        etUrl = view.findViewById(R.id.etUrl)
        btnLoadUrl = view.findViewById(R.id.btnLoadUrl)
        back = view.findViewById(R.id.back)

        webView.settings.javaScriptEnabled = true

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return false
            }

        }

        btnLoadUrl.setOnClickListener {
            val url = etUrl.text.toString().trim()
            if (url.isNotEmpty() && (url.startsWith("http://") || url.startsWith("https://"))) {
                webView.loadUrl(url)
            } else {
                etUrl.error = "Por favor, ingrese una URL válida"
            }
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

        back.setOnClickListener {
            (activity as MainActivity).loadFragment(MenuFragment())
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
}