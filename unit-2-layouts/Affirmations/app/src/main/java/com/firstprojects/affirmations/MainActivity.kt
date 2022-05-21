package com.firstprojects.affirmations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.firstprojects.affirmations.adapter.ItemAdapter
import com.firstprojects.affirmations.data.DataSource

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data  = DataSource()
        val adapter = ItemAdapter(data.loadAffirmations(),this@MainActivity)
        val recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
    }
}