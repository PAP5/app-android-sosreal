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

import pap.com.sosreal.BO.Usuario;

public class UsuarioService {
    private Usuario usu = new Usuario();

    private static String URL = "http://pap5.ga/ws/usuario";

    public List<Usuario> getAll() {
        List<Usuario> usuarios = new ArrayList<>();
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(URL);
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            Scanner s = new Scanner(in);
            String conteudo = s.useDelimiter("\\A").next();

            Gson gson = new Gson();
            usuarios = gson.fromJson(conteudo, new TypeToken<List<Usuario>>() {
            }.getType());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            urlConnection.disconnect();
        }
        return usuarios;
    }

    public Usuario getById(int id) {
        Usuario usu = new Usuario();
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(URL + "/" + id);
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            Scanner s = new Scanner(in);
            String conteudo = s.useDelimiter("\\A").next();

            Gson gson = new Gson();
            usu = gson.fromJson(conteudo, Usuario.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            urlConnection.disconnect();
        }
        return usu;
    }

    public Usuario getByUsuarioESenha(String usuario, String senha) {
        Usuario usu = new Usuario();
        HttpURLConnection urlConnection = null;

        URL = "http://pap5.ga/ws/login";
        try {
            URL url = new URL(URL + "/" + usuario + "/" + senha);
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            Scanner s = new Scanner(in);
            String conteudo = s.useDelimiter("\\A").next();

            Gson gson = new Gson();
            usu = gson.fromJson(conteudo, Usuario.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            urlConnection.disconnect();
        }

        URL = "http://pap5.ga/ws/usuario";
        return usu;
    }

    public boolean validarPerfilPF(int id) {
        HttpURLConnection urlConnection = null;
        boolean retorno;

        URL = "http://pap5.ga/ws/pf/usuario";
        try {
            URL url = new URL(URL + "/" + id);
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            Scanner s = new Scanner(in);
            String conteudo = s.useDelimiter("\\A").next();

            Gson gson = new Gson();
            retorno = gson.fromJson(conteudo, boolean.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            urlConnection.disconnect();
        }

        URL = "http://pap5.ga/ws/usuario";
        return retorno;
    }

    public boolean validarPerfilPJ(int id) {
        HttpURLConnection urlConnection = null;
        boolean retorno;

        URL = "http://pap5.ga/ws/pj/usuario";
        try {
            URL url = new URL(URL + "/" + id);
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            Scanner s = new Scanner(in);
            String conteudo = s.useDelimiter("\\A").next();

            Gson gson = new Gson();
            retorno = gson.fromJson(conteudo, boolean.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            urlConnection.disconnect();
        }

        URL = "http://pap5.ga/ws/usuario";
        return retorno;
    }

    public void post(Object usuario) {
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
            String json = gson.toJson(usuario);

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

