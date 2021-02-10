package pt.atp.bucketlist.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_feed.view.*
import pt.atp.bucketlist.MainActivity
import pt.atp.bucketlist.R

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
        holder.userImage.setImageResource(R.drawable.amsterdam)
        holder.userName.text = feed.place
        holder.image.setImageResource(R.drawable.amsterdam)
        /*Picasso.with(MainActivity())
            .load(feed.picture)
            .error(R.drawable.error)
            .into(holder.image)
        Picasso.with(MainActivity())
            .load(feed.picture)
            .error(R.drawable.error)
            .into(holder.userImage)*/

    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userImage = itemView.iv_user_image!!
        val userName = itemView.tv_user_name!!
        val image = itemView.iv_image!!
    }
}