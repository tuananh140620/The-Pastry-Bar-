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


class OrderViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.menu_item,parent,false)){
    private val foodImage: ImageView
    //    private lateinit var activity: AppCompatActivity
    private val foodName: TextView
    private val price : TextView

    init{
        foodImage = itemView.findViewById(R.id.image_view)
        foodName = itemView.findViewById(R.id.menu_food_name)
        price = itemView.findViewById(R.id.menu_food_price)
//        activity = HomeActivity()
    }

    fun bind(food: Food){
        foodName?.text = food.name
        Picasso.get().load(food.image).resize(250, 250) // resizes the image to these dimensions (in pixel)
            .centerCrop().into(foodImage)
        foodImage.setClipToOutline(true)
        price.text = "$ "+food.price.toString()

    }

}

class OrderAdapter(private val list:ArrayList<Food>)
    : RecyclerView.Adapter<OrderViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int):OrderViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        return OrderViewHolder(inflater,parent)
    }
    override fun onBindViewHolder(holder:OrderViewHolder,position:Int){
        val food:Food = list[position]
        holder.bind(food)
        holder.itemView.setOnClickListener{
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
    override fun getItemCount():Int = list.size
}