// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

/**
 * A command to drive the robot with joystick input (passed in as
 * {@link DoubleSupplier}s). Written
 * explicitly for pedagogical purposes - actual code should inline a command
 * this simple with {@link
 * edu.wpi.first.wpilibj2.command.RunCommand}.
 */
public class DefaultDrive extends Command {
    private final DriveSubsystem m_drive;
    private final DoubleSupplier m_forward;
    private final DoubleSupplier m_rotation;
    private final DoubleSupplier m_speedMultiplier;

    /**
     * Creates a new DefaultDrive object
     * 
     * @param subsystem       drivetrain subsystem
     * @param forward         control input for driving forwards/backwards
     * @param rotation        control input for turning
     * @param speedMultiplier control input for changing the drive speed
     */
    public DefaultDrive(DriveSubsystem subsystem, DoubleSupplier forward, DoubleSupplier rotation,
            DoubleSupplier speedMultiplier) {
        m_drive = subsystem;
        m_forward = forward;
        m_rotation = rotation;
        m_speedMultiplier = speedMultiplier;
        addRequirements(m_drive);
    }

    /* execute the command */
    @Override
    public void execute() {
        // first get the speed multiplier value from the dial on the joystick
        double joystickDialValue;
        joystickDialValue = m_speedMultiplier.getAsDouble(); // the code is getting the value from the joystick dial:
                                                             // +1.0 at bottom, -1.0 at top
        joystickDialValue = joystickDialValue * -1; // the code has switched it to +1.0 at the top and -1.0 at the
                                                    // bottom
        joystickDialValue = joystickDialValue + 1; // the code has switched it to +2.0 at the top and 0.0 at the bottom
        joystickDialValue = joystickDialValue / 2; // the code has switched it to +1.0 at the top and 0.0 at the bottom
        m_drive.setSpeedMultiplier(joystickDialValue);

        // then do the arcade drive
        m_drive.arcadeDrive(m_forward.getAsDouble(), m_rotation.getAsDouble());
    }
}
