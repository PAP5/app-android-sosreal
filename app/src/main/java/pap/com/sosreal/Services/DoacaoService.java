package pap.com.sosreal.Services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pap.com.sosreal.BO.Doacao;
import pap.com.sosreal.BO.PessoaFisica;
import pap.com.sosreal.BO.PessoaJuridica;

public class DoacaoService {
    private Doacao doacao = new Doacao();
    private static DoacaoService instance = null;

    private static String URL = "http://pap5.ga/ws/doacao";

    public List<Doacao> getAll() {
        List<Doacao> doacoes = new ArrayList<>();
        HttpURLConnection urlConnection = null;

        try {
            java.net.URL url = new URL(URL);
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            Scanner s = new Scanner(in);
            String conteudo = s.useDelimiter("\\A").next();

            Gson gson = new Gson();
            doacoes = gson.fromJson(conteudo, new TypeToken<List<Doacao>>() {
            }.getType());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            urlConnection.disconnect();
        }
        return doacoes;
    }

    public List<Doacao> getByIdUsuario(int id) {
        List<Doacao> doacoes = new ArrayList<>();
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(URL + "/usuario/" + id);
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            Scanner s = new Scanner(in);
            String conteudo = s.useDelimiter("\\A").next();

            Gson gson = new Gson();
            doacoes = gson.fromJson(conteudo, new TypeToken<List<Doacao>>() {
            }.getType());

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            urlConnection.disconnect();
        }
        return doacoes;
    }

    public static DoacaoService getInstance() {
        if (instance == null) {
            instance = new DoacaoService();
        }
        return instance;
    }

    public void post(Doacao doacao) {
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(URL);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");

            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
            Writer w = new BufferedWriter(new OutputStreamWriter(out));

            Gson gson = new Gson();
            String json = gson.toJson(doacao);

            w.write(json);
            w.close();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            Scanner s = new Scanner(in);
            String conteudo = s.nextLine();
            in.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            urlConnection.disconnect();
        }
    }
}

