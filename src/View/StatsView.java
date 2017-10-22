package View;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import Model.StatsMember;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class StatsView extends Parent {
	
	private TableView<StatsMember> table;
	private final ObservableList<StatsMember> data;
	
	public StatsView(){
		table = new TableView<StatsMember>();
		data = FXCollections.observableArrayList();
		populateTable();
	}

	private void populateTable(){
		try{
			BufferedReader reader = new BufferedReader(new FileReader("gameStats.txt"));
			String currentLine;
			String name;
			int wins,losses;
			while((currentLine = reader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(currentLine);
				name = st.nextToken();
				wins = Integer.parseInt(st.nextToken());
		    	losses = Integer.parseInt(st.nextToken());
		    	data.add(new StatsMember(name,wins,losses));
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	public Parent createTable(){

        final Label label = new Label("Player Statistics");
        label.setFont(new Font("Arial", 20));
 
        table.setEditable(true);
 
        TableColumn nameCol = new TableColumn("Name of the player");
        nameCol.setMinWidth(300);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<StatsMember, String>("name"));
 
        TableColumn winsCol = new TableColumn("Wins");
        winsCol.setMinWidth(100);
        winsCol.setCellValueFactory(
                new PropertyValueFactory<StatsMember, Integer>("wins"));
 
        TableColumn lossesCol = new TableColumn("Losses");
        lossesCol.setMinWidth(200);
        lossesCol.setCellValueFactory(
                new PropertyValueFactory<StatsMember, Integer>("losses"));
 
        table.setItems(data);
        table.getColumns().addAll(nameCol, winsCol, lossesCol);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);
 

        return vbox;
	}
}
