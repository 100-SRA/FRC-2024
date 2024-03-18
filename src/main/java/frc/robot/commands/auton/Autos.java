// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auton;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import frc.robot.Constants.AutoConstants;
import frc.robot.commands.ThrowNote;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.NoteIntakeSubsystem;
import frc.robot.subsystems.NoteThrowerSubsystem;

public final class Autos {
    /** Example static factory for an autonomous command. */
    // public static Command exampleAuto(ExampleSubsystem subsystem) {
    // return Commands.sequence(subsystem.exampleMethodCommand(), new
    // ExampleCommand(subsystem));
    // }
    
    public static Command throwNote(NoteThrowerSubsystem thrower, NoteIntakeSubsystem intake) {
        return new ThrowNote(thrower, intake);
    }

    public static Command leaveStartingZone(DriveSubsystem drive) {
        /* Create autonomous command that leaves the starting zone and quits after a timeout */
        return new FunctionalCommand(
                drive::resetEncoders /* initialization */,
                () -> drive.arcadeDrive(0.5, 0.0) /* execution */,
                interrupted -> drive.arcadeDrive(0.0, 0.0) /* end */,
                () -> drive.getMeanEncoderDistance() >= AutoConstants.kAutoLeaveDistance /* is it finished ? */,
                drive /* required subsystem */).withTimeout(14 /* seconds */);
    }

    private Autos() {
        throw new UnsupportedOperationException("This is a utility class!");
    }
}
