/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.commands.Delay;

public class AlignLimelight extends Command {
  double turn = 0;
  public AlignLimelight() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drivetrain);
    requires(Robot.limelightAlignmentLeft);
    requires(Robot.limelightAlignmentRight);
    requires(Robot.limelightMoveForewardWidth);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.limelightAlignmentLeft.lightsOn();
    Robot.limelightAlignmentLeft.enable();
    Robot.limelightAlignmentRight.enable();
    Robot.limelightMoveForewardWidth.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.limelightAlignmentRight.x > 0){
      turn = Robot.limelightAlignmentRight.currOutput;
    }else{
      turn = Robot.limelightAlignmentLeft.currOutput;
    }
    Robot.drivetrain.driveTime(Robot.limelightMoveForewardWidth.currOutput, -turn);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {

    return (Robot.limelightMoveForewardWidth.done && Robot.limelightAlignmentLeft.done);
    // return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    // Robot.limelightAlignmentLeft.lightsOff();
    Robot.limelightAlignmentRight.done = false;
    Robot.limelightAlignmentLeft.done = false;
    Robot.limelightMoveForewardWidth.done = false;
    Robot.limelightAlignmentLeft.disable();
    Robot.limelightAlignmentRight.disable();
    Robot.limelightMoveForewardWidth.disable();
    Robot.drivetrain.driveTime(0,0);
    System.out.println("ending limelight alignment");
  }


  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.limelightAlignmentLeft.disable();
    Robot.limelightAlignmentRight.disable();
    Robot.limelightMoveForewardWidth.disable();
  }
}
