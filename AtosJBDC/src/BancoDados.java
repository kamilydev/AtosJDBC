import java.sql.*;
import java.io.IOException;



public class BancoDados implements InterfaceBancoDados {
    private Connection c;
    private Log meuLogger; 
    
    public BancoDados() throws IOException {
        meuLogger = new Log("Log.txt");
    }
    
    
    @Override
    public void conectar(String db_url, String db_user, String db_password) {
        try {
            c = DriverManager.getConnection(db_url, db_user, db_password);
            meuLogger.logger.info("\n Conectado ao banco de dados");
        } catch (SQLException e) {
        	meuLogger.logger.info("Erro ao se conectar ao banco de dados." + e.getMessage());
        }
    }

    @Override
    public void desconectar() {
        try {
            if (c != null) {
                c.close();
                meuLogger.logger.info("Você foi desconectado do banco de dados!");
            }
        } catch (SQLException e) {
        	meuLogger.logger.info("Erro ao se desconectar do banco de dados." + e.getMessage());
        }
    }

    @Override
    public void consultar(String db_query) {
        try {
            PreparedStatement ps = c.prepareStatement(db_query);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmt = rs.getMetaData();
            int columnCount = rsmt.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String nomeColuna = rsmt.getColumnName(i);
                    String valorColuna = rs.getString(i);
                    System.out.println(nomeColuna + ": " + valorColuna);
                }
                System.out.println();
            }

        } catch (SQLException e) {
        	meuLogger.logger.info("Erro ao conectar: " + e);
        }
    }

    @Override
    public int inserirAlterarExcluir(String db_query) {
        try {
            Statement stmt = c.createStatement();
            return stmt.executeUpdate(db_query);
        } catch (SQLException e) {
        	meuLogger.logger.info("Erro ao executar a ação: " + e.getMessage());
            return 0;
        }
    }
}