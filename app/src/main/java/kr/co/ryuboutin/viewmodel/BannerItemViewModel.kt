package kr.co.ryuboutin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.ryuboutin.data.BannerItem

class BannerItemViewModel() : ViewModel() {
    private val _bannerItemList: MutableLiveData<List<BannerItem>> = MutableLiveData()
    private val _currentPosition: MutableLiveData<Int> = MutableLiveData()

    val bannerItemList: LiveData<List<BannerItem>>
        get() = _bannerItemList
    val currentPosition: LiveData<Int>
        get() = _currentPosition

    init{
        _currentPosition.value=0
    }

    fun setBannerItems(list: List<BannerItem>) {
        _bannerItemList.value = list
    }

    fun setCurrentPosition(position: Int){
        _currentPosition.value = position
    }

    fun getBannerSize() : Int {
        return _bannerItemList.value?.size ?: 0
    }

    fun getcurrentPosition() = currentPosition.value

}