package tiniakovdev.com.view.rv_viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tiniakovdev.com.R
import tiniakovdev.com.domain.Film
import tiniakovdev.com.view.customviews.RatingDonutView


class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title: TextView = itemView.findViewById(R.id.title)
    private val poster: ImageView = itemView.findViewById(R.id.poster)
    private val description: TextView = itemView.findViewById(R.id.description)
    private val ratingDonut: RatingDonutView = itemView.findViewById(R.id.rating_donut)


    fun bind(film: Film) {
        title.text = film.title
        Glide.with(itemView)
            .load(film.poster)
            .centerCrop()
            .into(poster)
        description.text = film.description

        ratingDonut.setProgress((film.rating * 10).toInt())
    }
}