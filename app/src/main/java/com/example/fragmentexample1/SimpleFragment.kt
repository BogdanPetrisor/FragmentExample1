package com.example.fragmentexample1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment


const val YES = 0
const val NO = 1

class SimpleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment.
        val rootView =
            inflater.inflate(R.layout.fragment_simple, container, false);
        val radioGroup = rootView.findViewById<RadioGroup>(R.id.radio_group)

        radioGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            val radio: RadioButton = rootView.findViewById(checkedId)
            val index = radioGroup.indexOfChild(radio)

            val textView: TextView = rootView.findViewById(R.id.fragment_header)

            when(index) {
                YES -> textView.setText(R.string.yes_message)
                NO -> textView.setText(R.string.no_message)
                else -> {}
            }
        })

        return rootView
    }

   companion object  {
        val newInstance = SimpleFragment()
    }
}