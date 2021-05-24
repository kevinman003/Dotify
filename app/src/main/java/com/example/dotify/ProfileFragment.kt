package com.example.dotify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.dotify.databinding.FragmentProfileBinding
import com.example.dotify.repository.DataRepository
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import model.User

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    private val gson: Gson = Gson()
    private lateinit var dotifyApp: DotifyApplication
    private lateinit var dataRepository: DataRepository
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentProfileBinding.inflate(inflater)
        dotifyApp = this.activity?.applicationContext as DotifyApplication
        dataRepository = dotifyApp.dataRepository

        lifecycleScope.launch {
            runCatching {
                val user = dataRepository.getUser()
                with(binding) {
                    tvUsername.text = user.username
                    tvName.text = "${user.firstName} ${user.lastName}"
                    tvPlatform.text = "Platform: ${user.platform}"
                    Picasso.get().load(user.profilePicURL).into(ivPfp)
                }
            }.onFailure {
                Toast.makeText(
                    activity,
                    "Error fetcching user data",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

        return binding.root
    }
}