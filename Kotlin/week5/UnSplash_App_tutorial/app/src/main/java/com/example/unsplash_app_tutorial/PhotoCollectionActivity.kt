package com.example.unsplash_app_tutorial

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.unsplash_app_tutorial.model.Photo
import com.example.unsplash_app_tutorial.recyclerview.PhotoGridRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_photo_collection.*

class PhotoCollectionActivity: AppCompatActivity() {

    //data
    var photoList=ArrayList<Photo>()

    //Adapter
    private lateinit var photoGridRecyclerViewAdapter: PhotoGridRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_collection)

        val bundle=intent.getBundleExtra("array_bundle")
        val searchTerm=intent.getStringExtra("search_term")
        photoList=bundle?.getSerializable("photo_array_list") as ArrayList<Photo>

        top_app_bar.title=searchTerm

        this.photoGridRecyclerViewAdapter= PhotoGridRecyclerViewAdapter()

        this.photoGridRecyclerViewAdapter.submitList(photoList)

        my_photo_recycler_view.layoutManager=GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false)
        my_photo_recycler_view.adapter=this.photoGridRecyclerViewAdapter

    }
}