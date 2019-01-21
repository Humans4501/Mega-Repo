package org.usfirst.frc.team4501.robot.commands;

import org.usfirst.frc.team4501.robot.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LAKUCENTERLEFTFRONT extends CommandGroup {

	public LAKUCENTERLEFTFRONT() {
		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
		addSequential(new DriveAutoTimed2(Constants.PART_1_HALVED)); // Initial going out distance
		addSequential(new GyroTurn(-90));
		addSequential(new DriveAutoTimed2(Constants.LONGWAYHALVED + 0.1)); // 88.755 inches
		addSequential(new GyroTurn(80));
		addSequential(new Delay(0.2));
		addSequential(new DriveUntilCollision());
		addSequential(new RunEverything(10));
	}
}
