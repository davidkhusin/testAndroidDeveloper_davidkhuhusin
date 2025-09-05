package com.example.androiddeveloper_davidkhuhusin.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddeveloper_davidkhuhusin.R
import com.example.androiddeveloper_davidkhuhusin.model.User

class UserAdapter(private  var users: List<User>)
    : RecyclerView.Adapter<UserAdapter.UserViewHolder> () {
    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText: TextView = itemView.findViewById(R.id.nameText);
        val emailText: TextView = itemView.findViewById(R.id.emailText);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view);
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.nameText.text = user.name
        holder.emailText.text = user.email
    }

    fun setData(newUsers: List<User>){
        users = newUsers
        notifyDataSetChanged()
    }
}