package com.grupotransmares.sagecofuve.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.grupotransmares.sagecofuve.home.agenda.AgendaFragment
import com.grupotransmares.sagecofuve.home.chatbot.ChatbotFragment

class TabsAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {

    companion object {
        const val ITEM_COUNT = 4
    }

    private val fragments = arrayOf(AgendaFragment(), Fragment(), Fragment(), ChatbotFragment())

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return ITEM_COUNT
    }
}