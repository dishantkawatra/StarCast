package com.example.starcast.fragment.homescreen

import android.content.Context
import android.view.*

import androidx.recyclerview.widget.RecyclerView
import com.example.starcast.R
import com.example.starcast.`interface`.RecyclerItemClickListener
import kotlinx.android.synthetic.main.item_character.view.*

import java.util.*


class CharacterListAdapter(private val characterList: ArrayList<CharacterDetailResponse>, val recyclerItemClickListener: RecyclerItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(parent.context)
        return  OrderViewHolder(inflater.inflate(R.layout.item_character, parent, false))
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as OrderViewHolder).bindOrder()
    }

    inner class OrderViewHolder(view: View) : RecyclerView.ViewHolder(view),View.OnClickListener {
        fun bindOrder() {
            itemView.tvName.text=characterList[adapterPosition].name
            itemView.tvName.setOnClickListener(this)

        }

        override fun onClick(p0: View?) {
            if(adapterPosition>=0)
                recyclerItemClickListener.onRecyclerItemClicked(itemView,adapterPosition)
        }

    }


}