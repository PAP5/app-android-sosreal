package pap.com.sosreal;


public class UsuarioDB {
    Usuario usu = new Usuario();

    public Usuario buscarUsuario(int id){
        usu.setId(1);
        usu.setEmail("email@email.com");
        usu.setSenha("loco");
        usu.setUsuario("loco");

        return usu;
    }

    public Usuario buscarPorUsuESenha(String usuario, String senha) throws IllegalArgumentException{
        usu.setId(1);
        usu.setEmail("email@email.com");
        usu.setSenha("loco");
        usu.setUsuario("loco");

        if(usuario.equals("loco") && senha.equals("loco")){
            return usu;
        }
        else{
            throw new IllegalArgumentException("Usuário não encontrado");
        }
    }
}
