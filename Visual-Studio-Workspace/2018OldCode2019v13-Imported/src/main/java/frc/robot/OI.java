package frc.robot;

import frc.robot.commands.Eject;
import frc.robot.commands.Intake2Close;
import frc.robot.commands.Intake2Open;
import frc.robot.commands.IntakeClose;
import frc.robot.commands.IntakeOpen;
import frc.robot.commands.IntakeStop;
import frc.robot.commands.JustIntake;
import frc.robot.commands.ShiftHigh;
import frc.robot.commands.ShiftLow;
import frc.robot.commands.StopEverything;
import frc.robot.commands.WinchClimb;
import frc.robot.commands.WinchHoldToRaiseArm;
import frc.robot.commands.WinchStop;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	public static final int TRIGGER = 1, BUTTON_2 = 2, BUTTON_3 = 3, BUTTON_4 = 4, BUTTON_5 = 5, BUTTON_6 = 6,
			BUTTON_7 = 7, BUTTON_8 = 8, BUTTON_9 = 9, BUTTON_10 = 10, BUTTON_11 = 11;

	XboxController controller = new XboxController(0);
	XboxController controller2 = new XboxController(1);

	// Shifting
	Button shiftHigh = new JoystickButton(controller, controller.BUTTON_A);
	Button shiftLow = new JoystickButton(controller, controller.BUTTON_B);

	// Intake
	Button intakeOpen = new JoystickButton(controller2, controller2.BUTTON_A);
	Button intakeClose = new JoystickButton(controller2, controller2.BUTTON_B);

	Button justIntake = new JoystickButton(controller2, controller2.BUMPER_R);

	Button intake2Open = new JoystickButton(controller, controller.BUTTON_X);
	Button intake2Close = new JoystickButton(controller, controller.BUTTON_Y);
	// Command Groups

	Button ejection = new JoystickButton(controller2, controller2.BUTTON_START);

	// WINCH
	Button winchHoldToRaise = new JoystickButton(controller2, controller2.BUTTON_X);
	Button winchClimb = new JoystickButton(controller2, controller2.BUTTON_Y);

	public OI() {
		shiftHigh.whenPressed(new ShiftLow());
		shiftHigh.whenReleased(new ShiftHigh());

		intakeOpen.whenPressed(new IntakeOpen());
		intakeClose.whenPressed(new IntakeClose());

		justIntake.whenPressed(new JustIntake());
		justIntake.whenReleased(new IntakeStop());

		intake2Open.whenPressed(new Intake2Open());
		intake2Close.whenPressed(new Intake2Close());

		ejection.whileHeld(new Eject());
		ejection.whenReleased(new StopEverything());

		winchHoldToRaise.whenPressed(new WinchHoldToRaiseArm());
		winchHoldToRaise.whenReleased(new WinchStop());

		winchClimb.whileHeld(new WinchClimb());
		winchClimb.whenReleased(new WinchStop());
	}

	public double getTriggers() {
		return controller.getRawAxis(XboxController.TRIGGER_R) - controller.getRawAxis(XboxController.TRIGGER_L);
	}

	public double getLeftXboxX() {
		return controller.getRawAxis(0);
	}

	public double getLeftXboxY() {
		return controller.getRawAxis(1);
	}

	public double getRightXboxX() {
		return controller.getRawAxis(4);
	}

	public double getRightXboxY() {
		return controller.getRawAxis(5);
	}

	public double getTriggers2() {
		return controller2.getRawAxis(XboxController.TRIGGER_R) - controller2.getRawAxis(XboxController.TRIGGER_L);
	}

	public double getRightTrigger2() {
		return controller2.getRawAxis(XboxController.TRIGGER_R);

	}

	public double getLeftTrigger2() {
		return controller2.getRawAxis(XboxController.TRIGGER_L);
	}

	public double getLeftXboxX2() {
		return controller2.getRawAxis(0);
	}

	public double getLeftXboxY2() {
		return controller2.getRawAxis(1);
	}

	public double getRightXboxX2() {
		return controller2.getRawAxis(4);
	}

	public double getRightXboxY2() {
		return controller2.getRawAxis(5);
	}

}
