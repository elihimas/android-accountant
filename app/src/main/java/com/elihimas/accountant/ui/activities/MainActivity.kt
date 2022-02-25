package com.elihimas.accountant.ui.activities

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.view.children
import com.elihimas.accountant.R
import com.elihimas.accountant.viewmodels.CountViewModel
import com.elihimas.accountant.databinding.ActivityMainBinding
import com.elihimas.accountant.model.CountEntry
import com.elihimas.accountant.ui.adapters.CountEntriesAdapter
import com.elihimas.accountant.viewmodels.AccountantViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: CountViewModel by viewModels { AccountantViewModelFactory(this) }
    private val adapter = CountEntriesAdapter { entry -> viewModel.deleteEntry(entry) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initViews()
    }

    private fun initViewModel() {
        viewModel.count.observe(this, ::updateCount)
        viewModel.countEntries.observe(this, ::updateCountEntries)
    }

    private fun updateCount(count: Int) {
        binding.tvQuantity.text = count.toString()
    }

    private fun updateCountEntries(entries: List<CountEntry>) {
        adapter.updateEntries(entries)

        if (entries.isNotEmpty()) {
            binding.rvItems.smoothScrollToPosition(entries.lastIndex)
        }
    }

    private fun initViews() {
        with(binding) {
            val incrementButtons = buttonsContainer.children.filter { it is Button }
            incrementButtons.forEach { it.setOnClickListener(::onIncrementClicked) }

            btConsolidate.setOnClickListener { onConsolidateClicked() }
            btAdd.setOnClickListener { onAddClicked() }

            rvItems.adapter = adapter
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.help_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.menu_item_help) {
            showHelpDialog()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    private fun showHelpDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.app_name)
            .setMessage(R.string.help_text)
            .setPositiveButton(R.string.ok) { dialog, id ->
                dialog.cancel()
            }
            .show()
    }

    private fun onConsolidateClicked() {
        val quantity = binding.tvQuantity.text.toString().toIntOrNull()
        quantity?.let { quantity ->
            viewModel.consolidateQuantity(quantity)
        }
    }

    private fun onAddClicked() {
        val quantity = binding.etQuantity.text.toString().toIntOrNull()
        quantity?.let { quantity ->
            viewModel.addEntry(quantity)
            binding.etQuantity.setText("");
        }
    }

    private fun onIncrementClicked(source: View) {
        val selectedQuantity = (source as Button).text.toString().toInt()
        viewModel.addEntry(selectedQuantity)
    }
}