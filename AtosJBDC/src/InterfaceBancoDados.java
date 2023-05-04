import java.sql.ResultSet;

public interface InterfaceBancoDados {
	public void conectar(String db_url, String db_user, String db_password);
	public void desconectar();
	public ResultSet consultar(String db_query);
	public int inserirAlterarExcluir(String db_query);
}
