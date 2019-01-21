
package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends SampleRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";

  Talon frontRight = new Talon(1);
  Talon backRight = new Talon(0);
  Talon frontLeft = new Talon(3);                                                                                                                                                       
  Talon backLeft = new Talon(2);

  SpeedControllerGroup right = new SpeedControllerGroup(backRight, frontRight);
  SpeedControllerGroup left = new SpeedControllerGroup(backLeft, frontLeft);

  private final DifferentialDrive m_robotDrive = new DifferentialDrive(left, right);
  private final Joystick m_stick = new Joystick(0);

  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  public Robot() {
    m_robotDrive.setExpiration(0.1);
  }

  @Override
  public void robotInit() {
    m_chooser.addDefault("Default Auto", kDefaultAuto);
    m_chooser.addObject("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto modes", m_chooser);
  }

  @Override
  public void operatorControl() {
    m_robotDrive.setSafetyEnabled(true);
    while (isOperatorControl() && isEnabled()) {
      // Drive arcade style
      m_robotDrive.arcadeDrive(m_stick.getX(), -m_stick.getY());
      // The motors will be updated every 5ms
      Timer.delay(0.005);
    }
  }
}
