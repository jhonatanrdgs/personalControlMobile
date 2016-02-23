package br.com.jhonatan.personalcontrolmobile.assincrono;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import br.com.jhonatan.personalcontrolmobile.R;
import br.com.jhonatan.personalcontrolmobile.dto.RelatorioResumoDTO;

/**
 * Created by jhonatan.rodrigues on 23/02/2016.
 */
public class ResultadoTextView implements Resultado {

    private RelatorioResumoDTO dto;//TODO tipagem

    public ResultadoTextView(RelatorioResumoDTO dto) {
        this.dto = dto;
    }

    @Override
    public void executar(View componente, Activity activity) {
        TextView rendimentos = (TextView) activity.findViewById(R.id.rendimentosText);
        rendimentos.setText(this.dto.getRendimentos().toString());

        TextView gastosFixos = (TextView) activity.findViewById(R.id.gastosFixosText);
        gastosFixos.setText(this.dto.getTotalGastosFixos().toString());

        TextView gastosVariaveis = (TextView) activity.findViewById(R.id.gastosVariaveisText);
        gastosVariaveis.setText(this.dto.getTotalGastosVariaveisPeriodo().toString());

        TextView gastos = (TextView) activity.findViewById(R.id.gastosText);
        gastos.setText(this.dto.getTotalGastos().toString());

        TextView rendimentosGastos = (TextView) activity.findViewById(R.id.rendimentosGastosText);
        rendimentosGastos.setText(this.dto.getSobra().toString());

        TextView percentualComprometido = (TextView) activity.findViewById(R.id.percentualComprometidoText);
        percentualComprometido.setText(this.dto.getPercentualComprometido().toString() + " %");


    }
}
