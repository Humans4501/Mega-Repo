package frc.robot; 
import edu.wpi.first.wpilibj.Joystick;

public class XboxController extends Joystick {

	// Stick Maps
	public static final int STICK_LX = 0, STICK_LY = 1,
			TRIGGER_L = 2, TRIGGER_R = 3,
			STICK_RX = 4, STICK_RY = 5;

	// Button Maps
	public static final int BUTTON_A = 1, BUTTON_B = 2, BUTTON_X = 3,
			BUTTON_Y = 4, BUMPER_R = 5, BUMPER_L = 6, BUTTON_BACK = 7,
			BUTTON_START = 8, BUTTON_STICKL = 9, BUTTON_STICKR = 10;
	
	public XboxController(int port) {
		super(port);
	}
	
	public void setRumble(RumbleType type, float value) {
		super.setRumble(type, value);
	}

}
