/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Shooter extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */
  WPI_TalonFX shooterLeft, shooterRight;
  CANSparkMax shooterTalonL, shooterTalonR;
  WPI_TalonSRX loader;

  public Shooter() {
    shooterLeft = new WPI_TalonFX(Constants.falcons1);
    shooterRight = new WPI_TalonFX(Constants.falcons2);

    loader = new WPI_TalonSRX(Constants.load);

    
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
    load(RobotContainer.joystick2.getRawAxis(3));



  }
//this is not up to date fucking hell help me

}
