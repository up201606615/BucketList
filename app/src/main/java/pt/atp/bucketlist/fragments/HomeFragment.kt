package pt.atp.bucketlist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import pt.atp.bucketlist.R
import pt.atp.bucketlist.model.FeedAdapter
import pt.atp.bucketlist.model.Place
import pt.atp.bucketlist.model.StoryAdapter

class HomeFragment: Fragment(R.layout.fragment_home) {

    private var listener = object : StoryAdapter.OnItemClickListener{
        override fun onItemClick(story: Place) {
            //Toast.makeText(context,story.country,Toast.LENGTH_LONG).show()
            formCountryList(story.country)
        }
    }

    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val rootView: View = inflater.inflate(R.layout.fragment_home,container,false)
        val stories: RecyclerView = rootView.findViewById(R.id.rv_stories)
        val feed: RecyclerView = rootView.findViewById(R.id.rv_feed)
        val listTotal: ArrayList<Place> = ArrayList()

        db.collection("PlaceToVisit").get()
            .addOnSuccessListener { result ->
                for(document in result){
                    listTotal.add(Place(
                        document["country"].toString(),
                        document["place"].toString(),
                        document["description"].toString(),
                        document["imageUrl"].toString()
                    )
                    )
                }
                feed.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(context)
                    adapter = FeedAdapter(context, listTotal)
                }
            }
            .addOnFailureListener {
                Toast.makeText(context,"Error getting data", Toast.LENGTH_LONG).show()
            }

        val countryList: ArrayList<String> = ArrayList()
        val storiesList: ArrayList<Place> = ArrayList()
        db.collection("PlaceToVisit").document("countryList").collection("list").get()
            .addOnSuccessListener { result ->
                for (document in result){
                    countryList.add(document["country"].toString())
                    storiesList.add(Place(
                        document["country"].toString(),
                        document["place"].toString(),
                        document["description"].toString(),
                        document["imageUrl"].toString()
                    ))
                }
                stories.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    adapter = StoryAdapter(context, storiesList, listener)
                }

            }
            .addOnFailureListener {
                Toast.makeText(context,"Error getting country list",Toast.LENGTH_LONG).show()
            }

        return rootView
    }

    private fun formCountryList(country: String) {
        val list: ArrayList<Place> = ArrayList()
        db.collection("PlaceToVisit").document("stories").collection(country).get()
            .addOnSuccessListener { result ->
                for (document in result){
                    list.add(Place(
                        document["country"].toString(),
                        document["place"].toString(),
                        document["description"].toString(),
                        document["imageUrl"].toString()
                    ))
                }
                Toast.makeText(context, list.toString(), Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener {
                Toast.makeText(context,"Error forming country list",Toast.LENGTH_LONG).show()
            }

    }


}