package com.mwsmith3.spacex.falcon9.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mwsmith3.spacex.R
import com.mwsmith3.spacex.databinding.LaunchBinding
import com.mwsmith3.spacex.falcon9.domain.model.Falcon9Launch
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

class Falcon9Adapter(
    private val launches: List<Falcon9Launch>
) : RecyclerView.Adapter<Falcon9Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LaunchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val launch = launches[position]
        holder.bind(launch)
    }

    override fun getItemCount() = launches.size

    class ViewHolder(private val binding: LaunchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(launch: Falcon9Launch) {
            with(binding) {
                title.text = launch.name
                date.text = getDateString(root.context, launch.launchDate)
                successImg.setImageResource(getStatusImg(launch.success))
                launch.imageUrl?.let {
                    Glide.with(root.context).load(launch.imageUrl).into(patch)
                } ?: run { patch.isVisible = false }
            }
        }

        @DrawableRes
        private fun getStatusImg(status: Boolean): Int {
            return if (status) {
                R.drawable.ic_baseline_check_24
            } else {
                R.drawable.ic_baseline_clear_24
            }
        }

        private fun getDateString(context: Context, date: DateTime): String {
            val formattedDateTime = DATE_TIME_FORMATTER.print(date)
            return context.getString(R.string.launch_date_prefix, formattedDateTime)
        }
    }

    companion object {
        val DATE_TIME_FORMATTER: DateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy")
    }
}
