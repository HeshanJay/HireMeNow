package com.example.hiremenow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Define MyAdapter class that extends RecyclerView.Adapter and takes an ArrayList of Users as input
class MyAdapter(val usersList: ArrayList<Users>):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    // Define a listener interface to handle item clicks
    private lateinit var mListener: OnItemClickListener
    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
    // Define a public method to set the click listener
    fun setOnItemClickListener(clickListener: OnItemClickListener){
        mListener=clickListener
    }
    // Define a view holder class that holds references to the views of a single item
    class MyViewHolder(itemView:View,clickListener: OnItemClickListener):RecyclerView.ViewHolder(itemView){
        // Get references to the TextViews in the item view
        val tvName:TextView = itemView.findViewById(R.id.tv_name)
        val tvSubject:TextView= itemView.findViewById(R.id.tv_subject)
        // Set an onClickListener for the item view that triggers the onItemClick method of the listener
        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }

    }
    // Inflate the layout for the item view and return a new instance of MyViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return MyViewHolder(itemView,mListener)
    }
    // Bind the data from the Users ArrayList to the TextViews in the item view
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvName.text = usersList[position].customerName
        holder.tvSubject.text= usersList[position].customerSubject
    }
    // Return the size of the Users ArrayList
    override fun getItemCount(): Int {
        return usersList.size
    }

}