package br.usjt.appcadastro.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import br.usjt.appcadastro.BuildConfig;
import br.usjt.appcadastro.R;
import br.usjt.appcadastro.model.Contato;
import br.usjt.appcadastro.model.ContatoViewModel;
import br.usjt.appcadastro.model.Usuario;
import br.usjt.appcadastro.model.UsuarioViewModel;

import static android.app.Activity.RESULT_OK;


public class ContatoFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String CONTATO_FRAGMENT_TAG = "contato_fragment";
    private EditText editTextNomeContato;
    private EditText editTextEmailContato;
    private EditText editTextTelefoneContato;
    private Contato contatoCorrente;
    private Button buttonSalvarContato;
    private ContatoViewModel contatoViewModel;
    private ImageView imageViewFoto;
    private TextView textViewLinkFoto;


    private String mParam1;
    private Contato mParam2;

    public ContatoFragment() {
    }

    public static ContatoFragment newInstance(String param1, Contato contato) {
        ContatoFragment fragment = new ContatoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putSerializable(ARG_PARAM2, contato);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = (Contato) getArguments().getSerializable(ARG_PARAM2);
        }
        contatoViewModel = new ViewModelProvider(this).get(ContatoViewModel.class);
        contatoViewModel.getSalvoSucesso().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable final Boolean sucesso) {
                String mensagem = "Contato falhou ao salvar!";
                if(sucesso){
                    mensagem = "Contato salvo com sucesso!";
                }
                Toast.makeText(getActivity(),mensagem,Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contato, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        Hawk.init(getActivity()).build();

        contatoCorrente = new Contato();

        editTextNomeContato = getView().findViewById(R.id.nomeContatoEditText);
        editTextEmailContato = getView().findViewById(R.id.emailContatoEditText);
        editTextTelefoneContato = getView().findViewById(R.id.telefoneContatoEditText);
        buttonSalvarContato = getView().findViewById(R.id.enviarContatoButton);
        imageViewFoto = getView().findViewById(R.id.imageViewFoto);
        textViewLinkFoto = getView().findViewById(R.id.textViewLinkFoto);

        textViewLinkFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tirarFoto();
            }
        });

        buttonSalvarContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });


        if(mParam2 != null){
            contatoCorrente = mParam2;
            editTextNomeContato.setText(contatoCorrente.getNome());
            editTextEmailContato.setText(contatoCorrente.getEmail());
            editTextTelefoneContato.setText(contatoCorrente.getTelefone());
        }

    }

    public void salvar(){
        contatoCorrente.setNome(editTextNomeContato.getText().toString());
        contatoCorrente.setEmail(editTextEmailContato.getText().toString());
        contatoCorrente.setTelefone(editTextTelefoneContato.getText().toString());
        if(mParam2 == null) {
            contatoViewModel.salvarContato(contatoCorrente);
        }else{
            contatoViewModel.alterarContato(contatoCorrente);
        }
        limparCampos();
    }

    private void limparCampos(){
        editTextNomeContato.setText("");;
        editTextEmailContato.setText("");
        editTextTelefoneContato.setText("");
    }

    private void tirarFoto(){
//        dispatchTakePictureIntent();
        dispatchTakePictureIntentFile();
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = (Bitmap) extras.get("data");
//            imageViewFoto.setImageBitmap(imageBitmap);
//        }
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            galleryAddPic();
            setPic();
        }

    }

    // a partir daqui código para gravar a foto local no file storage

    String currentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }


    static final int REQUEST_TAKE_PHOTO = 1;

    private void dispatchTakePictureIntentFile() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(Objects.requireNonNull(
                        getActivity().getApplicationContext()),
                        BuildConfig.APPLICATION_ID + ".provider", photoFile);


                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }


    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        getActivity().sendBroadcast(mediaScanIntent);
    }

    private void setPic() {
        // Get the dimensions of the View
        int targetW = imageViewFoto.getWidth();
        int targetH = imageViewFoto.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        imageViewFoto.setImageBitmap(bitmap);
    }

}