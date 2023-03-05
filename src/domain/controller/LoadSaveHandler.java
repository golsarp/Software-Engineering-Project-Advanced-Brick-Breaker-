package domain.controller;

import java.io.File;

import domain.helpers.FileAccessor;

public class LoadSaveHandler {
	//OVERVIEW: LoadSaveHandler is the class responsible for operations regarding save and load functionalities
	//			Receives messages from ui, delivers to FileAccessor

	private static LoadSaveHandler instance;
	private FileAccessor fileAccessor ;

	

	private LoadSaveHandler() {
		fileAccessor = new FileAccessor();
	}

	public static LoadSaveHandler getInstance() {
		if (instance == null) {
			instance = new LoadSaveHandler();
		}
		return instance;
	}

	public void loadGame(String path) {

		fileAccessor.loadGame(path);
	}

	public void saveGame() {

		fileAccessor.saveGame();
	}

	public String getPathSymbol() {

		return fileAccessor.getPathSymbol();
	}

	public String isRegistered(String username, String password) {
		return fileAccessor.isRegistered(username, password);
	}

	public String isAuthenticated(String username, String password) {
		return fileAccessor.isAuthenticated(username, password);
	}

	public File[] gameList() {
		return fileAccessor.gameList();
	}

	public String[] stringList(File[] flist) {
		return fileAccessor.stringList(flist);
	}
	
	public void loadScoreBoard() {
		
		 fileAccessor.loadScoreBoard();
	}
	public String [] getTop_five_users() {
		return fileAccessor.getTop_five_users();
	}

	public void setTop_five_users(String [] top_five_users) {
	fileAccessor.setTop_five_users(top_five_users);
	}

	public int [] getTop_five_scores() {
		return fileAccessor.getTop_five_scores();
	}

	public void setTop_five_scores(int [] top_five_scores) {
		fileAccessor.setTop_five_scores(top_five_scores);
	}
	
	public FileAccessor getFileAccessor() {
		return fileAccessor;
	}

	public void setFileAccessor(FileAccessor fileAccessor) {
		this.fileAccessor = fileAccessor;
	}
	
	
	
	
	
	

}
