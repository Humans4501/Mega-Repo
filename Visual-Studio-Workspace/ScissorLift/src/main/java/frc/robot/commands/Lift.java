package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.ScissorLift;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Lift extends Command {

    public Lift() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.scissorlift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.scissorlift.resetLimitSwitch();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.instance.isTest() || !Robot.instance.isAutonomous()){
            // if the limit switch is clicked and the movement is up then don't move
            // if the switch is not clicked move
            // if the switch is clicked but the movement is down then move lift
            System.out.println(Robot.scissorlift.isLimitSwitchSet());
            if(!Robot.scissorlift.isLimitSwitchSet() || -Robot.oi.getRightXboxY() < 0){
                Robot.scissorlift.LiftMove(-Robot.oi.getRightXboxY());
            }else{
                Robot.scissorlift.LiftMove(0);
            }
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.scissorlift.LiftMove(0);
    	System.out.println("HAS ENDED");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
