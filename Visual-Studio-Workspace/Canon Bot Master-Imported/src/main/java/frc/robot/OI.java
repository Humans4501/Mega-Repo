/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public static final int TRIGGER = 1, BUTTON_2 = 2, BUTTON_3 = 3, BUTTON_4 = 4, BUTTON_5 = 5, BUTTON_6 = 6,
			BUTTON_7 = 7, BUTTON_8 = 8, BUTTON_9 = 9, BUTTON_10 = 10, BUTTON_11 = 11;
	public static final int X = 3,  A = 1, Y = 4, B = 2, RB = 6, RT = 10, LB = 5, LT = 9, START = 12;

	XboxController controller = new XboxController(0);
	XboxController controller2 = new XboxController(1);
	Button fireSol = new JoystickButton(controller, RB);
	Button loadSol = new JoystickButton(controller, LB);
	Button loadAuto = new JoystickButton (controller, X);
	Button fireAuto = new JoystickButton (controller, A);

	public OI() {
		fireSol.whileHeld(new StartShoot());
		fireSol.whenReleased(new StopShoot());
		loadSol.whileHeld(new StartLoad());
		loadSol.whenReleased(new StopLoad());
		loadAuto.whenPressed(new SmartLoad60());
		fireAuto.whenPressed(new SmartShoot());
	}


	


	public double getTriggers() {
		return controller.getRawAxis(XboxController.TRIGGER_R) - controller.getRawAxis(XboxController.TRIGGER_L);
	}

	public double getRightTrigger() {
		return controller.getRawAxis(XboxController.TRIGGER_R);

	}

	public double getLeftTrigger() {
		return controller.getRawAxis(XboxController.TRIGGER_L);

	}

	public double getLeftXboxX() {
		if(Math.abs(controller.getRawAxis(0)) < 0.125){
			return 0;
		}else{
			return controller.getRawAxis(0);
		}
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

	public boolean getX() {
		return controller.getRawButton(3);
	}
	public boolean getY() {
		return controller.getRawButton(4);
	}
	public boolean getX2() {
		return controller2.getRawButton(3);
	}
	public boolean getY2() {
		return controller2.getRawButton(4);
	}
	public boolean getStart2() {
		return controller2.getRawButton(8);
	}
	public boolean getBack2() {
		return controller2.getRawButton(7);
	}
	public boolean getBumperR(){
		return controller2.getRawButton(5);
	}
	public boolean getBumperL(){
		return controller2.getRawButton(6);
	}
	public double getPOV2(){
		return controller2.getPOV();
	}
}
