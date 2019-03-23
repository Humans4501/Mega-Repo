/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.StayStraightPID;

public class DriveFor8Inches extends Command {
  double goal;
  boolean done;
  int counter = 0;
  public DriveFor8Inches() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drivetrain);
    requires(Robot.stayStraightPID);
    requires(Robot.movePID);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    done = false;
    Robot.movePID.setDistance(8);

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // System.out.println("I am driving straight");

    Robot.drivetrain.driveTime(Robot.movePID.speed, Robot.stayStraightPID.adjustment);
    if (Robot.stayStraightPID.isStraight() && Robot.movePID.isDone() && counter >= 10){
      counter = 0;
      done = true;
    }else{
      counter += 1;
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
    Robot.drivetrain.driveTime(0,0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
