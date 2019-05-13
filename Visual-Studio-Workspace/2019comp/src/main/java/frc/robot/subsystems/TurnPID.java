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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SPI;

/**
 * Add your docs here.
 */

public class TurnPID extends PIDSubsystem {
   public static TurnPID instance;
   static double Kp = 0.125;
   static double Ki = 0.000;
   static double Kd = 0.15;
   public double speed = 0;
   static double maxOutputRange = 0.7;
   public boolean done = false;
   static double targetAngle = 0;
   double counter = 0;


   public AHRS ahrs;
  
  /**
   * Add your docs here.
   */
  public TurnPID() {
    // Intert a subsystem name and PID values here
    super("MovePID", Kp, Ki, Kd);
    ahrs = new AHRS(SPI.Port.kMXP);
    instance = this;
    
    getPIDController().setOutputRange(-maxOutputRange, maxOutputRange);
    getPIDController().setContinuous(false);
    getPIDController().enable();
 


    // Use these to get going:
    // setSetpoint() - Sets where the PID controller should move the system
    // to
    // enable() - Enables the PID controller.
  }

  public void setTarget(double target){
    targetAngle = target;
    getPIDController().setSetpoint(target);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  protected double returnPIDInput() {
    SmartDashboard.putBoolean("turn done", turnDone());
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;
    return ahrs.getAngle();
  }

  @Override
  protected void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
    speed = output;
  }

  public boolean turnDone(){
    if (ahrs.getAngle() - targetAngle < 2 && ahrs.getAngle() - targetAngle > -2){
      if(counter >= 30){
        done = true;
        counter = 0;
      }else{
        counter += 1;
      }
    }else{
      counter = 0;
      done = false;
    }
    return done;
  }

  public void disable(){
    done = false;
    getPIDController().disable();
  }
  public void enable(){
    done = false;
    getPIDController().enable();
  }

}

