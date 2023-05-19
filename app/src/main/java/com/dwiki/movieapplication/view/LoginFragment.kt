package com.dwiki.movieapplication.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.dwiki.movieapplication.R
import com.dwiki.movieapplication.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {

     private var _binding:FragmentLoginBinding? = null
     private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater,container,false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth

        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val pass = binding.edtPassword.text.toString()
            if (email.isNotEmpty()&&pass.isNotEmpty()) setLogin(email, pass) else emailPassError()
        }

        binding.tvRegister.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_registerFragment)
        }

    }

    private fun setLogin(email: String, pass: String) {
        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(requireContext(), "Berhasil Login", Toast.LENGTH_SHORT).show()
                    val intent = Intent(requireContext(),HomeActivity::class.java)
                    startActivity(intent)
                    activity?.finish()
                } else {
                    Toast.makeText(requireContext(), "Gagal Login, Periksa Email dan Password", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener(requireActivity()) {
                //if login method not setup in firebase while return exception on craschlytics
                Toast.makeText(requireContext(), "This Login Method is not working", Toast.LENGTH_SHORT).show()
//                throw RuntimeException("Exception : ${it.message}")
            }
    }

    private fun emailPassError(){
        binding.tiEmail.error = "Email tidak boleh kosong"
        binding.tiPassword.error = "Password tidak boleh kosong"
    }

}