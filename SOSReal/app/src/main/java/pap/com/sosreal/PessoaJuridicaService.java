package pap.com.sosreal;


import android.util.Log;

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

public class PessoaJuridicaService {
    private PessoaJuridica pj = new PessoaJuridica();

    private static String URL = "http://pap5.ga/ws/pj";

    public List<PessoaJuridica> getAll() {
        List<PessoaJuridica> PessoaJuridicas = new ArrayList<>();
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(URL);
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            Scanner s = new Scanner(in);
            String conteudo = s.useDelimiter("\\A").next();

            Gson gson = new Gson();
            PessoaJuridicas = gson.fromJson(conteudo, new TypeToken<List<PessoaJuridica>>() {
            }.getType());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            urlConnection.disconnect();
        }
        return PessoaJuridicas;
    }

    public PessoaJuridica getById(int id) {
        PessoaJuridica pj = new PessoaJuridica();
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(URL + "/" + id);
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            Scanner s = new Scanner(in);
            String conteudo = s.useDelimiter("\\A").next();

            Gson gson = new Gson();
            pj = gson.fromJson(conteudo, PessoaJuridica.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            urlConnection.disconnect();
        }
        return pj;
    }

    public void post(Object PessoaJuridica) {
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
            String json = gson.toJson(PessoaJuridica);
            Log.d("json",json);
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

    public void delete(String id) {
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(URL + "/" + id);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("DELETE");

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            urlConnection.disconnect();
        }
    }
}

