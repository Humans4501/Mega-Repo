/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class TurnDegrees extends Command {
  double target;
  public TurnDegrees(double degrees) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);'
    requires(Robot.turnPID);
    requires(Robot.drivetrain);
    target = degrees;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    
    Robot.turnPID.setTarget(target);
    Robot.turnPID.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // System.out.println("turn to 90: " + Robot.turnPID.speed);
    // SmartDashboard.putNumber("turn speed", Robot.turnPID.speed);
   Robot.drivetrain.driveTime(0, Robot.turnPID.speed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.turnPID.turnDone();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drivetrain.driveTime(0, 0);
    Robot.turnPID.disable();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.turnPID.disable();
  }
}
