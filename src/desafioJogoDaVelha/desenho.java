package desafioJogoDaVelha;

import javax.swing.JOptionPane;

public class desenho {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int resposta = 0;
		String[] array = new String[2];
		array = nomesJogadores();
		int placarX = 0;
		int placarO = 0;
		do{
			String valor = "";
			int defineJogador = 0;

			String [][]numeros = new String [3][3];
			boolean ganhou = false;
			numeros = preencheMatriz(numeros);


			do{

				defineJogador += 1;
				valor = definirJogador(defineJogador);
				numeros = montaJogo(array, valor, numeros, placarX, placarO);
				ganhou = verificaGanhador(numeros);


				if(ganhou == true){
					vencedor(valor, array);
					if(valor == "X"){
						placarX += 1;
					}else if(valor == "O"){
						placarO += 1;
					}
					break;
				}else if(defineJogador == 9 && ganhou == false){
					JOptionPane.showMessageDialog(null,"Jogo deu velha!!!");
					break;
				}

			}while(defineJogador < 10);

			resposta = JOptionPane.showConfirmDialog(null, "Continuar?","Continuar a jogar?",JOptionPane.YES_NO_OPTION);
		}while(resposta == 0);
	}
	//=============== Preenche Matriz ============================================================================================
	public static String[][] preencheMatriz(String numeros[][]){
		int nmrTabela = 0;

		for(int L = 0; L < 3 ; L++){
			for(int C = 0; C < 3 ; C++){			
				nmrTabela = nmrTabela + 1;
				numeros[L][C] = Integer.toString(nmrTabela);
			}
		}

		return numeros;
	}
	//=============== nomes dos jogadores ========================================================================================
	public static String[] nomesJogadores(){

		String[] array = new String[2];

		array[0] = JOptionPane.showInputDialog(null,"Digite um nome para Jogador X:");
		array[1] = JOptionPane.showInputDialog(null,"Digite um nome para Jogador O:");

		return array;
	}

	//=============== define jogador =============================================================================================
	public static String definirJogador(int defineJogador){

		String valor = "";

		if(defineJogador % 2 == 0){
			valor = "O";
		}else{
			valor = "X";
		}

		return valor;

	}

	//=============== monta tabela do jogo =======================================================================================
	public static  String[][] montaJogo(String[] array, String valor, String[][] numeros, int placarX, int placarO){
		int jogada = 0;
		String msg = "";
		String nome = "";

		if(valor == "X"){
			nome = array[0];

		}else if(valor == "O"){
			nome = array[1];
		}

		msg += "#JOGO DA VELHA#\n";

		for(int L = 0; L < 3 ; L++){
			for(int C = 0; C < 3 ; C++){
				msg += "|   "+numeros[L][C]+"    ";
			}
			msg += "|";
			msg += "\n";
		}

		jogada = Integer.parseInt(JOptionPane.showInputDialog(null,msg+"\nPlacar:\n"
				+ "X -- "+array[0]+": "+placarX+". \n"
				+ "O -- "+array[1]+": "+placarO+". \n\n"
				+ "É sua vez "+nome+", escolha a onde jogar: "));

		if(jogada > 0 && jogada < 10){
			if(jogada > 0 && jogada < 4){
				jogada = jogada - 1;
				if(numeros[0][jogada] == "X" || numeros[0][jogada] == "O"){
					JOptionPane.showMessageDialog(null,"Número ja preenchido!");
					montaJogo(array, valor, numeros, placarX, placarO);
				}else{
					numeros[0][jogada] = valor;
				}
			}else if(jogada > 3 && jogada < 7){
				if(numeros[1][jogada - 4] == "X" || numeros[1][jogada - 4] == "O"){
					JOptionPane.showMessageDialog(null,"Número ja preenchido!");
					montaJogo(array, valor, numeros, placarX, placarO);
				}else{
					numeros[1][jogada - 4] = valor;
				}
			}else if(jogada > 6 && jogada < 10){
				if(numeros[2][jogada - 7] == "X" || numeros[2][jogada - 7] == "O"){
					JOptionPane.showMessageDialog(null,"Número ja preenchido!");
					montaJogo(array, valor, numeros, placarX, placarO);
				}else{
					numeros[2][jogada - 7] = valor;
				}
			}
		}else{
			JOptionPane.showMessageDialog(null,"Número não permitido!");
			montaJogo(array, valor, numeros, placarX, placarO);
		}

		return numeros;
	}
	//=============== verifica ganhador ==========================================================================================
	public static boolean verificaGanhador(String[][] numeros){
		boolean ganhou = false;
		//========================== vertical ===========================================
		if(ganhou != true){
			for(int C = 0; C < 3 ; C++){
				ganhou = true;
				for(int L = 0; L < 2 ; L++){
					if(numeros[L][C] != numeros[L + 1][C]){
						ganhou = false;
						break;
					}
				}
				if(ganhou == true){
					break;
				}
			}
			if(ganhou == true){
				return ganhou;
			}
		}

		//========================== horizontal ===========================================
		if(ganhou != true){
			for(int L = 0; L < 3 ; L++){
				ganhou = true;
				for(int C = 0; C < 2 ; C++){
					if(numeros[L][C] != numeros[L][C + 1]){
						ganhou = false;
						break;
					}
				}
				if(ganhou == true){
					break;
				}
			}
			if(ganhou == true){
				return ganhou;
			}
		}

		//========================== diagonal \ ===========================================
		if(ganhou != true){
			for(int C = 0; C < 2 ; C++){
				ganhou = true;
				if(numeros[C][C] != numeros[C + 1][C + 1]){
					ganhou = false;
					break;
				}	

			}
			if(ganhou == true){
				return ganhou;
			}
		}

		//========================== diagonal / ===========================================
		if(ganhou != true){
			for(int C = 2; C >= 1 ; C--){
				ganhou = true;
				if(numeros[2 - C][C] != numeros[3 - C][C - 1]){
					ganhou = false;
					break;
				}
			}
			if(ganhou == true){
				return ganhou;
			}
		}

		return ganhou;
	}
	//========================== Msg Vencedor ====================================================================================
	public static void vencedor(String valor, String[] array){
		if(valor == "X"){
			JOptionPane.showMessageDialog(null,"Parabéns! o vencedor é "+array[0]);
		}else if(valor == "O"){
			JOptionPane.showMessageDialog(null,"Parabéns! o vencedor é "+array[1]);
		}
	}
}
