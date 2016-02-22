package br.com.jhonatan.personalcontrolmobile.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import br.com.jhonatan.personalcontrolmobile.R;
import br.com.jhonatan.personalcontrolmobile.dto.Despesa;

public class StableArrayAdapter extends ArrayAdapter<Despesa> {

    List<Despesa> despesas;
    private Activity activity;

    public StableArrayAdapter(Activity activity, int textViewResourceId, List<Despesa> objects) {
        super(activity, textViewResourceId, objects);
        this.activity = activity;
        despesas = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(R.layout.listview_despesa,parent,false);
            // inflate custom layout called row
            holder = new ViewHolder();
            holder.tv =(TextView) convertView.findViewById(R.id.firstLine);
            holder.tv2 =(TextView) convertView.findViewById(R.id.secondLine);
            // initialize textview
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }
        Despesa despesa = despesas.get(position);//TODO deixar genérico
        holder.tv.setText(despesa.getDescricao());
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt-BR"));
        holder.tv2.setText("R$ " + despesa.getValorTotal() + " - " + df.format(despesa.getData()));

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }


    static class ViewHolder {
        TextView tv;
        TextView tv2;
    }
}

