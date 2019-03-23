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
public class LimelightMoveForeward extends PIDSubsystem {
  /**
   * Add your docs here.
   */
    public static LimelightMoveForeward instance;
    static double Kp = 0.14;
    static double Ki = 0.0000125;
    static double Kd = 0.0;
    static double MAX_RANGE = 0.7;
    
    public double currOutput;
    public boolean done = false;

    NetworkTable table;
    
    NetworkTableEntry tx;
    NetworkTableEntry ty;
    NetworkTableEntry ta;
    NetworkTableEntry ledMode;

    double area;
    double x;
    double y;
    
    public LimelightMoveForeward() {
      super("LimelightMoveForeward", Kp, Ki, Kd);
      instance = this;
      table = NetworkTableInstance.getDefault().getTable("limelight");
      System.out.println("table = " + table);
      getPIDController().setContinuous(false);
      getPIDController().setOutputRange(-MAX_RANGE, MAX_RANGE);
      getPIDController().setSetpoint(10);
      getPIDController().enable();
      
      tx = table.getEntry("tx");
      ty = table.getEntry("ty");
      ta = table.getEntry("ta");
      ledMode = table.getEntry("ledMode");
      
    }

    public double getLimeLightAlignment(){
      return currOutput;
    }


    @Override
    protected double returnPIDInput() {
      // Read limelight vertical error
      SmartDashboard.putNumber("limelight foreward", currOutput);
      area = ta.getDouble(0.0);
      return area;
      // return 0;
    }

    @Override
    protected void usePIDOutput(double output) {
      // if(Robot.limelightAlignment.done){
      //   currOutput = output;
      // }else{
      //   currOutput = 0;
      // }
      if(area == 0){
        currOutput = 0;
      }else{
        currOutput = output;
      }
      if (currOutput < 0.3 && currOutput > -0.3 ){
        done = true;
      }else{
        done = false;
      }
      // SmartDashboard.putBoolean("drive done", done);
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
