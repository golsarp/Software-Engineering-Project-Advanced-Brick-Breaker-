package domain.helpers;

import java.util.concurrent.Semaphore;

public class GlobalMutex {
	
	static GlobalMutex  instance;
	Semaphore semaphore = new Semaphore(1, true);
	
	private GlobalMutex() {}
	
	public static GlobalMutex getInstance() {
		if(instance == null) {
			instance = new GlobalMutex();
		}
		return instance;
	}
	
	public Semaphore getSemaphore() {
		return semaphore;
	}

	public void setSemaphore(Semaphore semaphore) {
		this.semaphore = semaphore;
	}

}
