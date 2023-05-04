import java.util.Scanner;

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
		System.out.println("[3] - Alterar dados");
		System.out.println("[4] - Excluir dados");
		System.out.println("[5] - Nenhuma. Gostaria de desconectar");
		System.out.println("----------------------------------------");
		
		Scanner sc = new Scanner(System.in);
		int dbCommand = sc.nextInt();
		
			if(dbCommand == 1) {
				db_query = ("SELECT * FROM reuniao;");
				db.consultar(db_query);
				
				
			} else if(dbCommand == 2) {
				System.out.println("Digite o ID do usuário: ");
				int dbInsertId = sc.nextInt();
				
				System.out.println("Digite o nome do usuário: ");
				String dbInsertName = sc.next();
				
				System.out.println("Digite o email do usuário: ");
				String dbInsertEmail = sc.next();
				
				System.out.println("Digite o cargo do usuário: ");
				String dbInsertCargo = sc.next();
				
				db.inserirAlterarExcluir(db_query);
				db_query = "INSERT INTO pessoa VALUES(" + dbInsertId + "," + dbInsertName +"," + dbInsertEmail + "," + dbInsertCargo + ")";
				System.out.println(db_query);
				
				
				
			} else if(dbCommand == 3) {
				System.out.println("Digite o nome do usuário: ");
				String dbUpdateName = sc.next();
				
				System.out.println("Digite o email do usuário: ");
				String dbUpdateEmail = sc.next();
				
				System.out.println("Digite o cargo do usuário: ");
				String dbUpdateCargo = sc.next();
				
				db.inserirAlterarExcluir(db_query);
				//db_query = ("INSERT INTO pessoa VALUES(?,?,?)");
				//PreparedStatement ps = c.prepareStatement(dbUpdateName,dbUpdateEmail,dbUpdateCargo);
				
					
			} else if(dbCommand == 4) {
				System.out.println("Digite o(s) ID(s) do(s) usuário(s) que deseja excluir: ");
				int dbDelete = sc.nextInt();
				
				db.inserirAlterarExcluir(db_query);
				db_query = ("DELETE FROM pessoa WHERE id = (?)");
				//PreparedStatement ps = c.prepareStatement(dbDelete);
				//ps.setInt(1, dbDelete);//parâmetro 1
				
				
			} else if(dbCommand == 5) {
				db.desconectar();
				
				
			} else {
				System.out.println("Desculpe, não existe essa opção! Tente novamente.");
			}
	
	}
}

			