package us.ait.shoppinglist

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.activity_details_page.*
import kotlinx.android.synthetic.main.activity_details_page.view.*
import kotlinx.android.synthetic.main.activity_details_page.view.tvDescription
import kotlinx.android.synthetic.main.activity_details_page.view.tvPrice
import kotlinx.android.synthetic.main.new_item_list.view.*
import kotlinx.android.synthetic.main.new_item_list.view.spCategory
import kotlinx.android.synthetic.main.shopping_item_row.view.*
import org.w3c.dom.Text
import us.ait.shoppinglist.data.ShoppingItem

class DetailsPage : DialogFragment() {

    private lateinit var tvName: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvPrice: TextView


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle(getString(R.string.mainTitle))

        val rootView = requireActivity().layoutInflater.inflate(
            R.layout.activity_details_page, null
        )

        tvName = rootView.tvName
        tvDescription = rootView.tvDescription
        tvPrice = rootView.tvPrice


        val item: ShoppingItem = (arguments?.getSerializable(ShoppingActivity.KEY_ITEM) as ShoppingItem)
        tvName.setText(getString(R.string.detName, item.name))
        tvDescription.setText(getString(R.string.detDesc,item.description))
        tvPrice.setText(getString(R.string.detPrice, item.price.toString()))


        builder.setPositiveButton("OK") { dialog, which ->
            // empty
        }

        builder.setView(rootView)

        return builder.create()
    }
}