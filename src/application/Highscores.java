package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Highscores {
ArrayList <Highscore> highscoresList = new ArrayList<Highscore>();
Label highscoresLabels[];
BufferedReader odczyt;

public Highscores() {
	this.loadHighscores();
}

public void loadHighscores() {
	
	String score=null;
	try {
		odczyt = new BufferedReader(new FileReader(new File("highscores.txt")));
		score=odczyt.readLine();
		while(score!=null) {
			highscoresList.add(new Highscore(score));
			score=odczyt.readLine();
			}
		odczyt.close();
	}
	catch (IOException a) {}
	catch (NullPointerException b) {}
	this.sortList();
}

public void fillGrid(GridPane grid) {
	highscoresLabels=new Label[highscoresList.size()];
	int rowNr=0;
	for (int i=0;i<highscoresList.size();i++) {
		highscoresLabels[i]=new Label(highscoresList.get(i).getHighscore()+"s");
		highscoresLabels[i].setId("highscores");
		highscoresLabels[i].setMinWidth(160);
		highscoresLabels[i].setMinHeight(30);
		highscoresLabels[i].setAlignment(Pos.CENTER);
		grid.add(highscoresLabels[i],0,rowNr);
		GridPane.setHalignment(highscoresLabels[i], HPos.CENTER);
		rowNr++;
	}
}

public void sortList() {
	Highscore p=new Highscore();
	if (highscoresList.size()>1) {
	for (int j=0;j<highscoresList.size()-1;j++)
		for (int i=1;i<highscoresList.size();i++) {
			if (highscoresList.get(i).getScore()<highscoresList.get(i-1).getScore()) {
				p.setHighscore(highscoresList.get(i).getHighscore());
				highscoresList.get(i).setHighscore(highscoresList.get(i-1).getHighscore());
				highscoresList.get(i-1).setHighscore(p.getHighscore());
			}
		}
	}
}

public boolean checkHighscore (String highscore) {
	Highscore p = new Highscore(highscore);
	if(highscoresList.size()<10) {
		highscoresList.add(p);
		this.saveToFile();
		return true;
	}
	else if (highscoresList.get(9).getScore()>p.getScore()) {
		highscoresList.get(9).setHighscore(p.getHighscore());
		this.saveToFile();
		return true;
	}
	else
		return false;
}

public void saveToFile() {
	this.sortList();
	File scoreFile = new File("highscores.txt");
	FileWriter filewriter;
	try {
		filewriter = new FileWriter(scoreFile,false);
		BufferedWriter writer = new BufferedWriter(filewriter);
		for (int i=0;i<highscoresList.size();i++) {
			writer.write(highscoresList.get(i).getHighscore());
			writer.newLine();
		}
		writer.close();
		filewriter.close();
	}
	catch (IOException e) {}

}

}
