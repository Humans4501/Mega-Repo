/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.*;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public static final int TRIGGER = 1, BUTTON_2 = 2, BUTTON_3 = 3, BUTTON_4 = 4, BUTTON_5 = 5, BUTTON_6 = 6,
			BUTTON_7 = 7, BUTTON_8 = 8, BUTTON_9 = 9, BUTTON_10 = 10, BUTTON_11 = 11;

	XboxController controller = new XboxController(0);
	XboxController controller2 = new XboxController(1);
	Joystick joystick1 = new Joystick(2);
	Joystick joystick2 = new Joystick(3);
	Button GoWinch = new JoystickButton(controller2, controller2.BUMPER_L);
	Button GoWinchBack = new JoystickButton(controller2, controller2.BUMPER_R);
	Button HatchClose = new JoystickButton(controller, controller.BUTTON_A);
	Button HatchOpen = new JoystickButton(controller, controller.BUTTON_X);
	Button HatchPushOff = new JoystickButton(controller, controller.BUTTON_B);
	Button HatchPush = new JoystickButton(controller, controller.BUTTON_Y);
	Button ArmScore = new JoystickButton(controller2, controller2.BUTTON_X);
	Button ArmIntake = new JoystickButton(controller2, controller2.BUTTON_A);
	Button ArmScoreHigh = new JoystickButton(controller2, controller2.BUTTON_Y);
	public OI() {
	GoWinch.whileHeld(new GoWinch());
	GoWinch.whenReleased(new StopWinch());
	GoWinchBack.whileHeld(new GoWinchBack());
	GoWinchBack.whenReleased(new StopWinch());
	HatchOpen.whenPressed(new OpenHatch());
	HatchClose.whenPressed(new CloseHatch());
	HatchPush.whenPressed(new PushHatch());
	HatchPushOff.whenPressed(new PushHatchOff());

	ArmScore.whenPressed(new ArmScoreBall());
	ArmIntake.whenPressed(new ArmIntakeBall());
	ArmScoreHigh.whenPressed(new ArmScoreHigh());
	


	}

	public double getJoy1Y(){
		return joystick1.getY();
	}
	public double getJoy2Y(){
		return joystick2.getY();
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
		return controller2.getRawAxis(XboxController.TRIGGER_R) - controller.getRawAxis(XboxController.TRIGGER_L);
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

	public boolean getX() {
		return controller.getRawButton(3);
	}
	public boolean getY() {
		return controller.getRawButton(4);
	}
	public boolean getBumperR(){
		return controller.getRawButton(5);
	}
	public boolean getBumperL(){
		return controller.getRawButton(6);
	}
}
