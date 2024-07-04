package com.glucode.about_you.about.views

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import com.glucode.about_you.R
import com.glucode.about_you.databinding.ViewProfileCardBinding

class ProfileCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {
    private val binding: ViewProfileCardBinding =
        ViewProfileCardBinding.inflate(LayoutInflater.from(context), this)

    var name: String?  = null
        set(value){
                field = value
        binding.name.text = value
        }

    var role: String? = null
        set(value) {
            field = value
            binding.role.text = value
        }
    var years: String? = null
        set(value) {
            field = value
            binding.actionYears.text= value
        }
    var coffees: String? = null
        set(value) {
            field = value
            binding.actionCoffees.text = value
        }
    var bugs: String? = null
        set(value) {
            field = value
            binding.actionBugs.text = value
        }


    var profileCardClick: OnClickListener? = null
        set(value) {
            field = value
            binding.profileCard.setOnClickListener(profileCardClick)
        }

    var profileImageUri: Uri? = null
        set(value) {
            field = value
            binding.profileCard.setImageURI(profileImageUri)
        }

    init {
        radius = resources.getDimension(R.dimen.corner_radius_normal)
        elevation = resources.getDimension(R.dimen.elevation_normal)
    //    setCardBackgroundColor(ContextCompat.getColor(context, R.color.black))
    }



}









