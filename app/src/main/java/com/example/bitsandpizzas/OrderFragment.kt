package com.example.bitsandpizzas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bitsandpizzas.databinding.FragmentOrderBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.chip.Chip
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class OrderFragment : Fragment() {
    private lateinit var binding: FragmentOrderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderBinding.inflate(inflater)

        //set up toolbar by getting a reference to the activity and then calling setSupportActionBar()
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        //set on click listeners
        binding.orderButton.setOnClickListener { orderPizza() }

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun orderPizza(){
        var order = ""
        //specify pizza type ordered
        when{
            binding.radioDiavolo.isChecked -> {
                order += "Diavolo pizza"
            }
            binding.radioFunghi.isChecked -> {
                order += "Funghi pizza"
            }
            else -> {
                makeToast("You need to choose a pizza type")
                return
            }
        }
        //specify any extras
        when {
            (binding.parmesan.isChecked && binding.chiliOil.isChecked) -> {
                order += " with extra parmesan and chili oil!"
            }
            binding.parmesan.isChecked -> {
                order += " with extra parmesan!"
            }
            binding.chiliOil.isChecked -> {
                order += " with extra chili oil!"
            }
        }
        //order pizza
        makeSnackbar(order)
    }

    private fun makeToast(message: String){
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    private fun makeSnackbar(message: String){
        Snackbar.make(binding.orderButton, message, Snackbar.LENGTH_SHORT).show()

        //snackbars can be interactive. Here is an example of code which includes
        //an action to undo something
        /*
        Snackbar.make(order_button, message, Snackbar.LENGTH_SHORT)
        .setAction("Undo") {
        //would put code here that runs when the user clicks the action
        }
        .show()*/

        //they can also be anchored to a view using .setAnchorView
        //otherwise they will just appear at the bottom of the screen
    }
}