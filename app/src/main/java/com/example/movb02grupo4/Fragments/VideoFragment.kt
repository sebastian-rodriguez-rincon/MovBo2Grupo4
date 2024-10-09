package com.example.movb02grupo4.Fragments

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.MediaController
import android.widget.VideoView
import com.example.movb02grupo4.MainActivity
import com.example.movb02grupo4.R

class VideoFragment : Fragment() {

    private lateinit var videoView: VideoView
    private lateinit var back: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_video, container, false)

        videoView = view.findViewById(R.id.video_view)
        back = view.findViewById(R.id.back)

        val mediaController = MediaController(requireContext())
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)

        val videoUri = Uri.parse("https://www.w3schools.com/html/mov_bbb.mp4")

        videoView.setVideoURI(videoUri)

        videoView.setOnPreparedListener { mp: MediaPlayer ->
            mp.isLooping = true
            videoView.start()
        }

        back.setOnClickListener{
            (activity as MainActivity).loadFragment(MenuFragment())
        }

        return view
    }

    override fun onPause() {
        super.onPause()
        videoView.pause()
    }

    override fun onResume() {
        super.onResume()
        videoView.start()
    }
}