/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;

public class ShooterDesiredSpeedFalcon2 extends PIDSubsystem {
  private final TalonFX falcon2 = new TalonFX(Constants.falcons2);
  static double Kp = 0.0002;
  static double Ki = 0.000;
  static double Kd = 0;

  public double currOutput;
  /**
   * Creates a new ShooterDesiredSpeed.
   */
  public ShooterDesiredSpeedFalcon2() {
    super(
        // The PIDController used by the subsystem
        new PIDController(Kp, Ki,Kd));

  }

  @Override
  public void useOutput(double output, double setpoint) {
    // Use the output here
    SmartDashboard.putNumber("setpoint2", setpoint);
    SmartDashboard.putNumber("output Falcon 2", output);
    currOutput = output + setpoint / 6503.8;
  }

  public void setpoint(double setPoint){
    getController().setSetpoint(setPoint);
    
  }
  

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    return (falcon2.getSelectedSensorVelocity() * 600.0)/2048.0;
  }
}
