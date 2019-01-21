package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.commands.Drive;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
	public static Drivetrain instance;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	Talon left, right;
	RobotDrive drive;

	DoubleSolenoid driveSol;

	DoubleSolenoid.Value desiredValue;

	public Drivetrain() {
		instance = this;

		left = new Talon(RobotMap.TALON_1);
		right = new Talon(RobotMap.TALON_2);



		drive = new RobotDrive(left, right);
	}

	public void driveTime(double forward, double rotate) {
		drive.arcadeDrive(-forward, -rotate);
		drive.setSafetyEnabled(false);
	}

	public void arcadeDrive(double forward, double rotate) {
		drive.arcadeDrive(forward, rotate);
	}

	public void tankDrive(double leftValue, double rightValue) {
		drive.tankDrive(leftValue, rightValue);
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new Drive());
	}

	public void shiftHigh() {
		desiredValue = DoubleSolenoid.Value.kForward;

		driveSol.set(desiredValue);
	}

	public void shiftLow() {
		desiredValue = DoubleSolenoid.Value.kReverse;
		driveSol.set(desiredValue);
	}

	public boolean isShifted() {
		return driveSol.get() == desiredValue;

	}

}
