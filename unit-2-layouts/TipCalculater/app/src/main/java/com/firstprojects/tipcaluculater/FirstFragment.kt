package com.firstprojects.tipcaluculater

import android.content.Context
import android.icu.number.NumberFormatter
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.firstprojects.tipcaluculater.databinding.FragmentFirstBinding
import java.text.DecimalFormat

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private var tipPercentage : Double? = null
    private var TIP : String? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        setOnClickListeners()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    private fun onClickedAnOption(id : Int){

        tipPercentage =

            when(id){

                R.id.option_twenty_percent ->
                    0.20

                R.id.option_eighteen_percent ->
                    0.18

                R.id.option_fifteen_percent ->
                    0.15

            else -> 0.15
        }

    }

    private fun onClickedButtonOfCalculation(view: View) {

        val stringInTheTextField = binding.etCostOfService.text.toString()
        val cost : Double? = stringInTheTextField.toDoubleOrNull()
        val formatter = DecimalFormat("#0.00")

        val id = binding.radioGroup.checkedRadioButtonId
        onClickedAnOption(id)

        if(cost != null && tipPercentage != null){
            TIP = formatter.format( cost * tipPercentage!!).toString()
            binding.textViewTipOfAmount.text = this.resources.getString(R.string.tip_amount_text_view_text,TIP!!)
        }else{
            binding.textViewTipOfAmount.text = "${resources.getText(R.string.tip_amount_text_view_text,resources.getString(R.string.none_value))}"
            binding.optionFifteenPercent.isChecked = true
            binding.etCostOfService.setText(this.resources.getString(R.string.none_value))
        }
    }

    private fun setOnClickListeners(){
        binding.buttonOfCalculation.setOnClickListener { onClickedButtonOfCalculation(it) }
        binding.etCostOfService.setOnKeyListener{view, keyCode, _ -> handleKeyEvent(view, keyCode)}
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}