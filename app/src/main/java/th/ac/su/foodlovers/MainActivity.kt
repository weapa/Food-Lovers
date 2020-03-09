package th.ac.su.foodlovers

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import th.ac.su.foodlovers.Utils.getJsonDataFromAsset
import th.ac.su.foodlovers.data.Food
import th.ac.su.foodlovers.data.foodAdapter

class MainActivity : AppCompatActivity() {

    var itemList:ArrayList<Food> = ArrayList<Food>()
    lateinit var arrayAdapter: ArrayAdapter<Food>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jsonFileString = getJsonDataFromAsset(applicationContext,"food_lover.json")
        val gson = Gson()
        val listItemType = object : TypeToken<ArrayList<Food>>(){}.type
//
        var foodList : ArrayList<Food> = gson.fromJson(jsonFileString,listItemType)

        itemList = foodList

        val adapter = foodAdapter(this@MainActivity,itemList)

        listView.adapter = adapter


        listView.setOnItemClickListener { parent, view, position, id ->

            var intent = Intent(this@MainActivity,DetailActivity::class.java)
            intent.putExtra("imageFile",itemList[position].imageFile)
            intent.putExtra("foodName",itemList[position].foodName)
            intent.putExtra("restaurantName",itemList[position].cafeName)
            intent.putExtra("star",itemList[position].star)
            intent.putExtra("description",itemList[position].description)



            startActivity(intent)
        }


    }

}

