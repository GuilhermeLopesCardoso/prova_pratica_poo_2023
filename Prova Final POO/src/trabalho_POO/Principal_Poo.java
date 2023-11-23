package trabalho_POO;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Principal_Poo {
static ArrayList<Rodovia> rodovias = new ArrayList<Rodovia>();
static ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();
static ArrayList<Acidente> acidentes = new ArrayList<Acidente>();
    public static void main(String[] args) {
//Menu----------------------------------------------------------------------------------------------------------------------------------------------------------
        int op = 0;
		do {
			op = menu();
			switch (op) {
			case 1:
                cadastraRodovia();
				break;
			case 2:
                cadastraAcidente();
				break;
			case 3:
				ListarEmbri();
				break;
			case 4:
                ListarRodoviaPeri(); 
				break;
			case 5:
			 	listarVeiculoCarga();
				break;
            case 6:
            	JOptionPane.showMessageDialog(null,listarRodoviaBike());
                break;
            case 7:
            	JOptionPane.showMessageDialog(null,listarRodoviaFatal());
                break;
            case 8:
            	JOptionPane.showMessageDialog(null,listarAcidenteVeiculoNovo());
                break;
            case 9:
            	JOptionPane.showMessageDialog(null,rodoviasCarnaval());
                break;
            case 10:
            	teste();
            	break;
						}
			}while(op != 99);
						}
    public static int menu() {
		String menu = "1 - Cadastrar Rodovia\n"
				+ "2 - Cadastrar Acidente\n"
                + "3 - Listar acidentes onde o condutor esteja embriagado.\n"
                + "4 - Listar a quantidade de Acidentes para cada nivel de periculosidade da rodovia.\n"
                + "5 - Listar veículos de carga envolvidos em acidentes.\n"
                + "6 - Mostrar rodovia com maior número de acidentes com bicicleta.\n"
                + "7 - Mostrar rodovia com maior número de acidentes com vítimas fatais \n"
                + "8 - Quantos acidentes possuem veículos novos (considerar o ano de 2013).\r \n"
                + "9 - Quais as rodovias que registraram acidentes no carnaval (considerar fevereiro). \n"
				+ "99 - Sair";
		return Integer.parseInt(JOptionPane.showInputDialog(menu));
	}	
	public static void teste() {
		for(Rodovia r: rodovias) {
			for (Acidente a: r.acidentes) {		
			}
		}
	}
/*Cadastra_Rodovia-------------------------------------------------------------------------------------------------------------------------------------------------------*/
    public static void cadastraRodovia(){
        String sigla = JOptionPane.showInputDialog(null,"Qual o nome da rodovia");
        char grau = JOptionPane.showInputDialog(null,"Qual o Grau de periculosidade(Alto,Médio,Baixo)").toUpperCase().charAt(0);
        if(grau != 'A' || grau != 'B'||grau != 'C') {
        	JOptionPane.showMessageDialog(null,"Cadastro invalido \n Cadastre novamente");
        	cadastraRodovia();
        	return;
        }
        Rodovia r = new Rodovia(sigla,grau);
        rodovias.add(r);
    }

/*Cadastra_Rodovia-------------------------------------------------------------------------------------------------------------------------------------------------------*/
public static Condutor cadastraCondutor() {
    String nome = JOptionPane.showInputDialog(null, "Informe o nome do condutor: ");
    int idade = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a idade do condutor: "));
    char sexo = JOptionPane.showInputDialog(null, "Informe o sexo do condutor: ").toUpperCase().charAt(0);
    char condição = JOptionPane.showInputDialog(null, "Qual o estado atual do condutor? (F-ferido/M-morto)").toUpperCase().charAt(0);
    char embriagado = JOptionPane.showInputDialog(null, "O condutor estava embriagado? ").toUpperCase().charAt(0);
    Condutor c = new Condutor(nome, idade, sexo, condição, embriagado);
    return c;
}
/*Cadastra_Rodovia-------------------------------------------------------------------------------------------------------------------------------------------------------*/
    public static Pessoa cadastraPessoa() {
        String nome = JOptionPane.showInputDialog(null, "Informe o nome: ");
        int idade = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a idade:"));
        char sexo = JOptionPane.showInputDialog(null, "Informe o sexo (M ou F): ").toUpperCase().charAt(0);
        char condição = JOptionPane.showInputDialog(null, "Qual o estado atual da vítima? (F-ferida/M-morta)").toUpperCase().charAt(0);
        Pessoa p = new Pessoa(nome, idade,sexo,condição);
        return p;
    }

/*Cadastra_Rodovia-------------------------------------------------------------------------------------------------------------------------------------------------------*/
    public static Veiculo cadastraVeiculo(){
        ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
        int anofab = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o ano de fabricação do veículo: "));
        char cod_modelo = JOptionPane.showInputDialog(null, "Informe o modelo do veículo:\n"+"1 - Carro\n"+"2 - Caminhão\n"+"3 - Moto\n"+"4 - Bicicleta\n"+"5 - Ônibus\n").toUpperCase().charAt(0); 
        char con;
        Condutor condutor = cadastraCondutor();
        pessoas.add(condutor);
        char conti = JOptionPane.showInputDialog(null,"Deseja incluir mais passageiros?(Sim ou Não)").toUpperCase().charAt(0);
        if (conti == 'S'){
        do {
        pessoas.add(cadastraPessoa());
        con = JOptionPane.showInputDialog(null,"Deseja incluir mais passageiros?(Sim ou Não)").toUpperCase().charAt(0);
        }while( con == 'S');}
        char veiculocarga = JOptionPane.showInputDialog(null, "É veículo de carga? (Sim ou Não): ").toUpperCase().charAt(0);
        if (veiculocarga == 'S'){
            Double carga = Double.parseDouble(JOptionPane.showInputDialog(null, "Qual o peso da carga? "));
            Veiculo_carga v = new Veiculo_carga(anofab,cod_modelo,pessoas,condutor,carga);
            return v;
        }else {
            Veiculo v = new Veiculo(anofab,cod_modelo,pessoas,condutor);
            return v;
        }
    }
/*Cadastra_Rodovia-------------------------------------------------------------------------------------------------------------------------------------------------------*/
    public static void cadastraAcidente(){
        ArrayList<Veiculo> veiculos_ac = new ArrayList<Veiculo>();
        
        Rodovia rodovia = selecionaRodovia();
        char con = ' ';
        int vitimas = 0;
        do {
        veiculos_ac.add(cadastraVeiculo());
        con = JOptionPane.showInputDialog(null,"Deseja incluir mais Veiculos?(Sim ou Não)").toUpperCase().charAt(0);
        }while(con =='S');
        int cod_mes = Integer.parseInt(JOptionPane.showInputDialog(null,"Qual o mês do Acidente (número)"));
        
        for (Veiculo v:veiculos_ac){
            for(Pessoa p: v.passageiros){
                    vitimas +=1;
            }
        }
        Acidente a = new Acidente(rodovia, vitimas,cod_mes,veiculos_ac);
        acidentes.add(a);
        for (Rodovia r: rodovias){
            if (r==rodovia){
                r.acidentes.add(a);
            }
        }
  
        }
        
/*Cadastra_Rodovia-------------------------------------------------------------------------------------------------------------------------------------------------------*/	
    public static Rodovia selecionaRodovia () {
	String menuAl = "Informe a rodovia onde ocorreu o acidente: \n";
	int cont = 1;
		for (Rodovia r : rodovias) {
			menuAl += cont + " - " + r+"\n";
			cont ++;
		}
		int escolha = Integer.parseInt(JOptionPane.showInputDialog(null, menuAl));
		return rodovias.get(escolha-1);
	}
        
/*Listar_todos_os_acidentes_cujo_algum_condutor_estava_embriagado--------------------------------------------------------------------------------------------------------*/
    public static void ListarEmbri(){
    for (Acidente a: acidentes){
    	for (Veiculo v: a.veiculos) {
    		if (v.Condutor.getEmbriagado()=='S') {
    			JOptionPane.showMessageDialog(null,a);
    		}
    	}
    }
}

/*Listar_a_quantidade_de_acidentes_para_cada_nível_de_periculosidade_da_rodovia------------------------------------------------------------------------------------------*/
public static void ListarRodoviaPeri(){
	
    int qta = 0;
    int qtb = 0;
    int qtm = 0;

    for (Acidente a: acidentes){
        if(a.rodovia.getGrau()=='A'){
            qta ++;
        }
        if(a.rodovia.getGrau()=='M'){
            qtm ++;
        }
        if(a.rodovia.getGrau()=='B'){
            qtb ++;
        }
        
    }
    JOptionPane.showMessageDialog(null, "Periculosidade Alta: "+qta+" Acidentes \n"+"Periculosidade Média: "+qtm+" Acidentes\n"+"Periculosidade Baixa: "+qtb+" Acidentes \n"); 
}

/*Mostrar_todos_os_veículos_de_carga_que_se_envolveram_em_acidentes------------------------------------------------------------------------------------------------------*/
	public static void listarVeiculoCarga() {
		for (Acidente a : acidentes) {
			for(Veiculo v: a.veiculos) {
					if (v.getClass().toString().equals("class trabalho_POO.Veiculo_carga")){
						System.out.print(v); 
				}
			}
		}
	}

/*Mostrar_qual_a_rodovia_em_que_ocorreram_mais_acidentes_com_bicicletas---------------------------------------------------------------------------------------------------*/
	public static String listarRodoviaBike() {
	int qtdinicia = 0;
    String exibir = " ";
    int i = 0;
    
    for (Rodovia r: rodovias){
        int val = 0;
        for(Acidente a: r.acidentes){
            if (verificaBike(a)) {
            val++;
            }
        }        
        if (val>=qtdinicia) {
        	qtdinicia = val;
        	exibir += r.toString()+"\n";
        	i++;
        }
    }
    if(i>0) {
    	return exibir;
    }return "Não existem acidentes cadastrados com bicicleta";   
}

public static boolean verificaBike(Acidente a){
    for (Veiculo v: a.veiculos){
            if(v.getCod_modelo() == '4'){
                return true;
            } 
    }return false;
}
/*Mostrar_qual_rodovia_que_possui_mais_acidentes_com_vítimas_fatais-------------------------------------------------------------------------------------------------------*/
public static String listarRodoviaFatal() {
	int qtdinicia = 0;
    String exibir = " ";
    int i = 0;
    
    for (Rodovia r: rodovias){
        int val = 0;
        for(Acidente a: r.acidentes){
        	for (Veiculo v:a.veiculos) {
        		if(verificavitima(v)) {
        			val++;	
        		}
        	}
        }
        if (val>=qtdinicia) {
        	qtdinicia = val;
        	exibir = r.toString();
        	i++;
        }
    }
    if(i>0) {
    	return exibir;
    }return "Não existem acidentes cadastrados com bicicleta";   
}
    
public static boolean verificavitima(Veiculo v){
		for (Pessoa p: v.passageiros ) {
        if(p.getCondição() == 'M'){
                return true;
            }
    }return false; 
}
/*Quantos_acidentes_possuem_veículos_novos_(considerar_o_ano_de_2013)-----------------------------------------------------------------------------------------------------*/
public static String listarAcidenteVeiculoNovo(){
	int i = 0;
	String exibir = "";
    for (Acidente a: acidentes){
    	if(verificveiculonovo(a.veiculos)){
    		exibir += a.toString()+"\n";
    		i ++;
    	}
    }
    if (i > 0) {
    	return exibir;
    }return "Não tem nenhum Veiculo novo cadastrado";
    
}
public static boolean verificveiculonovo(ArrayList<Veiculo> veiculos) {
	for (Veiculo v: veiculos) {
		if (v.anofab >= 2013){
        	return true; 
        }
    }
		return false;
}

/*Quais_as_rodovias_que_registraram_acidentes_no_carnaval_(considerar_fevereiro)-----------------------------------------------------------------------------------------*/
public static String rodoviasCarnaval() {
	int i = 0;
	String exibir = "";
	for (Rodovia r: rodovias) {
		if(verificaAcidenteCarna(r.acidentes)) {
				exibir += r.toString()+"\n";
				i++;
			}
	}if (i > 0) {
    	return exibir;
    }return "Não houveram acidentes em fevereiro";
}

public static boolean verificaAcidenteCarna(ArrayList<Acidente> acidentes) {
	for(Acidente a: acidentes) {
		if(a.getCod_mes()==2) {
			return true;
		}
	}
	return false;
}


        
        
        
/*Quais_as_rodovias_que_registraram_acidentes_no_carnaval_(considerar_fevereiro)-----------------------------------------------------------------------------------------*/
  
        public static ArrayList<Rodovia> getRodovias() {
            return rodovias;
        }
        public static void setRodovias(ArrayList<Rodovia> rodovias) {
            Principal_Poo.rodovias = rodovias;
        }
        public static ArrayList<Veiculo> getVeiculos() {
            return veiculos;
        }
        public static void setVeiculos(ArrayList<Veiculo> veiculos) {
            Principal_Poo.veiculos = veiculos;
        }
        public static ArrayList<Acidente> getAcidentes() {
            return acidentes;
        }
        public static void setAcidentes(ArrayList<Acidente> acidentes) {
            Principal_Poo.acidentes = acidentes;
        }
}


