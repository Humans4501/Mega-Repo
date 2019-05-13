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

public class DriveStraightForDistance extends Command {
  double goal;
  boolean done;
  int counter = 0;
  public DriveStraightForDistance(double distance) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drivetrain);
    requires(Robot.stayStraightPID);
    requires(Robot.movePID);
    goal = distance;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.movePID.encr.reset();
    Robot.movePID.encl.reset();
    Robot.stayStraightPID.setTarget(Robot.stayStraightPID.ahrs.getAngle());
    done = false;
    Robot.movePID.setDistance(goal);
    System.out.println("Running drive for distance");
    Robot.movePID.enable();
    Robot.stayStraightPID.enable();

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // System.out.println("I am driving straight");

    Robot.drivetrain.driveTime(Robot.movePID.speed, Robot.stayStraightPID.adjustment);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {

    return Robot.movePID.isDone();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drivetrain.driveTime(0,0);
    Robot.movePID.encr.reset();
    Robot.movePID.encl.reset();
    done = false;
    Robot.movePID.disable();
    Robot.stayStraightPID.disable();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.movePID.disable();
    Robot.stayStraightPID.disable();
  }
}
