/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PIDBase.Tolerance;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;
import frc.robot.Robot;

public class LimelightAim extends PIDSubsystem {
  static double Kp = 0.14;
  static double Ki = 0.055;
  static double Kd = 0.01;

  public double currOutput;


  /**
   * Creates a new LimelightAim.
   */
  public LimelightAim() {
    super(
        // The PIDController used by the subsystem
        new PIDController(Kp, Ki, Kd));
        getController().setTolerance(Constants.kLimelightTolerance, Constants.kLimelightToleranceCPS);
        
  }

  @Override
  public void useOutput(double output, double setpoint) {
    // Use the output here
    currOutput =  output/1.5;
  }

  public void setpoint(double setPoint){
    getController().setSetpoint(setPoint);
  }

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    // System.out.println(-Robot.getLimelightX());
    return -Robot.getLimelightX();
  }


  public boolean getDone(){
    return getController().atSetpoint() && Robot.getLimelightV()==1;
  }
}
