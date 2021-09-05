package com.example.testfocusstart.ui

import android.os.Bundle
import android.os.Handler
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testfocusstart.R
import ermilov.focusstarttestovoe.apiCb.CbrApi
import ermilov.focusstarttestovoe.model.Valute
import ermilov.focusstarttestovoe.recyclerAdapter.Adapter
import kotlinx.android.synthetic.main.fragment_valute.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ValuteFragment: Fragment() {
    var listValite : ArrayList<Valute> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_valute, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
    }

    fun refreshData(milliseconds: Int) {
        val handler = Handler()
        val runnable = Runnable {
            listValite.clear()
            getData()
            Toast.makeText(requireContext(), "Обновлено", Toast.LENGTH_SHORT).show()
        }
        handler.postDelayed(runnable, milliseconds.toLong())
    }

    private fun getData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.cbr-xml-daily.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val cbrApi = retrofit.create(CbrApi::class.java)
        CoroutineScope(Dispatchers.IO).launch{
            val response = cbrApi.getallValute()
            for (i in response.body()!!.Valute){
                listValite.add(i.value)
            }

            launch(Dispatchers.Main){
                val adapter = Adapter(
                    listValite,
                    requireContext()
                )
                recycler_valute.layoutManager = LinearLayoutManager(requireContext())
                recycler_valute.adapter = adapter
                refreshData(60000)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite) {
            getData()
            Toast.makeText(requireContext(), "Данные обновлены", Toast.LENGTH_SHORT).show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}