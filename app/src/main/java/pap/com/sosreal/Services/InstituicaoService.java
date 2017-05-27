package pap.com.sosreal.Services;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pap.com.sosreal.BO.Instituicao;
import pap.com.sosreal.BO.PessoaFisica;
import pap.com.sosreal.BO.PessoaJuridica;

public class InstituicaoService {
    private Instituicao ins = new Instituicao();
    private static InstituicaoService instance = null;

    private static String URL = "http://pap5.ga/ws/instituicao";

    public List<Instituicao> getAll() {
        List<Instituicao> instituicoes = new ArrayList<>();
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(URL);
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            Scanner s = new Scanner(in);
            String conteudo = s.useDelimiter("\\A").next();

            Gson gson = new Gson();
            instituicoes = gson.fromJson(conteudo, new TypeToken<List<Instituicao>>() {
            }.getType());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            urlConnection.disconnect();
        }
        return instituicoes;
    }

    public Object getByIdUsuario(int id) {
        PessoaFisica pf = new PessoaFisica();
        PessoaJuridica pj = new PessoaJuridica();
        HttpURLConnection urlConnection = null;
        URL = "http://pap5.ga/ws/usuario";

        try {
            URL url = new URL(URL + "/" + id + "/perfil");
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            Scanner s = new Scanner(in);
            String conteudo = s.useDelimiter("\\A").next();

            Gson gson = new Gson();
            if (conteudo.contains("cpf")) {
                pf = gson.fromJson(conteudo, PessoaFisica.class);
            } else if (conteudo.contains("cnpj")) {
                pj = gson.fromJson(conteudo, PessoaJuridica.class);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            urlConnection.disconnect();
        }
        URL = "http://pap5.ga/ws/instituicao";

        if (pf != null)
            return pf;

        return pj;
    }

    public Instituicao getById(int id) {
        Instituicao ins = new Instituicao();
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(URL + "/" + id);
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            Scanner s = new Scanner(in);
            String conteudo = s.useDelimiter("\\A").next();

            Gson gson = new Gson();

            ins = gson.fromJson(conteudo, Instituicao.class);


        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            urlConnection.disconnect();
        }

        return ins;
    }

    public static InstituicaoService getInstance() {
        if (instance == null) {
            instance = new InstituicaoService();
        }
        return instance;
    }


}

