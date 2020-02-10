/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Conveyor extends SubsystemBase {
  /**
   * Creates a new Conveyor.
   * 
   */

  CANSparkMax conveyorSpark1, conveyorSpark2;
  WPI_TalonSRX indexer;
  CANEncoder encoderMax1, encoderMax2;
  DigitalInput los1, los2, los3, los4, los5;
  
  public Conveyor() {
    conveyorSpark1 = new CANSparkMax(Constants.shoot1, MotorType.kBrushless);
    conveyorSpark2 = new CANSparkMax(Constants.shoot2, MotorType.kBrushless);
    indexer = new WPI_TalonSRX(Constants.index);

    encoderMax1 = conveyorSpark1.getEncoder();
    encoderMax2 = conveyorSpark2.getEncoder();

    los1 = new DigitalInput(Constants.losCon1);
    los2 = new DigitalInput(Constants.losCon2);
    los3 = new DigitalInput(Constants.losCon3);
    los4 = new DigitalInput(Constants.losCon4);
    los5 = new DigitalInput(Constants.losCon5);
  }

  public void convey(double speed1, double speed2, double speed3){
    if(!los5.get() && !los3.get() && !los4.get() && !los2.get()){
      speed1 = 0;
      speed2 = 0;
      speed3 = 0;
    }else if(!los5.get() && !los4.get() && los3.get() && (!los1.get() || !los2.get())){
      speed2 = 0;
    }else if(los2.get() && los1.get()){
      speed1 = 0;
      speed2 = 0;
    }else if(los4.get()){
      speed2 = 0;
    }else if(!los4.get()){
      speed1 = 0;
      speed3 = 0;
    }
    if(!los5.get()){
      speed2 = 0;
    }
    if(!los2.get()){
      speed3 = 0;
    }

    indexer.set(speed3);
    conveyorSpark1.set(-speed1);
    conveyorSpark2.set(-speed2);
  }
  public void conveyShoot(double speed1, double speed2, double speed3){
    if(los4.get() && (!los2.get() || !los1.get() || !los3.get())){
      speed2 = 0;
    }else if(!los4.get()){
      speed1 = 0;
      speed3 = 0;
    }
    if(!los1.get() || !los2.get()){
      speed3 = 0;
    }

    indexer.set(speed3);
    conveyorSpark1.set(-speed1);
    conveyorSpark2.set(-speed2);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // convey(RobotContainer.xbox.getRawAxis(5), RobotContainer.xbox.getRawAxis(1));
    SmartDashboard.putNumber("EncoderMax1 Rpm", (encoderMax1.getVelocity() * 600)/ (20 * 4096));
    SmartDashboard.putNumber("EncoderMax2 Rpm", (encoderMax2.getVelocity() * 600)/ (20 * 4096));
    SmartDashboard.putBoolean("LOS1", los1.get());
    SmartDashboard.putBoolean("LOS2", los2.get());
    SmartDashboard.putBoolean("LOS3", los3.get());
    SmartDashboard.putBoolean("LOS4", los4.get());
    SmartDashboard.putBoolean("LOS5", los5.get());
  }
}
