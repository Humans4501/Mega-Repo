/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class LimelightMoveForewardWidth extends PIDSubsystem {
  /**
   * Add your docs here.
   */
    public static LimelightMoveForewardWidth instance;
    static double Kp = 0.04;
    static double Ki = 0.0000125;
    static double Kd = 0.1;
    static double MAX_RANGE = 0.8;
    
    public double currOutput;
    public boolean done = false;
    int counter = 0;

    NetworkTable table;
    
    NetworkTableEntry tx;
    NetworkTableEntry ty;
    NetworkTableEntry ta;
    NetworkTableEntry thor;
    NetworkTableEntry ledMode;

    double area;
    double x;
    double y;
    double width;
    
    public LimelightMoveForewardWidth() {
      super("LimelightMoveForewardWidth", Kp, Ki, Kd);
      instance = this;
      table = NetworkTableInstance.getDefault().getTable("limelight");
      System.out.println("table = " + table);
      getPIDController().setContinuous(false);
      getPIDController().setOutputRange(-MAX_RANGE, MAX_RANGE);
      getPIDController().setSetpoint(135);
      getPIDController().enable();
      
      tx = table.getEntry("tx");
      ty = table.getEntry("ty");
      ta = table.getEntry("ta");
      thor = table.getEntry("thor");
      ledMode = table.getEntry("ledMode");
      
    }

    public double getLimeLightAlignment(){
      return currOutput;
    }


    @Override
    protected double returnPIDInput() {
      // Read limelight vertical error
      SmartDashboard.putNumber("limelight foreward Width", currOutput);
      SmartDashboard.putNumber("Width", width);
      width = thor.getDouble(0.0);
      SmartDashboard.putNumber("Width", width);
      return width;
      // return 0;
    }

    @Override
    protected void usePIDOutput(double output) {
      // if(Robot.limelightAlignment.done){
      //   currOutput = output;
      // }else{
      //   currOutput = 0;
      // }
      currOutput = output;
      
      if (width > 130){
        if (counter >= 10){
          counter = 0;
          done = true;
        }else{
          counter += 1;
        }
      }else{
        counter = 0;
        done = false;
      }
      SmartDashboard.putBoolean("drive done", done);
    }

    public void disable(){
      done = false;
      getPIDController().disable();
    }
    public void enable(){
      done = false;
      getPIDController().enable();
    }

    @Override
    protected void initDefaultCommand() {
      // TODO Auto-generated method stub
      
    }

}
