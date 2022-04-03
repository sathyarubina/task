package com.mvvm.task.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.task.R
import com.mvvm.task.databinding.FeedRowBinding
import com.mvvm.task.db.FeedEntity
import com.mvvm.task.utils.DateTimeZone

class FeedAdapter(private val clickListener: OnItemClickListener) :
    RecyclerView.Adapter<FeedAdapter.ViewHolder>() {
    private var listData: List<FeedEntity>? = null
    private lateinit var binding: FeedRowBinding

    fun setlistData(listData: List<FeedEntity>?) {
        this.listData = listData
    }

    class ViewHolder(private val binding: FeedRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: FeedEntity,clickListener: OnItemClickListener) {
            println("ID${data.id}")
            binding.name.text = data.roomname
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                binding.time.text = DateTimeZone.DateTimeZone(data.time)
            }
            if (data.islive)
                binding.isLiveLbl.visibility = View.VISIBLE
            else
                binding.isLiveLbl.visibility = View.GONE

            binding.cardL.setOnClickListener(View.OnClickListener {
               clickListener.onItemEditCLick(data)
            })

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.feed_row,
            parent,
            false
        )
        return ViewHolder(binding)

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        listData?.get(position)?.let { holder.bind(it,clickListener) }
    }

    override fun getItemCount(): Int {
        if(listData ==null)return 0
        return listData?.size!!
    }

    interface OnItemClickListener {
        fun onItemEditCLick(feeddata: FeedEntity)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

}