package com.shoykat.assignmentapp.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.shoykat.assignmentapp.databinding.UserItemBinding
import com.shoykat.assignmentapp.model.All

class UserAdapter(val callback: (UserItemBinding, All)->Unit) : ListAdapter<All, UserAdapter.ViewHolder>(UserDiffUtil()){

    class ViewHolder(val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: All) {
            binding.users = model
        }
    }

    class UserDiffUtil : DiffUtil.ItemCallback<All>() {
        override fun areItemsTheSame(oldItem: All, newItem: All): Boolean {
            return oldItem.userid == newItem.userid
        }

        override fun areContentsTheSame(oldItem: All, newItem: All): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = UserItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = getItem(position)
        holder.bind(model)
        callback(holder.binding, model)
        holder.binding.positionCount.text = "${position+4}"
    }

}