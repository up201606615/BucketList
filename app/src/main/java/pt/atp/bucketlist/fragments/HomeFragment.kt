package pt.atp.bucketlist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pt.atp.bucketlist.model.FeedAdapter
import pt.atp.bucketlist.Places
import pt.atp.bucketlist.R
import pt.atp.bucketlist.model.StoryAdapter

class HomeFragment: Fragment(R.layout.fragment_home) {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val rootView: View = inflater.inflate(R.layout.fragment_home,container,false)
        val stories: RecyclerView = rootView.findViewById(R.id.rv_stories)
        val feed: RecyclerView = rootView.findViewById(R.id.rv_feed)

        stories.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = StoryAdapter(Places.placesToVisit)
        }

        feed.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = FeedAdapter(Places.placesToVisit)
        }

        return rootView
    }
}