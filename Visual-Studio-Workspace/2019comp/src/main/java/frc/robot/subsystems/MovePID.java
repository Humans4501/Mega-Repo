/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class MovePID extends PIDSubsystem {
  public static MovePID instance;
  static double Kp = 0.5;
  static double Ki = 0.000;
  static double Kd = 0.0;
  static double maxOutputRange = 0.8;
  public boolean done = false;
  double target = 0;
  public Encoder encr;
  public Encoder encl;
  double maxError = 0.15;
  int counter = 0;

  

  double input;
  public double speed;
  /**
   * Add your docs here.
   */
  public MovePID() {
    // Intert a subsystem name and PID values here
    super("MovePID", Kp, Ki, Kd);
    instance = this;
    
    getPIDController().setOutputRange(-maxOutputRange, maxOutputRange);
    

    encr = new Encoder(RobotMap.ENCR[0], RobotMap.ENCR[1], true, Encoder.EncodingType.k4X);
    encl = new Encoder(RobotMap.ENCL[0], RobotMap.ENCL[1], false, Encoder.EncodingType.k4X);
    encr.setDistancePerPulse(0.07065936);
    encl.setDistancePerPulse(0.07065936);

    // Use these to get going:
    // setSetpoint() - Sets where the PID controller should move the system
    // to
    // enable() - Enables the PID controller.
  }

  public boolean isDone(){
    if(input < target + 1 && input > target - 1){
      if(counter >= 30){
        done = true;
        counter = 0;
      }else{
        counter += 1;
      }
    }else{
      done = false;
      counter = 0;
    }
    
    return done;

  }
 
  public void setDistance(double distance){
    target = distance;
    getPIDController().setSetpoint(target);
    System.out.println("target set");
    getPIDController().enable();
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
    // System.out.println("I am returnign pid input for MovePID");

    input = ((encr.getDistance() + encl.getDistance())/2);
    return input;
  }

  @Override
  protected void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);
    // System.out.println("I am setting pid output for MovePID");
    speed = output;
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
