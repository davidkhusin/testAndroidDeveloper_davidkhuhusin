package com.example.androiddeveloper_davidkhuhusin

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddeveloper_davidkhuhusin.data.repository.UserRepository
import com.example.androiddeveloper_davidkhuhusin.ui.adapter.UserAdapter
import com.example.androiddeveloper_davidkhuhusin.viewmodel.UserViewModel
import com.example.androiddeveloper_davidkhuhusin.viewmodel.UserViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repo = UserRepository()
        val factory = UserViewModelFactory(repo)
        viewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val errorText = findViewById<TextView>(R.id.errorText)

        adapter = UserAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.users.observe(this) { users ->
            adapter.setData(users)
        }

        viewModel.loading.observe(this) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(this) { error ->
            errorText.visibility = if (error != null) View.VISIBLE else View.GONE
            errorText.text = error
        }

        viewModel.fetchUsers()
    }
}