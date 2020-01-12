///*----------------------------------------------------------------------------*/
///* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
///* Open Source Software - may be modified and shared by FRC teams. The code   */
///* must be accompanied by the FIRST BSD license file in the root directory of */
///* the project.                                                               */
///*----------------------------------------------------------------------------*/
//
package frc.robot;

//
///**
// * The RobotMap is a mapping from the ports sensors and actuators are wired into
// * to a variable name. This provides flexibility changing wiring, makes checking
// * the wiring easier and significantly reduces the number of magic numbers
// * floating around.
// */
public class RobotMap {
	// // For example to map the left and right motors, you could define the
	// // following variables to use with your drivetrain subsystem.
	// // public static int leftMotor = 1;
	// // public static int rightMotor = 2;
	//
	// // If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;

	// DRIVE
	public static final int TALON_1 = 0;
	public static final int TALON_2 = 1;

	// INTAKE
	public static final int INTAKETALON_1 = 2;
	public static final int INTAKETALON_2 = 3;

	// CONVEYOR
	public static final int CONVEYORTALON_1 = 4;
	public static final int CONVEYORTALON_2 = 5;

	// SHOOTER
	public static final int SHOOTERTALON_1 = 6;
	public static final int SHOOTERTALON_2 = 7;

	// WINCH
	public static final int WINCHTALON = 8;

	// WINCH LIMIT SWITCH (DIO port #)
	public static final int WINCH_LIMIT_SWITCH = 4;

	// SOLENOID
	public static final int DRIVESOL1 = 1;
	public static final int DRIVESOL2 = 6;

	public static final int INTAKESOL1 = 0;
	public static final int INTAKESOL2 = 7;

	public static final int LIFTSOL1 = 2;
	public static final int LIFTSOL2 = 5;

	public static final int THIRDWHEEL1 = 2;
	public static final int THIRDWHEEL2 = 5;
}
