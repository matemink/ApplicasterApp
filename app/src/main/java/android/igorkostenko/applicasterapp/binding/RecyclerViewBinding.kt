package android.igorkostenko.applicasterapp.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

@BindingAdapter("adapter")
fun bindAdapter(view: RecyclerView, baseAdapter: RecyclerView.Adapter<*>) {
    view.adapter = baseAdapter
}

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String) {
    if (url.isEmpty()) return
    Picasso.get().load(url)
        .fit()
        .centerCrop()
        .into(this)
}
