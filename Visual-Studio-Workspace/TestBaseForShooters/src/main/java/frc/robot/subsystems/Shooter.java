/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */
  Joystick joystick;
  WPI_TalonFX shooterLeft, shooterRight;
  Talon shooterTalonL, shooterTalonR;
  Talon loader;

  public Shooter() {
    shooterLeft = new WPI_TalonFX(Constants.falcons1);
    shooterRight = new WPI_TalonFX(Constants.falcons2);
    shooterTalonL = new Talon(Constants.shoot1);
    shooterTalonR = new Talon(Constants.shoot2);
    loader = new Talon(Constants.load);
    joystick = new Joystick(1);
    
    
  }

  public void shoot(double speed1, double speed2){
    shooterLeft.set(speed1);
    shooterRight.set(speed2);
  }

  public void shootTalon(double speed1, double speed2){
    shooterTalonL.set(speed1);
    shooterTalonR.set(speed2);
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
