package frc.robot.commands;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Uses the GyroTurnSubsystem to turn to the specified angle.
 * 
 * According to <a href="https://www.pdocs.kauailabs.com/navx-mxp/examples/rotate-to-angle-2/">
 * this page</a>, it's best to rotate in place. Hence we use tankDrive instead of arcadeDrive.
 */
public class GyroTurn extends Command {
	double angle;

	public GyroTurn(double angle) {
		requires(Robot.driveTrain);
		requires(Robot.analogGyroTurn);
		this.angle = angle;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.analogGyroTurn.setRelativeAngle(angle);
		Robot.analogGyroTurn.enable();
		Robot.driveTrain.tankDrive(0, 0);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		System.out.printf("angle=%.2f target=%.2f rotateSpeed=%.2f\n",
				Robot.analogGyroTurn.safeGetAngle(),
				Robot.analogGyroTurn.targetAngle,
				Robot.analogGyroTurn.rotateSpeed);
		Robot.driveTrain.tankDrive(-Robot.analogGyroTurn.rotateSpeed, Robot.analogGyroTurn.rotateSpeed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.analogGyroTurn.isTurningDone();
	}

	// Called once after isFinished returns true
	protected void end() {
		System.out.printf("angle=%.2f target=%.2f rotateSpeed=%.2f\n",
				Robot.analogGyroTurn.safeGetAngle(),
				Robot.analogGyroTurn.targetAngle,
				Robot.analogGyroTurn.rotateSpeed);
		Robot.driveTrain.tankDrive(0, 0);
		Robot.analogGyroTurn.disable();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
