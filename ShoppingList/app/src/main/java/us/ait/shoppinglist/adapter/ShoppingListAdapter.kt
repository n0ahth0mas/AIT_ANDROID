package us.ait.shoppinglist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.shopping_item_row.view.*
import us.ait.shoppinglist.R
import us.ait.shoppinglist.ShoppingActivity
import us.ait.shoppinglist.data.AppDatabase
import us.ait.shoppinglist.data.ShoppingItem
import us.ait.shoppinglist.touch.ShoppingListTouchHelperCallback
import java.util.*

class ShoppingListAdapter : RecyclerView.Adapter<ShoppingListAdapter.ViewHolder>, ShoppingListTouchHelperCallback {

    var shoppingList = mutableListOf<ShoppingItem>()
    var context : Context


    constructor(context: Context, listShoppingItems: List<ShoppingItem>){
        this.context = context

        shoppingList.addAll(listShoppingItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val shoppingItemRow = LayoutInflater.from(context).inflate(
            R.layout.shopping_item_row, parent, false
        )

        return ViewHolder(shoppingItemRow)
    }

    override fun getItemCount(): Int {
        return shoppingList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var shoppingItem = shoppingList.get(holder.adapterPosition)

        holder.cbItem.isChecked = shoppingItem.status
        holder.cbItem.text = shoppingItem.name
        holder.tvDesctiption.text = shoppingItem.description
        holder.tvPrice.text = shoppingItem.price.toString()

        if(shoppingItem.category ==0 ){
            holder.spCategory.setImageResource(R.drawable.grocery)
        } else if (shoppingItem.category == 2 ){
            holder.spCategory.setImageResource(R.drawable.laundry)
        } else{
            holder.spCategory.setImageResource(R.drawable.toiletries)
        }


        holder.btnDelete.setOnClickListener{
           deleteShoppingItem(holder.adapterPosition)
        }

        holder.cbItem.setOnClickListener(){
            shoppingItem.status = holder.cbItem.isChecked
            updateShoppingItem(shoppingItem)
        }

        holder.btnEdit.setOnClickListener{
            (context as ShoppingActivity).showEditShoppingItemDialog(shoppingItem, holder.adapterPosition)
        }
    }

    fun updateShoppingItem(shoppingItem: ShoppingItem){
        Thread{
            AppDatabase.getInstance(context).shoppingItemDao().updateItem(shoppingItem)
        }.start()
    }

    fun updateShoppingItemOnPosition(shoppingItem: ShoppingItem, index: Int){
        shoppingList.set(index,shoppingItem)
        notifyItemChanged(index)
    }

    fun deleteShoppingItem(index: Int){
        Thread{
            AppDatabase.getInstance(context).shoppingItemDao().deleteItem(shoppingList[index])
            (context as ShoppingActivity).runOnUiThread {
                shoppingList.removeAt(index)
                notifyItemRemoved(index)
            }
        }.start()
    }

    fun deleteAllShoppingItems(){
        Thread{
            AppDatabase.getInstance(context).shoppingItemDao().deleteAllItems()

            (context as ShoppingActivity).runOnUiThread {
                shoppingList.clear()
                notifyDataSetChanged()
            }
        }.start()
    }

    fun addShoppingItems(shoppingItem: ShoppingItem){
        shoppingList.add(shoppingItem)
        notifyItemInserted(shoppingList.lastIndex)
    }

    override fun onDismissed(position: Int) {
        deleteShoppingItem(position)
    }

    override fun onItemMoved(fromPosition: Int, toPosition: Int) {
        Collections.swap(shoppingList, fromPosition, toPosition)
        notifyItemMoved(fromPosition,toPosition)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cbItem = itemView.cbItem
        val spCategory = itemView.itemImg
        val tvDesctiption = itemView.tvDescription
        val tvPrice = itemView.tvPrice

        val btnDelete = itemView.btnDelete
        val btnEdit = itemView.btnEdit
    }
}