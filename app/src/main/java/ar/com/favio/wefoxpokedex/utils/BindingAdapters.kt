package ar.com.favio.wefoxpokedex.utils


import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import ar.com.favio.wefoxpokedex.R
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
    Glide.with(context).load(url).placeholder(R.drawable.ic_pokemon_loader).error(R.drawable.ic_not_found).into(this)
}


@set:BindingAdapter("visibleOrGone")
var View.visibleOrGone
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }

@set:BindingAdapter("visible")
var View.visible
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.INVISIBLE
    }

@set:BindingAdapter("invisible")
var View.invisible
    get() = visibility == View.INVISIBLE
    set(value) {
        visibility = if (value) View.INVISIBLE else View.VISIBLE
    }

@set:BindingAdapter("gone")
var View.gone
    get() = visibility == View.GONE
    set(value) {
        visibility = if (value) View.GONE else View.VISIBLE
    }