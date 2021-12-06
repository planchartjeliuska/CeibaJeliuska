package com.example.ceibajeliuska.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.ceibajeliuska.data.retrofit.RetrofitService
import com.example.ceibajeliuska.data.repositories.Repository
import com.example.ceibajeliuska.data.repositories.MainViewModel
import com.example.ceibajeliuska.data.repositories.MainViewModelFactory
import com.example.ceibajeliuska.databinding.ActivityMainBinding
import com.example.ceibajeliuska.utils.models.ResponseListUsers.ResponseListUser
import com.example.ceibajeliuska.view.adapter.UserListAdapter
import com.example.ceibajeliuska.view.interfaz.OnClickUserListListener

class MainActivity : AppCompatActivity(), OnClickUserListListener {

    lateinit var userListAdapter : UserListAdapter
    lateinit var mainView : ActivityMainBinding
    lateinit var viewModel : MainViewModel
    private val retrofitService = RetrofitService.getInstance()

    private val filteredUsers = ResponseListUser()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainView = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainView.root)

        viewModel = ViewModelProvider(this, MainViewModelFactory(Repository(retrofitService,application))).get(MainViewModel::class.java)

        viewModel.listUser.observe(this,{
            userListAdapter.setUserList(it)
        })
        viewModel.getAllUsers()

        userListAdapter = UserListAdapter(this)
        mainView.recyclerViewSearchResults.adapter = userListAdapter

        mainView.editTextSearch.addTextChangedListener{ filtro ->

            if (filtro?.isBlank() == true){
                userListAdapter.setUserList(viewModel.listUser.value!!)
            }else{
                filteredUsers.clear()
                //Filtro
                viewModel.listUser.value?.forEach { user ->
                    if (user.name.contains(filtro.toString())){
                        filteredUsers.add(user)
                    }
                }
                if (filteredUsers.isEmpty()){
                    //TODO("MOSTRAR EMPTY VIEW")
                }else{
                    userListAdapter.setUserList(filteredUsers)
                }

            }
        }
    }

    override fun onClickUserPostList(userId: Int) {
        val intent = Intent(this, PostActivity::class.java)
        intent.putExtra("userId", userId)
        startActivity(intent)
    }
}