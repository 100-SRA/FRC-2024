// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ArmConstants;
import frc.robot.subsystems.ArmSubsystem;

/**
 * Necessary during testing to make sure we don't damage the components
 */
public class LimitArmSpeed extends Command {
    private final ArmSubsystem m_arm;

    public LimitArmSpeed(ArmSubsystem subsystem) {
        m_arm = subsystem;
    }

    @Override
    public void initialize() {
        // Limit the max speed of the arm so we have more control
        m_arm.setMaxOutput(ArmConstants.kArmMaxSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        // Return the max speed to 100%
        m_arm.setMaxOutput(1.0);
    }
    // adding something random
    //blah blah blah
}
