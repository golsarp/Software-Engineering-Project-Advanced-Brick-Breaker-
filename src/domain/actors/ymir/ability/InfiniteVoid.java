package domain.actors.ymir.ability;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import domain.controller.GameController;
import domain.objects.obstacle.ObstacleConfiguration;
import domain.objects.obstacle.types.Obstacle;
import domain.timertask.InfiniteVoidTask;

public class InfiniteVoid implements YmirAbility{
	private String name = "INFINITE VOID";
	private ArrayList<TimerTask> timerTaskList;
	private ArrayList<TimerTask> savedTaskList;   //saved timer tasks to be retrieved after the game is paused
	ObstacleConfiguration obstacleConfiguration = GameController.getInstance().getObstacleConfiguration();

	@Override
	public void activate() {
		// TODO Auto-generated method stub
		int frozenCount = obstacleConfiguration.getListOfObstacles().size();
		if (frozenCount > 8){
			frozenCount = 8;
		}
		timerTaskList = new ArrayList<TimerTask>();
		savedTaskList = new ArrayList<TimerTask>();
		Random random = new Random();
		while(frozenCount != 0) {
			
			//get a random obstacle index
			int index = random.nextInt(obstacleConfiguration.getListOfObstacles().size());
			
			//freeze the obstacle at that index
			Obstacle o = obstacleConfiguration.getListOfObstacles().get(index);

			o.setFrozen(true);

			Timer timer = new Timer(true);
			
		    TimerTask timerTask = new InfiniteVoidTask(timer, o); 
		    TimerTask savedTask = new InfiniteVoidTask(timer, o); 
		    
		    timerTaskList.add(timerTask);
		    savedTaskList.add(savedTask);
		    
		    frozenCount--;
		}
		scheduleTasks(timerTaskList); 

	}
	@Override
	public String getName() {
		return name;
	}
	public ArrayList<TimerTask> getTimerTaskList() {
		return timerTaskList;
	}
	public ArrayList<TimerTask> getSavedTaskList() {
		return savedTaskList;
	}
	public void setSavedTaskList(ArrayList<TimerTask> savedTaskList) {
		this.savedTaskList = savedTaskList;
	}
	public void scheduleTasks(ArrayList<TimerTask> taskList) {
		timerTaskList = taskList;
		savedTaskList = new ArrayList<TimerTask>();			//save the tasks for later scheduling
		for (TimerTask task: taskList) {

			InfiniteVoidTask task1 = (InfiniteVoidTask) task;
			TimerTask savedTask = new InfiniteVoidTask(task1.getTimer(), task1.getObstacle()); 
			savedTaskList.add(savedTask);			
			
			Timer timer = new Timer();
			timer.schedule(task1, 15*1000);
		}
	}
	
	public void cancelTasks(ArrayList<TimerTask> taskList) {
		for (TimerTask task: taskList) {
			task.cancel();
		}
	}
}
