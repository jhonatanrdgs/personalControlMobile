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
import br.com.jhonatan.personalcontrolmobile.assincrono.LinhaListView;

public class StableArrayAdapter extends ArrayAdapter<LinhaListView> {

    List<LinhaListView> listagem;
    private Activity activity;
    private int listView;

    public StableArrayAdapter(Activity activity, int textViewResourceId, int listView,  List<LinhaListView> objects) {
        super(activity, textViewResourceId, objects);
        this.activity = activity;
        this.listagem = objects;
        this.listView = listView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(listView, parent, false);
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
        LinhaListView linha = listagem.get(position);
        holder.tv.setText(linha.getPrimeiraLinha());
        holder.tv2.setText(linha.getSegundaLinha());

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

