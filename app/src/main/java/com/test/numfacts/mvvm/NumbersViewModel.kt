package com.test.numfacts.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.numfacts.db.FactViewElementEntity
import com.test.numfacts.services.NumService
import com.test.numfacts.services.NumServiceData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NumbersViewModel @Inject constructor(
    private val retrofit: NumService
) : ViewModel() {



    private val _numFact = MutableLiveData<NumServiceData>()
    val numFact: LiveData<NumServiceData> get() = _numFact

    private val _historyData = MutableLiveData<FactViewElementEntity>()
    val historyData: LiveData<FactViewElementEntity> get() = _historyData



    fun loadFact(num: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val data = retrofit.getNumFact(num)
                CoroutineScope(Dispatchers.Main).launch {
                    _numFact.postValue(data)
                    _historyData.value = FactViewElementEntity(null,data.number,data.text)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    fun loadRandomFact() {
        CoroutineScope(Dispatchers.IO).launch {

            try {
                val data = retrofit.getRandomNumFact()
                CoroutineScope(Dispatchers.Main).launch {
                    _numFact.postValue(data)
                    _historyData.value = FactViewElementEntity(null,data.number,data.text)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }


}