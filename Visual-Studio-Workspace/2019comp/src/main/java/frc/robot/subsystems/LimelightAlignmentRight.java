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
public class LimelightAlignmentRight extends PIDSubsystem {
  /**
   * Add your docs here.
   */
    public static LimelightAlignmentRight instance;
    static double Kp = 0.52;
    static double Ki = 0.000;
    static double Kd = 0.4;
    static double MAX_RANGE = 0.5;
    public boolean done = false;
    
    public double currOutput;

    NetworkTable table;
    
    NetworkTableEntry tx;
    NetworkTableEntry ty;
    NetworkTableEntry ta;
    NetworkTableEntry ledMode;
    
    double area;
    public double x;
    double y;
    
    public LimelightAlignmentRight() {
      super("LimelightAlignmentRight", Kp, Ki, Kd);
      instance = this;
      table = NetworkTableInstance.getDefault().getTable("limelight");
      System.out.println("table = " + table);
      getPIDController().setContinuous(false);
      getPIDController().setOutputRange(-MAX_RANGE, MAX_RANGE);
      getPIDController().setSetpoint(0);
      getPIDController().enable();
      
      tx = table.getEntry("tx");
      ty = table.getEntry("ty");
      ta = table.getEntry("ta");
      ledMode = table.getEntry("ledMode");
      

    }


    @Override
    protected double returnPIDInput() {
      // Read limelight vertical error
      x = tx.getDouble(0.0);
      y = ty.getDouble(0.0);
      area = ta.getDouble(0.0);
      // System.out.println("limelight x " + x);

      
      return x;
    }

    @Override
    protected void usePIDOutput(double output) {
      if(area == 0){
        currOutput = 0.6;
      }else{
        currOutput = output;
      }

     
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
