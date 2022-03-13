package com.eerick.dogos.utils

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.annotation.VisibleForTesting
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.eerick.dogos.R
import com.eerick.dogos.adapters.CardAdapter
import com.eerick.dogos.database.DogsDB
import com.eerick.dogos.database.DogsReaderDbHelper
import com.eerick.dogos.objects.Dog
import com.eerick.dogos.objects.RequestManager
import com.eerick.dogos.utils.FetchDataConstants.Companion.AGE
import com.eerick.dogos.utils.FetchDataConstants.Companion.DESCRIPTION
import com.eerick.dogos.utils.FetchDataConstants.Companion.IMAGE
import com.eerick.dogos.utils.FetchDataConstants.Companion.NAME
import com.eerick.dogos.utils.FetchDataUtils.Companion.get
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
/**
 * FetchDataUtils
 *
 * This class basically allows us to make a request to an API and get
 * useful data about dogs. As all the members are "static" methods
 * it doesn't needs a constructor
 *
 */
class FetchDataUtils {
    companion object {
        const val TAG: String = "FetchData"
        /**
         * [get] makes the request to the URL and get the dogs info.
         * @param context
         * @param listView the component which receives the data to render
         * @param button in case that something goes wrong because maybe we do
         * not have internet access or maybe the server is not available we
         * display a button which allows users to retry fetch data
         * @return nothing
         */
         fun get(URL: String, context: Context, button: Button, listView: ListView) {
            val request = StringRequest(
                Request.Method.GET, URL,
                { response ->
                    val data = JSONArray(response.toString())
                    @Nullable val listItems: ArrayList<Dog>? = maybeParseData(data)
                    if (listItems != null) {
                        DogsDB.getInstance(context).add(listItems)
                        SharedPreferencesUtils.save(
                            context,
                            context.getString(R.string.data_already_downloaded),
                            true /* value */
                        )
                        listView.adapter = CardAdapter(context, listItems)
                        button.visibility = View.GONE
                    } else {
                        button.visibility = View.VISIBLE
                    }
                },
                { error ->
                    Log.e(TAG, "Cannot get data from $URL: ")
                    button.visibility = View.VISIBLE
                    Toast.makeText(context, context.getString(R.string.something_went_wrong), Toast.LENGTH_LONG).show()
                })
            RequestManager.getInstance(context).addToRequestQueue(request)
        }

        /**
         * [maybeParseData]
         * @param jsonArray
         * @return An array list with all the dogs info ready to be used
         */
        @Nullable
         fun maybeParseData(jsonArray: JSONArray): ArrayList<Dog>? {
            val data: ArrayList<Dog> =  ArrayList()
            if (jsonArray.length() == 0) {
                return null
            }
            for (i in 0 until jsonArray.length()) {
                val jsonObject: JSONObject = jsonArray.getJSONObject(i)
                val petName: String = jsonObject.getString(NAME)
                val petDescription: String = jsonObject.getString(DESCRIPTION)
                val petAge: Int = jsonObject.getInt(AGE)
                val imageURL: String = jsonObject.getString(IMAGE)
                data.add(Dog(petName, petDescription, petAge, imageURL))
            }
            return data
        }
    }
}