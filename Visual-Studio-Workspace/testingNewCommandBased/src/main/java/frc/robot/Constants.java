/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.MecanumDriveKinematics;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static int frontLeft = 13;
    public static int frontRight = 11;
    public static int backLeft = 12;
    public static int backRight = 10;

    public static int shoot1 = 20;
    public static int shoot2 = 21;
    public static int climb1 = 16;
    public static int climb2 = 14;
    public static int load = 18;
    public static int index = 15;
    public static int elevator = 17;

    public static int falcons1 = 5; //left
    public static int falcons2 = 6; //right

    public static int losCon1 = 0;
    public static int losCon2 = 1;
    public static int losCon3 = 2;
    public static int losCon4 = 3;
    public static int losCon5 = 4;

    
    public static final double kMaxSpeedMetersPerSecond = 3;
    public static final double kMaxAccelerationMetersPerSecondSquared = 3;
    public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI;
    public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;

    public static final double ksVolts = 0.22;
    public static final double kvVoltSecondsPerMeter = 0.3;
    public static final double kaVoltSecondsSquaredPerMeter = 0;

    public static final double kPDriveVel = 0.2;

    //pid numbers for pid controllers in autocommand in robot container
    public static final double kPXController = 0.2;
    public static final double kPYController = 0.2;
    public static final double kPThetaController = 0.2;


    public static final TrapezoidProfile.Constraints kThetaControllerConstraints =
        new TrapezoidProfile.Constraints(kMaxAngularSpeedRadiansPerSecond,
        kMaxAngularSpeedRadiansPerSecondSquared);

    public static final SimpleMotorFeedforward kFeedforward = new SimpleMotorFeedforward(ksVolts, kvVoltSecondsPerMeter, kaVoltSecondsSquaredPerMeter);

    static Translation2d m_frontLeftLocation = new Translation2d(0.2794, 0.315722);
    static Translation2d m_frontRightLocation = new Translation2d(0.2794, -0.315722);
    static Translation2d m_backLeftLocation = new Translation2d(-0.2794, 0.315722);
    static Translation2d m_backRightLocation = new Translation2d(-0.2794, -0.315722);
    public static MecanumDriveKinematics m_kinematics = new MecanumDriveKinematics(m_frontLeftLocation,
            m_frontRightLocation, m_backLeftLocation, m_backRightLocation);

    public static final int STICK_LX = 0, STICK_LY = 1,
			TRIGGER_L = 2, TRIGGER_R = 3,
			STICK_RX = 4, STICK_RY = 5;
    public static final int TRIGGER = 1, BUTTON_2 = 2, BUTTON_3 = 3, BUTTON_4 = 4, BUTTON_5 = 5, BUTTON_6 = 6,
			BUTTON_7 = 7, BUTTON_8 = 8, BUTTON_9 = 9, BUTTON_10 = 10, BUTTON_11 = 11;
    public static final int X = 3,  A = 1, Y = 4, B = 2, RB = 6, RS = 10, LB = 5, LS = 9, START = 8, BACK = 7;
    


}
