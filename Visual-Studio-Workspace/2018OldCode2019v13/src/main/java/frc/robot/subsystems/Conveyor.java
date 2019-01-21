package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.commands.GoConveyor;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Conveyor extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	Talon ctalon1, ctalon2;

	RobotDrive conveyor;

	public Conveyor() {
		ctalon1 = new Talon(RobotMap.CONVEYORTALON_1);
		ctalon2 = new Talon(RobotMap.CONVEYORTALON_2);

		conveyor = new RobotDrive(ctalon1, ctalon2);
	}

	public void convey(double leftValue, double rightValue) {
		conveyor.tankDrive(-leftValue, -rightValue);
		conveyor.setSafetyEnabled(false);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new GoConveyor());
	}
}
