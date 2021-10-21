package com.exemplo.seletordatahora;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText editTextData;
    private EditText editTextHora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Associa os componentes da interface aos componentes da classe
        editTextData = findViewById(R.id.editTextData);
        editTextHora = findViewById(R.id.editTextHora);
    }

    /**
     * Evento do botão escolher data
     * @param v
     */
    public void onClickBotaoEscolharData(View v){
        // Abre o fragmento de esolha de data
        DialogFragment fragmentoEscolheData = new FragmentoSeletorData();
        fragmentoEscolheData.show(getSupportFragmentManager(), "datePicker");
    }

    /**
     * Evento do botão escolher data
     * @param v
     */
    public void onClickBotaoEscolharHora(View v){
        // Abre o fragmento de escolha de hora
        DialogFragment fragmentoEscolheTempo = new FragmentoSeletorTempo();
        fragmentoEscolheTempo.show(getSupportFragmentManager(), "timePicker");
    }

    /**
     * Evento do botão fechar
     * @param v
     */
    public void onClickBotaoFechar(View v){

        System.exit(0);
    }

    /**
     *  Classe interna para manipular o seletor de data.
     */
    public static class FragmentoSeletorData extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use a data atual como a data padrão no selecionador
            final Calendar c = Calendar.getInstance();
            int ano = c.get(Calendar.YEAR);
            int mes = c.get(Calendar.MONTH);
            int dia = c.get(Calendar.DAY_OF_MONTH);

            // Crie uma nova instância de FragmentoSeletorData e retorne-a
            return new DatePickerDialog(getActivity(), this, ano, mes, dia);
        }

        /**
         * Método que atualiza a caixa de texto com os dados da seleção.
         *
         * @param view
         * @param ano
         * @param mes
         * @param dia
         */
        public void onDateSet(DatePicker view, int ano, int mes, int dia) {
            // Faça algo com o tempo escolhido pelo usuário
            // Recupera a caixa de texto a ser alterada
            EditText editTextData1 =  getActivity().findViewById(R.id.editTextData);
            // Seta a caixa de texto com os dados da seleção
            editTextData1.setText(dia + "/" + mes + "/" + ano);
        }
    }

    /**
     *  Classe interna para manipular o seletor de hora.
     */
    public static class FragmentoSeletorTempo extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use a hora atual como os valores padrão para o seletor
            final Calendar c = Calendar.getInstance();
            int hora = c.get(Calendar.HOUR_OF_DAY);
            int minuto = c.get(Calendar.MINUTE);

            // Crie uma nova instância de FragmentoSeletorTempo e retorne-a
            return new TimePickerDialog(getActivity(), this, hora, minuto,
                    DateFormat.is24HourFormat(getActivity()));
        }

        /**
         * Método que atualiza a caixa de texto com os dados da seleção.
         *
         * @param view
         * @param hora
         * @param minuto
         */
        public void onTimeSet(TimePicker view, int hora, int minuto) {
            // Faça algo com o tempo escolhido pelo usuário
            // Recupera a caixa de texto a ser alterada
            EditText editTextHora1 =  getActivity().findViewById(R.id.editTextHora);
            // Seta a caixa de texto com os dados da seleção
            editTextHora1.setText(hora + ":" + minuto);
        }
    }
}