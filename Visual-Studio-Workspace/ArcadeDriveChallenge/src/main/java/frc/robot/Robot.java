
package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends SampleRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";

  Talon frontright = new Talon(1);
  Talon frontleft = new Talon(2);
  
  Talon backright = new Talon(0);
  Talon backleft = new Talon(3);

  SpeedControllerGroup m_Right = new SpeedControllerGroup(frontright, backright);
  SpeedControllerGroup m_Left = new SpeedControllerGroup(backleft, frontleft);

  DifferentialDrive m_robotDrive = new DifferentialDrive(m_Left,m_Right);
    
  private final Joystick m_stick = new Joystick(0);

  //these next to functions are moztly standardized so you don't need to mess with it
  public Robot() {
    m_robotDrive.setExpiration(0.1);
  }

  @Override
  public void robotInit() {
  }

  //this is also known as teleop and is where human control code takes place

  @Override
  public void operatorControl() {
    m_robotDrive.setSafetyEnabled(true);
    while (isOperatorControl() && isEnabled()) {
      // this is where you will place the arcade drive with the axis of the joystick

       m_robotDrive.arcadeDrive(m_stick.getY(), m_stick.getX());
      
      Timer.delay(0.005);
    }
  }
}
