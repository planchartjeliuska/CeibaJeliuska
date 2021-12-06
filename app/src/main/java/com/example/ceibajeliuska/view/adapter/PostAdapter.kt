package com.example.ceibajeliuska.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ceibajeliuska.R
import com.example.ceibajeliuska.utils.models.ResponseListPosts.ResponseListPostsItem

class PostAdapter (val postList: List<ResponseListPostsItem> ): RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var textTitle: TextView
        lateinit var textBody :TextView

        init {
            textTitle = view.findViewById(R.id.title)
            textBody = view.findViewById(R.id.body)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textTitle.text = postList[position].title
        holder.textBody.text = postList[position].body
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}