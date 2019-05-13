/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ArmDefault extends Command {
  public ArmDefault() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.arm);
    requires(Robot.armPID);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // if(Robot.oi.getPOV2() == 0){
    //   Robot.armPID.setTarget(130);
    // }else if(Robot.oi.getPOV2() == 90){
    //   Robot.armPID.setTarget(100);
    // }else if(Robot.oi.getPOV2() == 270){
    //   Robot.armPID.setTarget(70);
    // }else if(Robot.oi.getPOV2() == 180){
    //   Robot.armPID.setTarget(1);
    // }
    if(Robot.oi.getBack2()){
      Robot.arm.goArm(0.75 * Robot.oi.getRightXboxY2());
      Robot.armPID.setTarget(1);
    }else{
      Robot.arm.goArm(Robot.armPID.speed);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }
}