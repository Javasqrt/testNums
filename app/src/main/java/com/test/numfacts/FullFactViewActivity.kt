package com.test.numfacts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.numfacts.databinding.ActivityFullFactViewBinding

class FullFactViewActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityFullFactViewBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if(intent.getStringExtra("fact") != null){
            binding.numberView.text = intent.getStringExtra("fact")
        }

    }
}