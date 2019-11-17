package us.ait.shoppinglist

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.ArrayAdapter
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper

import kotlinx.android.synthetic.main.activity_shopping.*
import kotlinx.android.synthetic.main.shopping_item_row.*
import us.ait.shoppinglist.adapter.ShoppingListAdapter
import us.ait.shoppinglist.data.AppDatabase
import us.ait.shoppinglist.data.ShoppingItem
import us.ait.shoppinglist.touch.ShoppingListRecyclerTouchCallback

class ShoppingActivity : AppCompatActivity(), ShoppingDialog.ShoppingItemHandler {

    companion object{
        const val KEY_ITEM = "KEY_ITEM"
        const val KEY_STARTED = "KEY_STARTED"
        const val TAG_ITEM_DIALOG = "TAG_ITEM_DIALOG"
        const val TAG_TODO_EDIT = "TAG_TODO_EDIT"
        const val KEY_ITEM_DETAILS = "KEY_ITEM_DETAILS"
        const val TAG_ITEM_DETAILS= "TAG_ITEM_DETAILS"

    }

    lateinit var shoppingListAdapter: ShoppingListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        setSupportActionBar(toolbar)

        initRecyclerView()

        fab.setOnClickListener {
            showAddItemDialog()
        }

        fabDeleteAll.setOnClickListener {
            shoppingListAdapter.deleteAllShoppingItems()
        }
    }

    /**
    fun saveWasStarted(){
        var sharedPref = PreferenceManager.getDefaultSharedPreferences( this)
        var editor = sharedPref.edit()
        editor.putBoolean((KEY_STARTED, true)
        editor.apply()
    }

    fun wasStartedBefore(): Boolean{
        var sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        return sharedPref.getBoolean(KEY_STARTED, false)
    }
    **/

    private fun initRecyclerView(){
        Thread {
            var items = AppDatabase.getInstance(this@ShoppingActivity).shoppingItemDao().getAllItems()

            runOnUiThread {
                shoppingListAdapter = ShoppingListAdapter(this, items)
                recyclerShoppingItem.adapter = shoppingListAdapter

                var itemDecorator = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

                recyclerShoppingItem.addItemDecoration(itemDecorator)

                val callback = ShoppingListRecyclerTouchCallback(shoppingListAdapter)
                val touchHelper = ItemTouchHelper(callback)
                touchHelper.attachToRecyclerView(recyclerShoppingItem)
            }
        }.start()
    }

    fun showAddItemDialog(){
        ShoppingDialog().show(supportFragmentManager, TAG_ITEM_DIALOG)
    }

    var editIndex: Int = -1

    fun showEditShoppingItemDialog(itemToEdit: ShoppingItem, idx: Int){
        editIndex = idx

        var editDialog = ShoppingDialog()

        val bundle = Bundle()
        bundle.putSerializable(KEY_ITEM, itemToEdit)

        editDialog.arguments = bundle

        editDialog.show(supportFragmentManager, TAG_ITEM_DIALOG)
    }

    fun showDetails(shoppingItem: ShoppingItem){
        var detailsDialog = DetailsPage()

        val bundle = Bundle()

        bundle.putSerializable(KEY_ITEM_DETAILS, shoppingItem)

        detailsDialog.arguments = bundle

        detailsDialog.show(supportFragmentManager, TAG_ITEM_DETAILS)
    }


    fun saveShoppingItem(shoppingItem: ShoppingItem){
        Thread{
            var newId: Long = AppDatabase.getInstance(this@ShoppingActivity).shoppingItemDao().insertItem(shoppingItem)

            shoppingItem.itemId = newId

            runOnUiThread {
               shoppingListAdapter.addShoppingItems(shoppingItem)
            }
        }.start()
    }

    override fun shoppingItemCreated(shoppingItem: ShoppingItem){
        saveShoppingItem(shoppingItem)
    }

    override fun shoppingItemUpdated(item: ShoppingItem) {
        Thread{
            AppDatabase.getInstance(this@ShoppingActivity).shoppingItemDao().updateItem(item)

            runOnUiThread {
                shoppingListAdapter.updateShoppingItemOnPosition(item, editIndex)
            }
        }.start()
    }
}

