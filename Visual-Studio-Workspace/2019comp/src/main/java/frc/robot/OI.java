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
import java.math.*;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public static final int TRIGGER = 1, BUTTON_2 = 2, BUTTON_3 = 3, BUTTON_4 = 4, BUTTON_5 = 5, BUTTON_6 = 6,
			BUTTON_7 = 7, BUTTON_8 = 8, BUTTON_9 = 9, BUTTON_10 = 10, BUTTON_11 = 11;
	public static final int X = 4,  A = 1, Y = 5, B = 2, RB = 8, RT = 10, LB = 7, LT = 9, START = 12;

	XboxController controller = new XboxController(0);
	XboxController controller2 = new XboxController(1);
	Joystick fightstick = new Joystick(2);
	Button GoWinch = new JoystickButton(controller, controller.BUMPER_L);
	Button GoWinchBack = new JoystickButton(controller, controller.BUMPER_R);
	// Button HatchClose = new JoystickButton(controller, controller.BUTTON_A);

	Button HatchOpen = new JoystickButton(controller2, controller2.BUMPER_R);
	// Button HatchPushOff = new JoystickButton(controller, controller.BUTTON_B);
	Button HatchPush = new JoystickButton(controller2, controller2.BUMPER_L);
	// Button AutonimousAssist = new JoystickButton(controller2, controller2.BUTTON_START);
	Button StopAutonimous = new JoystickButton(controller, controller.BUTTON_A);
	Button StopAutonimous2 = new JoystickButton(fightstick, START);
	// Button AutoTesters = new JoystickButton(controller2, controller2.BUTTON_B);
	// Button AutoHatch = new JoystickButton(controller2, controller2.BUTTON_A);
	// Button AutoCargo = new JoystickButton(controller2, controller2.BUTTON_X);
	// Button AutoLoadHatch = new JoystickButton(controller2, controller2.BUTTON_Y);

	Button AutoRamp = new JoystickButton(fightstick, LT);

	Button AutoHatch = new JoystickButton(fightstick, X);
	Button AutoCargo = new JoystickButton(fightstick, Y);
	Button AutoLoadHatch = new JoystickButton(fightstick, A);
	Button AutoLoadCargo = new JoystickButton(fightstick, B);
	Button AutoCargoL1 = new JoystickButton(fightstick, RB);
	Button AutoHatchHigh = new JoystickButton(fightstick, LB);
	// Button AutonimousAssist = new JoystickButton(fightstick, RT);
	Button AutoTesters = new JoystickButton(fightstick, RT);

	Button ArmHigh = new JoystickButton(controller2, controller2.BUTTON_Y);
	Button ArmCargoL1 = new JoystickButton(controller2, controller2.BUTTON_B);
	Button ArmHatchL2 = new JoystickButton(controller2, controller2.BUTTON_X);
	Button ArmLow = new JoystickButton(controller2, controller2.BUTTON_A);




	Button PushRamp = new JoystickButton(controller, controller.BUTTON_BACK);

	public OI() {
		ArmHigh.whenPressed(new ArmScoreHigh());
		ArmCargoL1.whenPressed(new ArmScoreBall());
		ArmHatchL2.whenPressed(new ArmScoreHatchHigh());
		ArmLow.whenPressed(new ArmIntakeBall());


		GoWinch.whileHeld(new GoWinch());
		GoWinch.whenReleased(new StopWinch());
		GoWinchBack.whileHeld(new GoWinchBack());
		GoWinchBack.whenReleased(new StopWinch());

		PushRamp.whileHeld(new PushRamp());
		PushRamp.whenReleased(new RetractRamp());

		HatchOpen.whenPressed(new OpenHatch());
		HatchPush.whenPressed(new CloseHatch());
		HatchPush.whileHeld(new PushHatch());
		HatchPush.whenReleased(new PushHatchOff());


		// AutonimousAssist.whenPressed(new AlignLimelight());
		StopAutonimous.whenPressed(new StopDrive());
		StopAutonimous2.whenPressed(new StopDrive());
		AutoTesters.whenPressed(new FineAlignLimelight());
		AutoHatch.whenPressed(new AutoHatch());
		AutoCargo.whenPressed(new AutoCargo());
		AutoLoadHatch.whenPressed(new AutoLoadHatch());
		AutoRamp.whenPressed(new BackupAndDeployRamp());
		AutoLoadCargo.whenPressed(new AutoLoadCargo());
		AutoHatchHigh.whenPressed(new AutoHatchL2());
		AutoCargoL1.whenPressed(new AutoCargoL1());




	}


	public double getTriggers() {
		return controller.getRawAxis(XboxController.TRIGGER_R) - controller.getRawAxis(XboxController.TRIGGER_L);
	}

	public double getLeftXboxX() {
		if(controller.getRawAxis(0) < 0.05 && controller.getRawAxis(0) > -0.05){
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
