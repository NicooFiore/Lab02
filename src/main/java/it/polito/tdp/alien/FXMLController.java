package it.polito.tdp.alien;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.traduttore.model.Parola;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	List<Parola> parole;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtTesto;

    @FXML
    private TextArea txtTraduzione;

    @FXML
    void handleClear(ActionEvent event) {
    	txtTraduzione.clear();

    }

    @FXML
    void handleTraduci(ActionEvent event) {
    	String testo[]=txtTesto.getText().split(" ");
    	String res="";
    	if(testo.length==1) {
    		String cercaTraduzione=testo[0];
    		if(cercaTraduzione.matches("[a-zA-Z]+")) {
    		for(Parola p:parole){
    			if(p.getParola().toLowerCase().equals(cercaTraduzione.toLowerCase()))
    			for(String s:p.elencoTraduzioni()) {
    				res+=s+"\n";
    			}
    				txtTraduzione.setText(res);
    			txtTesto.clear();
    		}
    		}
    		else txtTraduzione.setText("Traduzione impossibile");
    		
    		
    	}
    	if(testo.length==2) {
    		if(testo[0].matches("[a-zA-Z]+") && testo[1].matches("[a-zA-Z]+")) {
    		Parola parola=new Parola(testo[0]);
    		parole.add(parola);
    		parola.aggiungiTraduzione(testo[1]);
    	    txtTesto.clear();
    		}
    		else txtTraduzione.setText("Parole inserite non valide");
    	}

    }

    @FXML
    void initialize() {
        assert txtTesto != null : "fx:id=\"txtTesto\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTraduzione != null : "fx:id=\"txtTraduzione\" was not injected: check your FXML file 'Scene.fxml'.";
        parole=new LinkedList<Parola>();
    }

}
