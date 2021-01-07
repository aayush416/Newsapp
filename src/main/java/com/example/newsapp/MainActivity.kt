package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var mAdapter: NewsListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerview.layoutManager=LinearLayoutManager(this)
        fetchdata()
        mAdapter=NewsListAdapter()
        recyclerview.adapter=mAdapter
    }
   private fun fetchdata() {
       val url = "https://github.com/SauravKanchan/NewsAPI/blob/master/top-headlines/category/entertainment/in.json"
       val jsonObjectRequest = JsonObjectRequest(
               Request.Method.GET,
       url,
       null,
       Response.Listener {
           val newsJsonArray = it.getJSONArray("articles")
           val newsArray = ArrayList<News>()
           for(i in 0 until newsJsonArray.length()) {
               val newsJsonObject = newsJsonArray.getJSONObject(i)
               val news = News(
                       newsJsonObject.getString("title"),
                       newsJsonObject.getString("author"),
                       newsJsonObject.getString("url"),
                       newsJsonObject.getString("urlToImage")
               )
               newsArray.add(news)
           }

           mAdapter.updateNews(newsArray)
       },
               Response.ErrorListener {
                   Log.d("error",it.message.toString())
               }
       )
       MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
   }
}