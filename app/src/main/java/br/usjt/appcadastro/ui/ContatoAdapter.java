package br.usjt.appcadastro.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.usjt.appcadastro.R;
import br.usjt.appcadastro.model.Contato;
import br.usjt.appcadastro.util.ImageUtil;

public class ContatoAdapter extends RecyclerView.Adapter<ContatoAdapter.ContatoHolder> {
    private List<Contato> results = new ArrayList<>();
    private static ItemClickListener itemClickListener;

    @NonNull
    @Override
    public ContatoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contato_item, parent, false);

        return new ContatoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContatoHolder holder, int position) {
        Contato contato = results.get(position);

        holder.textViewNome.setText(contato.getNome());
        holder.textViewEmail.setText(contato.getEmail());
        holder.textViewTelefone.setText(contato.getTelefone());
        holder.textViewTipoContato.setText(contato.getTipoContato());
        if(contato.isPrivado()){
            holder.textViewPrivado.setText("Contato Privado");
        }else{
            holder.textViewPrivado.setText("Contato Público");
        }
        if(contato.getImagem()==null || contato.getImagem().isEmpty()){
            holder.fotoCard.setImageResource(R.drawable.ic_place_holder);
        }else{
            holder.fotoCard.setImageBitmap(ImageUtil.decode(contato.getImagem()));
        }



    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public void setResults(List<Contato> results) {
        this.results = results;
        notifyDataSetChanged();
    }

    class ContatoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewNome;
        private TextView textViewEmail;
        private TextView textViewTelefone;
        private TextView textViewTipoContato;
        private TextView textViewPrivado;
        private ImageView fotoCard;


        public ContatoHolder(@NonNull View itemView) {
            super(itemView);
            textViewNome = itemView.findViewById(R.id.textViewNomeContato);
            textViewEmail = itemView.findViewById(R.id.textViewEmailContato);
            textViewTelefone = itemView.findViewById(R.id.textViewTelefoneContato);
            textViewTipoContato = itemView.findViewById(R.id.textViewTipoContato);
            textViewPrivado = itemView.findViewById(R.id.textViewPrivado);
            fotoCard = itemView.findViewById(R.id.fotoCard);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(itemClickListener != null) {
                itemClickListener.onItemClick(getAdapterPosition(), results.get(getAdapterPosition()));
            }
        }
    }
    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(int position, Contato contato);
    }
}