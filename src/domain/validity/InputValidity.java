package domain.validity;

public class InputValidity {

	public boolean isValidObstacleNumber(String str) {
		// EFFECTS: If str corresponds to a valid obstacle number between 0 and 250 returns true,
		//			else returns false
		
		try {
			long input = Long.parseLong(str);

			if (input > Integer.MAX_VALUE) {		// Overflow
				return false;
				
			} else if (input < Integer.MIN_VALUE) {	// Underflow
				return false;
			}

			if (input < 0 || input > 250) {			
				return false;
			}

			return true;
		} catch (NumberFormatException e) {			
			return false;
		}
	}

	public boolean isValidCredential(String username, String password) throws NullPointerException {
		//EFFECTS: If the username and password do not contain any illegal characters and if password contains 1 uppercase letter, return true
		//else if username is null, throws NullPointer exception, 
	    //else return false
		String illegalCharacters = ". # % & { } < > * ? / $ ! : @ | = ` ' \\ \" ";

		if (username == "") {
			throw new NullPointerException();
		}

		int index = 0;
		while (index < illegalCharacters.length()) {
			if (username.contains(illegalCharacters.substring(index, index + 1))
					|| password.contains(illegalCharacters.substring(index, index + 1))) {
				return false;
			}
			index++;
		}

		if (password.toLowerCase().contentEquals(password)) {
			return false;
		}

		return true;
	}
}