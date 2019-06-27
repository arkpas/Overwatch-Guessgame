package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Items {
ArrayList <String> itemsList = new ArrayList<String>();
Label itemsTable[];
private int cattegoryCounter;
private int itemCounter;

public void addToList(BufferedReader odczyt) throws IOException {
	String itemName="EOF";
	do {
		itemName = odczyt.readLine().toUpperCase();
		itemsList.add(itemName);
		}
	while (!itemName.equals("EOF"));
	itemsList.remove("EOF");
	odczyt.close();
	
}

public void fillTable(GridPane grid) {
	itemsTable=new Label[itemsList.size()];
	int colNr=-1, rowNr=0;
	for (int i=0;i<itemsList.size();i++) {
		if (itemsList.get(i).startsWith("-")) {
			itemsList.set(i,itemsList.get(i).substring(1));
			itemsList.set(i,itemsList.get(i).toUpperCase());
			itemsTable[i]=new Label(itemsList.get(i));
			itemsTable[i].setId("cattegories");
			itemsList.set(i, null);
			colNr++;
			rowNr=0;
			cattegoryCounter++;
			}
		else {
			itemsTable[i]=new Label(" ");
			itemsTable[i].setId("emptyLabel");
			}
		itemsTable[i].setPrefWidth(135);
		itemsTable[i].setPrefHeight(30);
		itemsTable[i].setAlignment(Pos.CENTER);
		grid.add(itemsTable[i],colNr,rowNr);
		GridPane.setHalignment(itemsTable[i], HPos.CENTER);
		rowNr++;
	}
}

public void fillTable(GridPane grid, String other) {
	itemsTable=new Label[itemsList.size()];
	int colNr=0, rowNr=0;
	for (int i=0;i<itemsList.size();i++) {
		if (i==0) {
			itemsTable[i]=new Label(other);
			itemsTable[i].setId("cattegories");
		}
		else {
			itemsTable[i]=new Label(itemsList.get(i));
			itemsTable[i].setId("highscores");
		}
		itemsTable[i].setPrefWidth(135);
		itemsTable[i].setPrefHeight(30);
		itemsTable[i].setAlignment(Pos.CENTER);
		grid.add(itemsTable[i],colNr,rowNr);
		GridPane.setHalignment(itemsTable[i], HPos.CENTER);
		rowNr++;
	}
}

public void fillAll() {
	int length=itemsTable.length;
	for (int i=0;i<length;i++) {
		if (itemsTable[i].getId().equals("emptyLabel")) {
		itemsTable[i].setText(itemsList.get(i));
		itemsTable[i].setId("cheater");
		}
	}
}

public boolean find(String userInput) {
	if (itemsList.contains(userInput))
		return true;
	else
		return false;
}
public boolean replace(String userInput, String id) {
	if (itemsList.contains(userInput)) {
		int position=itemsList.indexOf(userInput);
		itemsList.set(position, null);
		itemsTable[position].setText(userInput);
		itemsTable[position].setId(id);
		itemCounter++;
		return true;
		}
	else
		return false;
}

public int getListSize() {
	return itemsList.size()-cattegoryCounter;
}

private void clearTable() {
	for (int i=0;i<itemsList.size();i++) {
		itemsTable[i]=null;
		itemsTable[i].setId(null);
		}
}

public void clearAll(){
	itemsList.clear();
	clearTable();
	itemsTable=null;
	cattegoryCounter=0;
	itemCounter=0;
}

public int howMany() {
	return itemCounter;
	}
}
