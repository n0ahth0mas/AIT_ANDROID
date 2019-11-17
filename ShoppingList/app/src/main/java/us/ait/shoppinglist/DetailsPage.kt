package us.ait.shoppinglist

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.activity_details_page.view.*
import kotlinx.android.synthetic.main.new_item_list.view.*
import kotlinx.android.synthetic.main.new_item_list.view.spCategory
import us.ait.shoppinglist.data.ShoppingItem

class DetailsPage : DialogFragment() {

    private lateinit var spCategory: Spinner
    private lateinit var tvName: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvPrice: TextView


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle("View Item Details")

        val rootView = requireActivity().layoutInflater.inflate(
            R.layout.activity_details_page, null
        )

        spCategory = rootView.spCategory
        tvName = rootView.tvName
        tvDescription = rootView.tvDescription
        tvPrice = rootView.tvPrice

        builder.setView(rootView)

        var item: ShoppingItem = (arguments?.getSerializable("KEY_ITEM") as ShoppingItem)
        spCategory.setSelection(item.category)//whatever the spinner is at
        tvName.setText(item.name)
        tvDescription.setText(item.description)
        tvPrice.setText((item.price.toString()))


        return builder.create()
    }
}