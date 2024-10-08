package com.example.movb02grupo4.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.movb02grupo4.Fragments.DetailGalleryFragment
import com.example.movb02grupo4.Fragments.GalleryFragment
import com.example.movb02grupo4.ListGallery
import com.example.movb02grupo4.MainActivity
import com.example.movb02grupo4.R
import com.squareup.picasso.Picasso

class GalleryAdapter(
    private val context: Context,
    private val listGallery: List<ListGallery>
) : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_gallery_image, parent, false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val listGallery = listGallery[position]
        Picasso.get().load(listGallery.getUrl()).into(holder.imageView)

        holder.itemView.setOnClickListener{
            (context as MainActivity).loadFragment(DetailGalleryFragment(listGallery.getNombre(), listGallery.getEdad(), listGallery.getUrl()))
        }
    }

    override fun getItemCount(): Int {
        return listGallery.size
    }

    inner class GalleryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageViewGalleryItem)
    }
}
