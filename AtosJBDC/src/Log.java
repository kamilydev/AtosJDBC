//A classe está presente no pacote java.util.logging. O getLogger é um método estático presente na classe Logger que 
//cria um logger se não estiver presente no sistema com o nome fornecido. 

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class Log {
	public Logger logger;
	FileHandler fh;

	//Verifica se o arquivo de log já existe, senão o cria
	public Log(String nome_arquivo) throws IOException {
		File f = new File(nome_arquivo);
		if (!f.exists()) {
			f.createNewFile();
		}
		
		//A classe FileHandler está presente no pacote java.util.logging 
		//e foi especificamente projetada e configurada para lidar com o log impresso usando a instância logger.
		//O construtor FileHandler inicializa o manipulador para gravar as informações no nome de arquivo fornecido. 
		fh = new FileHandler(nome_arquivo, true); //cria o arquivo
		logger = Logger.getLogger("Teste Log"); //nome do logger
		logger.addHandler(fh);  //adiciona o conteúdo no arquivo 
		//O método addHandler mapeia um manipulador de arquivo para receber as mensagens de registro no arquivo.
		
		/*A instância SimpleFormatter é iniciada; a classe também está presente no pacote java.util.logging. 
		 * O formatador permite o privilégio de autoformatar os logs usando a propriedade logging.SimpleFormatter.format. 
		 * Esta propriedade permite que as mensagens de log estejam em um formato definido e, portanto, mantendo a uniformidade.
		 */
		SimpleFormatter formatter = new SimpleFormatter();
		fh.setFormatter(formatter); //O setFormatter é uma função que define um formatador na instância do logger.

	}
	
}