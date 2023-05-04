import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;



public class BancoDados implements InterfaceBancoDados{
	private Connection c;
	
	@Override
	public void conectar(String db_url, String db_user, String db_password) {

		try (Connection c = DriverManager.getConnection(db_url, db_user, db_password)) {// conexão ao banco de dados			
			System.out.println("Conectado com sucesso!");
			
		} catch (Exception e) {
			throw new RuntimeException("falha na conexão", e);

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

	@Override
	public void consultar(String db_query) {
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

		}
			catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Falha ao conectar ao MySql");
		}
	}


	@Override
	public int inserirAlterarExcluir(String db_query) {
		int linhas = 0;
		try {
			System.out.println("Conectado ao MySql");
			Statement statement = c.createStatement();
			linhas = statement.executeUpdate(db_query);
			System.out.println("Operacao Efetudada com Sucesso");
			return linhas;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Operação Não Efetuada");
		}
		return linhas;
	}
 

}
