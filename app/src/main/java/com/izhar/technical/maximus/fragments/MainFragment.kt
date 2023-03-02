package com.izhar.technical.maximus.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.izhar.technical.maximus.R
import com.izhar.technical.maximus.databinding.FragmentMainBinding
import com.izhar.technical.maximus.viewmodel.ApiViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    lateinit var bindingMainFragment: FragmentMainBinding
    lateinit var apiViewModel: ApiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    customExitDialog()
                }
            }
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingMainFragment = FragmentMainBinding.inflate(layoutInflater, container, false)
        loadtheResponse()
        bindingMainFragment.refresh.setOnClickListener {
            loadtheResponse()
        }
        return bindingMainFragment.root
    }

    fun loadtheResponse() {
        bindingMainFragment.progressBar.visibility = View.VISIBLE
        apiViewModel = ViewModelProvider(this@MainFragment)[ApiViewModel::class.java]
        apiViewModel.getResponse("fact").observe(requireActivity()) {
            if (it.fact != "") {
                bindingMainFragment.factText.text = it.fact
                bindingMainFragment.length.text = it.length.toString()
                bindingMainFragment.progressBar.visibility = View.GONE
            } else {
                bindingMainFragment.factText.text = resources.getString(R.string.noresponse)
                bindingMainFragment.length.text = resources.getString(R.string.noresponse)
                bindingMainFragment.progressBar.visibility = View.GONE
            }
        }
    }
    fun customExitDialog() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.custom_exit_dialog)
        val dialogButtonYes = dialog.findViewById(R.id.textViewYes) as TextView
        val dialogButtonNo = dialog.findViewById(R.id.textViewNo) as TextView
        dialogButtonNo.setOnClickListener {
            dialog.dismiss()
        }
        dialogButtonYes.setOnClickListener {
            ActivityCompat.finishAffinity(requireActivity())
            dialog.dismiss()
        }
        dialog.show()
    }
}