package com.example.practicafinal_victorhernandez

// TextViewAdapterSeries.kt
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TextViewAdapterSeries(private val dataList: List<String>, private val onItemClick: (String) -> Unit) :
    RecyclerView.Adapter<TextViewAdapterSeries.ViewHolder>() {

    // ViewHolder que contiene un TextView para mostrar el texto del elemento de la lista de las series
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textViewSeries)
    }

    // Crea y devuelve un nuevo ViewHolder que contiene la vista del elemento de la lista de las series
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_textviewseries, parent, false)
        return ViewHolder(view)
    }

    // Asigna los datos del elemento de la lista de las series al ViewHolder en la posición dada
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.textView.text = item
        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    // Devuelve el número total de elementos en la lista de las series
    override fun getItemCount(): Int {
        return dataList.size
    }
}