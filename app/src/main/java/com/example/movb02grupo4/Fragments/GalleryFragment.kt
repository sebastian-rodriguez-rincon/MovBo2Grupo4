package com.example.movb02grupo4.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movb02grupo4.Adapters.GalleryAdapter
import com.example.movb02grupo4.ListGallery
import com.example.movb02grupo4.R

class GalleryFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var galleryAdapter: GalleryAdapter
    private val almacenados: MutableList<ListGallery> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewGallery)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        galleryAdapter = GalleryAdapter(requireContext(), almacenados)
        recyclerView.adapter = galleryAdapter

        loadImages()

        return view
    }

    private fun loadImages() {
        almacenados.add(ListGallery("Perro", "7 años", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTxwDaJrr_VTkyfTM3sXiCyoxZZLzDeNBqHJg&s"))
        almacenados.add(ListGallery("Gato", "2 años","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSOQpMd1VvFulMmL5eZzbby_LtusqbLCivDZA&s"))
        almacenados.add(ListGallery("Cabra", "4 años","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSiWECrVVQSBFopSzgL4lYKBGIjn4way1Fsgw&s"))
        almacenados.add(ListGallery("Conejo", "1 años","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTRJH2ckCuhPdIlRu3pT1eZ-tBJSAg6kxBnFg&s"))
        almacenados.add(ListGallery("Hamster", "2 años","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRS1-3nz_YNvbGOViHC_NRNcUeLmCtGgyPcmg&s"))
        almacenados.add(ListGallery("Caballo", "9 años","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR-44ZBjh93LvKCNbWwy2rPKKE7Lc5fhtlxQQ&s"))
        almacenados.add(ListGallery("Vaca", "3 años","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTY5OMfcy3gYf_wipBL333NswsfU_OYTJjbQw&s"))
        almacenados.add(ListGallery("Cerdo", "1 años","https://st3.depositphotos.com/6055006/32615/i/450/depositphotos_326158040-stock-photo-pig-on-white.jpg"))
        almacenados.add(ListGallery("Oveja", "2 años","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQSxaHGCmcRF1mY8JulIyqYpCRFOeJS8gAuxg&s"))
        almacenados.add(ListGallery("Pato", "5 meses","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSaTNVB5PEOysPPcZh3TLI_52v3L4loh6UuYQ&s"))
        almacenados.add(ListGallery("Paloma", "9 meses","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQUCMneSIE2Rw5P9uuCCBC30hSai0ecSG68w&s"))
        almacenados.add(ListGallery("Pavo Real", "2 años","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ4n-HUISbAr2FO-9_47Olnwip-GEIoTCE6xg&s"))
        galleryAdapter.notifyDataSetChanged()
    }
}
