 package org.usfirst.frc.team4501.robot.commands;

 import org.usfirst.frc.team4501.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
//
// import edu.wpi.first.wpilibj.command.Command;
//
/// **
// *
// */
 public class ShiftLow extends Command {
//
public ShiftLow() {
// // Use requires() here to declare subsystem dependencies
// // eg. requires(chassis);
requires(Robot.driveTrain);
 }
//
// // Called just before this Command runs the first time
protected void initialize() {
 }
//
// // Called repeatedly when this Command is scheduled to run
  public void execute() {
   Robot.driveTrain.shiftLow();
   
}
//
// // Make this return true when this Command no longer needs to run execute()
 protected boolean isFinished() {
		return Robot.driveTrain.isShifted();
	 }
//
// // Called once after isFinished returns true
 protected void end() {
 }
//
// // Called when another command which requires one or more of the same
// // subsystems is scheduled to run
 protected void interrupted() {
}
}
