package com.example.ceibajeliuska.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.ceibajeliuska.data.retrofit.RetrofitService
import com.example.ceibajeliuska.data.repositories.*
import com.example.ceibajeliuska.databinding.ActivityPostBinding
import com.example.ceibajeliuska.view.adapter.PostAdapter

class PostActivity : AppCompatActivity() {

    lateinit var postAdapter: PostAdapter
    lateinit var postView : ActivityPostBinding

    private var userId:Int? = null

    private val retrofitService = RetrofitService.getInstance()

    lateinit var viewModel : PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postView = ActivityPostBinding.inflate(layoutInflater)
        setContentView(postView.root)

        viewModel = ViewModelProvider(this, PostViewModelFactory(Repository(retrofitService,application))).get(PostViewModel::class.java)

        if (intent.hasExtra("userId")){
            userId = intent.getIntExtra("userId", -1)
        }else{
            finish()
        }

        viewModel.getAllPostByUser(userId!!)
        viewModel.getLocalUser(userId!!)

        viewModel.listPost.observe(this){
            postAdapter = PostAdapter(it)
            postView.recyclerViewPostsResults.adapter = postAdapter
        }

        viewModel.userLocal.observe(this){
           postView.name.text = it.name
            postView.phone.text = it.phone
            postView.email.text = it.email
        }
    }


}