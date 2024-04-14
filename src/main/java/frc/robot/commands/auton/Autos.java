// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auton;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.Constants.AutoConstants;
import frc.robot.commands.ReverseDriveDirection;
import frc.robot.commands.SpinIntake;
import frc.robot.commands.ThrowNote;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.NoteIntakeSubsystem;
import frc.robot.subsystems.NoteThrowerSubsystem;

public final class Autos {
    /** Example static factory for an autonomous command. */
    // public static Command exampleAuto(ExampleSubsystem subsystem) {
    // return Commands.sequence(subsystem.exampleMethodCommand(), new
    // ExampleCommand(subsystem));
    // }


    public static Command throwNote(NoteThrowerSubsystem thrower,
            NoteIntakeSubsystem intake, ArmSubsystem arm  ) {
                Commands.run(() -> arm.liftArm(-0.5), thrower).withTimeout(1.5);
                        return ThrowNote.buildCommand(thrower, intake);
    }

    public static Command scoreOneNoteInSpeaker(
            NoteThrowerSubsystem thrower,
            NoteIntakeSubsystem intake,
            ArmSubsystem arm) {
        double kArmLoweringDuration = 2; /* seconds */
        double kArmLoweringPower = -0.5; /* motor power from -1 to 1 */
        return Commands.sequence(
            Commands.run(() -> arm.liftArm(kArmLoweringPower), arm)
                .withTimeout(kArmLoweringDuration),
            ThrowNote.buildCommand(thrower, intake)
        );
    }

    public static Command scoreTwoNotesInSpeaker(DriveSubsystem drive,
            NoteThrowerSubsystem thrower, NoteIntakeSubsystem intake, ArmSubsystem arm) {
        double kNoteRetrievalDriveDistance = 1.0; /* meters */
        double kNoteIntakeWaitTime = 0.5; /* seconds */
        double kNoteRetrievalDriveSpeed = 0.55; /* percentage of motor power */
        double kReturnDriveSpeed = 0.5; /* percentage of motor power */

        // TODO: fix the two note auto to include arm lowering at the start
        Command driveToNewNoteAndWait = Commands.sequence(
                Commands.run(() -> arm.liftArm('1')).withTimeout(1.5), //WIP it still does not more the arm at the start
                driveXMeters(drive, kNoteRetrievalDriveSpeed, kNoteRetrievalDriveDistance),
                new WaitCommand(kNoteIntakeWaitTime));

        Command waitAndIntakeNote = Commands.sequence(
                new WaitUntilCommand(() -> drive.getMeanEncoderDistance() >= kNoteRetrievalDriveDistance / 2),
                new SpinIntake(intake));

        Command driveAndRetrieveNote = Commands.race(
                driveToNewNoteAndWait, waitAndIntakeNote);

        /*
         * Full two-note autonomous program
         */
        Command scoreTwoNotes = Commands.sequence(
                ThrowNote.buildCommand(thrower, intake),
                driveAndRetrieveNote,
                new ReverseDriveDirection(drive),
                driveXMeters(drive, kReturnDriveSpeed, kNoteRetrievalDriveDistance),
                new ReverseDriveDirection(drive),
                ThrowNote.buildCommand(thrower, intake)
        );
        return scoreTwoNotes;
    }

    public static Command driveXMeters(DriveSubsystem drive, double speed,
            double distance) {
        /* Create autonomous command that leaves the starting zone and quits after a timeout */
        return new FunctionalCommand(
                () -> drive.resetEncoders() /* initialization */,
                () -> drive.arcadeDrive(speed, 0.0) /* execution */,
                interrupted -> drive.arcadeDrive(0.0, 0.0) /* end */,
                () -> drive.getMeanEncoderDistance() >= distance /* finish after driving the distance */,
                drive /* required subsystem */);
    }

    public static Command leaveStartingZone(DriveSubsystem drive) {
        return driveXMeters(drive, 0.5, AutoConstants.kAutoLeaveDistance)
            .withTimeout(14 /* seconds */);
    }

    private Autos() {
        throw new UnsupportedOperationException("This is a utility class!");
    } 
}
