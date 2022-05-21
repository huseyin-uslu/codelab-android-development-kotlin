/*
* Copyright (C) 2021 The Android Open Source Project.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.example.dogglers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogglers.R
import com.example.dogglers.const.Layout
import com.example.dogglers.data.DataSource

/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class DogCardAdapter(
    private val context: Context?,
    private val layout: Int
): RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {

    private val dogs = DataSource.dogs

    class DogCardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
       val imageView : ImageView = itemView.findViewById(R.id.imageViewForDogs)
       val textViewForName : TextView = itemView.findViewById(R.id.textViewForNameOfDog)
       val textViewForAge : TextView = itemView.findViewById(R.id.textViewForAgeOfDog)
       val textViewForHobby : TextView = itemView.findViewById(R.id.textViewForHobbyOfDog)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {

        val itemView : View = if(this.layout == Layout.GRID){

           LayoutInflater
                .from(parent.context)
                .inflate(R.layout.grid_list_item,
                    parent,
                    false)

        }else{

            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.vertical_horizontal_list_item,
                parent,
                false)
        }

        return DogCardViewHolder(itemView)
    }

    override fun getItemCount(): Int = dogs.count() // TODO: return the size of the data set instead of 0

    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {

        val resources = context?.resources
        val data = dogs[position]

        holder.imageView.setImageResource(data.imageResourceId)
        holder.textViewForName.text = data.name
        holder.textViewForAge.text = resources?.getString(R.string.dog_age,data.age)
        holder.textViewForHobby.text = resources?.getString(R.string.dog_hobbies,data.hobbies)

        // TODO: Set the text for the current dog's hobbies by passing the hobbies to the
        //  R.string.dog_hobbies string constant.
        //  Passing an argument to the string resource looks like:
        //  resources?.getString(R.string.dog_hobbies, dog.hobbies)
    }
}
