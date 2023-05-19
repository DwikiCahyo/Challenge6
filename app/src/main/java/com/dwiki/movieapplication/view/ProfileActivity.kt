package com.dwiki.movieapplication.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dwiki.movieapplication.MainActivity
import com.dwiki.movieapplication.databinding.ActivityProfileBinding
import com.dwiki.movieapplication.viewmodel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private val viewModel:LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = ""
        setSupportActionBar(binding.toolbar)

        val username = viewModel.getUsernamePreferences("key_username")
        binding.edtUsername.setText(username)

        binding.btnUpdate.setOnClickListener {
            val usernameEdit = binding.edtUsername.text.toString()
            viewModel.saveUsernamePreferences("key_username",usernameEdit)
            val intent = Intent(this@ProfileActivity,HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnLogout.setOnClickListener {
            val userAuth = FirebaseAuth.getInstance()
            userAuth.signOut()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnFav.setOnClickListener {
            val intent = Intent(this,FavoriteActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onStart() {
        super.onStart()
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null){
            val email = user.email
            binding.tvEmail.text = email
        }

    }
}