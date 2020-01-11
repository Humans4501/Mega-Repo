/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class FineAlignLimelight extends Command {
  double turn = 0;
  public FineAlignLimelight() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drivetrain);
    requires(Robot.limelightFineAdjustment);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.limelightFineAdjustment.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.drivetrain.driveTime(0, -Robot.limelightFineAdjustment.currOutput);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    
    return (Robot.limelightFineAdjustment.done);
    // return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.limelightFineAdjustment.done = false;
    Robot.limelightFineAdjustment.disable();
    Robot.drivetrain.driveTime(0,0);
  }


  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
