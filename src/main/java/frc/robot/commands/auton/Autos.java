// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auton;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import frc.robot.Constants.AutoConstants;
import frc.robot.subsystems.DriveSubsystem;

public final class Autos {
    /** Example static factory for an autonomous command. */
    // public static Command exampleAuto(ExampleSubsystem subsystem) {
    // return Commands.sequence(subsystem.exampleMethodCommand(), new
    // ExampleCommand(subsystem));
    // }

    public static Command leaveStartingZone(DriveSubsystem drive) {
        return new FunctionalCommand(
                drive::resetEncoders /* initialization */,
                () -> drive.arcadeDrive(0.5, 0.0) /* execution */,
                interrupted -> drive.arcadeDrive(0.0, 0.0) /* end */,
                () -> drive.getMeanEncoderDistance().gte(AutoConstants.kAutoLeaveDistance) /* is it finished ? */,
                drive /* required subsystem */).withTimeout(2);
    }

    private Autos() {
        throw new UnsupportedOperationException("This is a utility class!");
    }
}
