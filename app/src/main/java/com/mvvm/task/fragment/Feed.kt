package com.mvvm.task.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.CompoundButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvvm.task.R
import com.mvvm.task.adapter.FeedAdapter
import com.mvvm.task.databinding.DialogRoomBinding
import com.mvvm.task.databinding.FragmentLayoutBinding
import com.mvvm.task.db.FeedEntity
import com.mvvm.task.viewmodel.FeedViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@Suppress("ControlFlowWithEmptyBody")
@AndroidEntryPoint
class Feed : Fragment(), View.OnClickListener, FeedAdapter.OnItemClickListener {
    private lateinit var binding: FragmentLayoutBinding
    private lateinit var viewModel: FeedViewModel
    lateinit var feedAdapter: FeedAdapter
    private var feedData: List<FeedEntity>? = ArrayList()
    private lateinit var feedEntity: FeedEntity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_layout, container, false
        )
        val view: View = binding.root
        onClickLisnter()
        initRecyclerView()
        initVM()
        return view
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            feedAdapter = FeedAdapter(this@Feed)
            adapter = feedAdapter

        }

    }

    private fun onClickLisnter() {
        binding.fab.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.fab -> roomDialog(false)
        }
    }

    private fun roomDialog(IsEdit: Boolean) {
        val dialogBinding: DialogRoomBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(requireActivity()),
                R.layout.dialog_room,
                null,
                false
            )
        val dialog = AlertDialog.Builder(requireActivity(), 0).create()
        dialog.apply {
            setView(dialogBinding.root)
            setCancelable(false)
            requestWindowFeature(Window.FEATURE_NO_TITLE)
        }.show()
        dialogView(dialogBinding, dialog, IsEdit)
        dialog.show()
    }

    private fun dialogView(
        dialogBinding: DialogRoomBinding,
        dialog: AlertDialog,
        IsEdit: Boolean
    ) {
        var islive = true
        val id: Int
        when {
            IsEdit -> {
                id = feedEntity.id
                dialogBinding.roomEdt.setText(feedEntity.roomname)
                dialogBinding.isLiveSwitch.isChecked = feedEntity.islive
            }
            else -> (if (feedData?.size!! > 0) {
                (feedData!!.size) + 1
            } else {
                1
            }).also { id = it }
        }
        dialogBinding.isLiveSwitch.setOnCheckedChangeListener(
            CompoundButton.OnCheckedChangeListener
            { _, isChecked ->
                islive = isChecked
            })
        dialogBinding.close.setOnClickListener {
            dialog.dismiss()
        }
        dialogBinding.submit.setOnClickListener {
            submitFunction(dialogBinding, dialog, islive, IsEdit,id)

        }
    }

    private fun submitFunction(
        dialogBinding: DialogRoomBinding,
        dialog: AlertDialog,
        islive: Boolean,
        IsEdit: Boolean,
        id:Int
    ) {

        val roomname: String = dialogBinding.roomEdt.text.toString()
        var duplicate: Boolean
        duplicate = false
        feedData?.forEachIndexed { index, element ->
            if (feedData!![index].roomname == roomname) {
                duplicate=true
            }
        }
        if(duplicate){
            Toast.makeText(
                requireActivity(),
                resources.getText(R.string.room_unique_validate),
                Toast.LENGTH_SHORT
            ).show()
        }else{
            insertUpdate(id,roomname,islive,IsEdit)
        }
        dialog.dismiss()
    }
    private fun insertUpdate(id:Int,roomname:String,islive:Boolean,IsEdit:Boolean) {
        val calendar: Calendar = Calendar.getInstance()
        val cal: Long = calendar.timeInMillis
        when {
            roomname.isNotEmpty() -> {
                val feedEntity = FeedEntity(
                    id = id,
                    roomname = roomname,
                    islive = islive,
                    time = cal
                )
                when {
                    IsEdit -> viewModel.updateRecord(feedEntity)
                    else -> viewModel.insertRecord(feedEntity)
                }

            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initVM() {
        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        viewModel.getRecordsObserver()
            .observe(requireActivity(),
                { t ->
                    t?.forEach { _ ->

                        feedData = t
                        feedAdapter.setlistData(feedData)
                        feedAdapter.notifyDataSetChanged()
                    }
                })

    }


    override fun onItemEditCLick(feeddata: FeedEntity) {
        feedEntity = feeddata
        roomDialog(true)

    }

}