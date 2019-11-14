package us.ait.shoppinglist.data

import androidx.room.*

@Dao
interface ShoppingItemDao {

    @Query("SELECT * FROM items")
    fun getAllItems() : List<ShoppingItem>

    @Insert
    fun insertItem(shoppingItem: ShoppingItem) : Long

    @Delete
    fun deleteItem(shoppingItem: ShoppingItem)

    @Update
    fun updateItem(shoppingItem: ShoppingItem)

    @Query("DELETE FROM items")
    fun deleteAllItems()

}

