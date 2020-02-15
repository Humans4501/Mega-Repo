/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.MecanumDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.MecanumDriveMotorVoltages;
import edu.wpi.first.wpilibj.kinematics.MecanumDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.MecanumDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class DriveTrain extends SubsystemBase {
  MecanumDrive drive;
  WPI_TalonFX frontLeft, frontRight, backLeft, backRight;
  MecanumDriveOdometry m_odometry;
  MecanumDriveWheelSpeeds wheelSpeeds;

  /**
   * Creates a new ExampleSubsystem.
   */
  public DriveTrain() {
    frontLeft = new WPI_TalonFX(Constants.frontLeft);
    frontRight = new WPI_TalonFX(Constants.frontRight);
    backRight = new WPI_TalonFX(Constants.backRight);
    backLeft = new WPI_TalonFX(Constants.backLeft);

    drive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);
    m_odometry = new MecanumDriveOdometry(Constants.m_kinematics, Rotation2d.fromDegrees(RobotContainer.ahrs.getYaw()), new Pose2d(2, -2, new Rotation2d()));
    

  }

  public MecanumDriveKinematics getkinematics(){
    return Constants.m_kinematics;
  }

  public MecanumDriveWheelSpeeds getWheelSpeeds() {
    return wheelSpeeds;
  }

  public Pose2d getPose() {
    return m_odometry.getPoseMeters();
  }

  public void setDriveSpeedControllersVolts(MecanumDriveMotorVoltages volts) {
    frontLeft.setVoltage(volts.frontLeftVoltage);
    backLeft.setVoltage(volts.rearLeftVoltage);
    frontRight.setVoltage(volts.frontRightVoltage);
    backRight.setVoltage(volts.rearRightVoltage);
  }

  public void drive(double y, double x, double rotate) {
    drive.driveCartesian(-y, x, rotate*0.5);
    // , -RobotContainer.ahrs.getAngle()
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    wheelSpeeds = new MecanumDriveWheelSpeeds(RobotContainer.frontleft.getSelectedSensorVelocity() * (-0.10351972333/2048), RobotContainer.frontright.getSelectedSensorVelocity() * (0.10351972333/2048), RobotContainer.backleft.getSelectedSensorVelocity() * (-0.10351972333/2048), RobotContainer.backright.getSelectedSensorVelocity() * (0.10351972333/2048));
    m_odometry.update(Rotation2d.fromDegrees(RobotContainer.ahrs.getYaw()),wheelSpeeds );
    SmartDashboard.putNumber("displacement x", getPose().getTranslation().getX());
    SmartDashboard.putNumber("displacement y", getPose().getTranslation().getY());
    SmartDashboard.putNumber("frontleftWheelSpeed", wheelSpeeds.frontLeftMetersPerSecond);
    SmartDashboard.putNumber("frontrightWheelSpeed", wheelSpeeds.frontRightMetersPerSecond);
    SmartDashboard.putNumber("backleftWheelSpeed", wheelSpeeds.rearLeftMetersPerSecond);
    SmartDashboard.putNumber("backrightWheelSpeed", wheelSpeeds.rearRightMetersPerSecond);
  }

  

}
