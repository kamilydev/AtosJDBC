import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;


public class PrincipalConsulta {

    public static void main(String[] args) throws Exception {
    
        BancoDados db = new BancoDados();
        
        String db_url = "jdbc:mysql://localhost:3306/reuniao";
        String db_user = "root";
        String db_password = "";
        String db_query = "";
        
        db.conectar(db_url, db_user, db_password);
                
        
        System.out.println("Bem vindo a base de dados reuniao!");
        System.out.println("----------------------------------------");
        System.out.println("Qual ação gostaria de executar?");
        System.out.println("[1] - Visualizar todos os dados");
        System.out.println("[2] - Inserir dados");
        System.out.println("[3] - Atualizar dados");
        System.out.println("[4] - Excluir dados");
        System.out.println("[5] - Nenhuma. Gostaria de desconectar");
        System.out.println("----------------------------------------");
        
        Scanner sc = new Scanner(System.in);
        int dbCommand = sc.nextInt();
        
        if(dbCommand == 1) {
            db_query = ("SELECT * FROM pessoa;");
            ResultSet rs = db.consultar(db_query);
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String cargo = rs.getString("cargo");
                System.out.println(id + " - " + nome + " - " + email + " - " + cargo);
            }
            
            rs.close();
            
        } else if (dbCommand == 2) {
                System.out.println("Digite o ID do usuário: ");
                int dbInsertId = sc.nextInt();

                System.out.println("Digite o nome do usuário: ");
                String dbInsertName = sc.next();

                System.out.println("Digite o email do usuário: ");
                String dbInsertEmail = sc.next();

                System.out.println("Digite o cargo do usuário: ");
                String dbInsertCargo = sc.next();

                db_query = "INSERT INTO pessoa (id, nome, email, cargo) VALUES (?, ?, ?, ?)";
                try (PreparedStatement ps = ((Statement) db).getConnection().prepareStatement(db_query)) {
                    ps.setInt(1, dbInsertId);
                    ps.setString(2, dbInsertName);
                    ps.setString(3, dbInsertEmail);
                    ps.setString(4, dbInsertCargo);
                    ps.executeUpdate();
                    System.out.println("Dados inseridos com sucesso!");
                } catch (SQLException e) {
                    System.err.println("Falha ao executar a operação: " + e.getMessage());
                }
            

        } else if(dbCommand == 3) {
            System.out.println("Digite o ID do usuário que deseja atualizar: ");
            int dbUpdateId = sc.nextInt();
            
            System.out.println("Digite o nome do usuário: ");
            String dbUpdateName = sc.next();
            
            System.out.println("Digite o email do usuário: ");
            String dbUpdateEmail = sc.next();
            
            System.out.println("Digite o cargo do usuário: ");
            String dbUpdateCargo = sc.next();
            
            db_query = "UPDATE pessoa SET nome = ?, email = ?, cargo = ? WHERE id = ?";
            PreparedStatement ps = db.preparar(db_query);
            ps.setString(1, dbUpdateName);
            
        } else if(dbCommand == 3) {
        	System.out.println("Digite o ID do usuário que deseja alterar: ");
        	int dbUpdateId = sc.nextInt();
        	
        	System.out.println("Digite o novo nome do usuário: ");
        	String dbUpdateName = sc.next();

        	System.out.println("Digite o novo email do usuário: ");
        	String dbUpdateEmail = sc.next();

        	System.out.println("Digite o novo cargo do usuário: ");
        	String dbUpdateCargo = sc.next();

        	db_query = "UPDATE pessoa SET nome = '" + dbUpdateName + "', email = '" + dbUpdateEmail + "', cargo = '" + dbUpdateCargo + "' WHERE id = " + dbUpdateId;
        	db.inserirAlterarExcluir(db_query);
        	System.out.println("Dados do usuário com ID " + dbUpdateId + " foram atualizados.");
        	
        } else if(dbCommand == 4) {
        	System.out.println("Digite o(s) ID(s) do(s) usuário(s) que deseja excluir (separados por vírgula): ");
        	String dbDelete = sc.next();
        	
        	db_query = "DELETE FROM pessoa WHERE id IN (" + dbDelete + ")";
        	db.inserirAlterarExcluir(db_query);
        	System.out.println("Usuário(s) com ID(s) " + dbDelete + " foram excluídos.");
        } else if(dbCommand == 5) {
        	db.desconectar();

        	} else {
        	System.out.println("Desculpe, não existe essa opção! Tente novamente.");
        	}

        	sc.close();

        	}

        }

