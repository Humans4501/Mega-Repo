package frc.robot.subsystems;

import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Controls the winch motor and associated limit switch.
 */
public class Winch extends Subsystem {
	Talon talon1 = new Talon(RobotMap.WINCHTALON);;
	DigitalInput limitSwitch = new DigitalInput(RobotMap.WINCH_LIMIT_SWITCH);
	Counter counter = new Counter(limitSwitch);

	boolean isLimitSwitchSet;
	double winchSpeed;

	public Winch() {
		super();
		SmartDashboard.putBoolean("WinchLimitSwitch", isLimitSwitchSet);
		SmartDashboard.putNumber("WinchSpeed", winchSpeed);
	}

	/**
	 * Controls the winch speed with the safety of the limit switch.
	 * 
	 * Set the speed to zero to turn off the motor.
	 * 
	 * @param speed
	 *            Desired speed -1.0 to 1.0
	 */
	public void runWinch(double speed) {
		// Force speed to zero if the switch has been tripped.

		winchSpeed = isLimitSwitchSet() ? 0 : speed;
		System.out.println("Limit Switch =" + winchSpeed);
		talon1.set(winchSpeed);
	}

	/**
	 * Controls the winch speed ignoring the limit switch.
	 * 
	 * Set the speed to zero to turn off the motor.
	 * 
	 * @param speed
	 *            Desired speed -1.0 to 1.0
	 */
	public void runWinchOverrideLimitSwitch(double speed) {
		winchSpeed = speed;
		talon1.set(winchSpeed);
	}

	/**
	 * Resets the counter.
	 */
	public void resetLimitSwitch() {
		counter.reset();
	}

	/**
	 * @return true if the switch has been triggered at least once since the last
	 *         reset.
	 */
	public boolean isLimitSwitchSet() {
		isLimitSwitchSet = (counter.get() > 0);
		return isLimitSwitchSet;
	}

	@Override
	public void initDefaultCommand() {
	}
}
