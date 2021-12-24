package co.fanavari.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.RadioButton
import co.fanavari.myapplication.databinding.ActivityComponentExampleBinding
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.chip.Chip
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class ComponentExampleActivity : AppCompatActivity(),AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityComponentExampleBinding
    private lateinit var planetsArray: Array<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityComponentExampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bar = binding.bottomAppBar

        planetsArray = resources.getStringArray(R.array.planets_array)

        bar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.search -> {
                    //handle search item
                    true
                }
                R.id.more -> {
                    //handle more item
                    true
                }
                else -> false
            }

        }

        bar.setNavigationOnClickListener {
            if(bar.fabAlignmentMode == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER)
                bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
            else
                bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
        }

        binding.floatingActionButton.setOnClickListener {
            if(bar.fabAlignmentMode == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER)
                bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
            else
                bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
        }

        binding.topAppBar.setNavigationOnClickListener {
            // Handle navigation icon press
        }

        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.favorite -> {
                    // Handle favorite icon press
                    true
                }
                R.id.search -> {
                    // Handle search icon press
                    true
                }
                R.id.more -> {
                    // Handle more item (inside overflow menu) press
                    true
                }
                else -> false
            }
        }

        binding.toggleButton.addOnButtonCheckedListener { _, checkedId, isChecked ->
            // Respond to button selection
            if(isChecked)
                showToast(isChecked.toString())

            if(checkedId == R.id.button1toggle) {
                showToast("toggle 1 selected")
                binding.checkboxLabel1.isChecked = false
                binding.checkboxLabel5.isEnabled = true
            }


            when(checkedId){
                R.id.button1toggle -> {
                    // handle my rules
                    true
                }
                R.id.button2toggle -> {
                    // handle my rules
                    showToast(checkedId.toString())
                    true
                }
                R.id.button3toggle -> {
                    // handle my rules
                    true
                }
                else -> false
            }
        }

        binding.checkboxLabel1.setOnCheckedChangeListener {
                _, isChecked ->
            if(isChecked)
                showToast(isChecked.toString())

        }

        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            // Responds to child RadioButton checked/unchecked
        }

        val spinner = binding.planetsSpinner
        ArrayAdapter.createFromResource(
            this,
            R.array.planets_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = this

        binding.chipGroupFilter.setOnCheckedChangeListener { group, checkedId ->

            val chip: Chip? = group.findViewById(checkedId)
            if(checkedId == R.id.chip3) {
                showToast("chip 3")
                binding.switchMaterial.isChecked = false
            }

        }

    }

    fun onRadioButtonClicked(view: View) {
        if(view is RadioButton){

            val checked = view.isChecked

            when(view.getId()){
                R.id.radio_female -> {
                    if(checked){
                        binding.radioButton1.isChecked = false
                    }

                }

                R.id.radio_male ->
                    if(checked){

                        MaterialAlertDialogBuilder(this)
                            .setTitle(resources.getString(R.string.title))
                            .setMessage(resources.getString(R.string.supporting_text))
                            .setNeutralButton(resources.getString(R.string.cancel)) { dialog, which ->
                                // Respond to neutral button press
                            }
                            .setNegativeButton(resources.getString(R.string.decline)) { dialog, which ->
                                // Respond to negative button press
                            }
                            .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                                // Respond to positive button press
                            }
                            .show()
                    }
            }
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, view: View?, pos: Int, id: Long) {

        showToast("item with id $id and position $pos selected")
        showToast("item ${planetsArray[pos]}")

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        //TODO("Not yet implemented")
    }

    fun onCheckboxClicked(view: View){
        if(view is CheckBox){
            val checked: Boolean = view.isChecked

            when(view.id){
                R.id.checkboxLabel1 -> {
                    if(checked){
                        Snackbar.make(
                            binding.linearComponentActivity,
                            "Text label",
                            Snackbar.LENGTH_INDEFINITE)
                            .setAction("Action") {
                                // Responds to click on the action
                            }
                            .show()
                    }
                }
                R.id.checkboxLabel5 -> {
                    if(checked){

                    }
                }
            }
        }
    }
}