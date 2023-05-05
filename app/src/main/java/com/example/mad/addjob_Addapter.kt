package com.example.mad

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityManager.AccessibilityServicesStateChangeListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class addjob_Addapter ( val userList : ArrayList<retrive_job>) : RecyclerView.Adapter<addjob_Addapter.MyViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener : onItemClickListener){
        mListener = clickListener
    }

    class MyViewHolder(itemView : View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
        val ComName : TextView = itemView.findViewById(R.id.comnama)
        val POssition : TextView = itemView.findViewById(R.id.posnama)
        val Main : TextView = itemView.findViewById(R.id.mreq)

        init{
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int):MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.addjob_list,parent,false)
        return MyViewHolder(itemView, mListener)

    }

    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {

        holder.ComName.text = userList[position].comName
        holder.POssition.text = userList[position].possition
        holder.Main.text = userList[position].main


    }

    override fun getItemCount(): Int {

        return userList.size
    }

}
