package com.example.ceibajeliuska.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ceibajeliuska.R
import com.example.ceibajeliuska.utils.models.ResponseListUsers.ResponseListUser
import com.example.ceibajeliuska.utils.models.ResponseListUsers.ResponseListUserItem
import com.example.ceibajeliuska.view.interfaz.OnClickUserListListener

class UserListAdapter(private val onClickUserListListener: OnClickUserListListener) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    var saveUserList = emptyList<ResponseListUserItem>()

    fun setUserList(userList : List<ResponseListUserItem>){
        this.saveUserList = userList
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var textName: TextView
        lateinit var textPhone :TextView
        lateinit var textEmail : TextView
        lateinit var button: Button

        init {
            textName = view.findViewById(R.id.nameUserList)
            textPhone = view.findViewById(R.id.phoneUserList)
            textEmail = view.findViewById(R.id.emailUserList)
            button = view.findViewById(R.id.btn_view_post)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = saveUserList[position]

        holder.textName.text = user.name
        holder.textPhone.text = user.phone
        holder.textEmail.text = user.email
        holder.button.setOnClickListener {
            onClickUserListListener.onClickUserPostList(user.id)
        }
    }

    override fun getItemCount(): Int {
        return saveUserList.size
    }
}