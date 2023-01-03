package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.main_users_listview)
        listView.adapter = UsersListAdapter(this)
    }

    external fun initTable(): Int
    external fun createUsers(): Int
    external fun getUsers(): Array<User>

    companion object {
        // Used to load the 'core' library on application startup.
        init {
            System.loadLibrary("core")
        }
    }

    private inner class UsersListAdapter(context: Context) : BaseAdapter() {
        private val mContext: Context
        private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        private val users: ArrayList<User>

        init {
            this.mContext = context
            var result = initTable()
            createUsers()
            Log.d("SQL", "int result: $result")
            Log.d("path", context.getDatabasePath("test.db").absolutePath)
//            this.users = arrayListOf()
            var users = getUsers()
            for (user in users) {
                Log.d("User", user.name)
            }
            this.users = ArrayList(users.asList())
        }

        override fun getCount(): Int {
            return users.size
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItem(position: Int): Any {
            return "TEST STRING"
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            // Get view for row item
            val rowView = inflater.inflate(R.layout.user_row, parent, false)

            val nameView = rowView.findViewById<TextView>(R.id.name_textView)
            nameView.text = users[position].name
            val enabledView = rowView.findViewById<TextView>(R.id.enabled_textView)
            var unicode = if (users[position].enabled) 0x2705 else 0x274C
            var emoji = String(Character.toChars(unicode))
            enabledView.text = "Enabled: $emoji"

            return rowView
        }
    }
}