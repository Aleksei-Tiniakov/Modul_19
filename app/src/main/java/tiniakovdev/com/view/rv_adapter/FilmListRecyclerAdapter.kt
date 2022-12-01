package tiniakovdev.com.view.rv_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tiniakovdev.com.R
import tiniakovdev.com.data.entity.Film
import tiniakovdev.com.view.rv_viewholders.FilmViewHolder

class FilmListRecyclerAdapter(
    private val clickListener: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val items = mutableListOf<Film>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FilmViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.film_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FilmViewHolder -> {
                holder.bind(items[position])
                holder.itemView.findViewById<View>(R.id.item_container).setOnClickListener {
                    clickListener.click(items[position])
                }
            }
        }
    }

    override fun getItemCount() = items.size

    fun addItems(list: List<Film>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun click(film: Film)
    }
}