// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
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
        m_arm.setMaxOutput(0.5);
    }

    @Override
    public void end(boolean interrupted) {
        m_arm.setMaxOutput(1.0);
    }
}
