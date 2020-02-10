/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
  /**
   * Creates a new Climber.
   */
  WPI_TalonSRX climber1, climber2;
  public Climber() {
    climber1 = new WPI_TalonSRX(Constants.climb1);
    climber2 = new WPI_TalonSRX(Constants.climb2);
  }

  public void climb(double speed1, double speed2){
    climber1.set(speed1);
    climber2.set(speed2);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
