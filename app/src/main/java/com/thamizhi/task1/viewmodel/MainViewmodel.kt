package com.thamizhi.task1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.coroutines.toDeferred
import com.apollographql.apollo.exception.ApolloException
import com.thamizhi.task1.GetCategoriesQuery
import com.thamizhi.task1.api.apolloClient
import kotlinx.coroutines.launch

class MainViewmodel :ViewModel(){

    private val _result = MutableLiveData<String>()

    val result:LiveData<String>
    get() = _result

    var categories = listOf<GetCategoriesQuery.Result?>()

    init {
        viewModelScope.launch {
            val response = try {
                apolloClient.query(GetCategoriesQuery()).toDeferred().await()
            }catch (e: ApolloException){

                null
            }
            categories=response?.data?.getCategories?.result!!
            _result.value="ok"
        }
    }

}