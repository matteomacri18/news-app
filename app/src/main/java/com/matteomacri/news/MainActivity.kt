package com.matteomacri.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matteomacri.news.adapters.NewsAdapter
import com.matteomacri.news.clients.RetrofitClient
import com.matteomacri.news.databinding.ActivityMainBinding
import com.matteomacri.news.models.New
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var newsList = mutableListOf<New>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchNews(binding)
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.app_bar_menu, menu)
//        return true
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.miLanguage -> createDialog()
//        }
//        return true
//    }

//    private fun createDialog() {
//        var selected: String
//        val languages = arrayOf("it", "us")
//        val languageDialog = AlertDialog.Builder(this)
//            .setTitle("Choose language")
//            .setSingleChoiceItems(languages, 0) { dialogInterface, i ->
//                selected = languages[i]
//                Constants.language = selected
//            }.setPositiveButton("Accept") { _, _ -> }
//            .setNegativeButton("Cancel") { _, _ -> }
//            .create()
//            .show()
//    }

    private fun fetchNews(binding: ActivityMainBinding) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitClient.getRetrofitClient().getNews()
                for (new in response.news) {
                    Log.i(TAG, new.toString())
                    addToList(new)
                }

                withContext(Dispatchers.Main) {
                    binding.progressBar.visibility = View.VISIBLE
                    setRecyclerView(binding)
                    binding.progressBar.visibility = View.GONE
                }

            } catch (e: Exception) {
                Log.e(TAG, e.toString())
            }
        }

    }

    private fun setRecyclerView(binding: ActivityMainBinding) {
        binding.rvNews.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.rvNews.adapter = NewsAdapter(this@MainActivity, newsList)
    }

    private fun addToList(new: New) = newsList.add(new)

}