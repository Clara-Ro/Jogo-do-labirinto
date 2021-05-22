package labirinto;

public class main {
//variaveis 
	private static final char paredeVertical = '|';
	private static final char paredeHorizontal = '-';
	private static final char vazio = ' ';
	private static final char obstaculo = '=';
	private static final char inicio = 'I';
	private static final char chegada = 'C';
	private static final double probabilidade = 0.7;
	private static final char tamanho = 10;
	private static char[][] tabuleiro;
	private static int linhaInicio;
	private static int colunaInicio;
	private static char caminho= '.';
	private static char semSaida='x';
	
	private static boolean tentarCaminho(int proxLinha, int proxColuna) {
		boolean achou = false;
		if(tabuleiro[proxLinha][proxColuna]==chegada) {
			achou=true;
		} else if(posicaoVazia(proxLinha,proxColuna)) {
			//marcar
			tabuleiro[proxLinha][proxColuna]=caminho;
			imprimir();
			achou=procurarCaminho(proxLinha,proxColuna);
			if(!achou) {
				tabuleiro[proxLinha][proxColuna]=semSaida;
				imprimir();
			}
		}
		return achou;
	}
	
	public static boolean posicaoVazia(int linha, int coluna) {
		boolean Vazio=false;
		if(linha>=0 && coluna>=0 && linha<tamanho && coluna<tamanho) {
			Vazio= (tabuleiro[linha][coluna]==vazio);
		}
		return Vazio;
	}
	
	public static boolean procurarCaminho(int linhaAtual, int colunaAtual){
		int proxLinha;
		int proxColuna;
		boolean achou = false;
		
		//tentar subir
		proxLinha=linhaAtual-1;
		proxColuna=colunaAtual;
		achou= tentarCaminho(proxLinha,proxColuna);
		
		//tentar descer
		if(!achou) {
			proxLinha=linhaAtual+1;
			proxColuna=colunaAtual;
			achou= tentarCaminho(proxLinha,proxColuna);
		}
		
		//tentar esquerda
		if(!achou) {
			proxLinha=linhaAtual;
			proxColuna=colunaAtual-1;
			achou= tentarCaminho(proxLinha,proxColuna);
		}
		
		//tentar direita
		if(!achou) {
			proxLinha=linhaAtual;
			proxColuna=colunaAtual+1;
			achou= tentarCaminho(proxLinha,proxColuna);
		}
		return achou;
	}
	
//gerar número inteiro
	public static int gerarNumero(int min, int max) {
		int valor = (int)Math.round(Math.random()*(max-min));
		return min + valor;
	}
	
//gerar as paredes
	public static void iniciarMatriz(){
		int i,j;
		for (i=0;i<tamanho;i++) {
			tabuleiro[i][0]=paredeVertical;
			tabuleiro[i][tamanho-1]=paredeVertical;
			tabuleiro[0][i]=paredeHorizontal;
			tabuleiro[tamanho-1][i]=paredeHorizontal;
		}
		
//aleatoriaza os obstaculos 		
		for (i=1;i<tamanho-1;i++) {
			for (j=1;j<tamanho-1;j++) {
				if(Math.random()>probabilidade) {
					tabuleiro[i][j]=obstaculo;
				}else {
					tabuleiro[i][j]=vazio;
				}
			}
		}
		linhaInicio= gerarNumero(1,tamanho/2-1);
		colunaInicio=gerarNumero(1,tamanho/2-1);
		tabuleiro[linhaInicio][colunaInicio]=inicio;
		int linhaChegada= gerarNumero(tamanho/2,tamanho-2);
		int colunaChegada=gerarNumero(tamanho/2,tamanho-2);
		tabuleiro[linhaChegada][colunaChegada]=chegada;
	}
//impressão
	public static void imprimir(){
		for (int i=0;i<tamanho;i++) {
			for (int j=0;j<tamanho;j++) {
				System.out.print(tabuleiro[i][j]);
			}
				System.out.println();
		}
	}

	public static void main(String args[]) {
		tabuleiro = new char[tamanho][tamanho];
		iniciarMatriz();
		imprimir();
		System.out.println("\n- Procurando solução -\n"); 
		boolean achou = procurarCaminho(linhaInicio, colunaInicio); 
		if (achou) { 
			System.out.println("Achou o caminho!"); 
		} else { 
			System.out.println("Não tem caminho!"); 
		}
	}
}
	