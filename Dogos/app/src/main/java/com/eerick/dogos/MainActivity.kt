package com.eerick.dogos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import com.eerick.dogos.adapters.CardAdapter
import com.eerick.dogos.database.DogsDB
import com.eerick.dogos.objects.Dog
import com.eerick.dogos.utils.FetchDataUtils
import com.eerick.dogos.utils.SharedPreferencesUtils
import com.facebook.drawee.backends.pipeline.Fresco

class MainActivity : AppCompatActivity() {

    private val URL = "https://jsonblob.com/api/945366962796773376"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Fresco.initialize(applicationContext)
        val toolbarButton: ImageView = findViewById(R.id.icon_toolbar)
        val buttonRetry: Button = findViewById(R.id.button_retry)
        val listView: ListView = findViewById(R.id.list_view_cards)
        toolbarButton.setOnClickListener{
            this.finish()
        }
        buttonRetry.setOnClickListener {
            FetchDataUtils.get(URL, applicationContext, buttonRetry, listView)
        }
        if (SharedPreferencesUtils.getBoolean(applicationContext,
                getString(R.string.data_already_downloaded),
                false /* defaultValue */)) {
            val dogs: ArrayList<Dog> = DogsDB.getInstance(applicationContext).get()
            listView.adapter = CardAdapter(applicationContext, dogs)
        } else {
            FetchDataUtils.get(URL, applicationContext, buttonRetry, listView)
        }
    }
}