/**
 * 
 */
package main;

import java.util.*;

import autostrada.*;
import veicolo.*;
import utility.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * @author Luca
 *
 */
public class TestAutostrada extends Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
		
		Map<Integer,Float> tariffaA25 = new HashMap<Integer,Float>();
		List<Casello> listCasello = new ArrayList<Casello>();
		Autostrada autostrada = new Autostrada("A24", tariffaA25, listCasello, Constants.PEDAGGIO_KM, 22);
		Veicolo veicolo = new ClasseA("E7843JK","AUDI","A5",2009,1);
		veicolo.setAssi(2); //per dimostrazione, richiamo metodo definito nella classe abstract
		
		//mappa tariffe
		tariffaA25.put(Constants.CLASSE_A, (float) 0.057);
		tariffaA25.put(Constants.CLASSE_B, (float) 0.067);
		tariffaA25.put(Constants.CLASSE_3, (float) 0.078);
		tariffaA25.put(Constants.CLASSE_4, (float) 0.088);
		tariffaA25.put(Constants.CLASSE_5, (float) 0.099);
		
		//lista caselli
		listCasello.add(new Casello("AQ OVEST",100));
		listCasello.add(new Casello("PE NORD", 250));
		listCasello.add(new Casello("TE OVEST", 180));
		
		Casello casello1 = new Casello("AQ OVEST",100);
		Casello casello2 = new Casello("PE NORD", 250);
		System.out.println("Il pedaggio per il veicolo e': "+ autostrada.stampaPedaggio(veicolo, casello1, casello2)+"0€");
		
		autostrada.setIva ( 24 );
		autostrada.save();
		
		autostrada.retrieve( 1 );
		
		System.out.println( autostrada.getNome() );
	}
	
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

}
