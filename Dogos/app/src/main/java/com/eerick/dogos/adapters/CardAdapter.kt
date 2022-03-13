package com.eerick.dogos.adapters

import android.content.Context
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.eerick.dogos.R
import com.eerick.dogos.objects.Dog
import com.facebook.drawee.view.SimpleDraweeView

class CardAdapter(var context: Context, var petsList:  ArrayList<Dog>): BaseAdapter() {
    override fun getCount(): Int {
        return petsList.size
    }

    override fun getItem(index: Int): Any {
        return petsList[index]
    }

    override fun getItemId(index: Int): Long {
        return index.toLong()
    }

    override fun getView(index: Int, convertView: View?, viewGroup: ViewGroup?): View {
        val dogItem: Dog = petsList[index]
        val suffix: String = if (dogItem.age > 1) "s" else ""
        val view: View = inflate(context, R.layout.card_view_item, null)
        val image: SimpleDraweeView = view.findViewById(R.id.image_view_pet_card)
        val name: TextView = view.findViewById(R.id.text_card_pet_name)
        val description: TextView = view.findViewById(R.id.text_card_pet_description)
        val age: TextView = view.findViewById(R.id.text_card_pet_age)
        name.text = dogItem.name
        description.text = dogItem.description
        age.text = context.getString(R.string.age_pets, dogItem.age, suffix)
        image.setImageURI(dogItem.uri)
        return view
    }

}