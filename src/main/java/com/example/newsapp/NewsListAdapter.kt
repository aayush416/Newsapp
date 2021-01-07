package com.example.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class NewsListAdapter(): RecyclerView.Adapter<NewsViewHolder>() {
    private val items:ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder{
val view =LayoutInflater.from(parent.context).inflate(R.layout.newstheme,parent,false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
      return items.size;
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentitem=items[position]
        holder.newstheme.text = currentitem.title
    }
fun updateNews(updateditems:ArrayList<News>)
{
    items.clear()
    items.addAll(updateditems)
    notifyDataSetChanged()
}
}
class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),View.OnClickListener
{
    init {
        itemView.setOnClickListener(this)
    }
  val newstheme:TextView=itemView.findViewById(R.id.title)
    override fun onClick(v: View?) {
        if (v != null) {
            Toast.makeText(v.context,"Item clicked ${newstheme.text}",Toast.LENGTH_SHORT).show()
        }
    }
}
interface Newsitemclicked
{
    fun onItemClicked(item:String)
}