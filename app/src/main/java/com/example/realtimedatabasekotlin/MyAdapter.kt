package com.example.firebaserecyclerviewkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.realtimedatabasekotlin.R
import com.example.realtimedatabasekotlin.User
import java.util.*

class MyAdapter(private val userList: ArrayList<User>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.user_item,
            parent, false
        )
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = userList[position]

        holder.name.text = currentitem.name
        holder.number.text = currentitem.number
        holder.address.text = currentitem.address
        holder.age.text = currentitem.age
        holder.date.text = currentitem.date

    }

    override fun getItemCount(): Int {

        return userList.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name: TextView = itemView.findViewById(R.id.tvname)
        val number: TextView = itemView.findViewById(R.id.tvnumber)
        val address: TextView = itemView.findViewById(R.id.tvaddress)
        val age: TextView = itemView.findViewById(R.id.tvage)
        val date: TextView = itemView.findViewById(R.id.tvdate)

    }

}