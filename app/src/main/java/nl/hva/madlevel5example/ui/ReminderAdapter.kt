package nl.hva.madlevel5example.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel5example.R
import com.example.madlevel5example.databinding.ItemReminderBinding
import nl.hva.madlevel5example.model.Reminder

class ReminderAdapter(private val reminders: List<Reminder>) :
    RecyclerView.Adapter<ReminderAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemReminderBinding.bind(itemView)

        fun databind(reminder: Reminder) {
            binding.tvReminder.text = reminder.reminderText
        }
    }

    /**
     * Creates and returns a ViewHolder object, inflating a standard layout called simple_list_item_1.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_reminder, parent, false)
        )
    }

    /**
     * Returns the size of the list
     */
    override fun getItemCount(): Int {
        return reminders.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(reminders[position])
    }
}