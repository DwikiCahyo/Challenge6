package com.dwiki.movieapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.dwiki.movieapplication.R
import com.dwiki.movieapplication.databinding.FragmentRegisterBinding
import com.dwiki.movieapplication.viewmodel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth

        binding.btnRegister.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val pass = binding.edtPassword.text.toString()
            val username = binding.edtUsername.text.toString()

            if (username.isEmpty() && email.isEmpty() && pass.isEmpty()) {
                Toast.makeText(requireContext(), "Email, Username, password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.saveUsernamePreferences("key_username", username)
                auth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(requireContext(), "Berhasil Register, Silahkan Login", Toast.LENGTH_SHORT).show()
                            Navigation.findNavController(binding.root).navigate(R.id.action_registerFragment_to_loginFragment)
                        } else {
                            Toast.makeText(requireContext(), "Gagal register", Toast.LENGTH_SHORT).show()
                        }
                    }
                    .addOnFailureListener(requireActivity()) {
                        //if login method not setup in firebase while return exception on craschlytics
                        Toast.makeText(requireContext(), "This register Method is not working", Toast.LENGTH_SHORT).show()
//                throw RuntimeException("Exception : ${it.message}")
                    }
            }
        }

    }
}