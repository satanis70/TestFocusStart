package ermilov.focusstarttestovoe.recyclerAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.testfocusstart.R
import ermilov.focusstarttestovoe.model.Valute

class Adapter(var listValute: ArrayList<Valute>, val context: Context) : RecyclerView.Adapter<Adapter.MainHolder>() {

    inner class MainHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val valuteName_textView = itemView.findViewById<TextView>(R.id.valuteName_textView)
        private val valuteValue_textView = itemView.findViewById<TextView>(R.id.valuteValue_textView)
        private val sumRuble_editText = itemView.findViewById<TextView>(R.id.sumRuble_editText)

        fun bind(valuteName: String, valuteValue: Double){
            valuteName_textView.text = valuteName
            valuteValue_textView.text = valuteValue.toString()
            sumRuble_editText.setOnEditorActionListener { textView, i, keyEvent ->
                val edittextIn = sumRuble_editText.text
                if (edittextIn.isEmpty()){
                    Toast.makeText(context, "Введите сумму!", Toast.LENGTH_SHORT).show()
                } else {
                    val summ = textView.text.toString().toDouble()*listValute[position].Value
                    Toast.makeText(context, summ.toString(), Toast.LENGTH_SHORT).show()
                }
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_item,
            parent,
            false
        )
        return MainHolder(view)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(listValute.get(position).Name, listValute.get(position).Value)
    }

    override fun getItemCount(): Int {
        return listValute.size
    }

}