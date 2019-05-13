/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveForTime extends Command {
  long delayMsec;
  long expiry;
  boolean done = false;
  public DriveForTime(long msec) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    delayMsec = msec;
    requires(Robot.drivetrain);
  }

  @Override
  protected void initialize() {
    expiry = System.currentTimeMillis() + delayMsec;
    done = false;
  }

  @Override
  protected void execute() {
    Robot.drivetrain.driveTime(0.8,0);
    if (System.currentTimeMillis() >= expiry){
      done = true;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    
    return done;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drivetrain.driveTime(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
