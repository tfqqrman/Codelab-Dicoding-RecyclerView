package com.example.recyclerviewapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class CardHeroAdapter(private val listHero:ArrayList<Hero>):RecyclerView.Adapter<CardHeroAdapter.CardHeroViewHolder>() {
    inner class CardHeroViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var imgPhoto:ImageView = itemView.findViewById(R.id.img_item_photo)
        var heroesName: TextView = itemView.findViewById(R.id.tv_item_name)
        var heroesDetail: TextView = itemView.findViewById(R.id.tv_item_detail)
        var btnFav: Button = itemView.findViewById(R.id.btn_set_favourite)
        var btnShare: Button = itemView.findViewById(R.id.btn_set_share)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHeroViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_cardview_hero,parent,false)
        return CardHeroViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardHeroViewHolder, position: Int) {
        val hero = listHero[position]
        Glide.with(holder.itemView.context)
                .load(hero.photo)
                .apply(RequestOptions().override(350,350))
                .into(holder.imgPhoto)
        holder.heroesName.text = hero.name
        holder.heroesDetail.text = hero.detail
        holder.btnFav.setOnClickListener{Toast.makeText(holder.itemView.context,"Favourite "+listHero[holder.adapterPosition].name,Toast.LENGTH_SHORT).show()}
        holder.btnShare.setOnClickListener{Toast.makeText(holder.itemView.context,"Share "+listHero[holder.adapterPosition].name, Toast.LENGTH_SHORT).show()}
        holder.itemView.setOnClickListener{Toast.makeText(holder.itemView.context,"Kamu memilih "+listHero[holder.adapterPosition].name,Toast.LENGTH_SHORT).show()}
    }

    override fun getItemCount(): Int {
        return listHero.size
    }
}