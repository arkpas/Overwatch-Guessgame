package application;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.io.BufferedReader;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;


public class Main extends Application{
	private int items;
	private final int timelimit=420;
	private int time;
	private int score;
	
public void start (Stage primaryStage) throws URISyntaxException {

VBox root=new VBox();
root.setId("mainBox");

Media intro=new Media(getClass().getResource("/resources/overwatch.mp3").toURI().toString());
MediaPlayer player=new MediaPlayer(intro);
player.play();

BorderPane menuBox = new BorderPane();
menuBox.setId("menu");
Image logoImage = new Image("/resources/overwatch2.png");
ImageView logo = new ImageView(logoImage);
menuBox.setTop(logo);
BorderPane.setAlignment(logo, Pos.TOP_CENTER);
VBox menuList = new VBox();
menuList.setAlignment(Pos.CENTER_LEFT);
menuList.setPadding(new Insets(20));
Label start = menuLabel("Start game");
Label highscores = menuLabel("Highscores");
Label exit = menuLabel("Exit");
menuList.getChildren().addAll(start,highscores,exit);
menuBox.setLeft(menuList);

BorderPane topPane = new BorderPane();
topPane.setId("topBox");
VBox topBox=new VBox();
topBox.setAlignment(Pos.CENTER);	
topBox.setPadding(new Insets(5));
topBox.setPrefHeight(600);

Label title = new Label();
title.setId("title");
title.setPrefSize(640,280);

TextField field = new TextField();
field.setMaxWidth(600);
Tooltip fieldTooltip = new Tooltip ("Type hero or map name!");
field.setTooltip(fieldTooltip);

Label timerLabel = new Label("TIMER");
timerLabel.setId("timer");
timerLabel.setPrefSize(50, 30);
timerLabel.setAlignment(Pos.CENTER);

topBox.getChildren().addAll(title,field,timerLabel);
topPane.setCenter(topBox);

VBox rightBox = new VBox();
rightBox.setPadding(new Insets(0,0,20,0));
rightBox.setAlignment(Pos.BOTTOM_CENTER);
HBox heroAvatars2 = new HBox();
rightBox.getChildren().add(heroAvatars2);
rightBox.setPrefWidth(300);
topPane.setRight(rightBox);

VBox leftBox = new VBox();
leftBox.setPrefWidth(300);
leftBox.setPadding(new Insets(0,0,20,0));
leftBox.setAlignment(Pos.BOTTOM_CENTER);
HBox heroAvatars = new HBox();
heroAvatars.setAlignment(Pos.BOTTOM_CENTER);
leftBox.getChildren().add(heroAvatars);
topPane.setLeft(leftBox);

HBox bottomBox = new HBox();
bottomBox.setMinWidth(700);
bottomBox.setAlignment(Pos.CENTER);
bottomBox.setSpacing(20);
VBox heroesBox = new VBox();
heroesBox.setAlignment(Pos.TOP_CENTER);
Label bigHeroes = new Label("HEROES");
bigHeroes.setId("bigLabel");
bigHeroes.setAlignment(Pos.CENTER);

GridPane heroesGrid = createGrid(4,10,140);
heroesGrid.setAlignment(Pos.CENTER);
heroesBox.getChildren().addAll(bigHeroes, heroesGrid);
Items heroes = new Items();

VBox mapsBox = new VBox();
mapsBox.setAlignment(Pos.TOP_CENTER);
Label bigMaps = new Label("MAPS");
bigMaps.setId("bigLabel");
GridPane mapsGrid = createGrid(5,8,140);
mapsBox.getChildren().addAll(bigMaps,mapsGrid);
Items maps = new Items();

bottomBox.getChildren().addAll(heroesBox,mapsBox);
VBox.setMargin(field, new Insets(5,0,5,0));
root.getChildren().addAll(topPane,bottomBox);

Scene menuScene = new Scene(menuBox,1280,780);
Scene gameScene = new Scene(root,1280,780);
gameScene.getStylesheets().add("application/stylesheet.css");
menuScene.getStylesheets().add("application/stylesheet.css");
menuScene.setCursor(Cursor.HAND);
primaryStage.setScene(menuScene);
primaryStage.setMinHeight(780);
primaryStage.setMinWidth(1280);
primaryStage.show();

Stage secondaryStage = new Stage();
Stage thirdStage = new Stage();
Stage highscoresStage = new Stage();

VBox winBox = new VBox(5);
winBox.setId("winBox");
winBox.setAlignment(Pos.CENTER);
Label win = new Label("You won!");
win.setId("winnerText");
Text timeScore=new Text("Your time: ");
timeScore.setId("scoreText");
Text yourName = new Text("What is your name?");
yourName.setId("scoreText");
TextField nameField = new TextField();
nameField.setMaxWidth(160);
Button submitScore = new Button("Submit!");

VBox lossBox = new VBox();
lossBox.setId("lossBox");
lossBox.setAlignment(Pos.CENTER);
Label loss = new Label("You lost!");
loss.setId("loserText");

VBox highscoresBox = new VBox(10);
highscoresBox.setId("highscoresBox");
highscoresBox.setAlignment(Pos.TOP_CENTER);
highscoresBox.setPadding(new Insets(10));
Label highscoresLabel = new Label ("TOP 10 PLAYERS");
highscoresLabel.setId("cattegories");
GridPane highscoresGrid = createGrid(1,10,290);
highscoresGrid.setAlignment(Pos.TOP_CENTER);
Highscores highscoresList = new Highscores();

highscoresBox.getChildren().addAll(highscoresLabel,highscoresGrid);
lossBox.getChildren().addAll(loss);
winBox.getChildren().addAll(win,timeScore,yourName,nameField,submitScore);

int endScreenSize=200;
Scene lossScene = new Scene(lossBox,200,200);
lossScene.getStylesheets().add("application/stylesheet.css");
secondaryStage.setScene(lossScene);
secondaryStage.setMinHeight(endScreenSize);
secondaryStage.setMinWidth(endScreenSize);
secondaryStage.setMaxHeight(endScreenSize);
secondaryStage.setMaxWidth(endScreenSize);

Scene winScene = new Scene(winBox,200,200);
winScene.getStylesheets().add("application/stylesheet.css");
thirdStage.setScene(winScene);
thirdStage.setMinHeight(endScreenSize);
thirdStage.setMinWidth(endScreenSize);
thirdStage.setMaxHeight(endScreenSize);
thirdStage.setMaxWidth(endScreenSize);

Scene highscoresScene = new Scene(highscoresBox,300,500);
highscoresScene.getStylesheets().add("application/stylesheet.css");
highscoresStage.setScene(highscoresScene);
highscoresStage.setMinHeight(500);



time=timelimit;
Timeline timeline = new Timeline();
timeline.setCycleCount(Timeline.INDEFINITE);
timeline.getKeyFrames().add(new KeyFrame(
		Duration.seconds(1),
		new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				time--;
				timerLabel.setText(Integer.toString(time));
				if (time<=0) {
					System.out.println("Koniec czasu");
					heroes.fillAll();
					maps.fillAll();
					secondaryStage.show();
					timeline.stop();
					time=timelimit;
					
				}
			}
		}));

start.setOnMouseClicked(new EventHandler<MouseEvent>() {
	public void handle (MouseEvent event) {
		clearImages(heroAvatars,heroAvatars2,topPane);
		muteVolume(player);
		primaryStage.setScene(gameScene);
		time=timelimit;
		timerLabel.setText(Integer.toString(time));
		timeline.playFromStart();
		items=0;
		heroes.clearAll();
		heroesGrid.getChildren().clear();
		try {
			BufferedReader odczyt = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/resources/heroes.txt")));
			heroes.addToList(odczyt);
			}
		catch (IOException a) {System.out.println("File not found!");}
		catch (NullPointerException a) {System.out.println("File not found!");}
		heroes.fillTable(heroesGrid);
		maps.clearAll();
		mapsGrid.getChildren().clear();
		try {
			BufferedReader odczyt = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/resources/maps.txt")));
			maps.addToList(odczyt);
		}
		catch (IOException a) {System.out.println("File not found!");}
		catch (NullPointerException a) {System.out.println("File not found!");}
		maps.fillTable(mapsGrid);
		
	}
	
});
highscores.setOnMouseClicked(new EventHandler<MouseEvent>() {
	public void handle(MouseEvent event) {
		highscoresList.fillGrid(highscoresGrid);
		highscoresStage.show();
	}		
});

exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
	public void handle (MouseEvent event) {
		Platform.exit();
	}
});

Media correctSound = new Media(getClass().getResource("/resources/poom.mp3").toURI().toString());
MediaPlayer correct = new MediaPlayer(correctSound);
correct.setVolume(0.1);
Media wrongSound = new Media(getClass().getResource("/resources/wrong.mp3").toURI().toString());
MediaPlayer wrong = new MediaPlayer(wrongSound);
wrong.setVolume(0.1);

field.setOnKeyPressed(new EventHandler<KeyEvent>(){
	public void handle(KeyEvent enter) {
		if (enter.getCode().equals(KeyCode.ENTER)) {
			correct.stop();
			wrong.stop();
			String userInput = field.getText().toUpperCase();
			if (userInput.equals("IAMCHEATER")) {
				System.out.println("Cheat activated!");
				heroes.fillAll();
				maps.fillAll();
				items=heroes.getListSize()+maps.getListSize();
				nameField.setText("CHEATER");
				nameField.setEditable(false);
			}
			else if (heroes.find(userInput)) {
				correct.play();
				heroes.replace(userInput, "heroes");
				if (heroes.howMany()%2==1)
					showAvatar(heroAvatars,userInput.toLowerCase());
				else
					showAvatar(heroAvatars2,userInput.toLowerCase());
				items++;
			}	
			else if (maps.find(userInput)) {
				correct.play();
				if(userInput.length()>17)
					maps.replace(userInput, "mapslong");
				else
					maps.replace(userInput, "maps");
				items++;
				showMap(topPane,userInput.toLowerCase());
			}
			else
				wrong.play();
		if (items==(heroes.getListSize()+maps.getListSize())) {

			thirdStage.show();
			timeline.stop();
			score=timelimit-time;
			timeScore.setText("Your time: "+score+"s");
		}
		field.clear();
		}
	}
});	

nameField.setOnKeyPressed(new EventHandler<KeyEvent>(){
	public void handle(KeyEvent enter) {
		if (enter.getCode().equals(KeyCode.ENTER))
			submitScore.fire();
	}
});

submitScore.setOnAction(new EventHandler<ActionEvent>() {
	public void handle (ActionEvent submit) {
		if(nameField.getText().isEmpty())
			nameField.setText("Random Player");
		else if (nameField.getText().length()>18)
			nameField.setText("YOUR NAME IS TOO LONG");
		else {
		highscoresList.checkHighscore(nameField.getText()+" : "+(timelimit-time));
		nameField.clear();
		nameField.setEditable(true);
		primaryStage.setScene(menuScene);
		player.play();
		thirdStage.hide();
		}
	}
});
	
highscoresStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	public void handle (WindowEvent event) {
		highscoresStage.hide();
	}
});

thirdStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	public void handle (WindowEvent event) {
		primaryStage.setScene(menuScene);
		player.play();
		thirdStage.hide();
	}
});

secondaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	public void handle (WindowEvent event) {
		primaryStage.setScene(menuScene);
		player.play();
		secondaryStage.hide();
	}
});

primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	public void handle (WindowEvent event) {
		Platform.exit();
	}
});
}

public static void main (String args[]) { launch(args); }
	
public Label menuLabel(String name) {
	Label label=new Label(name);
	label.setId("menuLabel");
	label.setPrefHeight(40);
	label.setPadding(new Insets(0));
	
	label.setOnMouseEntered(new EventHandler<MouseEvent>() {
		public void handle(MouseEvent event) {
			label.setId("mouseIn");
		}
	});
	
	label.setOnMouseExited(new EventHandler<MouseEvent>() {
		public void handle(MouseEvent event) {
			label.setId("menuLabel");
		}
	});
	return label;
}

public GridPane createGrid (int colNr, int rowNr, int columnWidth) {
	
	GridPane grid = new GridPane();
	ColumnConstraints col = new ColumnConstraints(columnWidth);
	RowConstraints row = new RowConstraints(35);
	for (int i=0;i<colNr;i++) {
		grid.getColumnConstraints().add(col);
		}

	for (int i=0;i<rowNr;i++) {
		grid.getRowConstraints().add(row);
		}
	return grid;
}

public void muteVolume (MediaPlayer player) {
	Timeline timeline = new Timeline();
	timeline.setCycleCount(500);
	timeline.getKeyFrames().add(new KeyFrame (
			Duration.millis(10),
			new EventHandler <ActionEvent>() {
				public void handle(ActionEvent a){
					player.setVolume(player.getVolume()-0.002);
				}
			}
			));
	timeline.play();
	timeline.setOnFinished(new EventHandler<ActionEvent>() {
		public void handle (ActionEvent b) {
			player.stop();
			player.setVolume(1);
		}
	});
}

public void showAvatar (HBox avatar, String name) {
	Image avatarImage;
	try {
	avatarImage = new Image("/resources/avatars/"+name+".png",300,300,false,true);
	}
	catch (IllegalArgumentException v) {
	avatarImage = new Image("/resources/owlogo.png",300,300,false,false);
	}
	
	ImageView avatarView = new ImageView(avatarImage);
	avatar.getChildren().clear();
	avatar.getChildren().add(avatarView);
}

public void showMap (BorderPane map, String name) {
	String image;
	try {
	image = getClass().getResource("/resources/avatars/"+name+".jpg").toExternalForm();
	map.setStyle("-fx-background-image: url('" + image + "');"
			+ "-fx-background-size: 100%;"); 
	}
	catch (IllegalArgumentException v) {}
	catch (NullPointerException z) {}
}

public void clearImages(HBox avatar1, HBox avatar2, BorderPane map) {
	avatar1.getChildren().clear();
	avatar2.getChildren().clear();
	map.setStyle("");
}

}

