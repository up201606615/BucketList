package pt.atp.bucketlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pt.atp.bucketlist.model.Place
import kotlinx.android.synthetic.main.item_feed.view.*

class FeedAdapter internal constructor(private val places: List<Place>) : RecyclerView.Adapter<FeedAdapter.MainViewHolder?>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MainViewHolder(inflater.inflate(R.layout.item_feed, parent, false))
    }

    override fun getItemCount(): Int {
        return places.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val feed = places[position]
        holder.userImage.setImageResource(feed.picture)
        holder.userName.text = feed.name
        holder.image.setImageResource(feed.picture)

    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userImage = itemView.iv_user_image!!
        val userName = itemView.tv_user_name!!
        val image = itemView.iv_image!!
    }
}