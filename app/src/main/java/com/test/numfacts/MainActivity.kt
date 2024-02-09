package com.test.numfacts

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.numfacts.databinding.ActivityMainBinding
import com.test.numfacts.db.FactViewElementEntity
import com.test.numfacts.db.NumsMainDb
import com.test.numfacts.di.AppModule
import com.test.numfacts.di.DaggerAppComponent
import com.test.numfacts.mvvm.NumbersViewModel
import com.test.numfacts.recyclerview.DurationStyle
import com.test.numfacts.recyclerview.OnNumberFactClickListener
import com.test.numfacts.recyclerview.RecyclerViewFactAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var numbersViewModel: NumbersViewModel

    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var recyclerViewAdapter: RecyclerViewFactAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        appComponent.inject(this)
        appComponent.inject(numbersViewModel)
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val db = NumsMainDb.getDb(this)

        numbersViewModel.numFact.observe(this@MainActivity, Observer {
            val intent = Intent(this@MainActivity, FullFactViewActivity::class.java)
            intent.putExtra("fact", it.text)
            startActivity(intent)
        })
        db.getDao().getAllElements().asLiveData().observe(this@MainActivity) {
            recyclerViewAdapter = RecyclerViewFactAdapter(it, object : OnNumberFactClickListener {
                override fun onClick(positionList: Int, factView: FactViewElementEntity) {
                    val intent = Intent(this@MainActivity, FullFactViewActivity::class.java)
                    intent.putExtra("fact", factView.fact)
                    startActivity(intent)
                }

            })
            recyclerViewAdapter.notifyDataSetChanged()
            binding.apply {
                recyclerViewHistory.adapter = recyclerViewAdapter
            }
        }
        numbersViewModel.historyData.observe(this@MainActivity, Observer {

            CoroutineScope(Dispatchers.IO).launch {
                db.getDao().insert(it)
            }


        })
        binding.apply {
            recyclerViewHistory.setHasFixedSize(true)
            recyclerViewHistory.layoutManager = layoutManager
            recyclerViewHistory.addItemDecoration(DurationStyle(20))
            getRandomFactView.setOnClickListener {
                numbersViewModel.loadRandomFact()

            }
            getFactView.setOnClickListener {
                if (inputNumberView.text?.isNotEmpty() == true) {
                    numbersViewModel.loadFact(inputNumberView.text.toString().toInt())
                } else {
                    inputNumberView.error = "Field is must not be empty"
                }
            }

        }
    }
}