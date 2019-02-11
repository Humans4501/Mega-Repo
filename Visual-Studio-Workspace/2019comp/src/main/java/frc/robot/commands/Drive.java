package frc.robot.commands;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Drive extends Command {
    public Drive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.instance.isTest() || !Robot.instance.isAutonomous()){
        // Robot.drivetrain.tankDrive(Robot.oi.getJoy1Y(), Robot.oi.getJoy2Y());
        Robot.drivetrain.driveTime(Robot.oi.getLeftXboxY(), Robot.oi.getLeftXboxX());
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.driveTime(0, 0);
    	System.out.println("HAS ENDED");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
