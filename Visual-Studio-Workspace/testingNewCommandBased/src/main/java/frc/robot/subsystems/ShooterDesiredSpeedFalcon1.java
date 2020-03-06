/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.RobotContainer;

public class ShooterDesiredSpeedFalcon1 extends PIDSubsystem {
  static double Kp = 0.0001;
  static double Ki = 0.000025;
  static double Kd = 0.000001;

  public double currOutput;
  /**
   * Creates a new ShooterDesiredSpeed.
   */
  public ShooterDesiredSpeedFalcon1() {
    super(
        // The PIDController used by the subsystem
        new PIDController(Kp, Ki,Kd));
  }

  @Override
  public void useOutput(double output, double setpoint) {
    // Use the output here
    SmartDashboard.putNumber("setpoint", setpoint);
    SmartDashboard.putNumber("output Falcon 1", output);
    currOutput = output + setpoint / 6320;
  }

  public void setpoint(double setPoint){
    getController().setSetpoint(setPoint);
  }
  

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    return (RobotContainer.falcon1.getSelectedSensorVelocity() * 600.0)/2048.0;
  }
}