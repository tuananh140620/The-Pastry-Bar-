package com.example.mock.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mock.R
import com.example.mock.entity.Food
import com.squareup.picasso.Picasso

class DealViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.deal_item,parent,false)){
    private val foodImage: ImageView = itemView.findViewById(R.id.food_image2)
    private val foodDiscount: TextView = itemView.findViewById(R.id.food_discount2)

    init{
        //        foodName = itemView.findViewById(R.id.food_name)
    }

    fun bind(food: Food){
        foodDiscount?.text = food.discount.toString() + "% OFF!"
        Picasso.get().load(food.image).resize(500, 500) // resize the image to these dimensions (in pixel)
            .centerCrop().into(foodImage)
        foodImage.clipToOutline = true

        foodImage.setOnClickListener{
            val intent = Intent(it.context, DetailActivity::class.java)
            intent.putExtra("id",food.id)
            intent.putExtra("name",food.name)
            intent.putExtra("description",food.description)
            intent.putExtra("discount",food.discount)
            intent.putExtra("available",food.available)
            intent.putExtra("amount",food.amount)
            intent.putExtra("image",food.image)
            intent.putExtra("price",food.price)
            intent.putExtra("ordertimes",food.ordertimes)
            intent.putExtra("sort",food.sort)
            it.context.startActivity(intent)
        }

    }

}

class DealAdapter(private val list:ArrayList<Food>)
    : RecyclerView.Adapter<DealViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int):DealViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        return DealViewHolder(inflater,parent)
    }
    override fun onBindViewHolder(holder:DealViewHolder,position:Int){
        val popularFood:Food = list[position]
        holder.bind(popularFood)
    }
    override fun getItemCount():Int = list.size
}