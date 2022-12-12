package com.example.movieteather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.lib.AdvanceTicket
import com.google.android.material.imageview.ShapeableImageView

class MyAdapter(private val listOfAdvanceTicket : MutableList<AdvanceTicket>, val recyclerViewInterface: RecyclerViewInterface) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.my_item, parent, false)
        return MyViewHolder(itemView, recyclerViewInterface)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val itemsViewModel = listOfAdvanceTicket[position]
        holder.imageView.setImageResource(R.drawable.videotape)
        holder.id.text = itemsViewModel.id.toString()
        holder.price.text = itemsViewModel.price.toString()
        holder.daysAhead.text = itemsViewModel.daysAhead.toString()

    }

    override fun getItemCount(): Int {
        return listOfAdvanceTicket.size
    }

    class MyViewHolder(ItemView: View, recyclerViewInterface : RecyclerViewInterface) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ShapeableImageView = itemView.findViewById(R.id.imageAdvanceTicket)
        val id: TextView = itemView.findViewById(R.id.textViewIdAdvanceTicket)
        val price: TextView = itemView.findViewById(R.id.textViewPriceAdvanceTicket)
        val daysAhead: TextView = itemView.findViewById(R.id.textViewDaysAheadAdvanceTicket)

        init {
            itemView.setOnLongClickListener() {
                if (recyclerViewInterface != null) {
                    val myPos = adapterPosition

                    if (myPos != RecyclerView.NO_POSITION) {
                        recyclerViewInterface.onItemLongCLick(myPos)
                    }
                }
                return@setOnLongClickListener true
            }

            itemView.setOnClickListener {
                if (recyclerViewInterface != null) {
                    val myPos = adapterPosition

                    if (myPos != RecyclerView.NO_POSITION) {
                        recyclerViewInterface.onItemClick(myPos)
                    }
                }
            }
        }
    }
}