import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;



public class BancoDados implements InterfaceBancoDados{
	private Connection c;
	
	@Override
	public void conectar(String db_url, String db_user, String db_password) {
	    try {
	        c = DriverManager.getConnection(db_url, db_user, db_password); // atribui a conexão à variável "c"
	        System.out.println("Conectado com sucesso!");
	    } catch (SQLException e) {
	        throw new RuntimeException("Falha na conexão", e);
	    }
	}

	@Override
	public void desconectar() {
		try {
			c.close();
			System.out.println("Desconectado com sucesso!");
		}
			
		
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Falha ao desconectar ao MySql");
		}
		
	}
	
	public PreparedStatement preparar(String sql) throws SQLException {
	    return c.prepareStatement(sql);
	}

	@Override
	public ResultSet consultar(String db_query) {
	    try {
	        Statement statement = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        ResultSet resultSet = statement.executeQuery(db_query); 
	        ResultSetMetaData metaData = resultSet.getMetaData();
	        int numberOfColumns = metaData.getColumnCount();
	        System.out.println("Conectado ao MySql");

	        System.out.printf("Resultado da pesquisa:%n%n");

	        // exibe os nomes de coluna no ResultSet
	        for (int i = 1; i <= numberOfColumns; i++)
	            System.out.printf("%-25s\t", metaData.getColumnName(i));
	        System.out.println();

	        while (resultSet.next()) {
	            for (int i = 1; i <= numberOfColumns; i++)
	                System.out.printf("%-25s\t", resultSet.getObject(i));
	            System.out.println();
	        }
	    } catch (SQLException e) {
	        String mensagem = "Falha ao executar a consulta: " + e.getMessage();
	        if (e.getErrorCode() == 1146) { // tabela não encontrada
	            mensagem = "Tabela não encontrada no banco de dados";
	        }
	        System.err.println(mensagem);
	    }
		return null;
	}

	public int inserirAlterarExcluir(String ps) {
	    int linhas = 0;
	    try (PreparedStatement statement = c.prepareStatement(ps)) {
	        System.out.println("Conectado ao MySql");
	        linhas = statement.executeUpdate();
	        System.out.println("Operação Efetuada com Sucesso");
	    } catch (SQLException e) {
	        String mensagem = "Falha ao executar a operação: " + e.getMessage();
	        if (e.getErrorCode() == 1062) { // chave duplicada
	            mensagem = "Chave duplicada no banco de dados";
	        }
	        System.err.println(mensagem);
	    }
	    return linhas;
	}

}
