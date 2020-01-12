/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TestShooter2 extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */

  Spark shooterLeft, shooterRight;

  public TestShooter2() {
    shooterLeft = new Spark(Constants.shoot3);
    shooterRight = new Spark(Constants.shoot4);
    
  }

  public void shoot(double speed1, double speed2){
    shooterLeft.set(speed1);
    shooterRight.set(speed2);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


}
