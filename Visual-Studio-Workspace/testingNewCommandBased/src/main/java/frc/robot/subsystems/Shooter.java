/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.Drive;

public class Shooter extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */

  WPI_TalonFX shooterLeft, shooterRight;

  public Shooter() {
    // shooterLeft = new WPI_TalonFX(Constants.falcons1);
    // shooterRight = new WPI_TalonFX(Constants.falcons2);
    
  }

  public void shoot(double speed1, double speed2){
    // shooterLeft.set(speed1);
    // shooterRight.set(speed2);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


}
