/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */
  Joystick joystick;
  Spark shooterLeft, shooterRight;
  Talon loader;

  public Shooter() {
    shooterLeft = new Spark(Constants.shoot1);
    shooterRight = new Spark(Constants.shoot2);
    loader = new Talon(Constants.load);
    joystick = new Joystick(1);
    
  }

  public void shoot(double speed1, double speed2){
    shooterLeft.set(speed1);
    shooterRight.set(speed2);
  }

  public void load(double speed){
    loader.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    load(joystick.getRawAxis(3));
  }


}
