package application;

public class Highscore {

private String name;
private int score;
private String highscore;

public Highscore() {
	
}

public Highscore(String name,int score) {
	this.name=name;
	this.score=score;
	this.highscore=name+" : "+score;
}

public Highscore(String highscore) {
	this.highscore=highscore;
	this.splitIt();
}

public void splitIt() {
	if (highscore!=null) {
		String parts[]=highscore.split(" : ");
		name=parts[0];
		score=Integer.parseInt(parts[1]);
	}
		
}


public void setName(String name) {
	this.name=name;
}

public void setScore(int score) {
	this.score=score;
}

public void setHighscore (String highscore) {
this.highscore=highscore;
this.splitIt();
}

public String getName() {
	return name;
}

public int getScore() {
	return score;
}

public String getHighscore () {
	return highscore;
}
}
