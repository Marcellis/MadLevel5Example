package nl.hva.madlevel5example.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel5example.databinding.FragmentRemindersBinding
import nl.hva.madlevel5example.model.Reminder
import nl.hva.madlevel5example.vm.ReminderViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class RemindersFragment : Fragment() {

    private var _binding: FragmentRemindersBinding? = null
    private val binding get() = _binding!!

//    private lateinit var reminderRepository: ReminderRepository

    private val reminders = arrayListOf<Reminder>()
    private val reminderAdapter = ReminderAdapter(reminders)

    private val viewModel: ReminderViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRemindersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        observeAddReminderResult()

//        reminderRepository = ReminderRepository(requireContext())
//        getRemindersFromDatabase()


    }

    private fun initViews() {
        // Initialize the recycler view with a linear layout manager, adapter
        binding.rvReminders.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvReminders.adapter = reminderAdapter
        binding.rvReminders.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        createItemTouchHelper().attachToRecyclerView(binding.rvReminders)
    }

    private fun observeAddReminderResult() {
        viewModel.reminders.observe(viewLifecycleOwner, Observer { reminders ->
            this@RemindersFragment.reminders.clear()
            this@RemindersFragment.reminders.addAll(reminders)
            reminderAdapter.notifyDataSetChanged()
        })
    }


//    private fun observeAddReminderResult() {
//        setFragmentResultListener(REQ_REMINDER_KEY) { key, bundle ->
//            bundle.getString(BUNDLE_REMINDER_KEY)?.let {
//                val reminder = Reminder(it)
//
////                reminders.add(reminder)
////                reminderAdapter.notifyDataSetChanged()
////                reminderRepository.insertReminder(reminder)
////                getRemindersFromDatabase()
//                CoroutineScope(Dispatchers.Main).launch {
//                    withContext(Dispatchers.IO) {
//                        reminderRepository.insertReminder(reminder)
//                    }
//                    getRemindersFromDatabase()
//                }
//
//            } ?: Log.e("ReminderFragment", "Request triggered, but empty reminder text!")
//
//        }
//    }

    /**
     * Create a touch helper to recognize when a user swipes an item from a recycler view.
     * An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder,
     * and uses callbacks to signal when a user is performing these actions.
     */
    private fun createItemTouchHelper(): ItemTouchHelper {

        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
        // Use ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) to also enable right swipe.
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
//                reminders.removeAt(position)
//                reminderAdapter.notifyDataSetChanged()
                val reminderToDelete = reminders[position]
//                reminderRepository.deleteReminder(reminderToDelete)
//                getRemindersFromDatabase()
//                CoroutineScope(Dispatchers.Main).launch {
//                    withContext(Dispatchers.IO) {
//                        reminderRepository.deleteReminder(reminderToDelete)
//                    }
//                    getRemindersFromDatabase()
//                }
                viewModel.deleteReminder(reminderToDelete)


            }
        }
        return ItemTouchHelper(callback)
    }

//    private fun getRemindersFromDatabase() {
////        val reminders = reminderRepository.getAllReminders()
////        this@RemindersFragment.reminders.clear()
////        this@RemindersFragment.reminders.addAll(reminders)
////        reminderAdapter.notifyDataSetChanged()
//        CoroutineScope(Dispatchers.Main).launch {
//            val reminders = withContext(Dispatchers.IO) {
//                reminderRepository.getAllReminders()
//            }
//            this@RemindersFragment.reminders.clear()
//            this@RemindersFragment.reminders.addAll(reminders)
//            reminderAdapter.notifyDataSetChanged()
//        }
//
//    }

}