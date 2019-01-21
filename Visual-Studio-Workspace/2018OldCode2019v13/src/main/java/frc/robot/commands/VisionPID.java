package frc.robot.commands;

import frc.robot.MovePID;
import frc.robot.TurnPID;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class VisionPID extends Command {
	
	MovePID movePID;
	TurnPID turnPID;

    public VisionPID() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	movePID = new MovePID();
    	turnPID = new TurnPID();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	movePID.enable();
    	turnPID.enable();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	movePID.disable();
    	turnPID.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
