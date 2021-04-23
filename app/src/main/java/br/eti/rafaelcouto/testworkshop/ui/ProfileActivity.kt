package br.eti.rafaelcouto.testworkshop.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import br.eti.rafaelcouto.testworkshop.R
import br.eti.rafaelcouto.testworkshop.databinding.ActivityProfileBinding
import br.eti.rafaelcouto.testworkshop.domain.viewmodel.ProfileViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileActivity : AppCompatActivity() {

    private val profileViewModel: ProfileViewModel by viewModel()
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<ActivityProfileBinding>(
            this, R.layout.activity_profile
        ).apply {
            lifecycleOwner = this@ProfileActivity
            viewModel = profileViewModel
            btnRetry.setOnClickListener { profileViewModel.loadData() }
        }

        setupObservers()

        profileViewModel.loadData()
    }

    private fun setupObservers() {
        profileViewModel.isLoading.observe(this) {
            binding.clLoader.isVisible = it
        }

        profileViewModel.hasError.observe(this) {
            binding.clError.isVisible = it
        }
    }
}