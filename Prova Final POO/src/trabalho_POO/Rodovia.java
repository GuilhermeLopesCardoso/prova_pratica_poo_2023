package trabalho_POO;

import java.util.ArrayList;

public class Rodovia {

    ArrayList<Acidente> acidentes = new ArrayList<Acidente>();

    String sigla;
    char grau;
    int qtdacidente = 0;
    public Rodovia() {
    	
    }
    public Rodovia(String sigla, char grau) {
        this.sigla=sigla;
        this.grau=grau;
        for(Acidente a: acidentes){
            qtdacidente ++;
        }
    }
    public Rodovia(Acidente a) {
        this.acidentes.add(a);
        }   
    public String toString(){
        if(getGrau() == 'A') {
            return "Rodovia " + getSigla() + " - " + "Periculosidade Alta.";
        }
        if(getGrau() == 'M') {
            return "Rodovia " + getSigla() + " - " + "Periculosidade Média.";
        }
        if(getGrau() == 'B') {
            return "Rodovia " + getSigla() + " - " + "Periculosidade Baixa.";
        }
        return getSigla()+"-"+getGrau();
    }

//GETS & SETS--------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public String getSigla() {
        return sigla;
    }
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    public char getGrau() {
        return grau;
    }
    public ArrayList<Acidente> getAcidentes() {
        return acidentes;
    }
    
    public void setAcidentes(ArrayList<Acidente> acidentes) {
		this.acidentes = acidentes;
	}
	public void setGrau(char grau) {
        this.grau = grau;
    }
    public int getQtdacidente() {
        return qtdacidente;
    }
    public void setQtdacidente(int qtdacidente) {
        this.qtdacidente = qtdacidente;
    }

    

}
