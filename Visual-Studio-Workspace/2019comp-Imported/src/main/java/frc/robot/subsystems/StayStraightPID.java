/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.SPI;

/**
 * Add your docs here.
 */

public class StayStraightPID extends PIDSubsystem {
   public static StayStraightPID instance;
   static double Kp = 0.15;
   static double Ki = 0.0001;
   static double Kd = 0.0;
   public double adjustment = 0;
   static double maxOutputRange = 0.5;
   static double targetAngle = 0;
   double maxError = 1;
   public boolean straight = false;


   public AHRS ahrs;
  
  /**
   * Add your docs here.
   */
  public StayStraightPID() {
    // Intert a subsystem name and PID values here
    super("StayStraightPID", Kp, Ki, Kd);
    ahrs = new AHRS(SPI.Port.kMXP);
    instance = this;
    getPIDController().setOutputRange(-maxOutputRange, maxOutputRange);
    getPIDController().enable();
 


    // Use these to get going:
    // setSetpoint() - Sets where the PID controller should move the system
    // to
    // enable() - Enables the PID controller.
  }

  public boolean isStraight (){
    double deltaAngle = Math.abs(ahrs.getAngle() - targetAngle);
    if (deltaAngle < maxError){
      straight = true;
    }else{
      straight = false;
    }
    return straight;
  }

  public void setTarget(double target){
    targetAngle = target;
    getPIDController().setSetpoint(targetAngle);
    
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  protected double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;
    // System.out.println("staying straight input");
    return ahrs.getAngle();
  }

  @Override
  protected void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
    // System.out.println("staying straight output");
    adjustment = output;
  }

  public void disable(){
    getPIDController().disable();
  }
  public void enable(){
    getPIDController().enable();
  }

}
