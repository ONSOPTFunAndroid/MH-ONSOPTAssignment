package com.example.mh_onsopt_assignment.feature.portfolio

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mh_onsopt_assignment.R
import com.example.mh_onsopt_assignment.databinding.RvItemPortfolioBinding
import com.example.mh_onsopt_assignment.vo.PortfolioListData

class PortfolioAdapter(private val context: Context) : RecyclerView.Adapter<PortfolioViewHolder>() {
    var data = mutableListOf<PortfolioListData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PortfolioViewHolder {
        val binding:RvItemPortfolioBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.rv_item_portfolio,
            parent,
            false
        )

        return PortfolioViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PortfolioViewHolder, position: Int) {
        data[position].let {
            holder.bind(it)
        }

        holder.itemView.setOnClickListener {
            val detailIntent = Intent(context, PortfolioDetailActivity::class.java)


            detailIntent.putExtra("image",data[position].image)
            detailIntent.putExtra("title",data[position].title)
            detailIntent.putExtra("sub",data[position].subTitle)
            detailIntent.putExtra("date",data[position].date)
            detailIntent.putExtra("contents",data[position].contents)

            context.startActivity(detailIntent)
        }
    }

    override fun getItemCount(): Int = data.size
}

class PortfolioViewHolder(val binding: RvItemPortfolioBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(item: PortfolioListData){
        binding.item = item
    }
}