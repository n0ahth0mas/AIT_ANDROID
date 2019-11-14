package us.ait.shoppinglist

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.new_item_list.view.*
import us.ait.shoppinglist.data.ShoppingItem
import java.lang.RuntimeException

class ShoppingDialog : DialogFragment() {

    interface ShoppingItemHandler {
        fun shoppingItemCreated(item: ShoppingItem)
        fun shoppingItemUpdated(item: ShoppingItem)
    }

    private lateinit var shoppingItemHandler: ShoppingItemHandler

    override fun onAttach(context: Context){
        super.onAttach(context)

        if (context is ShoppingItemHandler) {
            shoppingItemHandler = context
        } else {
            throw RuntimeException(
                "The activity does not implement the ShoppingItemHandlerInterface")
        }
    }

    private lateinit var etCategory: Spinner
    private lateinit var etName: EditText
    private lateinit var etDescription: EditText
    private lateinit var etPrice: EditText

    var isEditMode = false

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle("New Shopping List Item")

        val rootView = requireActivity().layoutInflater.inflate(
            R.layout.new_item_list, null
        )
        etCategory = rootView.etCategory
        etName = rootView.etName
        etDescription = rootView.etDescription
        etPrice = rootView.etPrice

        builder.setView(rootView)

        isEditMode = ((arguments != null) && arguments!!.containsKey("KEY_ITEM"))

        if (isEditMode) {
            var shoppingItem: ShoppingItem =
                (arguments?.getSerializable("KEY_ITEM") as ShoppingItem)
            etCategory.setSelection(shoppingItem.category)//whatever the spinner is at)
            etName.setText(shoppingItem.name)
            etDescription.setText(shoppingItem.description)
            etPrice.setText((shoppingItem.price).toInt())
        }

        builder.setPositiveButton("OK") { dialog, witch ->
            // empty
        }

        return builder.create()
    }

    override fun onResume() {
        super.onResume()

        val positiveButton = (dialog as AlertDialog).getButton(Dialog.BUTTON_POSITIVE)
        positiveButton.setOnClickListener {
            if (etName.text.isNotEmpty()) {
                if (isEditMode) {
                    handleShoppingItemEdit()
                } else {
                    handleShoppingItemCreate()
                }

                dialog!!.dismiss()
            } else {
                etName.error = "This field cannot be empty"
            }
        }
    }


    private fun handleShoppingItemCreate() {
        shoppingItemHandler.shoppingItemCreated(
            ShoppingItem(
                null,
                etCategory.selectedItemPosition,
                etName.text.toString(),
                etDescription.text.toString(),
                etPrice.text.toString().toInt(),
                false
            )
        )
    }

    private fun handleShoppingItemEdit() {
        val shoppingItemToEdit = arguments?.getSerializable(
            "KEY_ITEM"
        ) as ShoppingItem
        shoppingItemToEdit.name = etName.text.toString()
        shoppingItemToEdit.category = etCategory.selectedItemPosition
        shoppingItemToEdit.description = etDescription.text.toString()
        shoppingItemToEdit.price = etPrice.text.toString().toInt()

        shoppingItemHandler.shoppingItemUpdated(shoppingItemToEdit)
    }
}