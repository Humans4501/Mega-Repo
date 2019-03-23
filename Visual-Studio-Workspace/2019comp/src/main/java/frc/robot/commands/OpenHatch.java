/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.HatchManipulator;

public class OpenHatch extends Command {
  // boolean toggled;

  public OpenHatch() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.hatchManipulator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    // if (toggled){
    //   toggled = false;
    // }else{
    //   toggled = true;
    // }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // if(toggled){
    //   Robot.hatchManipulator.openHatch();
    // }else{
    //   Robot.hatchManipulator.closeHatch();
    // }
    Robot.hatchManipulator.openHatch();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
