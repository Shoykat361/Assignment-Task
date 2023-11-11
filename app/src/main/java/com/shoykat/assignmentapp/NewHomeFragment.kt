package com.shoykat.assignmentapp

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.shoykat.assignmentapp.Adapter.UserAdapter
import com.shoykat.assignmentapp.ViewModel.UserViewModel
import com.shoykat.assignmentapp.databinding.FragmentNewHomeBinding
import com.shoykat.assignmentapp.model.All
import com.shoykat.assignmentapp.model.Top3

class NewHomeFragment : Fragment() {
    private lateinit var binding : FragmentNewHomeBinding
    private val userViewModel: UserViewModel by activityViewModels()
    private val top3UserList = mutableListOf<Top3>()
    private val allUserList = mutableListOf<All>()
    private lateinit var adapter: UserAdapter




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewHomeBinding.inflate(inflater, container, false)
        //Hide Actionbar as it is not necessary in this fragment
        (activity as AppCompatActivity).supportActionBar!!.hide()

        requireActivity().window.apply {
            statusBarColor = Color.TRANSPARENT
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }

        adapter = UserAdapter { userBinding, model->

        }

        binding.userRV.apply {
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
        binding.userRV.adapter = adapter


        //get user list
        userViewModel.fetchUserData().observe(viewLifecycleOwner){list->
            top3UserList.clear()
            allUserList.clear()
            top3UserList.addAll(list?.userData?.hostDaily?.top3!!)
            allUserList.addAll(list.userData.hostDaily.all)

            Log.d("TAG", "topList: $top3UserList")
            Log.d("TAG", "AllList: $allUserList")

            setTop3UserData()

            if (allUserList.isEmpty()) {
                binding.progressBar.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.GONE
                adapter.submitList(allUserList)
            }
        }


        return binding.root
    }

    private fun setTop3UserData() {
        val sortedList = top3UserList.sortedBy { it.position }

        binding.nameTop1.text = "${sortedList[0].firstName} ${sortedList[0].lastName}"
        binding.coinTop1.text = "${sortedList[0].giftcoin}"
        Glide.with(requireActivity()).load(sortedList[0].profilePic).into(binding.imgTop1)

        binding.nameTop2.text = "${sortedList[1].firstName} ${sortedList[1].lastName}"
        binding.coinTop2.text = "${sortedList[1].giftcoin}"
        Glide.with(requireActivity()).load(sortedList[1].profilePic).into(binding.imgTop2)

        binding.nameTop3.text = "${sortedList[2].firstName} ${sortedList[2].lastName}"
        binding.coinTop3.text = "${sortedList[2].giftcoin}"
        Glide.with(requireActivity()).load(sortedList[2].profilePic).into(binding.imgTop3)
    }

}