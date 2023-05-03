package com.example.listapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mymind.R
import com.mymind.core.UserMoodModel
import io.realm.kotlin.query.RealmResults

class MoodListFragmentRecyclerViewAdapter(
    private var dataBase: RealmResults<UserMoodModel>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_mood_card, parent, false)
        return ViewHolder(viewHolder)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        val moodCardData: UserMoodModel = dataBase.toList().get(position)
        val imageView = viewHolder.itemView.findViewById<ImageView>(R.id.imageViewMoodCard)
        val commentaryTextView = viewHolder.itemView.findViewById<TextView>(R.id.textViewCommentary)
        val timeTextView = viewHolder.itemView.findViewById<TextView>(R.id.textViewTime)
        val dateTextView = viewHolder.itemView.findViewById<TextView>(R.id.textViewDate)

        val currentMoodImage: Int
        when (moodCardData.mood) {
            0 -> currentMoodImage = R.drawable.cat_mood1
            1 -> currentMoodImage = R.drawable.cat_mood2
            2 -> currentMoodImage = R.drawable.cat_mood3
            3 -> currentMoodImage = R.drawable.cat_mood4
            4 -> currentMoodImage = R.drawable.cat_mood5
            else -> currentMoodImage = R.drawable.error
        }
        imageView.setImageResource(currentMoodImage)

        commentaryTextView.text = moodCardData.commentary
        timeTextView.text = "${moodCardData.hour}:${moodCardData.minute}"
        dateTextView.text = moodCardData.date
    }

    override fun getItemCount(): Int {
        return dataBase.size
    }
}