import java.util.Scanner;

public class PrincipalConsulta {
    public static void main(String[] args) {
        BancoDados db = new BancoDados();
        
        Scanner sc = new Scanner(System.in);
        
        String db_url = "jdbc:mysql://localhost:3306/reuniao";
        String db_user = "root";
        String db_password = "";

        db.conectar(db_url, db_user, db_password);
        
        System.out.println("Acesso ao banco de dados reuniao realizado com sucesso!");


        System.out.println("Escolha uma das opções:");
        System.out.println("1 - Consultar dados");
        System.out.println("2 - Inserir, alterar ou excluir dados");

        int escolha = sc.nextInt();
        sc.nextLine();

        if (escolha == 1) {
            System.out.print("Digite a consulta: ");
            String db_query = sc.nextLine();
            db.consultar(db_query + ";");
            
        } else if (escolha == 2) {
        	System.out.print("---------------------------------------- \n");
            System.out.print("Digite a ação desejada em *INGLÊS*: \n");
            System.out.print("[INSERT] - Inserir um dado \n");
            System.out.print("[UPDATE] - Alterar/atualizar um dado \n");
            System.out.print("[DELETE] - Excluir um registro da tabela \n");
            System.out.print("---------------------------------------- \n");
            String acao = sc.nextLine();
            
            System.out.print("---------------------------------------- \n");
            System.out.print("Digite a tabela em que essa ação ocorrerá: ");
            
            String tabela = sc.nextLine();
            
            if (acao.equals("INSERT")) {
            	System.out.print("---------------------------------------- \n");
		        System.out.print("Digite os valores na seguinte ordem: \n");
		        System.out.print("***OBSERVAÇÃO*** \n");
		        System.out.print("Dados em caracteres sempre com aspas ' ' \n");
		        System.out.print("Exemplo: 'NOME', 'EMAIL', 'CARGO' \n");
		        String valores = sc.nextLine();
		
		        String db_query = acao + " INTO " + tabela + " VALUES(" + valores + ");";
		        int linhasAfetadas = db.inserirAlterarExcluir(db_query);
		        System.out.println(linhasAfetadas + " linha(s) afetada(s)");
            
            } else if (acao.equals("UPDATE")) {
            	System.out.print("---------------------------------------- \n");
	            System.out.print("Digite os novos valores na seguinte ordem: \n");

	            System.out.print("nome: \n");
	            String nome = sc.nextLine();
	            System.out.print("email: \n");
	            String email = sc.nextLine();
	            System.out.print("cargo: \n");
	            String cargo = sc.nextLine();
	            
	            System.out.print("Qual ID irá ser alterado/atualizado? ");
	            int num = sc.nextInt();
	
	            String db_query = acao + " " + tabela + " SET nome='" + nome + "', email='" + email + "', cargo='" + cargo +"' WHERE ID = " + num + ";";
	            int linhasAfetadas = db.inserirAlterarExcluir(db_query);
	            System.out.println(linhasAfetadas + " linha(s) afetada(s)"); 
	            
        
		    } else if (acao.equals("DELETE")){
		        System.out.print("---------------------------------------- \n");
            	System.out.print("Digite o ID do registro que será excluído: ");
            	
                int valor = sc.nextInt();
                
                String db_query = acao + " INTO " + tabela + " WHERE id = " + valor + ";";
                int linhasAfetadas = db.inserirAlterarExcluir(db_query);
                System.out.println(linhasAfetadas + " linha(s) afetada(s)");
		    }
		}

        sc.close();
        
        db.desconectar();
        
        
    }
    	
}

